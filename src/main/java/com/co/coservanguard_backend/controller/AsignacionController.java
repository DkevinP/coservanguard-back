package com.co.coservanguard_backend.controller;

import com.co.coservanguard_backend.dto.AsignacionDTO;
import com.co.coservanguard_backend.service.AsignacionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/asignacion")
@CrossOrigin(origins = "http://localhost:4200")
public class AsignacionController {

    private AsignacionService asignacionService;

    public AsignacionController(AsignacionService asignacionService) {
        this.asignacionService = asignacionService;
    }

    @PostMapping("/crear-asignacion")
    public ResponseEntity<AsignacionDTO> crearAsignacion(@Valid @RequestBody AsignacionDTO asignacionDTO) {
        AsignacionDTO asignacionDTO1 = asignacionService.crearAsignacion(asignacionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(asignacionDTO1);
    }

    @GetMapping("/list-asignacion")
    public ResponseEntity<List<AsignacionDTO>> listarAsignacion() {
        List<AsignacionDTO>asignacionDTOS= asignacionService.listarAsignacion();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(asignacionDTOS);
    }




}
