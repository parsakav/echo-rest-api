package com.parsakav.echorestapi.entity;



import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "OFFER")
public class Offer {
    @Id
    @Column
    @GeneratedValue
    private int id;

    @Column(length = 30,nullable = false)
    private String title;
    @Column(nullable = false)
    private String text;

    @Column(nullable = true)
    private Boolean accept=null;


    @ManyToOne(optional = false)
    private Influencer influencer;
    @ManyToOne(optional = false)
    private BusinessOwner businessOwner;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date date= new Date();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean isAccept() {
        return accept;
    }

    public void setAccept(boolean accept) {
        this.accept = accept;
    }

    public Influencer getInfluencer() {
        return influencer;
    }

    public void setInfluencer(Influencer influencer) {
        this.influencer = influencer;
    }

    public BusinessOwner getBusinessOwner() {
        return businessOwner;
    }

    public void setBusinessOwner(BusinessOwner businessOwner) {
        this.businessOwner = businessOwner;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


}
