package br.edu.infnet.biblioteca_irlan_api.livro;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados de retorno de um livro")
public record LivroResponseDTO(
    @Schema(description = "Identificador único do livro", example = "1")
    Long id,
    @Schema(description = "Título do livro", example = "O Senhor dos Anéis")
    String titulo,
    @Schema(description = "Autor do livro", example = "J.R.R. Tolkien")
    String autor
) {}
