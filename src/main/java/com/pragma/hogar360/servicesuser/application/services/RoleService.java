package com.pragma.hogar360.servicesuser.application.services;

import com.pragma.hogar360.servicesuser.application.dto.request.SaveRoleRequest;
import com.pragma.hogar360.servicesuser.application.dto.response.SaveRoleResponse;

public interface RoleService {
    SaveRoleResponse saveRole(SaveRoleRequest request);
}
