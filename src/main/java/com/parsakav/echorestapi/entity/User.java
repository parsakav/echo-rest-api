package com.parsakav.echorestapi.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import java.util.Objects;

@MappedSuperclass
public class User {
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

    public User(long phoneNumber, String fullName, String mail) {
        this.phoneNumber = phoneNumber;
        this.fullName = fullName;
        this.mail = mail;
    }
    public User(){

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return phoneNumber == user.phoneNumber && Objects.equals(fullName, user.fullName) && Objects.equals(mail, user.mail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phoneNumber, fullName, mail);
    }
}
