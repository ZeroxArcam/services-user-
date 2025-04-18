package com.pragma.hogar360.servicesuser.infrastructure.rest.controller;

import com.pragma.hogar360.servicesuser.application.dto.request.SaveUserLoginRequest; // Asegúrate de usar tu DTO de login
import com.pragma.hogar360.servicesuser.application.dto.response.JwtAuthenticationResponse; // Asegúrate de usar tu DTO de respuesta JWT
import com.pragma.hogar360.servicesuser.application.services.AuthService; // Asegúrate de usar tu AuthService
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> login(@RequestBody SaveUserLoginRequest request) {
        JwtAuthenticationResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }
}