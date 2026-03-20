package com.co.coservanguard_backend.controller;



import com.co.coservanguard_backend.dto.SedeClienteDTO;
import com.co.coservanguard_backend.service.SedeClienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sede-cliente")
@CrossOrigin(origins = "http://coservanguard.eastus.cloudapp.azure.com")
public class SedeController {

    private final SedeClienteService sedeClienteService;

    public SedeController(SedeClienteService sedeClienteService) {
        this.sedeClienteService = sedeClienteService;
    }

    @PostMapping("/crear-sede")
    public ResponseEntity<SedeClienteDTO> crearSedeCliente(@Valid @RequestBody SedeClienteDTO sedeClienteDTO) {
        SedeClienteDTO sedeClienteDTO1 = sedeClienteService.crearSedeCliente(sedeClienteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(sedeClienteDTO1);
    }

    @GetMapping("/list-sede")
    public ResponseEntity<List<SedeClienteDTO>> listarSedeCliente() {
        List<SedeClienteDTO>sedeClienteDTOS= sedeClienteService.listarSedeCliente();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(sedeClienteDTOS);
    }
}
