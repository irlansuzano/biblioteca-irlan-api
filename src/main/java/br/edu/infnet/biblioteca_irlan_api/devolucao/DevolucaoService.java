package br.edu.infnet.biblioteca_irlan_api.devolucao;

import br.edu.infnet.biblioteca_irlan_api.aluguel.Aluguel;
import br.edu.infnet.biblioteca_irlan_api.aluguel.AluguelService;
import br.edu.infnet.biblioteca_irlan_api.common.dto.NotificacaoDTO;
import br.edu.infnet.biblioteca_irlan_api.common.messaging.NotificacaoProducer;
import br.edu.infnet.biblioteca_irlan_api.common.service.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class DevolucaoService extends BaseService<Devolucao, DevolucaoRepository> {

    private final AluguelService aluguelService;
    private final DevolucaoMapper devolucaoMapper;
    private final NotificacaoProducer notificacaoProducer;

    public DevolucaoService(DevolucaoRepository repository, AluguelService aluguelService, DevolucaoMapper devolucaoMapper, NotificacaoProducer notificacaoProducer) {
        super(repository);
        this.aluguelService = aluguelService;
        this.devolucaoMapper = devolucaoMapper;
        this.notificacaoProducer = notificacaoProducer;
    }

    @Transactional
    public DevolucaoResponseDTO devolver(DevolucaoRequestDTO dto) {
        Aluguel aluguel = aluguelService.obterPorId(dto.aluguelId());
        
        if (!Boolean.TRUE.equals(aluguel.getAtivo())) {
            throw new IllegalArgumentException("Este aluguel já foi finalizado.");
        }

        Devolucao devolucao = new Devolucao();
        devolucao.setDataDevolucao(dto.dataDevolucao());
        devolucao.setAluguel(aluguel);
        devolucao.setAlunoId(aluguel.getAlunoId());

        this.incluir(devolucao);
        
        aluguelService.finalizarAluguel(aluguel.getId());

        NotificacaoDTO notificacao = new NotificacaoDTO(
                devolucao.getAlunoId(),
                aluguel.getLivro().getTitulo(),
                aluguel.getDataInicioAluguel(),
                aluguel.getDataFimAluguel(),
                devolucao.getDataDevolucao(),
                "DEVOLUCAO"
        );
        notificacaoProducer.enviarNotificacaoDevolucao(notificacao);

        return devolucaoMapper.toResponseDto(devolucao);
    }

    public Collection<DevolucaoResponseDTO> obterDevolucoes() {
        return this.obterLista().stream()
                .map(devolucaoMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    public DevolucaoResponseDTO obterDevolucaoPorId(Long id) {
        return devolucaoMapper.toResponseDto(this.obterPorId(id));
    }
}
