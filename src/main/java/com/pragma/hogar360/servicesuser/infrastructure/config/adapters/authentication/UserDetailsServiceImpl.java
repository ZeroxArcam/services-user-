package com.pragma.hogar360.servicesuser.infrastructure.config.adapters.authentication;

import com.pragma.hogar360.servicesuser.domain.model.UserModel;
import com.pragma.hogar360.servicesuser.domain.ports.in.UserServicePort;
import com.pragma.hogar360.servicesuser.infrastructure.config.utils.constants.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements ExtendedUserDetailsService {

    private final UserServicePort userServicePort;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserModel user = userServicePort.getUserByEmail(email)
                .orElseThrow(() -> {
                    log.warn(Constants.USER_NOT_FOUND_BY_EMAIL_WARNING, email);
                    return new UsernameNotFoundException(Constants.USER_NOT_FOUND_BY_EMAIL_EXCEPTION + email);
                });

        String roleName = user.getRoleName().toUpperCase();
        List<SimpleGrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(Constants.ROLE_PREFIX + roleName)); // Usa la constante

        log.info(Constants.USER_LOADED_SUCCESSFULLY, user.getEmail());
        log.info(Constants.USER_ROLE_LOG, user.getRoleName());

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                authorities
        );
    }

    public UserDetails loadUserById(Long id) {
        UserModel user = userServicePort.getUserById(id)
                .orElseThrow(() -> {
                    log.warn(Constants.USER_NOT_FOUND_BY_ID_WARNING, id);
                    return new UsernameNotFoundException(Constants.USER_NOT_FOUND_BY_ID_EXCEPTION + id);
                });

        String roleName = user.getRoleName().toUpperCase();
        List<SimpleGrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(Constants.ROLE_PREFIX + roleName));

        log.info(Constants.USER_LOADED_SUCCESSFULLY_BY_ID, id);
        log.info(Constants.USER_ROLE_LOG, user.getRoleName());

        return new org.springframework.security.core.userdetails.User(
                String.valueOf(user.getId()),
                user.getPassword(),
                authorities
        );
    }
}