package com.pragma.hogar360.servicesuser.commons.configurations.beans;

import com.pragma.hogar360.servicesuser.domain.ports.in.RoleServicePort;
import com.pragma.hogar360.servicesuser.domain.ports.in.UserServicePort;
import com.pragma.hogar360.servicesuser.domain.ports.out.RolePersistencePort;
import com.pragma.hogar360.servicesuser.domain.ports.out.UserPersistencePort;
import com.pragma.hogar360.servicesuser.domain.usecases.RoleUseCase;
import com.pragma.hogar360.servicesuser.domain.usecases.UserUseCase;
import com.pragma.hogar360.servicesuser.infrastructure.config.adapters.persistence.RolePersistenceAdapter;
import com.pragma.hogar360.servicesuser.infrastructure.config.adapters.persistence.UserPersistenceAdapter;
import com.pragma.hogar360.servicesuser.infrastructure.config.mappers.RoleEntityMapper;
import com.pragma.hogar360.servicesuser.infrastructure.config.mappers.UserEntityMapper;
import com.pragma.hogar360.servicesuser.infrastructure.config.repositories.mysql.RoleRepository;
import com.pragma.hogar360.servicesuser.infrastructure.config.repositories.mysql.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class BeanConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())  // Deshabilita CSRF para pruebas (opcional)
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // Permite todas las peticiones (ajústalo después)
                );
        return http.build();
    }

    @Bean
    public UserServicePort userServicePort(UserPersistencePort userPersistencePort, RolePersistencePort rolePersistencePort) {
        return new UserUseCase(userPersistencePort, rolePersistencePort);
    }

    @Bean
    public UserPersistencePort userPersistencePort(UserEntityMapper userEntityMapper, UserRepository userRepository, RoleRepository roleRepository) {
        return new UserPersistenceAdapter(userEntityMapper, userRepository, roleRepository);
    }
    @Bean
    public RoleServicePort roleServicePort(RolePersistencePort rolePersistencePort) {
        return new RoleUseCase(rolePersistencePort);
    }
    @Bean
    public RolePersistencePort rolePersistencePort(RoleEntityMapper roleEntityMapper, RoleRepository roleRepository) {
        return new RolePersistenceAdapter(roleEntityMapper,roleRepository);
    }
}