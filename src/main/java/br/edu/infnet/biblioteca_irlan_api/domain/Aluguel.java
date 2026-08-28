package br.edu.infnet.biblioteca_irlan_api.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Aluguel {

    private Long id;
    private LocalDate dataInicioAluguel;
    private LocalDate dataFimAluguel;

    private List<Livro> livrosAlugados = new ArrayList<>();

    private Aluno aluno;

    public Aluguel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataInicioAluguel() {
        return dataInicioAluguel;
    }

    public void setDataInicioAluguel(LocalDate dataInicioAluguel) {
        this.dataInicioAluguel = dataInicioAluguel;
    }

    public LocalDate getDataFimAluguel() {
        return dataFimAluguel;
    }

    public void setDataFimAluguel(LocalDate dataFimAluguel) {
        this.dataFimAluguel = dataFimAluguel;
    }

    public List<Livro> getLivrosAlugados() {
        return Collections.unmodifiableList(livrosAlugados);
    }

    public void setLivrosAlugados(List<Livro> livrosAlugados) {
        this.livrosAlugados = livrosAlugados;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    private String obterTitulosLivrosAlugados() {
        if (livrosAlugados == null || livrosAlugados.isEmpty()) {
            return "nenhum livro alugado";
        }
        return livrosAlugados.stream().map(Livro::getTitulo)
                .collect(java.util.stream.Collectors.joining(", "));
    }

    public void alugarLivro(Livro livro, Aluno aluno) {
        validarSeAluguelValido(livro, aluno);

        this.livrosAlugados.add(livro);
        this.aluno = aluno;
        aluno.addAluguel(this);
        livro.addAluguel(this);
    }

    private void validarSeAluguelValido(Livro livro, Aluno aluno) {
        if (livro == null) {
            throw new IllegalArgumentException("O livro não pode ser nulo!!!");
        }
        if (aluno == null) {
            throw new IllegalArgumentException("O aluno não pode ser nulo!!!");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Aluguel aluguel = (Aluguel) o;
        return Objects.equals(id, aluguel.id)
                && Objects.equals(dataInicioAluguel, aluguel.dataInicioAluguel)
                && Objects.equals(dataFimAluguel, aluguel.dataFimAluguel)
                && Objects.equals(livrosAlugados, aluguel.livrosAlugados)
                && Objects.equals(aluno, aluguel.aluno);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dataInicioAluguel, dataFimAluguel, livrosAlugados, aluno);
    }

    @Override
    public String toString() {
        return String.format("Aluguel do(s) Livro(s) %s feito pelo Aluno %s. Alugado de %s até %s", obterTitulosLivrosAlugados(), aluno.getNome(), dataInicioAluguel, dataFimAluguel);
    }
}
