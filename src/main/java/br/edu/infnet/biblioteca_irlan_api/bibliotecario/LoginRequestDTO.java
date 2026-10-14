package br.edu.infnet.biblioteca_irlan_api.bibliotecario;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados para autenticação")
public record LoginRequestDTO(
    @Schema(description = "Login do usuário", example = "bibliotecario1")
    String login, 
    @Schema(description = "Senha do usuário", example = "senha123")
    String senha
) {}
