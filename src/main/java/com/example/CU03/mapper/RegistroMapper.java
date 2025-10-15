package com.example.CU03.mapper;
import com.example.CU03.dto.RegistroDTO;
import com.example.CU03.entity.Registro;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RegistroMapper extends BaseMapper<Registro, RegistroDTO>{
    @Mapping(source = "conductorId.id", target = "conductorId")
    @Mapping(source = "ubicacionGPSId.id", target = "ubicacionGPSId")
    RegistroDTO toDTO(Registro entity);

    @Mapping(source = "conductorId", target = "conductorId.id")
    @Mapping(source = "ubicacionGPSId", target = "ubicacionGPSId.id")
    Registro toEntity(RegistroDTO dto);
}
