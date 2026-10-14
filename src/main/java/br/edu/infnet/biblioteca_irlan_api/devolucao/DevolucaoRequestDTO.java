package br.edu.infnet.biblioteca_irlan_api.devolucao;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record DevolucaoRequestDTO(
    @NotNull(message = "A data de devolução é obrigatória")
    LocalDate dataDevolucao,

    @NotNull(message = "O ID do aluguel é obrigatório")
    Long aluguelId
) {}
