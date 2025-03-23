package com.pragma.hogar360.servicesuser.domain.usecases;

import com.pragma.hogar360.servicesuser.domain.exceptions.*;
import com.pragma.hogar360.servicesuser.domain.model.RoleModel;
import com.pragma.hogar360.servicesuser.domain.model.UserModel;
import com.pragma.hogar360.servicesuser.domain.ports.out.RolePersistencePort;
import com.pragma.hogar360.servicesuser.domain.ports.out.UserPersistencePort;
import com.pragma.hogar360.servicesuser.factory.RoleModelFactory;
import com.pragma.hogar360.servicesuser.factory.UserModelFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserUseCaseTest {

    @Mock
    private UserPersistencePort userPersistencePort;

    @Mock
    private RolePersistencePort rolePersistencePort;

    @InjectMocks
    private UserUseCase userUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveUser_shouldSaveUserWhenValid() {
        UserModel userModel = UserModelFactory.createDefaultUserModel();
        when(rolePersistencePort.existsByName(userModel.getRole().getName())).thenReturn(true);
        when(userPersistencePort.existsByEmail(userModel.getEmail())).thenReturn(false);
        when(userPersistencePort.existsByIdNumber(userModel.getIdNumber())).thenReturn(false);
        when(userPersistencePort.existsByPhoneNumber(userModel.getPhoneNumber())).thenReturn(false);
        when(userPersistencePort.encode(userModel.getPassword())).thenReturn("encodedPassword");
        when(userPersistencePort.saveUser(any(UserModel.class))).thenReturn(userModel);

        UserModel savedUser = userUseCase.saveUser(userModel);

        assertNotNull(savedUser);
        assertEquals(userModel, savedUser);
        verify(userPersistencePort, times(1)).saveUser(any(UserModel.class));
    }

    @Test
    void saveUser_shouldThrowExceptionWhenRoleNotFound() {
        UserModel userModel = UserModelFactory.createDefaultUserModel();
        when(rolePersistencePort.existsByName(userModel.getRole().getName())).thenReturn(false);

        assertThrows(RoleNotFoundException.class, () -> userUseCase.saveUser(userModel));
    }

    @Test
    void saveUser_shouldThrowExceptionWhenEmailInvalid() {
        UserModel userModel = UserModelFactory.createDefaultUserModel();
        userModel.setEmail("invalid-email");
        when(rolePersistencePort.existsByName(userModel.getRole().getName())).thenReturn(true);

        assertThrows(InvalidEmailException.class, () -> userUseCase.saveUser(userModel));
    }

    @Test
    void saveUser_shouldThrowExceptionWhenPhoneInvalid() {
        UserModel userModel = UserModelFactory.createDefaultUserModel();
        userModel.setPhoneNumber("invalid-phone");
        when(rolePersistencePort.existsByName(userModel.getRole().getName())).thenReturn(true);

        assertThrows(InvalidPhoneNumberException.class, () -> userUseCase.saveUser(userModel));
    }

    @Test
    void saveUser_shouldThrowExceptionWhenIdNumberInvalid() {
        UserModel userModel = UserModelFactory.createDefaultUserModel();
        userModel.setIdNumber("invalid-id");
        when(rolePersistencePort.existsByName(userModel.getRole().getName())).thenReturn(true);

        assertThrows(InvalidIdentificationException.class, () -> userUseCase.saveUser(userModel));
    }

    @Test
    void saveUser_shouldThrowExceptionWhenAgeInvalid() {
        UserModel userModel = UserModelFactory.createDefaultUserModel();
        userModel.setBirthDate(LocalDate.now().plusYears(1));
        when(rolePersistencePort.existsByName(userModel.getRole().getName())).thenReturn(true);

        assertThrows(InvalidParameterException.class, () -> userUseCase.saveUser(userModel));
    }

    @Test
    void saveUser_shouldThrowExceptionWhenEmailAlreadyExists() {
        UserModel userModel = UserModelFactory.createDefaultUserModel();
        when(rolePersistencePort.existsByName(userModel.getRole().getName())).thenReturn(true);
        when(userPersistencePort.existsByEmail(userModel.getEmail())).thenReturn(true);

        assertThrows(EmailAlreadyExistException.class, () -> userUseCase.saveUser(userModel));
    }

    @Test
    void saveUser_shouldThrowExceptionWhenIdNumberAlreadyExists() {
        UserModel userModel = UserModelFactory.createDefaultUserModel();
        when(rolePersistencePort.existsByName(userModel.getRole().getName())).thenReturn(true);
        when(userPersistencePort.existsByEmail(userModel.getEmail())).thenReturn(false);
        when(userPersistencePort.existsByIdNumber(userModel.getIdNumber())).thenReturn(true);

        assertThrows(IdNumberAlreadyExistException.class, () -> userUseCase.saveUser(userModel));
    }

    @Test
    void saveUser_shouldThrowExceptionWhenPhoneNumberAlreadyExists() {
        UserModel userModel = UserModelFactory.createDefaultUserModel();
        when(rolePersistencePort.existsByName(userModel.getRole().getName())).thenReturn(true);
        when(userPersistencePort.existsByEmail(userModel.getEmail())).thenReturn(false);
        when(userPersistencePort.existsByIdNumber(userModel.getIdNumber())).thenReturn(false);
        when(userPersistencePort.existsByPhoneNumber(userModel.getPhoneNumber())).thenReturn(true);

        assertThrows(PhoneAlreadyExistException.class, () -> userUseCase.saveUser(userModel));
    }

    @Test
    void saveUser_shouldThrowExceptionWhenAgeTooYoung() {
        UserModel userModel = UserModelFactory.createDefaultUserModel();
        LocalDate birthDate = LocalDate.now().minusYears(15); // Edad menor que 18
        userModel.setBirthDate(birthDate);

        when(rolePersistencePort.existsByName(userModel.getRole().getName())).thenReturn(true);
        when(userPersistencePort.existsByEmail(userModel.getEmail())).thenReturn(false);
        when(userPersistencePort.existsByIdNumber(userModel.getIdNumber())).thenReturn(false);
        when(userPersistencePort.existsByPhoneNumber(userModel.getPhoneNumber())).thenReturn(false);

        assertThrows(InvalidAgeException.class, () -> userUseCase.saveUser(userModel));
    }

    @Test
    void saveUser_shouldThrowExceptionWhenBirthYearTooOld() {
        UserModel userModel = UserModelFactory.createDefaultUserModel();
        LocalDate birthDate = LocalDate.of(1800, 1, 1); // Nacido antes de MAX_AGE
        userModel.setBirthDate(birthDate);

        when(rolePersistencePort.existsByName(userModel.getRole().getName())).thenReturn(true);
        when(userPersistencePort.existsByEmail(userModel.getEmail())).thenReturn(false);
        when(userPersistencePort.existsByIdNumber(userModel.getIdNumber())).thenReturn(false);
        when(userPersistencePort.existsByPhoneNumber(userModel.getPhoneNumber())).thenReturn(false);

        assertThrows(InvalidAgeException.class, () -> userUseCase.saveUser(userModel));
    }


}