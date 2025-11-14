package com.co.coservanguard_backend.service;

import com.co.coservanguard_backend.dto.UsuarioDTO;
import com.co.coservanguard_backend.entity.Cargo;

import com.co.coservanguard_backend.entity.Usuario;
import com.co.coservanguard_backend.interfaces.services.UsuarioServiceInterface;
import com.co.coservanguard_backend.repository.CargoRepository;
import com.co.coservanguard_backend.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UsuarioService implements UsuarioServiceInterface {

    private UsuarioRepository usuarioRepository;
    private CargoRepository cargoRepository;

    public UsuarioService(UsuarioRepository usuarioRepository, CargoRepository cargoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.cargoRepository = cargoRepository;
    }

    @Override
    public UsuarioDTO crearUsuario(UsuarioDTO usuarioDTO) {
        Cargo cargo = cargoRepository.findById(usuarioDTO.getId_cargo())
                .orElseThrow(() -> new NoSuchElementException("Cargo no encontrado"));
        Usuario usuario = new Usuario();
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setApellido(usuarioDTO.getApellido());
        usuario.setCedula(usuarioDTO.getCedula());
        usuario.setContrasena(usuarioDTO.getContrasena());
        usuario.setTelefono(usuarioDTO.getTelefono());
        usuario.setCorreo(usuarioDTO.getCorreo());
        usuario.setId_cargo(cargo);


        Usuario save = usuarioRepository.save(usuario);

        return new UsuarioDTO(save.getNombre(),save.getApellido(),save.getCedula(),save.getContrasena(),save.getTelefono(),
                save.getCorreo(),save.getId_cargo().getId_cargo());

    }

    @Override
    public List<UsuarioDTO> listarUsuario() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<UsuarioDTO> usuarioDTOs = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            usuarioDTOs.add(new UsuarioDTO(usuario.getId_user(),usuario.getNombre(),usuario.getApellido(),usuario.getCedula(), usuario.getContrasena(),usuario.getTelefono(),
                    usuario.getCorreo(),usuario.getId_cargo().getId_cargo()));
        }
        return usuarioDTOs;
    }

    public Usuario findByCedula(String cedula){
        Usuario usuario = usuarioRepository.findByCedula(cedula);
        return usuario;
    }
}
