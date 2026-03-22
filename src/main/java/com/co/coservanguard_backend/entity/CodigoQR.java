package com.co.coservanguard_backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "Codigoqr")
public class CodigoQR {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_codigo")
    private Integer id_codigo;

    @Column(name = "qr", nullable = false, length = 200)
    private String qr;


    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @ManyToOne
    @JoinColumn(name = "id_puesto", nullable = false)
    private Puesto id_puesto;

    public CodigoQR() {}

    public Integer getId_codigo() {
        return id_codigo;
    }

    public void setId_codigo(Integer id_codigo) {
        this.id_codigo = id_codigo;
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

    public Puesto getId_puesto() {
        return id_puesto;
    }

    public void setId_puesto(Puesto id_puesto) {
        this.id_puesto = id_puesto;
    }
}
