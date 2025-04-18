package com.pragma.hogar360.servicesuser.domain.model;

import java.time.LocalDate;

public class UserModel {
    private Long id;
    private String name;
    private String lastName;
    private String idNumber;
    private String phoneNumber;
    private LocalDate birthDate;
    private String email;
    private String password;
    private RoleModel role;

//    public UserModel(Long id, String name, String lastName,String idNumber,String phoneNumber,LocalDate birthDate, String email, String password, RoleModel role) {
//        this.id = id;
//        this.name = name;
//        this.lastName = lastName;
//        this.idNumber = idNumber;
//        this.phoneNumber = phoneNumber;
//        this.birthDate = birthDate;
//        this.email = email;
//        this.password = password;
//        this.role = role;
//    }
    public UserModel() {}

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public RoleModel getRole() {
        return role;
    }

    public String getRoleName() {
        return role.getName();
    }
    public void setRoleName(String roleName) {
        this.role.setName(roleName);
    }
    public void setRole(RoleModel role) {
        this.role = role;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
