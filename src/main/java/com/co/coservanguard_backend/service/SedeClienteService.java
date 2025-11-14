package com.co.coservanguard_backend.service;



import com.co.coservanguard_backend.dto.SedeClienteDTO;
import com.co.coservanguard_backend.entity.Cliente;
import com.co.coservanguard_backend.entity.SedeCliente;
import com.co.coservanguard_backend.interfaces.services.SedeClienteInterface;
import com.co.coservanguard_backend.repository.ClienteRepository;
import com.co.coservanguard_backend.repository.SedeClienteRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class SedeClienteService implements SedeClienteInterface {

    private final SedeClienteRepository sedeClienteRepository;

    private final ClienteRepository clienteRepository;

    public SedeClienteService(SedeClienteRepository sedeClienteRepository, ClienteRepository clienteRepository) {
        this.sedeClienteRepository = sedeClienteRepository;
        this.clienteRepository = clienteRepository;
    }


    @Override
    public SedeClienteDTO crearSedeCliente(SedeClienteDTO sedeClienteDTO) {
        Cliente cliente = clienteRepository.findById(sedeClienteDTO.getId_cliente())
                .orElseThrow(() -> new NoSuchElementException("Cliente no encontrado"));
        SedeCliente sedeCliente = new SedeCliente();
        sedeCliente.setSede(sedeClienteDTO.getSede());
        sedeCliente.setDireccion(sedeClienteDTO.getDireccion());
        sedeCliente.setId_cliente(cliente);


        SedeCliente save = sedeClienteRepository.save(sedeCliente);

        return new SedeClienteDTO(save.getSede(), save.getDireccion(), save.getId_cliente().getId_cliente());

    }

    @Override
    public List<SedeClienteDTO> listarSedeCliente() {
        List<SedeCliente> sedeCliente = sedeClienteRepository.findAll();
        List<SedeClienteDTO> sedeClienteDTO = new ArrayList<>();
        for (SedeCliente sedeCliente1 : sedeCliente) {
            sedeClienteDTO.add(new SedeClienteDTO(sedeCliente1.getId_sede(),sedeCliente1.getSede(), sedeCliente1.getDireccion(), sedeCliente1.getId_cliente().getId_cliente()));
        }
        return sedeClienteDTO;
    }
}
