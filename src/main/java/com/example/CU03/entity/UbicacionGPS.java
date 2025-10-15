package com.example.CU03.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TBL_UBICACION_GPS")
public class UbicacionGPS {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "DISTRITO")
    private String distrito;

    @Column(name = "CORDENADAS")
    private String cordenadas;

    @Column(name = "DIRECCION")
    private String direccion;
}
