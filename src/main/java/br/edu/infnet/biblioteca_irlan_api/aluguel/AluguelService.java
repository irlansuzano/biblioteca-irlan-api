package br.edu.infnet.biblioteca_irlan_api.aluguel;

import br.edu.infnet.biblioteca_irlan_api.common.dto.NotificacaoDTO;
import br.edu.infnet.biblioteca_irlan_api.common.messaging.NotificacaoProducer;
import br.edu.infnet.biblioteca_irlan_api.common.service.BaseService;
import br.edu.infnet.biblioteca_irlan_api.livro.Livro;
import br.edu.infnet.biblioteca_irlan_api.livro.LivroRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AluguelService extends BaseService<Aluguel, AluguelRepository> {

    private final LivroRepository livroRepository;
    private final AluguelMapper aluguelMapper;
    private final NotificacaoProducer notificacaoProducer;

    public AluguelService(AluguelRepository repository, LivroRepository livroRepository, AluguelMapper aluguelMapper, NotificacaoProducer notificacaoProducer) {
        super(repository);
        this.livroRepository = livroRepository;
        this.aluguelMapper = aluguelMapper;
        this.notificacaoProducer = notificacaoProducer;
    }

    @Transactional
    public AluguelResponseDTO alugar(AluguelRequestDTO dto) {
        if (isLivroAlugado(dto.livroId())) {
            throw new IllegalArgumentException("Livro já está alugado.");
        }
        if (dto.dataInicioAluguel().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Não é possível efetivar o aluguel antes da data de início.");
        }

        Livro livro = livroRepository.findById(dto.livroId())
                .orElseThrow(() -> new IllegalArgumentException("Livro não encontrado."));

        Aluguel aluguel = aluguelMapper.toEntity(dto);
        aluguel.setLivro(livro);
        aluguel.setAtivo(true);
        this.incluir(aluguel);

        NotificacaoDTO notificacao = new NotificacaoDTO(
                aluguel.getAlunoId(),
                livro.getTitulo(),
                aluguel.getDataInicioAluguel(),
                aluguel.getDataFimAluguel(),
                null,
                "ALUGUEL"
        );
        notificacaoProducer.enviarNotificacaoAluguel(notificacao);

        return aluguelMapper.toResponseDto(aluguel);
    }

    @Transactional
    public AluguelResponseDTO renovar(Long idAluguel, LocalDate novaDataFim) {
        Aluguel aluguel = this.obterPorId(idAluguel);
        if (!Boolean.TRUE.equals(aluguel.getAtivo())) {
            throw new IllegalArgumentException("Não é possível renovar um aluguel inativo.");
        }
        if (novaDataFim.isBefore(aluguel.getDataFimAluguel())) {
            throw new IllegalArgumentException("A nova data de fim deve ser posterior à data atual de entrega.");
        }
        aluguel.setDataFimAluguel(novaDataFim);
        this.alterar(aluguel);

        NotificacaoDTO notificacao = new NotificacaoDTO(
                aluguel.getAlunoId(),
                aluguel.getLivro().getTitulo(),
                aluguel.getDataInicioAluguel(),
                aluguel.getDataFimAluguel(),
                null,
                "RENOVACAO"
        );
        notificacaoProducer.enviarNotificacaoAluguel(notificacao);

        return aluguelMapper.toResponseDto(aluguel);
    }

    public AluguelResponseDTO obterAluguelPorId(Long id) {
        return aluguelMapper.toResponseDto(this.obterPorId(id));
    }

    public Collection<AluguelResponseDTO> obterAlugueis() {
        return this.obterLista().stream()
                .map(aluguelMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    public void finalizarAluguel(Long idAluguel) {
        Aluguel aluguel = repository.findById(idAluguel).orElse(null);
        if (aluguel == null || !Boolean.TRUE.equals(aluguel.getAtivo())) {
            return;
        }
        aluguel.setAtivo(false);
        this.alterar(aluguel);
    }

    public AluguelResponseDTO buscarAluguelPorLivro(Long idLivro) {
        return livroRepository.findById(idLivro)
                .flatMap(repository::findByLivroAndIsAtivoTrue)
                .map(aluguelMapper::toResponseDto)
                .orElse(null);
    }

    public List<AluguelResponseDTO> buscarAlugueisPorLivro(Long idLivro) {
        return livroRepository.findById(idLivro)
                .map(livro -> repository.findByLivro(livro).stream()
                        .map(aluguelMapper::toResponseDto)
                        .collect(Collectors.toList()))
                .orElse(List.of());
    }

    public List<AluguelResponseDTO> buscarAlugueisPorAluno(Long idAluno) {
        return repository.findByAlunoId(idAluno).stream()
                .map(aluguelMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    private boolean isLivroAlugado(Long idLivro) {
        return livroRepository.findById(idLivro)
                .map(livro -> repository.findByLivroAndIsAtivoTrue(livro).isPresent())
                .orElse(false);
    }

    public String obterListaAlugueisAtivos() {
        List<Aluguel> alugueisAtivos = repository.findByIsAtivoTrue();
        StringBuilder sb = new StringBuilder("Os seguintes Alugueis estão ativos:\n");
        alugueisAtivos.forEach(aluguel -> sb.append(aluguel.getLivro().getTitulo())
                .append(" alugado pelo Aluno ID ")
                .append(aluguel.getAlunoId())
                .append(" até ")
                .append(aluguel.getDataFimAluguel())
                .append("\n"));
        return sb.toString();
    }
}
