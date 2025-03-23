package com.pragma.hogar360.servicesuser.infrastructure.config.endpoints.rest;


import com.pragma.hogar360.servicesuser.application.dto.request.SaveRoleRequest;
import com.pragma.hogar360.servicesuser.application.dto.request.SaveUserRequest;
import com.pragma.hogar360.servicesuser.application.dto.response.SaveRoleResponse;
import com.pragma.hogar360.servicesuser.application.dto.response.SaveUserResponse;
import com.pragma.hogar360.servicesuser.application.services.RoleService;
import com.pragma.hogar360.servicesuser.infrastructure.config.repositories.mysql.RoleRepository;
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
@RequestMapping("/api/v1/role")
@RequiredArgsConstructor
@Tag(name = "roles", description = "Operations related to roles")
public class RoleController {

    private final RoleRepository roleRepository;
    private final RoleService roleService;


    @PostMapping("/")
    @Operation(summary = "Save a new role", description = "Saves a new role in the system.")
    public ResponseEntity<SaveRoleResponse> saveRole(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Role data to save",required = true)
            @RequestBody SaveRoleRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(roleService.saveRole(request));
    }
}
