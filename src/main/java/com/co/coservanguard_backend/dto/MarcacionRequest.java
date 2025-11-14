package com.co.coservanguard_backend.dto;

import java.time.LocalDateTime;

public class MarcacionRequest {

    private String cedula;
    private String qr;
    private Double latitude;
    private Double longitude;
    private String fecha;

    public MarcacionRequest() {}

    public MarcacionRequest(String cedula, String qr, Double latitude, Double longitude, String fecha) {
        this.cedula = cedula;
        this.qr = qr;
        this.latitude = latitude;
        this.longitude = longitude;
        this.fecha = fecha;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getQr() {
        return qr;
    }

    public void setQr(String qr) {
        this.qr = qr;
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
