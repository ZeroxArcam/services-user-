package com.pragma.hogar360.servicesuser.domain.ports.out;

import com.pragma.hogar360.servicesuser.domain.model.UserModel;

import java.util.Optional;

public interface UserPersistencePort {

    Optional<UserModel> findByEmail(String email);
    UserModel saveUser(UserModel user);
    boolean existsByEmail(String email);
    boolean existsByIdNumber(String idNumber);
    String encode(String password);
    boolean matches(String rawPassword, String encodedPassword);
    Optional<UserModel> getUserById(Long id);
    boolean existsByPhoneNumber(String phoneNumber);
}
