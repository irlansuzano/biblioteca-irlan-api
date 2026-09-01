package br.edu.infnet.biblioteca_irlan_api.service;

import br.edu.infnet.biblioteca_irlan_api.domain.Aluguel;
import br.edu.infnet.biblioteca_irlan_api.domain.Livro;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public class AluguelService extends BaseService<Aluguel> {

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

    public void finalizarAluguel(Long idLivro) {
        if (!isLivroAlugado(idLivro)) {
            return;
        }
        Aluguel aluguel = this.obterPorId(idLivro);
        aluguel.setAtivo(false);
        this.alterar(aluguel);
    }

    public Aluguel buscarAluguelPorLivro(Long idLivro) {
        return this.obterLista().stream()
                .filter(aluguel -> aluguel.getLivro().getId().equals(idLivro))
                .findFirst().orElse(null);
    }

    private boolean isLivroAlugado(Long idLivro) {
        return this.obterLista().stream()
                .anyMatch(aluguel ->
                        aluguel.getLivro().getId().equals(idLivro)
                                && Boolean.TRUE.equals(aluguel.getAtivo()));
    }

    public String obterListaAlgueisAtivos() {
        List<Aluguel> alugueisAtivos = this.obterLista().stream().filter(aluguel -> Boolean.TRUE.equals(aluguel.getAtivo())).toList();
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
