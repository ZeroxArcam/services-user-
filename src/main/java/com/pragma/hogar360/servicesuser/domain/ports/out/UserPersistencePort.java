package com.pragma.hogar360.servicesuser.domain.ports.out;

import com.pragma.hogar360.servicesuser.domain.model.UserModel;

public interface UserPersistencePort {

    UserModel saveUser(UserModel user);
    boolean existsByEmail(String email);
    boolean existsByIdNumber(String idNumber);
    String encode(String password);
    boolean matches(String rawPassword, String encodedPassword);
   // String getPassword(String email);
    boolean existsByPhoneNumber(String phoneNumber);
}
