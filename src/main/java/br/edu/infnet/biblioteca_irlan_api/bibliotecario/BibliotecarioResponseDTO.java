package br.edu.infnet.biblioteca_irlan_api.bibliotecario;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados de retorno de um bibliotecário")
public record BibliotecarioResponseDTO(
    @Schema(description = "Identificador único do bibliotecário", example = "1")
    Long id, 
    @Schema(description = "Login do bibliotecário", example = "bibliotecario1")
    String login, 
    @Schema(description = "Nível hierárquico do bibliotecário", example = "GERENCIA")
    NivelHierarquico nivelHierarquico
) {}
