package com.co.coservanguard_backend.interfaces.services;

import com.co.coservanguard_backend.dto.UsuarioDTO;

import java.util.List;

public interface UsuarioServiceInterface {
    UsuarioDTO crearUsuario(UsuarioDTO usuarioDTO);

    List<UsuarioDTO> listarUsuario();
}
