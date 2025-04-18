package com.pragma.hogar360.servicesuser.infrastructure.config.adapters.persistence;

import com.pragma.hogar360.servicesuser.domain.model.UserModel;
import com.pragma.hogar360.servicesuser.domain.ports.out.UserPersistencePort;
import com.pragma.hogar360.servicesuser.infrastructure.config.entities.RoleEntity;
import com.pragma.hogar360.servicesuser.infrastructure.config.entities.UserEntity;
import com.pragma.hogar360.servicesuser.infrastructure.config.mappers.UserEntityMapper;
import com.pragma.hogar360.servicesuser.infrastructure.config.repositories.mysql.RoleRepository;
import com.pragma.hogar360.servicesuser.infrastructure.config.repositories.mysql.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserPersistenceAdapter implements UserPersistencePort {

    private final UserEntityMapper userEntityMapper;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserModel saveUser(UserModel user) {
        RoleEntity roleEntity = roleRepository.findByName(user.getRole().getName());
        UserEntity userEntity = userEntityMapper.toEntity(user,roleEntity);
        userEntity.setRole(roleEntity);
        log.info("🟡 Saving user with email: {}", userEntity.getEmail());
        UserEntity savedEntity = userRepository.save(userEntity);
        log.info("✅ User saved with ID: {}", savedEntity.getId());

        return userEntityMapper.toModel(savedEntity);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public String encode(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public boolean matches(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
    @Override
    public boolean existsByIdNumber(String idNumber) {
        return userRepository.existsByIdNumber(idNumber);
    }
    @Override
    public boolean existsByPhoneNumber(String phoneNumber) {
        return userRepository.existsByPhoneNumber(phoneNumber);
    }
    @Override
    public Optional<UserModel> findByEmail(String email) {
        Optional<UserEntity> userEntityOptional = userRepository.findByEmail(email);
        return userEntityOptional.map(userEntityMapper::toModel);
    }
    public Optional<UserModel> getUserById(Long id){
        Optional<UserEntity> userEntityOptional = userRepository.findById(id);
        return userEntityOptional.map(userEntityMapper::toModel);
    }

//    @Override
//    public String getPassword(String email) {
//        return userRepository.findByEmail(email)
//                .map(UserEntity::getPassword)
//                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con el email: " + email));
//    }



}
