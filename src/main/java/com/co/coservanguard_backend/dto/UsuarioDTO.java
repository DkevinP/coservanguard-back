package com.co.coservanguard_backend.dto;

public class UsuarioDTO {

    private Integer id;
    private String nombre;
    private String apellido;
    private String cedula;
    private String contrasena;
    private String telefono;
    private String correo;
    private Integer id_cargo;

    public UsuarioDTO() {}

    public UsuarioDTO(Integer id,String nombre, String apellido, String cedula, String contrasena, String telefono, String correo, Integer id_cargo) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.contrasena = contrasena;
        this.telefono = telefono;
        this.correo = correo;
        this.id_cargo = id_cargo;
    }

    public UsuarioDTO(String nombre, String apellido, String cedula, String contrasena, String telefono, String correo, Integer id_cargo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.contrasena = contrasena;
        this.telefono = telefono;
        this.correo = correo;
        this.id_cargo = id_cargo;
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

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Integer getId_cargo() {
        return id_cargo;
    }

    public void setId_cargo(Integer id_cargo) {
        this.id_cargo = id_cargo;
    }
}
