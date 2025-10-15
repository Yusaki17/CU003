package com.example.CU03.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "TBL_REPORTE_LLEGADA")
public class Reporte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "FECHA_REPORTE")
    private String fechareporte;

    @Column(name = "CODIGO")
    private String codigo;

    @Column(name = "ESTADO")
    private String estado;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "REGISTRO_LLEGADA_ID")
    private Registro registroId;

}
