package com.co.coservanguard_backend.controller;

import com.co.coservanguard_backend.dto.CargoDTO;
import com.co.coservanguard_backend.service.CargoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cargo")
@CrossOrigin(origins = "http://localhost:4200")
public class CargoController {

    private final CargoService cargoService;

    public CargoController(CargoService cargoService) {
        this.cargoService = cargoService;
    }

    @PostMapping("/crear-cargo")
    public ResponseEntity<CargoDTO> crearCargo(@Valid @RequestBody CargoDTO cargoDTO) {
        CargoDTO cargoDTO1 = cargoService.crearCargo(cargoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(cargoDTO1);
    }

    @GetMapping("/list-cargo")
    public ResponseEntity<List<CargoDTO>> listarCargo() {
        List<CargoDTO>cargoDTOS= cargoService.listarCargo();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(cargoDTOS);
    }
}
