package br.edu.infnet.biblioteca_irlan_api.service;

import br.edu.infnet.biblioteca_irlan_api.domain.Aluguel;
import br.edu.infnet.biblioteca_irlan_api.domain.Aluno;
import br.edu.infnet.biblioteca_irlan_api.domain.Livro;
import br.edu.infnet.biblioteca_irlan_api.repository.AluguelRepository;
import br.edu.infnet.biblioteca_irlan_api.repository.AlunoRepository;
import br.edu.infnet.biblioteca_irlan_api.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Service
public class AluguelService extends BaseService<Aluguel, AluguelRepository> {

    private final LivroRepository livroRepository;
    private final AlunoRepository alunoRepository;

    public AluguelService(AluguelRepository repository, LivroRepository livroRepository, AlunoRepository alunoRepository) {
        super(repository);
        this.livroRepository = livroRepository;
        this.alunoRepository = alunoRepository;
    }

    public void alugar(Aluguel aluguel) {
        if (isLivroAlugado(aluguel.getLivro().getId())) {
            throw new IllegalArgumentException("Livro já está alugado.");
        }
        if (aluguel.getDataInicioAluguel().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Não é possível efetivar o aluguel antes da data de início.");
        }

        aluguel.setAtivo(true);
        this.incluir(aluguel);
    }

    public void alterarLivroAlugado(Long idLivroAtual, Livro novoLivro) {
        Aluguel aluguelAtual = buscarAluguelPorLivro(idLivroAtual);
        if (aluguelAtual.getAtivo() == false) {
            throw new IllegalArgumentException("não existe aluguel ativo com esse livro.");
        }
        if (aluguelAtual == null) {
            throw new IllegalArgumentException("não existe aluguel com esse livro.");
        }
        aluguelAtual.setLivro(novoLivro);
        this.alterar(aluguelAtual);
    }

    public void prolongarAluguel(Long idAluguel, LocalDate novoPrazo) {
        if (!isLivroAlugado(idAluguel)) {
            throw new IllegalArgumentException("não existe aluguel com esse livro.");
        }
        Aluguel aluguel = this.obterPorId(idAluguel);
        aluguel.setDataFimAluguel(novoPrazo);
        this.alterar(aluguel);
    }

    public Collection<Aluguel> obterAlugueis() {
        return this.obterLista();
    }

    public void finalizarAluguel(Long idAluguel) {
        Aluguel aluguel = repository.findById(idAluguel).orElse(null);
        if (aluguel == null || !Boolean.TRUE.equals(aluguel.getAtivo())) {
            return;
        }
        aluguel.setAtivo(false);
        this.alterar(aluguel);
    }

    public Aluguel buscarAluguelPorLivro(Long idLivro) {
        return livroRepository.findById(idLivro)
                .flatMap(repository::findByLivroAndIsAtivoTrue)
                .orElse(null);
    }

    public List<Aluguel> buscarAlugueisPorLivro(Long idLivro) {
        return livroRepository.findById(idLivro)
                .map(repository::findByLivro)
                .orElse(List.of());
    }

    public List<Aluguel> buscarAlugueisPorAluno(Long idAluno) {
        return alunoRepository.findById(idAluno)
                .map(repository::findByAluno)
                .orElse(List.of());
    }

    private boolean isLivroAlugado(Long idLivro) {
        return livroRepository.findById(idLivro)
                .map(livro -> repository.findByLivroAndIsAtivoTrue(livro).isPresent())
                .orElse(false);
    }

    public String obterListaAlgueisAtivos() {
        List<Aluguel> alugueisAtivos = repository.findByIsAtivoTrue();
        StringBuilder sb = new StringBuilder("Os seguintes Alugueis estão ativos:\n");
        alugueisAtivos.forEach(aluguel -> sb.append(aluguel.getLivro().getTitulo())
                .append(" alugado por ")
                .append(aluguel.getAluno().getNome())
                .append(" até ")
                .append(aluguel.getDataFimAluguel())
                .append("\n"));
        return sb.toString();
    }
}
