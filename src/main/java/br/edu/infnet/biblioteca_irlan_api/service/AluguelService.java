package br.edu.infnet.biblioteca_irlan_api.service;

import br.edu.infnet.biblioteca_irlan_api.domain.Aluguel;
import br.edu.infnet.biblioteca_irlan_api.domain.Livro;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class AluguelService {

    private final Map<Long, Aluguel> alugueis = new LinkedHashMap<>();

    public void alugar(Long idAluno, Aluguel aluguel) {
        this.alugueis.put(idAluno, aluguel);
    }

    public Collection<Aluguel> obterAlugueis() {
        return alugueis.values();
    }

    public void devolverLivro(Long idAluno, Livro livro) {
        this.alugueis.remove(idAluno);
    }

    public Aluguel obterAluguelPorLivro(Livro livro) {
        return alugueis.values().stream()
                .filter(aluguel -> aluguel.getLivro().equals(livro))
                .findFirst().orElse(null);
    }

    public Aluguel obterAluguelPorIdAluno(Long idAluno) {
        return alugueis.get(idAluno);
    }
}
