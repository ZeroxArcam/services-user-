package com.pragma.hogar360.servicesuser.infrastructure.config.adapters.authentication;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface ExtendedUserDetailsService extends UserDetailsService {
    UserDetails loadUserById(Long id) throws UsernameNotFoundException;
}