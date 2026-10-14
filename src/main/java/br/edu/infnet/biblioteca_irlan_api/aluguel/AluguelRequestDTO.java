package br.edu.infnet.biblioteca_irlan_api.aluguel;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Schema(description = "Dados para registro de um novo aluguel")
public record AluguelRequestDTO(
    @Schema(description = "Data de início do aluguel", example = "2023-10-01")
    @NotNull(message = "A data de início do aluguel é obrigatória")
    LocalDate dataInicioAluguel,

    @Schema(description = "Data prevista para o fim do aluguel", example = "2023-10-15")
    @NotNull(message = "A data de fim do aluguel é obrigatória")
    LocalDate dataFimAluguel,

    @Schema(description = "ID do livro a ser alugado", example = "1")
    @NotNull(message = "O ID do livro é obrigatório")
    Long livroId,

    @Schema(description = "ID do aluno que está alugando", example = "101")
    @NotNull(message = "O ID do aluno é obrigatório")
    Long alunoId,

    @Schema(description = "Indica se o aluguel está ativo", example = "true")
    Boolean isAtivo
) {}
