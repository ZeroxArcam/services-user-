package com.pragma.hogar360.servicesuser.application.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

public record SaveUserRequest(
        String name,
        String lastName,
        String idNumber,
        String phoneNumber,
        @JsonFormat(pattern = "yyyy-M-d") LocalDate birthDate,
        String email,
        String password,
        String role
) {
}
