package com.pragma.hogar360.servicesuser.domain.usecases;

import com.pragma.hogar360.servicesuser.domain.exceptions.*;
import com.pragma.hogar360.servicesuser.domain.model.RoleModel;
import com.pragma.hogar360.servicesuser.domain.model.UserModel;
import com.pragma.hogar360.servicesuser.domain.ports.in.UserServicePort;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserUseCaseTest {

    @Mock
    private UserPersistencePort userPersistencePort;

    @InjectMocks
    private UserUseCase userUseCase;

    @Mock
    private RolePersistencePort rolePersistencePort;


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
    void getUserByEmail_shouldReturnUserWhenEmailExists(){
        String email="valid_email@example.com";
        UserModel userModel = UserModelFactory.createUserModelWithEmail(email);
        when(userPersistencePort.findByEmail(email)).thenReturn(Optional.of(userModel));

        Optional<UserModel> result = userUseCase.getUserByEmail(email);

        assertTrue(result.isPresent());
        assertEquals(userModel,result.get());
        verify(userPersistencePort,times(1)).findByEmail(email);
    }
    @Test
    void getUserByEmail_shouldReturnEmptyOptionalWhenEmailDoesNotExist() {
        String email = "nonexistent@example.com";
        when(userPersistencePort.findByEmail(email)).thenReturn(Optional.empty());

        Optional<UserModel> result = userUseCase.getUserByEmail(email);

        assertFalse(result.isPresent());
        verify(userPersistencePort, times(1)).findByEmail(email);
    }



    @Test
    void getUserByEmail_shouldReturnEmptyOptionalWhenEmailIsNull() {
        String email = null;
        when(userPersistencePort.findByEmail(null)).thenReturn(Optional.empty());

        Optional<UserModel> result = userUseCase.getUserByEmail(email);

        assertFalse(result.isPresent());
        verify(userPersistencePort, times(1)).findByEmail(null);
    }

    @Test
    void getUserByEmail_shouldReturnEmptyOptionalWhenEmailIsEmpty() {
        String email = "";
        when(userPersistencePort.findByEmail("")).thenReturn(Optional.empty());

        Optional<UserModel> result = userUseCase.getUserByEmail(email);

        assertFalse(result.isPresent());
        verify(userPersistencePort, times(1)).findByEmail("");
    }

    @Test
    void getUserById_shouldReturnUserWhenIdExists() {
        // Arrange
        Long id = 123L;
        UserModel userModel = UserModelFactory.createUserModelWithId(id);
        when(userPersistencePort.getUserById(id)).thenReturn(Optional.of(userModel));

        // Act
        Optional<UserModel> result = userUseCase.getUserById(id);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(userModel, result.get());
        verify(userPersistencePort, times(1)).getUserById(id);
    }

    @Test
    void getUserById_shouldReturnEmptyOptionalWhenIdDoesNotExist() {
        // Arrange
        Long id = 456L;
        when(userPersistencePort.getUserById(id)).thenReturn(Optional.empty());

        // Act
        Optional<UserModel> result = userUseCase.getUserById(id);

        // Assert
        assertFalse(result.isPresent());
        verify(userPersistencePort, times(1)).getUserById(id);
    }


    @Test
    void getUserById_shouldReturnEmptyOptionalWhenIdIsNull() {
        // Arrange
        Long id = null;
        when(userPersistencePort.getUserById(null)).thenReturn(Optional.empty());

        // Act
        Optional<UserModel> result = userUseCase.getUserById(id);

        // Assert
        assertFalse(result.isPresent());
        verify(userPersistencePort, times(1)).getUserById(null);
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
    @Test
    void saveUser_shouldThrowExceptionWhenBirthDateIsInFuture() {
        UserModel userModel = UserModelFactory.createDefaultUserModel();
        userModel.setBirthDate(LocalDate.now().plusDays(1)); // Fecha futura

        when(rolePersistencePort.existsByName(userModel.getRole().getName())).thenReturn(true);
        when(userPersistencePort.existsByEmail(userModel.getEmail())).thenReturn(false);
        when(userPersistencePort.existsByIdNumber(userModel.getIdNumber())).thenReturn(false);
        when(userPersistencePort.existsByPhoneNumber(userModel.getPhoneNumber())).thenReturn(false);

        assertThrows(InvalidParameterException.class, () -> userUseCase.saveUser(userModel));
    }
    @Test
    void saveUser_shouldThrowNullPointerExceptionWhenNameIsNull() {
        UserModel userModel = UserModelFactory.createDefaultUserModel();
        userModel.setName(null);
        when(rolePersistencePort.existsByName(userModel.getRole().getName())).thenReturn(true);

        assertThrows(NullPointerException.class, () -> userUseCase.saveUser(userModel));
    }

    @Test
    void saveUser_shouldThrowEmptyNameExceptionWhenNameIsEmpty() {
        UserModel userModel = UserModelFactory.createDefaultUserModel();
        userModel.setName("");
        when(rolePersistencePort.existsByName(userModel.getRole().getName())).thenReturn(true);

        assertThrows(EmptyNameException.class, () -> userUseCase.saveUser(userModel));
    }

    @Test
    void saveUser_shouldThrowNullPointerExceptionWhenLastNameIsNull() {
        UserModel userModel = UserModelFactory.createDefaultUserModel();
        userModel.setLastName(null);
        when(rolePersistencePort.existsByName(userModel.getRole().getName())).thenReturn(true);

        assertThrows(NullPointerException.class, () -> userUseCase.saveUser(userModel));
    }


    @Test
    void saveUser_shouldThrowExceptionWhenPhoneNumberContainsLetters() {
        UserModel userModel = UserModelFactory.createDefaultUserModel();
        userModel.setPhoneNumber("123abc456");
        when(rolePersistencePort.existsByName(userModel.getRole().getName())).thenReturn(true);

        assertThrows(InvalidPhoneNumberException.class, () -> userUseCase.saveUser(userModel));
    }

    @Test
    void saveUser_shouldThrowExceptionWhenIdNumberContainsLetters() {
        UserModel userModel = UserModelFactory.createDefaultUserModel();
        userModel.setIdNumber("123abc456");
        when(rolePersistencePort.existsByName(userModel.getRole().getName())).thenReturn(true);

        assertThrows(InvalidIdentificationException.class, () -> userUseCase.saveUser(userModel));
    }

    @Test
    void saveUser_shouldSaveUserWhenAgeIsMinimum() {
        UserModel userModel = UserModelFactory.createDefaultUserModel();
        userModel.setBirthDate(LocalDate.now().minusYears(18));
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


}