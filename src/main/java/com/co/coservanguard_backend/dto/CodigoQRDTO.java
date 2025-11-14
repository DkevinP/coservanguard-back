package com.co.coservanguard_backend.dto;

public class CodigoQRDTO {
    private Integer id;
    private String qr;
    private Double latitude;
    private Double longitude;
    private Integer id_puesto;

    public CodigoQRDTO() {}

    public CodigoQRDTO(Integer id, String qr, Double latitude, Double longitude, Integer id_puesto) {
        this.id = id;
        this.qr = qr;
        this.latitude = latitude;
        this.longitude = longitude;
        this.id_puesto = id_puesto;
    }

    public CodigoQRDTO(String qr, Double latitude, Double longitude, Integer id_puesto) {
        this.qr = qr;
        this.latitude = latitude;
        this.longitude = longitude;
        this.id_puesto = id_puesto;
    }
    public CodigoQRDTO(  Integer id_puesto) {
        this.id_puesto = id_puesto;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getId_puesto() {
        return id_puesto;
    }

    public void setId_puesto(Integer id_puesto) {
        this.id_puesto = id_puesto;
    }
}
