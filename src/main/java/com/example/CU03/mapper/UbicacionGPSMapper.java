package com.example.CU03.mapper;
import com.example.CU03.dto.UbicacionGPSDTO;
import com.example.CU03.entity.UbicacionGPS;
import org.mapstruct.Mapper;
@Mapper(componentModel = "spring")
public interface UbicacionGPSMapper extends BaseMapper<UbicacionGPS, UbicacionGPSDTO>{
}
