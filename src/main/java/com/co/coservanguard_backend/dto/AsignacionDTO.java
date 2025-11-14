package com.co.coservanguard_backend.dto;

public class AsignacionDTO {

    private Integer id;
    private Integer id_user;
    private Integer id_puesto;

    public AsignacionDTO() {}

    public AsignacionDTO(Integer id, Integer id_user, Integer id_puesto) {
        this.id = id;
        this.id_user = id_user;
        this.id_puesto = id_puesto;
    }

    public AsignacionDTO(Integer id_user, Integer id_puesto) {
        this.id_user = id_user;
        this.id_puesto = id_puesto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_user() {
        return id_user;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    public Integer getId_puesto() {
        return id_puesto;
    }

    public void setId_puesto(Integer id_puesto) {
        this.id_puesto = id_puesto;
    }
}
