package com.example.CU03.mapper;

import com.example.CU03.dto.ConductorDTO;
import com.example.CU03.entity.Conductor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ConductorMapper extends BaseMapper<Conductor, ConductorDTO>{
}
