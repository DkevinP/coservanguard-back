package com.co.coservanguard_backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "Cargo")
public class Cargo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cargo")
    private Integer id_cargo;

    @NotBlank
    @Column(name = "nombre_cargo", nullable = false, length = 200)
    private String nombre_cargo;

    public Cargo() {}

    public Integer getId_cargo() {
        return id_cargo;
    }

    public void setId_cargo(Integer id_cargo) {
        this.id_cargo = id_cargo;
    }

    public @NotBlank String getNombre_cargo() {
        return nombre_cargo;
    }

    public void setNombre_cargo(@NotBlank String nombre_cargo) {
        this.nombre_cargo = nombre_cargo;
    }
}
