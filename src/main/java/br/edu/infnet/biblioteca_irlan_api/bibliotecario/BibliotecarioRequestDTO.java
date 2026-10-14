package br.edu.infnet.biblioteca_irlan_api.bibliotecario;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados para registro de um bibliotecário")
public record BibliotecarioRequestDTO(
    @Schema(description = "Login do bibliotecário", example = "bibliotecario1")
    String login, 
    @Schema(description = "Senha do bibliotecário", example = "senha123")
    String senha, 
    @Schema(description = "Nível hierárquico do bibliotecário", example = "GERENCIA")
    NivelHierarquico nivelHierarquico
) {}
