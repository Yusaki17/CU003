package com.example.CU03.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UbicacionGPSDTO {
    private Long id;
    private String distrito;
    private String cordenadas;
    private String direccion;
}
