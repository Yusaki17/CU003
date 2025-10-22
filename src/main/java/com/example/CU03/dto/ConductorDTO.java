package com.example.CU03.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ConductorDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private Integer telefono;
    private String licencia;
    private List<RegistroDTO> registro;
}
