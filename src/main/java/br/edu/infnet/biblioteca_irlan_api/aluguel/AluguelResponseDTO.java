package br.edu.infnet.biblioteca_irlan_api.aluguel;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

@Schema(description = "Dados de retorno de um aluguel")
public record AluguelResponseDTO(
    @Schema(description = "Identificador único do aluguel", example = "1")
    Long id,
    @Schema(description = "Data de início do aluguel", example = "2023-10-01")
    LocalDate dataInicioAluguel,
    @Schema(description = "Data prevista/realizada para o fim do aluguel", example = "2023-10-15")
    LocalDate dataFimAluguel,
    @Schema(description = "Título do livro alugado", example = "O Senhor dos Anéis")
    String livroTitulo,
    @Schema(description = "ID do aluno requerente", example = "101")
    Long alunoId,
    @Schema(description = "Indica se o aluguel ainda está ativo", example = "true")
    Boolean isAtivo
) {}
