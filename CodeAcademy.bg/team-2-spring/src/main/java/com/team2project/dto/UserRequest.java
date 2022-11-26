package com.team2project.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserRequest{


    @Pattern(regexp = "^[A-Za-z0-9.\\-]+$", message = "Invalid username.")
    @Size(min = 3, max = 20,message = "Username should be between 3 and 20 symbols!")
    @NotNull
    private String username;
    @Size(min = 8, max = 20,message = "Password should be between 8 and 20 symbols!")
    @NotNull
    private String pass;
    @NotNull
    private String passwordConfirmation;
    @NotNull
    private String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
