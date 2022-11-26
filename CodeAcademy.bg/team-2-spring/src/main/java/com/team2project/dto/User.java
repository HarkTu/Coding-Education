package com.team2project.dto;

import org.springframework.security.core.GrantedAuthority;

import javax.validation.constraints.Email;
import java.beans.Transient;

public class User implements GrantedAuthority {

    private Long userId;
    private String username;
    private String pass;
    private UserRole role;

    @Email
    private String email;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public enum UserRole {
        ADMIN, USER
    }

    @Override
    public String toString() {
        return "Username - " + username + "\n"
              +"Email - " + email;
    }

    @Transient
    @Override
    public String getAuthority() {
        return "ROLE_" + role.toString();
    }
}
