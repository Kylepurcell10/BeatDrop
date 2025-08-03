package com.kpurcell.BeatDrop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig
{
    @Bean
    public PasswordEncoder passwordEncoder() {
        // Define BCryptPasswordEncoder for secure password hashing
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        http.csrf(csrf -> csrf.disable()) // Disable CSRF for stateless REST APIs using JWT
                .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/api/auth/login").permitAll() // Permit login endpoint
                    .requestMatchers("/api/users/addUser").permitAll() // Permit login endpoint
                    .anyRequest().authenticated() // Require authentication for all other endpoints
                );
        // Note: Add JWT filter later for token validation
        return http.build();
    }
}
