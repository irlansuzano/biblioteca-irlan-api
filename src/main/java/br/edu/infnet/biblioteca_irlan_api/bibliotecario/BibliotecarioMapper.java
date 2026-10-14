package br.edu.infnet.biblioteca_irlan_api.bibliotecario;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BibliotecarioMapper {
    Bibliotecario toEntity(BibliotecarioRequestDTO dto);
    BibliotecarioResponseDTO toResponseDto(Bibliotecario entity);
}
