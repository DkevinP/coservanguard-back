package com.co.coservanguard_backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;



@Entity
@Table(name = "cliente")
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cliente")
	private Integer id_cliente;

	@NotBlank
	@Column(name = "nombre", nullable = false, length = 100)
	private String nombre;

	@NotNull
	@Column(name = "nit", nullable = false)
	private Integer nit;

	@NotBlank
	@Column(name = "telefono", nullable = false,  length = 20)
	private String telefono;

	@Email
	@Column(name = "email", nullable = false,  length = 100)
	private String email;

	public Cliente(){

	}


	public Integer getId_cliente() {
		return id_cliente;
	}



	public @NotBlank String getNombre() {
		return nombre;
	}

	public void setNombre(@NotBlank String nombre) {
		this.nombre = nombre;
	}

	public @NotNull Integer getNit() {
		return nit;
	}

	public void setNit(@NotNull Integer nit) {
		this.nit = nit;
	}

	public @NotBlank String getTelefono() {
		return telefono;
	}

	public void setTelefono(@NotBlank String telefono) {
		this.telefono = telefono;
	}

	public @Email String getEmail() {
		return email;
	}

	public void setEmail(@Email String email) {
		this.email = email;
	}
}
