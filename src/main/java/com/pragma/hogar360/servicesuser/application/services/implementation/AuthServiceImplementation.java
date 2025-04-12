package com.pragma.hogar360.servicesuser.application.services.implementation;

import com.pragma.hogar360.servicesuser.application.dto.request.SaveUserLoginRequest;
import com.pragma.hogar360.servicesuser.application.dto.response.JwtAuthenticationResponse;
import com.pragma.hogar360.servicesuser.application.services.AuthService;
import com.pragma.hogar360.servicesuser.application.utils.constants.ApplicationConstants;
import com.pragma.hogar360.servicesuser.domain.model.UserModel;
import com.pragma.hogar360.servicesuser.domain.ports.in.UserServicePort;
import com.pragma.hogar360.servicesuser.infrastructure.config.security.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImplementation implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;
    private final UserServicePort userServicePort;

    @Override
    public JwtAuthenticationResponse login(SaveUserLoginRequest request) {
        log.info(ApplicationConstants.AUTH_ATTEMPT_LOGIN_MESSAGE, request.email());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );

        if (authentication.isAuthenticated()) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(request.email());
            Optional<UserModel> userOptional = userServicePort.getUserByEmail(userDetails.getUsername());

            if (userOptional.isPresent()) {
                UserModel user = userOptional.get();
                String token = jwtService.generateToken(user);

                log.info(ApplicationConstants.AUTH_SUCCESSFUL_LOGIN_MESSAGE);
                log.info(ApplicationConstants.AUTH_USER_INFO_MESSAGE);
                log.info(ApplicationConstants.AUTH_USER_ID_LOG, user.getId());
                log.info(ApplicationConstants.AUTH_USER_NAME_LOG, user.getName());
                log.info(ApplicationConstants.AUTH_USER_EMAIL_LOG, user.getEmail());
                log.info(ApplicationConstants.AUTH_USER_ROLE_LOG, user.getRoleName());

                return new JwtAuthenticationResponse(token, user.getId(), user.getName(), user.getEmail(), user.getRoleName());
            } else {
                log.error(ApplicationConstants.AUTH_USER_DETAILS_NOT_FOUND_ERROR);
                throw new AuthenticationException(ApplicationConstants.AUTH_USER_DETAILS_NOT_FOUND_EXCEPTION_MESSAGE) {};
            }
        } else {
            log.warn(ApplicationConstants.AUTH_INVALID_CREDENTIALS_WARNING, request.email());
            throw new org.springframework.security.authentication.BadCredentialsException(ApplicationConstants.AUTH_INVALID_CREDENTIALS_MESSAGE);
        }
    }
}