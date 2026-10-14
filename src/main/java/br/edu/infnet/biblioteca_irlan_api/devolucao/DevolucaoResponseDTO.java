package br.edu.infnet.biblioteca_irlan_api.devolucao;

import java.time.LocalDate;

public record DevolucaoResponseDTO(
    Long id,
    LocalDate dataDevolucao,
    Long aluguelId,
    String livroTitulo,
    Long alunoId
) {}
