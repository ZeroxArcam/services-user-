package com.pragma.hogar360.servicesuser.application.services.implementation;

import com.pragma.hogar360.servicesuser.application.dto.request.SaveRoleRequest;
import com.pragma.hogar360.servicesuser.application.dto.response.SaveRoleResponse;
import com.pragma.hogar360.servicesuser.application.mappers.RoleDtoMapper;
import com.pragma.hogar360.servicesuser.application.services.RoleService;
import com.pragma.hogar360.servicesuser.domain.ports.in.RoleServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class RoleSeriviceImplementation implements RoleService {
    private final RoleServicePort roleServicePort;
    private final RoleDtoMapper roleDtoMapper;

    @Override
    public SaveRoleResponse saveRole(SaveRoleRequest request){
         roleServicePort.saveRole(roleDtoMapper.requesToModel(request));
         return new SaveRoleResponse("Role created", LocalDateTime.now());
    }

}
