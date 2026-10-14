package br.edu.infnet.biblioteca_irlan_api.client;

public record AlunoDTO(
    Long id,
    String nome,
    String email,
    String matricula
) {}
