package com.pragma.hogar360.servicesuser.application.dto.response;

public record JwtAuthenticationResponse(
        String token,
        Long userId,
        String name,
        String email,
        String rol
) {}
