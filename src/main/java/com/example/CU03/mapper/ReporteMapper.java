package com.example.CU03.mapper;
import com.example.CU03.dto.ReporteDTO;
import com.example.CU03.entity.Reporte;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReporteMapper extends BaseMapper<Reporte, ReporteDTO>{
    @Mapping(source = "registroId.id", target = "registroId")
    ReporteDTO toDTO(Reporte entity);

    @Mapping(source = "registroId", target = "registroId.id")
    Reporte toEntity(ReporteDTO dto);
}
