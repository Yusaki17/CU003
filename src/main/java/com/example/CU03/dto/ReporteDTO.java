package com.example.CU03.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReporteDTO {
    private Long id;
    private String fechareporte;
    private String codigo;
    private String estado;
    private Long registroId;

}
