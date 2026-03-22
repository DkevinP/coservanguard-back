package com.co.coservanguard_backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Asignacion")
public class Asignacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_asignacion")
    private Integer id_asignacion;

    // Relación con Usuario
    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private Usuario id_user;

    @ManyToOne
    @JoinColumn(name = "id_puesto", nullable = false)
    private Puesto id_puesto;

    public Asignacion() {}

    public Integer getId_asignacion() {
        return id_asignacion;
    }

    public void setId_asignacion(Integer id_asignacion) {
        this.id_asignacion = id_asignacion;
    }

    public Usuario getId_user() {
        return id_user;
    }

    public void setId_user(Usuario id_user) {
        this.id_user = id_user;
    }

    public Puesto getId_puesto() {
        return id_puesto;
    }

    public void setId_puesto(Puesto id_puesto) {
        this.id_puesto = id_puesto;
    }
}
