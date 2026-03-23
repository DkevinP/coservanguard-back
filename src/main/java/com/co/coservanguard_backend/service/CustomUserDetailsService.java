package com.co.coservanguard_backend.service;

import com.co.coservanguard_backend.entity.Usuario;
import com.co.coservanguard_backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

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

        // 2. Extraemos el nombre del cargo tal cual viene de la base de datos
        // Importante: No usamos .roles() para evitar que Spring añada "ROLE_" automáticamente
        String nombreCargo = usuario.getId_cargo().getNombre_cargo().toUpperCase();

        // 3. Creamos la autoridad exacta que espera tu SecurityConfig
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(nombreCargo);

        // 4. Devolvemos el usuario con su autoridad exacta
        return new User(
                usuario.getCedula(),
                usuario.getContrasena(),
                Collections.singletonList(authority) // Esto asigna "ADMINISTRADOR" directamente
        );
    }
}