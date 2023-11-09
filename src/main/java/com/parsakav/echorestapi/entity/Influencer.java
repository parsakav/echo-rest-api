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

public class Influencer {
    @Id
    @Column(name = "INFLUENCER_PHONENUMBER")
/*    @NotNull(message = "Phone number can't be empty")
    @NotEmpty(message = "Phone number can't be null")*/
    private long phoneNumber;
    @Column(name = "INFLUENCER_FULLNAME",nullable = false)
 /*   @NotNull(message = "Phone number can't be empty")
    @NotEmpty(message = "Phone number can't be null")*/
    private String fullName;
    @Column(name = "INFLUENCER_MAIL",unique = true)
    private String mail;
    @Column(name = "INFLUENCER_ACCOUNTID",unique = true,nullable = false)
    private String accountId;

    public Influencer(){

    }
    public Influencer(long phoneNumber, String fullName, String mail, String accountId) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Influencer that = (Influencer) o;
        return phoneNumber == that.phoneNumber && Objects.equals(fullName, that.fullName) && Objects.equals(mail, that.mail) && Objects.equals(accountId, that.accountId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phoneNumber, fullName, mail, accountId);
    }
}
