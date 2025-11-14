package com.co.coservanguard_backend.interfaces.services;

import com.co.coservanguard_backend.dto.ClienteDTO;

import java.util.List;

public interface ClienteServiceInterface {

    ClienteDTO crearCliente(ClienteDTO clienteDTO);

    List<ClienteDTO> listarClientes();

}
