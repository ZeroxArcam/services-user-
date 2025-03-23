package com.pragma.hogar360.servicesuser.domain.usecases;

import com.pragma.hogar360.servicesuser.domain.exceptions.*;
import com.pragma.hogar360.servicesuser.domain.model.RoleModel;
import com.pragma.hogar360.servicesuser.domain.model.UserModel;
import com.pragma.hogar360.servicesuser.domain.ports.in.UserServicePort;
import com.pragma.hogar360.servicesuser.domain.ports.out.RolePersistencePort;
import com.pragma.hogar360.servicesuser.domain.ports.out.UserPersistencePort;
import com.pragma.hogar360.servicesuser.domain.utils.constants.DomainConstants;
import com.pragma.hogar360.servicesuser.domain.utils.constants.Validation;
import java.time.LocalDate;
import java.time.Period;

public class UserUseCase implements UserServicePort {
    private final UserPersistencePort userPersistencePort;
    private final RolePersistencePort rolePersistencePort;

    public UserUseCase(UserPersistencePort userPersistencePort, RolePersistencePort rolePersistencePort) {
        this.userPersistencePort = userPersistencePort;
        this.rolePersistencePort = rolePersistencePort;
    }
    @Override
    public UserModel saveUser(UserModel user){
        Validation.validateName(user.getName());
        Validation.validateLastName(user.getLastName());
        validateRoleExists(user.getRole());
        validateEmail(user);
        validatePhone(user);
        validateIdNumber(user.getIdNumber());
        validateAge(user.getBirthDate());
        existsByEmail(user);
        existByIdNumber(user.getIdNumber());
        existByPhoneNumber(user.getPhoneNumber());
        System.out.println("Contraseña original: " + user.getPassword());
        String encryptedPassword=userPersistencePort.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        System.out.println("Contraseña encriptada: " + encryptedPassword);
        return userPersistencePort.saveUser(user);
    }

    private void validateRoleExists(RoleModel role) {
        if (!rolePersistencePort.existsByName(role.getName())) {
            throw new RoleNotFoundException(DomainConstants.ROLE_NOT_FOUND);
        }
    }
    private void validateEmail(UserModel user) {
        if(!Validation.isValidEmail(user.getEmail())){
            throw new InvalidEmailException(DomainConstants.INVALID_EMAIL);
        }
    }
    private void validatePhone(UserModel user) {
        String phone = user.getPhoneNumber();
        if (phone == null || !Validation.isValidPhone(phone) || phone.length() > DomainConstants.PHONE_LENGTH) {
            throw new InvalidPhoneNumberException(DomainConstants.INVALID_PHONE_NUMBER);
        }
    }

    private void validateIdNumber(String idNumber) {
        if (idNumber == null || !idNumber.matches("\\d+")) {
            throw new InvalidIdentificationException(DomainConstants.INVALID_ID_NUMBER);
        }
    }
    private void validateAge(LocalDate birthDate) {
        if (birthDate == null || birthDate.isAfter(LocalDate.now())) {
            throw new InvalidParameterException(DomainConstants.INVALID_DATE_OF_BIRTH);
        }
        LocalDate today = LocalDate.now();
        Period age = Period.between(birthDate, today);
        if (age.getYears() < DomainConstants.MIN_AGE || birthDate.getYear() < DomainConstants.MAX_AGE) {
            throw new InvalidAgeException(DomainConstants.INVALID_AGE);
        }
    }
    private void existsByEmail(UserModel user){
        if (userPersistencePort.existsByEmail(user.getEmail())) {
            throw new EmailAlreadyExistException(DomainConstants.EMAIL_ALREADY_EXISTS);
        }
    }
    private void existByIdNumber(String idNumber){
        if (userPersistencePort.existsByIdNumber(idNumber)) {
            throw new IdNumberAlreadyExistException(DomainConstants.ID_NUMBER_ALREADY_EXISTS);
        }
    }
    private void existByPhoneNumber(String phoneNumber) {
        if (userPersistencePort.existsByPhoneNumber(phoneNumber)) {
            throw new PhoneAlreadyExistException(DomainConstants.PHONE_NUMBER_ALREADY_EXISTS);
        }
    }





}
