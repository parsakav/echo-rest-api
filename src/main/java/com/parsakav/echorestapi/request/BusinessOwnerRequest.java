package com.parsakav.echorestapi.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class BusinessOwnerRequest {
    @Min(1000000000)
    private long phoneNumber;

    @NotNull(message = "Name can't be empty")
    @NotEmpty(message = "Name can't be null")
    private String fullName;

    @Email

    private String mail;

    @NotNull(message = "Password can't be empty")
    @NotEmpty(message = "Password can't be null")
    private String password;

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
