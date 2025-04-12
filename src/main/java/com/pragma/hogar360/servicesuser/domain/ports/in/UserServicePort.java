package com.pragma.hogar360.servicesuser.domain.ports.in;

import com.pragma.hogar360.servicesuser.domain.model.UserModel;

import java.util.Optional;

public interface UserServicePort {
    UserModel saveUser(UserModel user);
    Optional<UserModel> getUserByEmail(String email);
    Optional<UserModel> getUserById(Long id);
}
