package com.naveen.keycloak_syngenity.contoller;

import com.naveen.keycloak_syngenity.config.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/token")
public class TokenController {

  private final TokenGenerator tokenGenerator;

  @Autowired
  public TokenController(TokenGenerator tokenGenerator) {
    this.tokenGenerator = tokenGenerator;
  }

  @GetMapping("/generate")
  public String generateToken() {
    String token = tokenGenerator.generateToken();
    if (token != null) {
      return token;
    } else {
      return "Failed to generate token";
    }
  }
}
