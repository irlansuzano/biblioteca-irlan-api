package br.edu.infnet.biblioteca_irlan_api.domain;

import com.google.common.base.MoreObjects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Curso {
    private Long id;
    private String nome;
    private String descricao;
    private boolean ativo;

    private Professor coordenador;

    private List<Turma> turmas = new ArrayList<>();

    public Curso() {
    }

    public Curso(Long id, String nome, String descricao, Professor coordenador) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.coordenador = coordenador;
        this.ativo = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Pessoa getCoordenador() {
        return coordenador;
    }

    public void setCoordenador(Professor coordenador) {
        if (coordenador == null) {
            throw new IllegalArgumentException("O coordenador não pode ser nulo!!!");
        }
        this.coordenador = coordenador;
    }

    public List<Turma> getTurmas() {
        return Collections.unmodifiableList(turmas);
    }

    public void setTurmas(List<Turma> turmas) {
        if (turmas == null || turmas.isEmpty()) {
            throw new IllegalArgumentException("é necessário informar pelo menos uma turma para o curso");
        }
        this.turmas = turmas;
    }

    public void adicionarTodasTurmas(List<Turma> turmas) {
        if (turmas == null || turmas.isEmpty()) {
            throw new IllegalArgumentException("é necessário informar pelo menos uma turma para o curso");
        }
        this.turmas.addAll(turmas);
    }

    public void adicionarTurma(Turma turma) {
        if (turma == null) {
            throw new IllegalArgumentException("A turma não pode ser nula!!!");
        }
        turmas.add(turma);
        turma.setCurso(this);
    }

    public void removerTurma(Turma turma) {
        if (turma == null) {
            throw new IllegalArgumentException("A turma não pode ser nula!!!");
        }
        if (!turmas.isEmpty()) {
            turmas.remove(turma);
        }
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Curso curso = (Curso) o;
        return ativo == curso.ativo
                && Objects.equals(id, curso.id)
                && Objects.equals(nome, curso.nome)
                && Objects.equals(descricao, curso.descricao)
                && Objects.equals(coordenador, curso.coordenador)
                && Objects.equals(turmas, curso.turmas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, descricao, ativo, coordenador, turmas);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("nome", nome)
                .add("descrição", descricao)
                .add("ativo", ativo)
                .add("coordenador", coordenador.getNome())
                .add("turmas", turmas)
                .toString();
    }
}
