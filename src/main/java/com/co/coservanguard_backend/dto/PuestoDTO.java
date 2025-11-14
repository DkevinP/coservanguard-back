package com.co.coservanguard_backend.dto;



public class PuestoDTO {

    private Integer id;

    private String puesto;

    private Integer id_sede;

    public PuestoDTO() {}

    public PuestoDTO(String puesto, Integer id_sede) {
        this.puesto = puesto;
        this.id_sede = id_sede;
    }
    public PuestoDTO(Integer id, String puesto, Integer id_sede) {
        this.id = id;
        this.puesto = puesto;
        this.id_sede = id_sede;
    }




    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPuesto() {
        return puesto;
    }



    public Integer getId_sede() {
        return id_sede;
    }

}
