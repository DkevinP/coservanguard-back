package com.co.coservanguard_backend.dto;


public class ClienteDTO {


    private Integer id;

    private String nombre;

    private Integer nit;

    private String telefono;

    private String email;

    public ClienteDTO() {}

    public ClienteDTO(Integer id, String nombre, Integer nit, String telefono, String email) {
        this.id = id;
        this.nombre = nombre;
        this.nit = nit;
        this.telefono = telefono;
        this.email = email;
    }

    public ClienteDTO(String nombre, Integer nit, String telefono, String email) {
        this.nombre = nombre;
        this.nit = nit;
        this.telefono = telefono;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }


    public Integer getNit() {
        return nit;
    }


    public String getTelefono() {
        return telefono;
    }


    public String getEmail() {
        return email;
    }

}
