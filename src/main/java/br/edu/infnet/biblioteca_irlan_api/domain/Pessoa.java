package br.edu.infnet.biblioteca_irlan_api.domain;

import java.time.LocalDate;
import java.util.Objects;

import static com.google.common.base.MoreObjects.toStringHelper;

public abstract class Pessoa {

    private String nome;
    private String cpf;
    private String email;
    private LocalDate dataNascimento;
    private boolean ativo;
    private LocalDate dataCadastro;

    protected Pessoa() {
    }

    protected Pessoa(String nome, String cpf, String email, LocalDate dataNascimento) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.ativo = true;
        this.dataCadastro = LocalDate.now();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Pessoa pessoa = (Pessoa) o;
        return ativo == pessoa.ativo && Objects.equals(nome, pessoa.nome) && Objects.equals(cpf, pessoa.cpf) && Objects.equals(email, pessoa.email) && Objects.equals(dataNascimento, pessoa.dataNascimento) && Objects.equals(dataCadastro, pessoa.dataCadastro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, cpf, email, dataNascimento, ativo, dataCadastro);
    }

    @Override
    public String toString() {
        return toStringHelper(this)
                .add("nome", nome)
                .add("cpf", cpf)
                .add("email", email)
                .add("dataNascimento", dataNascimento)
                .add("ativo", ativo)
                .add("dataCadastro", dataCadastro)
                .toString();
    }
}
