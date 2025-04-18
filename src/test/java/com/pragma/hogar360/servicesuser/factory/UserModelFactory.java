package com.pragma.hogar360.servicesuser.factory;

import com.pragma.hogar360.servicesuser.domain.model.RoleModel;
import com.pragma.hogar360.servicesuser.domain.model.UserModel;

import java.time.LocalDate;

public class UserModelFactory {

    public static UserModel createUserModel(Long id, String name, String lastName, String idNumber, String phoneNumber, LocalDate birthDate, String email, String password, RoleModel role) {
        UserModel userModel = new UserModel();
        userModel.setId(id);
        userModel.setName(name);
        userModel.setLastName(lastName);
        userModel.setIdNumber(idNumber);
        userModel.setPhoneNumber(phoneNumber);
        userModel.setBirthDate(birthDate);
        userModel.setEmail(email);
        userModel.setPassword(password);
        userModel.setRole(role);
        return userModel;
    }

    public static UserModel createDefaultUserModel() {
        return createUserModel(1L, "Default Name", "Default Last Name", "1234567890", "+571234567890", LocalDate.of(1990, 1, 1), "default@example.com", "password", RoleModelFactory.createDefaultRoleModel());
    }

    public static UserModel createUserModelWithName(String name) {
        return createUserModel(1L, name, "Default Last Name", "1234567890", "+571234567890", LocalDate.of(1990, 1, 1), "default@example.com", "password", RoleModelFactory.createDefaultRoleModel());
    }

    public static UserModel createUserModelWithEmail(String email) {
        return createUserModel(1L, "Default Name", "Default Last Name", "1234567890", "+571234567890", LocalDate.of(1990, 1, 1), email, "password", RoleModelFactory.createDefaultRoleModel());
    }

    public static UserModel createUserModelWithRole(RoleModel role) {
        return createUserModel(1L, "Default Name", "Default Last Name", "1234567890", "+571234567890", LocalDate.of(1990, 1, 1), "default@example.com", "password", role);
    }

    public static UserModel createUserModelWithId(long id) {
        return createUserModel(id, "Default Name", "Default Last Name", "1234567890", "+571234567890", LocalDate.of(1990, 1, 1), "default@example.com", "password", RoleModelFactory.createDefaultRoleModel());
    }
}