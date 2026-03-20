package com.co.coservanguard_backend.controller;


import com.co.coservanguard_backend.dto.LoginRequestDTO;
import com.co.coservanguard_backend.dto.LoginResponseDTO;
import com.co.coservanguard_backend.dto.UsuarioDTO;
import com.co.coservanguard_backend.entity.Usuario;
import com.co.coservanguard_backend.repository.UsuarioRepository;
import com.co.coservanguard_backend.security.JwtUtil;
import com.co.coservanguard_backend.service.CustomUserDetailsService;
import com.co.coservanguard_backend.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuario")
@CrossOrigin(origins = "http://coservanguard.eastus.cloudapp.azure.com")
public class UsuarioController {

    private UsuarioService usuarioService;
    private PasswordEncoder passwordEncoder;

    private AuthenticationManager authenticationManager; // El que verifica la contraseña

    private CustomUserDetailsService userDetailsService; // El que busca los roles

    private JwtUtil jwtUtil; // El que fabrica el token

    private UsuarioRepository usuarioRepository; // Para buscar los datos extras (nombre, cargo)


    public UsuarioController(UsuarioService usuarioService,AuthenticationManager authenticationManager, CustomUserDetailsService customUserDetailsService,
                             JwtUtil jwtUtil, UsuarioRepository usuarioRepository) {
        this.usuarioService = usuarioService;
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.authenticationManager = authenticationManager;
        this.userDetailsService = customUserDetailsService;
        this.jwtUtil = jwtUtil;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("/crear-usuario")
    public ResponseEntity<UsuarioDTO> crearUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        String passwordEncriptada = passwordEncoder.encode(usuarioDTO.getContrasena());
        usuarioDTO.setContrasena(passwordEncriptada);
        UsuarioDTO usuarioDTO1 = usuarioService.crearUsuario(usuarioDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioDTO1);
    }

    @GetMapping("/list-usuario")
    public ResponseEntity<List<UsuarioDTO>> listarUsuario() {
        List<UsuarioDTO>usuarioDTOS= usuarioService.listarUsuario();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(usuarioDTOS);
    }

    // --- EL NUEVO ENDPOINT DE LOGIN ---
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDTO loginRequest) {
        try {
            // 1. Spring Security intenta autenticar (Compara la contraseña encriptada de la BD con la que mandó el usuario)
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getCedula(), loginRequest.getContrasena())
            );

        } catch (BadCredentialsException e) {
            // 2. Si la contraseña o la cédula están mal, lanzamos un error 401 Unauthorized
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Cédula o contraseña incorrectos");
        }

        // 3. Si pasó la línea anterior, las credenciales son correctas. Buscamos sus datos (Roles).
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getCedula());

        // 4. Generamos el Token (La manilla JWT)
        String jwt = jwtUtil.generateToken(userDetails);

        // 5. Buscamos al usuario en tu BD para sacar su nombre y cargo reales
        Usuario usuario = usuarioRepository.findByCedula(loginRequest.getCedula());

        // 6. Armamos el paquete de respuesta (LoginResponseDTO)
        LoginResponseDTO response = new LoginResponseDTO();
        response.setToken(jwt);
        response.setCedula(usuario.getCedula());

        // Manejamos el apellido por si es nulo en tu base de datos
        String apellido = usuario.getApellido() != null ? " " + usuario.getApellido() : "";
        response.setNombre(usuario.getNombre() + apellido);

        response.setCargo(usuario.getId_cargo().getNombre_cargo()); // Ej: "VIGILANTE"

        // 7. Se lo enviamos al Front-end con un estado 200 OK
        return ResponseEntity.ok(response);
    }
}
