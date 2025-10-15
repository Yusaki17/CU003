package com.example.CU03.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ConductorDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String telefono;
    private String licencia;

}
