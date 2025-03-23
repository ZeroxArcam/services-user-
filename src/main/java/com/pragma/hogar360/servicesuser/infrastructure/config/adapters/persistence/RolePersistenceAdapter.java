package com.pragma.hogar360.servicesuser.infrastructure.config.adapters.persistence;

import com.pragma.hogar360.servicesuser.domain.model.RoleModel;
import com.pragma.hogar360.servicesuser.domain.ports.out.RolePersistencePort;
import com.pragma.hogar360.servicesuser.infrastructure.config.entities.RoleEntity;
import com.pragma.hogar360.servicesuser.infrastructure.config.mappers.RoleEntityMapper;
import com.pragma.hogar360.servicesuser.infrastructure.config.repositories.mysql.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class RolePersistenceAdapter implements RolePersistencePort {
    private final RoleEntityMapper roleEntityMapper;
    private final RoleRepository roleRepository;

    @Override
    public RoleModel saveRole(RoleModel role){
        RoleEntity roleEntity = roleEntityMapper.toEntity(role);
        RoleEntity saveEntity = roleRepository.save(roleEntity);
        return roleEntityMapper.toModel(saveEntity);
    }
    @Override
    public boolean existsByName(String name){
       // boolean exists = roleRepository.existsByName(name);
        //return exists;
        return roleRepository.existsByName(name);
    }
}
