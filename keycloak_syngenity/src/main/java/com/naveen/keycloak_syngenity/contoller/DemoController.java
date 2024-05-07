package com.naveen.keycloak_syngenity.contoller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo")
public class DemoController {

  @PostMapping
  @PreAuthorize("hasRole('user')")
  public String hello() {
    return "Hello from Spring boot & Keycloak-user";
  }

  @GetMapping("/hello-2")
  @PreAuthorize("hasRole('admin')") // Assuming "ROLE_" is the prefix
  public String hello2() {
    return "Hello from Spring boot & Keycloak - ADMIN";
  }

  @GetMapping("/hello")
  public String hello3() {
    return "Hello from Spring boot & Keycloak - ADMIN";
  }
}
