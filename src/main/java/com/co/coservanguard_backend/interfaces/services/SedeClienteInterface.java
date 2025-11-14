package com.co.coservanguard_backend.interfaces.services;

import com.co.coservanguard_backend.dto.SedeClienteDTO;

import java.util.List;


public interface SedeClienteInterface {
    SedeClienteDTO crearSedeCliente(SedeClienteDTO sedeClienteDTO);

    List<SedeClienteDTO> listarSedeCliente();
}
