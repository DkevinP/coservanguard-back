package com.co.coservanguard_backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

@Entity
@Table(name ="Marcacionqr")
public class MarcacionQR {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_logs")
    private Integer id_logs;

    @ManyToOne
    @JoinColumn(name = "id_asignacion", nullable = false)
    private Asignacion id_asignacion;

    @ManyToOne
    @JoinColumn(name = "id_codigo", nullable = false)
    private CodigoQR id_codigo;

    @Column(name= "fecha")
    private LocalDateTime fecha;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "distanciaM")
    private Integer distanciaM;

    @Column(name = "es_cercano")
    private Boolean es_cercano;

    public Integer getId_logs() {
        return id_logs;
    }

    public void setId_logs(Integer id_logs) {
        this.id_logs = id_logs;
    }

    public Asignacion getId_asignacion() {
        return id_asignacion;
    }

    public void setId_asignacion(Asignacion id_asignacion) {
        this.id_asignacion = id_asignacion;
    }

    public CodigoQR getId_codigo() {
        return id_codigo;
    }

    public void setId_codigo(CodigoQR id_codigo) {
        this.id_codigo = id_codigo;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Integer getDistanciaM() {
        return distanciaM;
    }

    public void setDistanciaM(Integer distanciaM) {
        this.distanciaM = distanciaM;
    }

    public Boolean getEs_cercano() {
        return es_cercano;
    }

    public void setEs_cercano(Boolean es_cercano) {
        this.es_cercano = es_cercano;
    }
}
