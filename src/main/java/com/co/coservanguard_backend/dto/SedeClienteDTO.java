package com.co.coservanguard_backend.dto;



public class SedeClienteDTO {

    private Integer id;

    private String sede;

    private String direccion;

    private Integer  id_cliente;

    public SedeClienteDTO() {}

    public SedeClienteDTO(Integer id, String sede, String direccion, Integer id_cliente) {
        this.id = id;
        this.sede = sede;
        this.direccion = direccion;
        this.id_cliente = id_cliente;
    }

    public SedeClienteDTO(String sede, String direccion, Integer id_cliente) {
        this.sede = sede;
        this.direccion = direccion;
        this.id_cliente = id_cliente;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSede() {
        return sede;
    }



    public String getDireccion() {
        return direccion;
    }


    public Integer getId_cliente() {
        return id_cliente;
    }


}
