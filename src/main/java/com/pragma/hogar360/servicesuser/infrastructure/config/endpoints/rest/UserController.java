package com.pragma.hogar360.servicesuser.infrastructure.config.endpoints.rest;

import com.pragma.hogar360.servicesuser.application.dto.request.SaveUserRequest;
import com.pragma.hogar360.servicesuser.application.dto.response.SaveUserResponse;
import com.pragma.hogar360.servicesuser.application.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Tag(name = "users", description = "Operations related to users")
public class UserController {
    private final UserService userService;

    @PostMapping("/")
    @Operation(summary = "Save a new user", description = "Saves a new user in the system.")
    public ResponseEntity<SaveUserResponse> saveUser(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "User data to save",required = true)
            @RequestBody SaveUserRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(request));
    }

}
