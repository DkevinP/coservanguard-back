package com.co.coservanguard_backend.security;

import com.co.coservanguard_backend.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod; // Importante para permitir el OPTIONS del CORS
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    // 1. BEAN PARA ENCRIPTAR CONTRASEÑAS
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 2. BEAN PARA MANEJAR LA AUTENTICACIÓN
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    // 3. SECURITY FILTER CHAIN (Las reglas de las puertas)
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                // Deshabilitamos CSRF porque usaremos JWT (Tokens)
                .csrf(csrf -> csrf.disable())

                // Configuramos las reglas de las URLs
                .authorizeHttpRequests(auth -> auth
                        // 1. PERMITIR LA PETICIÓN FANTASMA DEL NAVEGADOR (CORS PREFLIGHT)
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                        // ZONA PÚBLICA: Todos pueden intentar hacer login (Con y sin /api para Nginx)
                        .requestMatchers("/api/usuario/login", "/usuario/login").permitAll()
                        .requestMatchers("/api/usuario/crear-usuario", "/usuario/crear-usuario").permitAll()
                        .requestMatchers("/api/cargo/crear-cargo", "/cargo/crear-cargo").permitAll()
                        .requestMatchers("/error").permitAll()

                        // ZONA ADMINISTRATIVA (Con y sin /api para Nginx)
                        .requestMatchers("/api/codigoqr", "/codigoqr",
                                "/api/cliente/**", "/cliente/**",
                                "/api/sede-cliente/**", "/sede-cliente/**",
                                "/api/puesto/**", "/puesto/**",
                                "/api/usuario/**", "/usuario/**",
                                "/api/asignacion/**", "/asignacion/**",
                                "/api/cargo/**", "/cargo/**")
                        .hasAnyRole("COORDINADOR", "SUPERVISOR", "ADMINISTRADOR")

                        // ZONA OPERATIVA (Con y sin /api para Nginx)
                        .requestMatchers("/api/marcacionqr/**", "/marcacionqr/**").permitAll()

                        // ZONA DE DESVÍO: Cualquier otra petición debe estar autenticada
                        .anyRequest().authenticated()
                )

                // Indicamos que nuestra API es Stateless
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // Añadimos nuestro filtro JWT antes del filtro estándar de Spring Security
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        // Permitir Angular en producción y local
        configuration.setAllowedOrigins(Arrays.asList(
                "http://coservanguard.eastus.cloudapp.azure.com",
                "http://localhost:4200"
        ));

        // Permitir métodos
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));

        // Permitir cabeceras (Token)
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}