package com.platform.doctic_project;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()  // Deshabilitar CSRF para pruebas
            .authorizeHttpRequests((auth) -> auth
                .anyRequest().permitAll()  // Permitir todas las solicitudes sin autenticación
            );

        return http.build();
    }
}
