package com.co.coservanguard_backend.dto;



import java.time.LocalDateTime;

public class MarcacionQRDTO {

    private Integer id;

    private Integer id_asignacion;

    private Integer id_codigo;

    private LocalDateTime fecha;


    private Double latitude;


    private Double longitude;

    private Integer distanciaM;

    private Boolean es_cercano;

    public MarcacionQRDTO() {}

    public MarcacionQRDTO(Integer id, Integer id_asignacion, Integer id_codigo, LocalDateTime fecha, Double latitude, Double longitude, Integer distanciaM, Boolean es_cercano) {
        this.id = id;
        this.id_asignacion = id_asignacion;
        this.id_codigo = id_codigo;
        this.fecha = fecha;
        this.latitude = latitude;
        this.longitude = longitude;
        this.distanciaM = distanciaM;
        this.es_cercano = es_cercano;
    }

    public MarcacionQRDTO(Integer id_asignacion, Integer id_codigo, LocalDateTime fecha, Double latitude, Double longitude, Integer distanciaM, Boolean es_cercano) {
        this.id_asignacion = id_asignacion;
        this.id_codigo = id_codigo;
        this.fecha = fecha;
        this.latitude = latitude;
        this.longitude = longitude;
        this.distanciaM = distanciaM;
        this.es_cercano = es_cercano;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_asignacion() {
        return id_asignacion;
    }

    public void setId_asignacion(Integer id_asignacion) {
        this.id_asignacion = id_asignacion;
    }

    public Integer getId_codigo() {
        return id_codigo;
    }

    public void setId_codigo(Integer id_codigo) {
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
