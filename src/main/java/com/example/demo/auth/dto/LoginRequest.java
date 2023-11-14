package com.example.demo.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LoginRequest {

    @Size(max = 30)
    @NotNull
    @NotEmpty
    @JsonProperty(value = "email")
    private String email;

    @Size(max = 30)
    @NotNull
    @NotEmpty
    @JsonProperty(value = "password")
    private String password;

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
