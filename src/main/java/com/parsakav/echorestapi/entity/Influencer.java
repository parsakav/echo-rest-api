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

    public Influencer(){

    }

    public Influencer(long phoneNumber, String fullName, String mail, String accountId) {
        super(phoneNumber, fullName, mail);
        this.accountId = accountId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Influencer that = (Influencer) o;
        return Objects.equals(accountId, that.accountId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), accountId);
    }
}
