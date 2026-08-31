package br.edu.infnet.biblioteca_irlan_api.domain;

import java.time.LocalDate;
import java.util.Objects;

public class Professor extends Pessoa {
    private String registroProfissional;
    private Curso coordenacao;

    public Professor(Long id, String nome, String cpf, String email, LocalDate dataNascimento, String registroProfissional, Curso coordenacao) {
        super(id, nome, cpf, email, dataNascimento);
        this.registroProfissional = registroProfissional;
        this.coordenacao = coordenacao;
    }

    public String getRegistroProfissional() {
        return registroProfissional;
    }

    public void setRegistroProfissional(String registroProfissional) {
        this.registroProfissional = registroProfissional;
    }

    public Curso getCoordenacao() {
        return coordenacao;
    }

    public void setCoordenacao(Curso coordenacao) {
        this.coordenacao = coordenacao;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Professor professor = (Professor) o;
        return Objects.equals(getId(), professor.getId()) && Objects.equals(registroProfissional, professor.registroProfissional);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId(), registroProfissional);
    }

    @Override
    public String toString() {
        return String.format("Professor %s, registro profissional: %s, coordenacao: %s",
                getNome(),
                registroProfissional,
                coordenacao != null ? coordenacao.getNome() : "N/A");
    }
}
