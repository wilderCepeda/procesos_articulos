package com.example.demo.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserRequest {

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

    @Size(max = 100)
    @NotNull
    @NotEmpty
    @JsonProperty(value = "first_name")
    private String firstName;

    @Size(max = 100)
    @NotNull
    @NotEmpty
    @JsonProperty(value = "last_name")
    private String lastName;

    @Size(max = 300)
    @NotNull
    @NotEmpty
    @JsonProperty(value = "address")
    private String address;

    @Size(max = 20)
    @NotNull
    @NotEmpty
    @JsonProperty(value = "phone_number")
    private String phoneNumber;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
