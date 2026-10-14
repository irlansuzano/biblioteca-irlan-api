package br.edu.infnet.biblioteca_irlan_api.common.dto;

import java.time.LocalDate;

public record NotificacaoDTO(
    Long alunoId,
    String livroTitulo,
    LocalDate dataInicio,
    LocalDate dataFim,
    LocalDate dataDevolucao,
    String tipoEvento // ALUGUEL, DEVOLUCAO, RENOVACAO
) {}
