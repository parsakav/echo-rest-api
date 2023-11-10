package com.parsakav.echorestapi.request;

import jakarta.validation.constraints.Min;

public class UserLoginRequest{
    private long phoneNumber;
   private String password;

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserLoginRequest() {
    }

    public UserLoginRequest(long phoneNumber, String password) {
        this.phoneNumber = phoneNumber;
        this.password = password;
    }
}
