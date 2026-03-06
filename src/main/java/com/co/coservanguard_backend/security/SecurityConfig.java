package com.co.coservanguard_backend.security;

import com.co.coservanguard_backend.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private JwtFilter jwtFilter;

    public SecurityConfig (JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    // 1. BEAN PARA ENCRIPTAR CONTRASEÑAS
    // Spring usará esto para comparar la contraseña que envía el usuario con la de la BD
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 2. BEAN PARA MANEJAR LA AUTENTICACIÓN (Lo usaremos en el controlador de Login)
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    // 3. SECURITY FILTER CHAIN (Las reglas de las puertas)
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Deshabilitamos CSRF porque usaremos JWT (Tokens) en lugar de Cookies de sesión
                .csrf(csrf -> csrf.disable())

                // Configuramos las reglas de las URLs
                .authorizeHttpRequests(auth -> auth
                        // ZONA PÚBLICA: Todos pueden intentar hacer login
                        .requestMatchers("/api/usuario/login").permitAll()

                        // ZONA ADMINISTRATIVA (Para Angular): Solo Coordinador y Supervisor
                        // Suponiendo que tus endpoints de gestión empiecen con /api/admin/ o /api/cliente/
                        .requestMatchers("/api/admin/**", "/api/cliente/**", "/api/sede/**").hasAnyRole("COORDINADOR", "SUPERVISOR")

                        // ZONA OPERATIVA (Para Android): Solo Vigilante y Supervisor
                        // Suponiendo que la app consume endpoints que empiecen con /api/marcacion/
                        .requestMatchers("/api/marcacion/**", "/api/codigoqr/**").hasAnyRole("VIGILANTE", "SUPERVISOR")

                        // ZONA CREACIÓN USUARIOS: Solo el Coordinador puede crear más usuarios
                        .requestMatchers("/api/usuario/crear-usuario").permitAll()

                        // Cualquier otra URL que no esté arriba, requiere estar logueado al menos
                        .anyRequest().authenticated()
                )

                // Indicamos que nuestra API es Stateless (sin estado), no guarda sesión en memoria
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // NOTA: En un flujo completo con JWT, aquí se añade un filtro extra para leer el Token JWT
         http.addFilterBefore(jwtFilter, org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


}
