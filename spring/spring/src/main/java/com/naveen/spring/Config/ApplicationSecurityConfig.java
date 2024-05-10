package com.naveen.spring.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // Enable method-level security
public class ApplicationSecurityConfig {

  @Autowired
  private UserDetailsService userDetailsService;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setUserDetailsService(userDetailsService);
    provider.setPasswordEncoder(passwordEncoder());
    return provider;
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    // Configure Spring Security
    http
      .csrf().disable() // Consider enabling CSRF protection for production
      .authorizeRequests()
      .requestMatchers("/login", "/logout").permitAll() // Allow login and logout freely
      .anyRequest().authenticated()
      .and()
      .formLogin()
      .loginPage("/login")
      .usernameParameter("username") // Customize username parameter (optional)
      .passwordParameter("password") // Customize password parameter (optional)
      .defaultSuccessUrl("/home")
      .and()
      .logout()
      .invalidateHttpSession(true)
      .clearAuthentication(true)
      .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
      .logoutSuccessUrl("/home"); // Redirect to home after logout

    return http.build();
  }
}
