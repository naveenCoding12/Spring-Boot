package com.naveen.keycloak_syngenity.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  private final JwtAuthConverter jwtAuthConverter;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
      .cors().configurationSource(corsConfigurationSource())
      .and()
      .csrf()
      .disable()
      .authorizeRequests()
      .requestMatchers("/api/v1/token/generate","/api/v1/demo/hello").permitAll()
      .anyRequest()
      .authenticated();

    http
      .oauth2ResourceServer()
      .jwt()
      .jwtAuthenticationConverter(jwtAuthConverter);

    http
      .sessionManagement()
      .sessionCreationPolicy(STATELESS);

    return http.build();
  }
  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(Arrays.asList("*")); // Allow all origins
    configuration.setAllowedMethods(Arrays.asList("*")); // Allow all methods
    configuration.setAllowedHeaders(Arrays.asList("*")); // Allow all headers

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);

    return source;
  }

}

