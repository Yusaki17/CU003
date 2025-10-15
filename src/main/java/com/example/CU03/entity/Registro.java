package com.example.CU03.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "TBL_REGISTRO_LLEGADA")
public class Registro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "FECHA_INGRESO")
    private LocalDate fechaingreso;

    @Column(name = "HORA_INGRESO")
    private LocalTime horaingeso;

    @Column(name = "ESTADO")
    private String estado;

    @Column(name = "OBSERVACIONES")
    private String observaciones;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CONDUCTOR_ID")
    private Conductor conductorId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "UBICACION_GPS_ID")
    private UbicacionGPS ubicacionGPSId;
}
