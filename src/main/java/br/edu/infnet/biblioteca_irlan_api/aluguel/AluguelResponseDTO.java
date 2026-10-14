package br.edu.infnet.biblioteca_irlan_api.aluguel;

import java.time.LocalDate;

public record AluguelResponseDTO(
    Long id,
    LocalDate dataInicioAluguel,
    LocalDate dataFimAluguel,
    String livroTitulo,
    Long alunoId,
    Boolean isAtivo
) {}
