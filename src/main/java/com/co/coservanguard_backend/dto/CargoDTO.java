package com.co.coservanguard_backend.dto;

public class CargoDTO {

    private Integer id;

    private String nombre_cargo;

    public CargoDTO(){}
    public CargoDTO(Integer id, String nombre_cargo) {
        this.id = id;
        this.nombre_cargo = nombre_cargo;
    }

    public CargoDTO(String nombre_cargo) {
        this.nombre_cargo = nombre_cargo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre_cargo() {
        return nombre_cargo;
    }

    public void setNombre_cargo(String nombre_cargo) {
        this.nombre_cargo = nombre_cargo;
    }
}
