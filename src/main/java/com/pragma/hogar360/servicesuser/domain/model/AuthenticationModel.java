package com.pragma.hogar360.servicesuser.domain.model;

public class AuthenticationModel {

    private String email;
    private String password;

    public AuthenticationModel(){}
    public AuthenticationModel(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
    public String setEmail(){
        return this.email;
    }
    public String setPassword(){
        return this.password;
    }
}
