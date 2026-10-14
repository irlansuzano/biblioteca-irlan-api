package br.edu.infnet.biblioteca_irlan_api.devolucao;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DevolucaoMapper {
    @Mapping(target = "aluguel", ignore = true)
    Devolucao toEntity(DevolucaoRequestDTO dto);

    @Mapping(target = "aluguelId", source = "aluguel.id")
    @Mapping(target = "livroTitulo", source = "aluguel.livro.titulo")
    @Mapping(target = "alunoId", source = "aluguel.alunoId")
    DevolucaoResponseDTO toResponseDto(Devolucao entity);
}
