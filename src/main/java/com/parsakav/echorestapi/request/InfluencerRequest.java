package com.parsakav.echorestapi.request;

import jakarta.persistence.Column;
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
    @NotNull(message = "Account type can't be empty")
    @NotEmpty(message = "Account type can't be null")
    private String accountType;
    @NotNull(message = "Followers can't be empty")
    @NotEmpty(message = "Followers can't be null")
    private int numberOfFollowers;




    public InfluencerRequest(long phoneNumber, String fullName, String mail, String accountId, String accountType, int numberOfFollowers) {
        this.phoneNumber = phoneNumber;
        this.fullName = fullName;
        this.mail = mail;
        this.accountId = accountId;
        this.accountType = accountType;
        this.numberOfFollowers = numberOfFollowers;
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

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public int getNumberOfFollowers() {
        return numberOfFollowers;
    }

    public void setNumberOfFollowers(int numberOfFollowers) {
        this.numberOfFollowers = numberOfFollowers;
    }
}
