package com.pragma.hogar360.servicesuser.domain.usecases;

import com.pragma.hogar360.servicesuser.domain.exceptions.RoleAlreadyExistException;
import com.pragma.hogar360.servicesuser.domain.model.RoleModel;
import com.pragma.hogar360.servicesuser.domain.ports.in.RoleServicePort;
import com.pragma.hogar360.servicesuser.domain.ports.out.RolePersistencePort;
import com.pragma.hogar360.servicesuser.domain.utils.constants.DomainConstants;
import com.pragma.hogar360.servicesuser.domain.utils.constants.Validation;

public class RoleUseCase implements RoleServicePort {
    private final RolePersistencePort rolePersistencePort;

    public RoleUseCase(RolePersistencePort rolePersistencePort) {
        this.rolePersistencePort = rolePersistencePort;
    }
    @Override
    public RoleModel saveRole(RoleModel role){
        Validation.validateName(role.getName());
        Validation.validateDescription(role.getDescription());
        existsByName(role.getName());
        return rolePersistencePort.saveRole(role);
    }

    public void existsByName(String name){
        if(rolePersistencePort.existsByName(name)){
            throw new RoleAlreadyExistException(DomainConstants.ROLE_ALREADY_EXISTS);
        }
    }
}
