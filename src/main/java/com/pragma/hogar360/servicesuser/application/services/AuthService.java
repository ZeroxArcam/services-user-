package com.pragma.hogar360.servicesuser.application.services;

import com.pragma.hogar360.servicesuser.application.dto.request.SaveUserLoginRequest;
import com.pragma.hogar360.servicesuser.application.dto.response.JwtAuthenticationResponse;

public interface AuthService {
    JwtAuthenticationResponse login(SaveUserLoginRequest request);
}