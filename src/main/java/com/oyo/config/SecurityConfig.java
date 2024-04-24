package com.oyo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

@Configuration
public class SecurityConfig {

    private JWTRequestFilter jwtRequestFilter;

    public SecurityConfig(JWTRequestFilter jwtRequestFilter) {
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().disable();
        http.addFilterBefore(jwtRequestFilter , AuthorizationFilter.class);
//        http.authorizeHttpRequests()
//                .requestMatchers("/api/oyo/sign-up" , "/api/oyo/sign-in").permitAll()
//                .requestMatchers("/api/oyo/profile").hasAnyRole("ADMIN" , "USER")
//                .anyRequest().authenticated();

        http.authorizeHttpRequests().anyRequest().permitAll();
        return http.build();
    }
}