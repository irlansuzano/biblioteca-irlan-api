package br.edu.infnet.biblioteca_irlan_api.domain;

import com.google.common.base.MoreObjects;

import java.time.LocalDate;
import java.util.Objects;

public class Professor extends Pessoa {
    private String registroProfissional; //matricula do professor
    private Curso coordenacao;

    public Professor(String nome, String cpf, String email, LocalDate dataNascimento, String registroProfissional, Curso coordenacao) {
        super(nome, cpf, email, dataNascimento);
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
        return Objects.equals(registroProfissional, professor.registroProfissional)
                && Objects.equals(coordenacao, professor.coordenacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), registroProfissional, coordenacao);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("Nome", getNome())
                .add("CPF", getCpf())
                .add("Email", getEmail())
                .add("Registro profissional", registroProfissional)
                .add("Coordenacao", coordenacao)
                .toString();
    }
}
