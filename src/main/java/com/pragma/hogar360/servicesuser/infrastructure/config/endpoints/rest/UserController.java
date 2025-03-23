package com.pragma.hogar360.servicesuser.infrastructure.config.endpoints.rest;

import com.pragma.hogar360.servicesuser.application.dto.request.SaveUserRequest;
import com.pragma.hogar360.servicesuser.application.dto.response.SaveUserResponse;
import com.pragma.hogar360.servicesuser.application.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @ApiResponse(responseCode = "201", description = "User created", content = @Content(schema = @Schema(implementation = SaveUserResponse.class), examples = @ExampleObject(value = "User created")))
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = String.class), examples = @ExampleObject(value = "\"Name cannot exceed 50 characters.\"\n \"Description cannot exceed 90 characters.\" ")))
    @ApiResponse(responseCode = "404", description = "Role not found", content = @Content(schema = @Schema(implementation = String.class), examples = @ExampleObject(value = "Role not found.")))
    @ApiResponse(responseCode = "409", description = "User already exists", content = @Content(schema = @Schema(implementation = String.class), examples = @ExampleObject(value = "Email, Phone number or id number already exists")))

    public ResponseEntity<SaveUserResponse> saveUser(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "User data to save",
                    required = true,
                    content = @Content(
                            examples = @ExampleObject(
                                    value = "{" +
                                            "\"name\": \"John\", " +
                                            "\"lastName\": \"Doe\", " +
                                            "\"idNumber\": \"1234567890\", " +
                                            "\"phoneNumber\": \"+155212344567\", " +
                                            "\"birthDate\": \"1990-01-15\", " +
                                            "\"email\": \"john.doe@example.com\", " +
                                            "\"password\": \"SecurePass123!\", " +
                                            "\"role\": \"Seller\"" +
                                            "}"
                            )       )       )
            @RequestBody SaveUserRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(request));
    }

}
