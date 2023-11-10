package com.parsakav.echorestapi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
