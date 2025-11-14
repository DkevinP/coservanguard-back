package com.co.coservanguard_backend.controller;


import com.co.coservanguard_backend.dto.UsuarioDTO;
import com.co.coservanguard_backend.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuario")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {

    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/crear-usuario")
    public ResponseEntity<UsuarioDTO> crearUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDTO usuarioDTO1 = usuarioService.crearUsuario(usuarioDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioDTO1);
    }

    @GetMapping("/list-usuario")
    public ResponseEntity<List<UsuarioDTO>> listarUsuario() {
        List<UsuarioDTO>usuarioDTOS= usuarioService.listarUsuario();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(usuarioDTOS);
    }
}
