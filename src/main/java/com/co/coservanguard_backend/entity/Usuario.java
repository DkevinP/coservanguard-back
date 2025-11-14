package com.co.coservanguard_backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Integer id_user;

    @NotNull
    @NotBlank
    @Column(name = "nombre", nullable = false, length = 200)
    private String nombre;

    @Column(name = "apellido",  length = 200)
    private String apellido;

    @NotNull
    @NotBlank
    @Column(name = "cedula",nullable = false, unique = true, length = 10)
    private String cedula;

    @NotNull
    @NotBlank
    @Column(name = "contrasena", nullable = false,length = 200)
    private String  contrasena;


    @Column(name = "telefono",  length = 10)
    private String telefono;


    @Column(name = "correo",  length = 200)
    private String correo;


    @ManyToOne
    @JoinColumn(name = "id_cargo", nullable = false)
    private Cargo id_cargo;

    public Usuario() {}

    public Integer getId_user() {
        return id_user;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    public @NotBlank String getNombre() {
        return nombre;
    }

    public void setNombre(@NotBlank String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public @NotBlank String getCedula() {
        return cedula;
    }

    public void setCedula(@NotBlank String cedula) {
        this.cedula = cedula;
    }

    public @NotBlank String getContrasena() {
        return contrasena;
    }

    public void setContrasena(@NotBlank String contrasena) {
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

    public Cargo getId_cargo() {
        return id_cargo;
    }

    public void setId_cargo(Cargo id_cargo) {
        this.id_cargo = id_cargo;
    }
}
