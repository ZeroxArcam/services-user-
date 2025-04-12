package com.pragma.hogar360.servicesuser.infrastructure.config.repositories.mysql;

import com.pragma.hogar360.servicesuser.domain.model.UserModel;
import com.pragma.hogar360.servicesuser.infrastructure.config.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByIdNumber(String idNumber);
    boolean existsByPhoneNumber(String phoneNumber);
    Optional<UserEntity> getUserById(Long id);
}
