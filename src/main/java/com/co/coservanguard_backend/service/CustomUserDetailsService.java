package com.co.coservanguard_backend.service;

import com.co.coservanguard_backend.entity.Usuario;
import com.co.coservanguard_backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String cedula) throws UsernameNotFoundException {
        // 1. Buscamos el usuario en tu base de datos por la cédula
        Usuario usuario = usuarioRepository.findByCedula(cedula);

        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado con cédula: " + cedula);
        }

        // 2. Extraemos el nombre del cargo de la base de datos y lo pasamos a mayúsculas
        // Ejemplo: "Vigilante" se convierte en "VIGILANTE"
        String nombreCargo = usuario.getId_cargo().getNombre_cargo().toUpperCase();

        // 3. Devolvemos un objeto "User" de Spring Security.
        // OJO: El método .roles() automáticamente le añade el prefijo "ROLE_" por debajo.
        // Entonces "VIGILANTE" se convierte en "ROLE_VIGILANTE" para Spring.
        return User.builder()
                .username(usuario.getCedula())
                .password(usuario.getContrasena()) // Aquí va la contraseña encriptada que está en la DB
                .roles(nombreCargo)
                .build();
    }


}
