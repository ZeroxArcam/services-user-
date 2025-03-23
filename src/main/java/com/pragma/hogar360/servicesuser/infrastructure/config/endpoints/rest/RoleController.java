package com.pragma.hogar360.servicesuser.infrastructure.config.endpoints.rest;


import com.pragma.hogar360.servicesuser.application.dto.request.SaveRoleRequest;
import com.pragma.hogar360.servicesuser.application.dto.request.SaveUserRequest;
import com.pragma.hogar360.servicesuser.application.dto.response.SaveRoleResponse;
import com.pragma.hogar360.servicesuser.application.dto.response.SaveUserResponse;
import com.pragma.hogar360.servicesuser.application.services.RoleService;
import com.pragma.hogar360.servicesuser.infrastructure.config.repositories.mysql.RoleRepository;
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
@RequestMapping("/api/v1/role")
@RequiredArgsConstructor
@Tag(name = "roles", description = "Operations related to roles")
public class RoleController {

    private final RoleRepository roleRepository;
    private final RoleService roleService;


    @PostMapping("/")
    @Operation(summary = "Save a new role", description = "Saves a new role in the system.")
    @ApiResponse(responseCode = "201", description = "Role created", content = @Content(schema = @Schema(implementation = SaveRoleResponse.class), examples = @ExampleObject(value = "Role created")))
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = String.class), examples = @ExampleObject(value = "\"Name cannot exceed 50 characters.\"\n \"Description cannot exceed 90 characters.\" ")))
    @ApiResponse(responseCode = "409", description = "Role already exists", content = @Content(schema = @Schema(implementation = String.class), examples = @ExampleObject(value = "Role 'Seller' already exists")))
    public ResponseEntity<SaveRoleResponse> saveRole(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Role data to save",
                    required = true,
                    content = @Content(
                            examples = @ExampleObject(
                                    value = "{\"Name\": \"Seller\", \"Description\": \"Real state agent\"}"
            )       )       )
            @RequestBody SaveRoleRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(roleService.saveRole(request));
    }
}
