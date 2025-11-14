package com.co.coservanguard_backend.controller;

import com.co.coservanguard_backend.dto.ClienteDTO;
import com.co.coservanguard_backend.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cliente")
@CrossOrigin(origins = "http://localhost:4200")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping("/crear-cliente")
    public ResponseEntity<ClienteDTO> crearCliente(@Valid @RequestBody ClienteDTO clienteDTO) {
        ClienteDTO clienteCreadoDTO = clienteService.crearCliente(clienteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteCreadoDTO);
    }

    @GetMapping("/list-cliente")
    public ResponseEntity<List<ClienteDTO>> listarCliente() {
        List<ClienteDTO>clienteDTOS= clienteService.listarClientes();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(clienteDTOS);
    }
}
