package com.parsakav.echorestapi.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;

public class InfluencerRequest {



    @Min(1000000000)

    private long phoneNumber;
    @NotNull(message = "Name can't be empty")
    @NotEmpty(message = "Name can't be null")
    private String fullName;

    @Email
    private String mail;
    @NotNull(message = "Account id can't be empty")
    @NotEmpty(message = "Account id can't be null")
    private String accountId;
    @NotNull(message = "Account type can't be empty")
    @NotEmpty(message = "Account type can't be null")
    private String accountType;
    @NotNull(message = "Password can't be empty")
    @NotEmpty(message = "Password can't be null")
    private String password;



    @Min(10000)
    private int numberOfFollowers;




    public InfluencerRequest(long phoneNumber, String fullName, String mail, String accountId, String accountType, String password, int numberOfFollowers) {
        this.phoneNumber = phoneNumber;
        this.fullName = fullName;
        this.mail = mail;
        this.accountId = accountId;
        this.accountType = accountType;
        this.password = password;
        this.numberOfFollowers = numberOfFollowers;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
