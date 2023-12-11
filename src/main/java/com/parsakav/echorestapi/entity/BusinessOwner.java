package com.parsakav.echorestapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.Set;

@Entity
@Table(name = "BUSINESS_OWNER")
public class BusinessOwner extends User{
    @OneToMany(mappedBy = "businessOwner")
private Set<Offer> sentOffers;
    public BusinessOwner(long phoneNumber, String fullName, String mail, String password) {
        super(phoneNumber, fullName, mail, password);
    }

    public BusinessOwner(){

    }

    public Set<Offer> getSentOffers() {
        return sentOffers;
    }

    public void setSentOffers(Set<Offer> sentOffers) {
        this.sentOffers = sentOffers;
    }
}
