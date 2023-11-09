package com.parsakav.echorestapi.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class InfluencerRequest {


    @NotNull(message = "Phone number can't be empty")
    @NotEmpty(message = "Phone number can't be null")
    private long phoneNumber;
    @NotNull(message = "Name can't be empty")
    @NotEmpty(message = "Name can't be null")
    private String fullName;

    private String mail;
    @NotNull(message = "Account id can't be empty")
    @NotEmpty(message = "Account id can't be null")
    private String accountId;

    public InfluencerRequest() {
    }

    public InfluencerRequest(long phoneNumber, String fullName, String mail, String accountId) {
        this.phoneNumber = phoneNumber;
        this.fullName = fullName;
        this.mail = mail;
        this.accountId = accountId;
    }

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

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}
