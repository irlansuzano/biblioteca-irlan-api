package br.edu.infnet.biblioteca_irlan_api.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Curso implements Identificavel{
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

    @Override
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
        coordenador.setCoordenacao(this);
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
                && Objects.equals(descricao, curso.descricao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, descricao);
    }

    @Override
    public String toString() {
        return String.format("Curso %s, descricao: %s, %s, coordenador: %s, quantidade de turmas: %d",
                nome, descricao, ativo ? "ativo" : "inativo", coordenador.getNome(), turmas.size());
    }
}
