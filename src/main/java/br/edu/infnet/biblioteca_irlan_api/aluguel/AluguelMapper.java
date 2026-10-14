package br.edu.infnet.biblioteca_irlan_api.aluguel;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AluguelMapper {
    @Mapping(target = "livro", ignore = true)
    Aluguel toEntity(AluguelRequestDTO dto);

    @Mapping(target = "livroTitulo", source = "livro.titulo")
    AluguelResponseDTO toResponseDto(Aluguel entity);
}
