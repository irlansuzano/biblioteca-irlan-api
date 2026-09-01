package br.edu.infnet.biblioteca_irlan_api.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Turma implements Identificavel{

    private Long id;
    private String identificador;
    private Professor professorCoordenador;
    private boolean ativo;

    private Curso curso;
    private List<Aluno> alunos = new ArrayList<>();

    public Turma() {
    }

    public Turma(Long id, String identificador, Professor professorCoordenador, boolean ativo) {
        this.id = id;
        this.identificador = identificador;
        this.professorCoordenador = professorCoordenador;
        this.ativo = ativo;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public Professor getProfessorCoordenador() {
        if (professorCoordenador == null) {
            throw new IllegalArgumentException("O professor coordenador não pode ser nulo!!!");
        }
        return professorCoordenador;
    }

    public void setProfessorCoordenador(Professor professorCoordenador) {
        this.professorCoordenador = professorCoordenador;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        if (curso == null) {
            throw new IllegalArgumentException("O curso não pode ser nulo!!!");
        }
        this.curso = curso;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public List<Aluno> getAlunos() {
        return Collections.unmodifiableList(alunos);
    }

    public void setAlunos(List<Aluno> alunos) {
        if (alunos == null || alunos.isEmpty()) {
            throw new IllegalArgumentException("É necessário informar pelo menos um aluno para a turma");
        }
        this.alunos = alunos;
    }

    public void adicionarAluno(Aluno aluno) {
        if (aluno == null) {
            throw new IllegalArgumentException("O aluno não pode ser nulo!!!");
        }
        alunos.add(aluno);

        aluno.setTurma(this);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Turma turma = (Turma) o;
        return Objects.equals(id, turma.id)
                && Objects.equals(identificador, turma.identificador)
                && Objects.equals(professorCoordenador, turma.professorCoordenador);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, identificador, professorCoordenador);
    }

    @Override
    public String toString() {
        return String.format("Turma: %s, coordenador %s, %s, curso %s",
                identificador,
                professorCoordenador != null ? professorCoordenador.getNome() : "N/A",
                ativo ? "em atividade" : "inativo",
                curso != null ? curso.getNome() : "N/A");
    }
}
