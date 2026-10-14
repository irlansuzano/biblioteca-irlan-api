package br.edu.infnet.biblioteca_irlan_api.livro;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Dados para cadastro/atualização de um livro")
public record LivroRequestDTO(
    @Schema(description = "Título do livro", example = "O Senhor dos Anéis")
    @NotBlank(message = "O título é obrigatório")
    @Size(min = 1, max = 200, message = "O título deve ter entre 1 e 200 caracteres")
    String titulo,

    @Schema(description = "Autor do livro", example = "J.R.R. Tolkien")
    @NotBlank(message = "O autor é obrigatório")
    @Size(min = 2, max = 150, message = "O autor deve ter entre 2 e 150 caracteres")
    String autor
) {}
