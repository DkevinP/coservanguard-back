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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

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
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                // Deshabilitamos CSRF porque usaremos JWT (Tokens) en lugar de Cookies de sesión
                .csrf(csrf -> csrf.disable())

                // Configuramos las reglas de las URLs
                .authorizeHttpRequests(auth -> auth
                        // ZONA PÚBLICA: Todos pueden intentar hacer login
                        .requestMatchers("/api/usuario/login").permitAll()
                        .requestMatchers("/api/usuario/crear-usuario").permitAll()
                        .requestMatchers("/api/cargo/crear-cargo").permitAll()
                        // ZONA ADMINISTRATIVA
                        .requestMatchers("/api/codigoqr", "/api/cliente/**", "/api/sede-cliente/**","/api/puesto/**","/api/usuario/**","/api/asignacion/**","/api/cargo/**").hasAnyRole("COORDINADOR", "SUPERVISOR","ADMINISTRADOR")

                        // ZONA OPERATIVA
                        // Suponiendo que la app consume endpoints que empiecen con /api/marcacion/
                        .requestMatchers("/api/marcacionqr/**").permitAll()
                        //ZONA DE DESVIO
                        .anyRequest().authenticated()
                )

                // Indicamos que nuestra API es Stateless (sin estado), no guarda sesión en memoria
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));


         http.addFilterBefore(jwtFilter, org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        // Permitir Angular
        configuration.setAllowedOrigins(Arrays.asList("http://coservanguard.eastus.cloudapp.azure.com"));
        //
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
