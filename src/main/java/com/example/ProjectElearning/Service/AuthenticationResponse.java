package com.example.ProjectElearning.Service;



public class AuthenticationResponse {

    String token;

    String role;

    Long id;

    public AuthenticationResponse(String token) {
        this.token = token;
    }

    public AuthenticationResponse(String token, String role) {
        this.token = token;
        this.role = role;
    }

    public AuthenticationResponse(String token, String role, Long id) {
        this.token = token;
        this.role = role;
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
