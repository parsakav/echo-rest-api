package com.parsakav.echorestapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "BUSINESS_OWNER")
public class BusinessOwner extends User{
    public BusinessOwner(long phoneNumber, String fullName, String mail, String password) {
        super(phoneNumber, fullName, mail, password);
    }
    public BusinessOwner(){

    }

}
