package com.pragma.hogar360.servicesuser.domain.ports.in;

import com.pragma.hogar360.servicesuser.domain.model.RoleModel;

public interface RoleServicePort {
    RoleModel saveRole(RoleModel role);
}
