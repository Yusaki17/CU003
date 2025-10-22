package com.example.CU03.mapper;

import com.example.CU03.dto.ConductorDTO;
import com.example.CU03.entity.Conductor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {RegistroMapper.class})
public interface ConductorMapper extends BaseMapper<Conductor, ConductorDTO> {

    // Al convertir a DTO, SÍ incluye los registros
    @Mapping(target = "registro", source = "registro")
    ConductorDTO toDTO(Conductor conductor);

    // Al convertir a Entity, IGNORA los registros (evita objetos transient)
    @Mapping(target = "registro", ignore = true)
    Conductor toEntity(ConductorDTO conductorDTO);
}