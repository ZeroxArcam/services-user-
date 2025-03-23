package com.pragma.hogar360.servicesuser.domain.ports.in;

import com.pragma.hogar360.servicesuser.domain.model.UserModel;

public interface UserServicePort {
    UserModel saveUser(UserModel user);
}
