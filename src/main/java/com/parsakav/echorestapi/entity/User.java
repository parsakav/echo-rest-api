package com.parsakav.echorestapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import java.util.Objects;

@MappedSuperclass
public class User {
    @Id
    @Column(name = "PHONENUMBER")
/*    @NotNull(message = "Phone number can't be empty")
    @NotEmpty(message = "Phone number can't be null")*/
    private Long phoneNumber;
    @Column(name = "FULLNAME",nullable = false)
 /*   @NotNull(message = "Phone number can't be empty")
    @NotEmpty(message = "Phone number can't be null")*/
    private String fullName;
    @Column(name = "EMAIL",unique = true,nullable = false)
    private String mail;
    @Column(name = "PASSWORD",unique = true,nullable = false)
    private String password;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(long phoneNumber, String fullName, String mail, String password) {
        this.phoneNumber = phoneNumber;
        this.fullName = fullName;
        this.mail = mail;
        this.password = password;
    }
    public User(){

    }

 }
