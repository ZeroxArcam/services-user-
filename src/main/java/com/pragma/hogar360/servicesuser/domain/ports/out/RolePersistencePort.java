package com.pragma.hogar360.servicesuser.domain.ports.out;

import com.pragma.hogar360.servicesuser.domain.model.RoleModel;

public interface RolePersistencePort {
    RoleModel saveRole(RoleModel role);
    boolean existsByName(String name);
}
