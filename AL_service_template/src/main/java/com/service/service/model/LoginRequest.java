package com.service.service.model;

import javax.validation.constraints.NotBlank;

/**
 * DTO for login requests.
 */
public class LoginRequest {
    @NotBlank(message = "Username is mandatory")
    private String email;

    @NotBlank(message = "Password is mandatory")
    private String password;

   
    public LoginRequest() {
    }

    // Nouveau constructeur avec paramètres
    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
