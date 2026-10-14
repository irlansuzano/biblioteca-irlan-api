package br.edu.infnet.biblioteca_irlan_api.devolucao;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Schema(description = "Dados para registro de uma devolução")
public record DevolucaoRequestDTO(
    @Schema(description = "Data em que a devolução foi realizada", example = "2023-10-10")
    @NotNull(message = "A data de devolução é obrigatória")
    LocalDate dataDevolucao,

    @Schema(description = "ID do aluguel que está sendo finalizado", example = "1")
    @NotNull(message = "O ID do aluguel é obrigatório")
    Long aluguelId
) {}
