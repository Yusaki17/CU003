package com.example.CU03.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegistroDTO {
    private Long id;
    private LocalDateTime fechahoraingreso;
    private String estado;
    private String observaciones;
    private Long conductorId;
    private Long ubicacionGPSId;

}
