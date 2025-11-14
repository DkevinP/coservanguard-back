package com.co.coservanguard_backend.service;

import com.co.coservanguard_backend.dto.ClienteDTO;
import com.co.coservanguard_backend.entity.Cliente;
import com.co.coservanguard_backend.interfaces.services.ClienteServiceInterface;
import com.co.coservanguard_backend.repository.ClienteRepository;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class ClienteService implements ClienteServiceInterface {


    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public ClienteDTO crearCliente(ClienteDTO clienteDTO) {
            Cliente cliente = new Cliente();
            cliente.setNombre(clienteDTO.getNombre());
            cliente.setNit(clienteDTO.getNit());
            cliente.setTelefono(clienteDTO.getTelefono());
            cliente.setEmail(clienteDTO.getEmail());

     Cliente save = clienteRepository.save(cliente);

     return new ClienteDTO(save.getNombre(), save.getNit(), save.getTelefono(), save.getEmail());
    }

    @Override
    public List<ClienteDTO> listarClientes() {

        List<Cliente> clientes = clienteRepository.findAll();
        List<ClienteDTO> clienteDTOs = new ArrayList<>();
        for (Cliente cliente : clientes) {
            clienteDTOs.add(new ClienteDTO(cliente.getId_cliente(), cliente.getNombre(), cliente.getNit(), cliente.getTelefono(), cliente.getEmail()));
        }
        return clienteDTOs ;
    }
}
