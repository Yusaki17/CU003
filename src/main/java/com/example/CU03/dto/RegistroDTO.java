package com.example.CU03.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegistroDTO {
    private Long id;
    private LocalDate fechaingreso;
    private LocalTime horaingreso;
    private String estado;
    private String observaciones;
    private Long conductorId;
    private Long ubicacionGPSId;

}
