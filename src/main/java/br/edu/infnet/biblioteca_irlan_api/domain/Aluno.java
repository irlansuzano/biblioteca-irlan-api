package br.edu.infnet.biblioteca_irlan_api.domain;

import com.google.common.base.MoreObjects;

import java.time.LocalDate;
import java.util.Objects;

public class Aluno extends Pessoa {

    private String matricula;

    private Turma turma;

    public Aluno(String nome, String cpf, String email, LocalDate dataNascimento, String matricula, String turma, Curso curso) {
        super(nome, cpf, email, dataNascimento);
        this.matricula = matricula;
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
        return Objects.equals(matricula, aluno.matricula)
                && Objects.equals(turma, aluno.turma);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), matricula, turma);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("nome", getNome())
                .add("cpf", getCpf())
                .add("email", getEmail())
                .add("dataNascimento", getDataNascimento())
                .add("matricula", matricula)
                .add("turma", turma)
                .toString();
    }
}
