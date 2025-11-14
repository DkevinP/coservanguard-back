package com.co.coservanguard_backend.controller;

import com.co.coservanguard_backend.dto.PuestoDTO;

import com.co.coservanguard_backend.service.PuestoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/puesto")
@CrossOrigin(origins = "http://localhost:4200")
public class PuestoController {

    private final PuestoService puestoService;

    public PuestoController(PuestoService puestoService) {
        this.puestoService = puestoService;
    }

    @PostMapping("/crear-puesto")
    public ResponseEntity<PuestoDTO> crearPuesto(@Valid @RequestBody PuestoDTO puestoDTO) {
        PuestoDTO puestoDTO1 = puestoService.crearPuesto(puestoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(puestoDTO1);
    }

    @GetMapping("/list-puesto")
    public ResponseEntity<List<PuestoDTO>> listarPuesto() {
        List<PuestoDTO>puestoDTOS= puestoService.listarPuestos();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(puestoDTOS);
    }



}
