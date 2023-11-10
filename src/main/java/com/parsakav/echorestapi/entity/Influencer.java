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

    @Column(name = "INFLUENCER_ACCOUNTID",unique = true,nullable = false)
    private String accountId;
    @Column(name = "INFLUENCER_ACCOUNTTYPE",unique = true,nullable = false)
    private String accountType;
    @Column(name = "INFLUENCER_FOLLOWERS",unique = true,nullable = false)
    private int numberOfFollowers;

    public Influencer(){

    }

    public Influencer(long phoneNumber, String fullName, String mail, String accountId, String accountType, int numberOfFollowers) {
        super(phoneNumber, fullName, mail);
        this.accountId = accountId;
        this.accountType = accountType;
        this.numberOfFollowers = numberOfFollowers;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }


}
