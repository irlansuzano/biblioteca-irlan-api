package br.edu.infnet.biblioteca_irlan_api.bibliotecario;

public record BibliotecarioRequestDTO(String login, String senha, NivelHierarquico nivelHierarquico) {}
