package com.parsakav.echorestapi.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "COMMENT")
public class Comment {
    @Id
    @Column
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private int rate;
    @Column(nullable = false)
    private String text;

    @Column(nullable = true)
    private Boolean accept=null;
    @OneToOne(optional = false)
    private Offer offer;


    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date date= new Date();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getAccept() {
        return accept;
    }

    public void setAccept(Boolean accept) {
        this.accept = accept;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public Date getDate() {
        return date;
    }


}
