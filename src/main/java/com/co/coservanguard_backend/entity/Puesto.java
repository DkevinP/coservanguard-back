package com.co.coservanguard_backend.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "puesto")
public class Puesto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_puesto")
    private Integer id_puesto;

    @NotBlank
    @Column(name = "puesto", nullable = false, length = 200)
    private String puesto;

    @ManyToOne
    @JoinColumn(name = "id_sede", nullable = false)
    private SedeCliente id_sede;

    public Puesto() {}


    public @NotBlank String getPuesto() {
        return puesto;
    }

    public void setPuesto(@NotBlank String puesto) {
        this.puesto = puesto;
    }

    public SedeCliente getId_sede() {
        return id_sede;
    }

    public void setId_sede(SedeCliente id_sede) {
        this.id_sede = id_sede;
    }

    public Integer getId_puesto() {
        return id_puesto;
    }

    public void setId_puesto(Integer id_puesto) {
        this.id_puesto = id_puesto;
    }
}
