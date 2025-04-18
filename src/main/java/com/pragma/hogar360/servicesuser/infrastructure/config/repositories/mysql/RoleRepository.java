package com.pragma.hogar360.servicesuser.infrastructure.config.repositories.mysql;

import com.pragma.hogar360.servicesuser.infrastructure.config.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    RoleEntity findByName(String name);
    boolean existsByName(String name);
}
