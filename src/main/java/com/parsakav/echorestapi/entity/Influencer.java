package com.parsakav.echorestapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Entity
@Table(name = "INFLUENCER")

public class Influencer extends User {

    @Column(name = "ACCOUNTID",unique = true,nullable = false)
    private String accountId;
    @Column(name = "ACCOUNTTYPE",nullable = false)
    private String accountType;
    @Column(name = "FOLLOWERS",nullable = false)
    private int numberOfFollowers;

    public Influencer(){

    }

    public Influencer(long phoneNumber, String fullName, String mail, String password, String accountId, String accountType, int numberOfFollowers) {
        super(phoneNumber, fullName, mail, password);
        this.accountId = accountId;
        this.accountType = accountType;
        this.numberOfFollowers = numberOfFollowers;
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

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }


}
