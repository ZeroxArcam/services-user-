package com.pragma.hogar360.servicesuser.domain.usecases;

import com.pragma.hogar360.servicesuser.domain.exceptions.RoleAlreadyExistException;
import com.pragma.hogar360.servicesuser.domain.model.RoleModel;
import com.pragma.hogar360.servicesuser.domain.ports.out.RolePersistencePort;
import com.pragma.hogar360.servicesuser.factory.RoleModelFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RoleUseCaseTest {

    @Mock
    private RolePersistencePort rolePersistencePort;

    @InjectMocks
    private RoleUseCase roleUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveRole_shouldSaveRoleWhenRoleDoesNotExist() {
        // Arrange
        RoleModel roleModel = RoleModelFactory.createDefaultRoleModel();
        when(rolePersistencePort.existsByName(roleModel.getName())).thenReturn(false);
        when(rolePersistencePort.saveRole(roleModel)).thenReturn(roleModel);

        // Act
        RoleModel savedRole = roleUseCase.saveRole(roleModel);

        // Assert
        assertNotNull(savedRole);
        assertEquals(roleModel, savedRole);
        verify(rolePersistencePort, times(1)).existsByName(roleModel.getName());
        verify(rolePersistencePort, times(1)).saveRole(roleModel);
    }

    @Test
    void saveRole_shouldThrowExceptionWhenRoleAlreadyExists() {
        // Arrange
        RoleModel roleModel = RoleModelFactory.createDefaultRoleModel();
        when(rolePersistencePort.existsByName(roleModel.getName())).thenReturn(true);

        // Act & Assert
        assertThrows(RoleAlreadyExistException.class, () -> roleUseCase.saveRole(roleModel));
        verify(rolePersistencePort, times(1)).existsByName(roleModel.getName());
        verify(rolePersistencePort, never()).saveRole(roleModel);
    }

    @Test
    void existsByName_shouldThrowExceptionWhenRoleExists() {
        // Arrange
        String roleName = "Existing Role";
        when(rolePersistencePort.existsByName(roleName)).thenReturn(true);

        // Act & Assert
        assertThrows(RoleAlreadyExistException.class, () -> roleUseCase.existsByName(roleName));
        verify(rolePersistencePort, times(1)).existsByName(roleName);
    }

    @Test
    void existsByName_shouldNotThrowExceptionWhenRoleDoesNotExist() {
        // Arrange
        String roleName = "Nonexistent Role";
        when(rolePersistencePort.existsByName(roleName)).thenReturn(false);

        // Act & Assert
        assertDoesNotThrow(() -> roleUseCase.existsByName(roleName));
        verify(rolePersistencePort, times(1)).existsByName(roleName);
    }
}