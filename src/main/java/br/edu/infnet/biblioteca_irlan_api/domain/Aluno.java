package br.edu.infnet.biblioteca_irlan_api.domain;

import java.time.LocalDate;
import java.util.Objects;

public class Aluno extends Pessoa {

    private String matricula;

    private Turma turma;

    private Aluguel aluguel;

    public Aluno(Long id, String nome, String cpf, String email, LocalDate dataNascimento, String matricula, String turma, Curso curso) {
        super(id, nome, cpf, email, dataNascimento);
        this.matricula = matricula;
    }

    public Aluguel getAluguel() {
        return aluguel;
    }

    public void setAluguel(Aluguel aluguel) {
        this.aluguel = aluguel;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Aluno aluno = (Aluno) o;
        return Objects.equals(getId(), aluno.getId()) && Objects.equals(matricula, aluno.matricula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId(), matricula);
    }

    @Override
    public String toString() {
        return String.format("Aluno %s, cpf: %s, email: %s, matricula: %s, turma: %s",
                getNome(), getCpf(), getEmail(), matricula, turma.getIdentificador());
    }
}
