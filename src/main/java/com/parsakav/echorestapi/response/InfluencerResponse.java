package com.parsakav.echorestapi.response;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class InfluencerResponse {


    private long phoneNumber;

    private String fullName;

    private String mail;

    private String accountId;

    public InfluencerResponse(){

    }
    public InfluencerResponse(long phoneNumber, String fullName, String mail, String accountId) {
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
