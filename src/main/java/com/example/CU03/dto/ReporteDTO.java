package com.example.CU03.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReporteDTO {
    private Long id;
    private LocalDate fechareporte;
    private String codigoreporte;
    private String estado;
    private Long registroId;

}
