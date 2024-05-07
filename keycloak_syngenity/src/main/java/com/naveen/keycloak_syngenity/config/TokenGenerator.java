package com.naveen.keycloak_syngenity.config;

import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.stereotype.Component;

@Component
public class TokenGenerator {

  public String generateToken() {
    String tokenEndpoint = "http://localhost:8080/realms/syngenity-project/protocol/openid-connect/token";
    String clientId = "naveen";
    String username = "eshop_user";
    String password = "9999";

    // Prepare form parameters
    MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
    formData.add("grant_type", "password");
    formData.add("client_id", clientId);
    formData.add("username", username);
    formData.add("password", password);

    // Prepare headers
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

    // Prepare request entity
    HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(formData, headers);

    // Create RestTemplate instance
    RestTemplate restTemplate = new RestTemplate();

    // Send POST request
    ResponseEntity<String> response = restTemplate.postForEntity(tokenEndpoint, requestEntity, String.class);

    if (response.getStatusCode() == HttpStatus.OK) {
      // Token generated successfully
      return response.getBody();
    } else {
      // Handle error response
      System.err.println("Failed to generate token. Status code: " + response.getStatusCodeValue());
      return null;
    }
  }

  public static void main(String[] args) {
    TokenGenerator tokenGenerator = new TokenGenerator();
    String token = tokenGenerator.generateToken();
    if (token != null) {
      System.out.println("Generated Token: " + token);
    }
  }
}

