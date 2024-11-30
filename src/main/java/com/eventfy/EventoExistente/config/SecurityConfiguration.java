package com.eventfy.EventoExistente.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;

@Configuration
public class SecurityConfiguration {

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("https://event-fy.vercel.app", "http://localhost:4200"));  // Domínios permitidos
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));  // Métodos permitidos
        config.setAllowedHeaders(List.of("Authorization", "Content-Type"));  // Cabeçalhos permitidos
        config.setAllowCredentials(true);  // Permitir credenciais (cookies, tokens)

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);  // Aplica a configuração para todas as rotas
        return source;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))  // Habilitar configuração de CORS
                .authorizeRequests(authz -> authz
                        .requestMatchers("/eventos/organizadorEvents").permitAll()  // Permitir acesso sem autenticação a esse endpoint específico
                        .anyRequest().authenticated()  // Requer autenticação para outras rotas
                )
                .csrf(csrf -> csrf.disable());  // Desabilitar CSRF (se não estiver usando)

        return http.build();
    }
}
