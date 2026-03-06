package com.co.coservanguard_backend.dto;

public class LoginRequestDTO {

    private String cedula;
    private String contrasena;

    public LoginRequestDTO() {}

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
