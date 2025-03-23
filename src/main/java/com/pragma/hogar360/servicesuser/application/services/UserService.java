package com.pragma.hogar360.servicesuser.application.services;

import com.pragma.hogar360.servicesuser.application.dto.request.SaveUserRequest;
import com.pragma.hogar360.servicesuser.application.dto.response.SaveUserResponse;

public interface UserService {
    SaveUserResponse saveUser(SaveUserRequest request);
}
