package br.edu.infnet.biblioteca_irlan_api.devolucao;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;

@Schema(description = "Dados de retorno de uma devolução")
public record DevolucaoResponseDTO(
    @Schema(description = "Identificador único da devolução", example = "1")
    Long id,
    @Schema(description = "Data em que a devolução foi realizada", example = "2023-10-10")
    LocalDate dataDevolucao,
    @Schema(description = "ID do aluguel correspondente", example = "1")
    Long aluguelId,
    @Schema(description = "Título do livro devolvido", example = "O Senhor dos Anéis")
    String livroTitulo,
    @Schema(description = "ID do aluno que devolveu", example = "101")
    Long alunoId
) {}
