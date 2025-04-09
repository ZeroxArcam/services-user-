package com.pragma.hogar360.servicesuser.application.services.implementation;

import com.pragma.hogar360.servicesuser.application.dto.request.SaveUserRequest;
import com.pragma.hogar360.servicesuser.application.dto.response.SaveUserResponse;
import com.pragma.hogar360.servicesuser.application.mappers.UserDtoMapper;
import com.pragma.hogar360.servicesuser.application.services.UserService;
import com.pragma.hogar360.servicesuser.application.utils.constants.ApplicationConstants;
import com.pragma.hogar360.servicesuser.domain.ports.in.UserServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class UserServiceImplementation implements UserService {
    private final UserServicePort userServicePort;
    private final UserDtoMapper userDtoMapper;

    @Override
    public SaveUserResponse saveUser(SaveUserRequest request) {
        userServicePort.saveUser(userDtoMapper.requestToModel(request));
        return new SaveUserResponse(ApplicationConstants.SAVE_USER_RESPONSE_MESSAGE, LocalDateTime.now());
    }
}
