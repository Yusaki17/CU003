package com.example.CU03.mapper;

import com.example.CU03.dto.ConductorDTO;
import com.example.CU03.entity.Conductor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {RegistroMapper.class})
public interface ConductorMapper extends BaseMapper<Conductor, ConductorDTO> {

    @Mapping(target = "registro", source = "registro")
    ConductorDTO toDTO(Conductor conductor);


    @Mapping(target = "registro", ignore = true)
    Conductor toEntity(ConductorDTO conductorDTO);
}