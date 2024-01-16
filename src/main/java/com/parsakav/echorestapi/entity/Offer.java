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


    @OneToOne(mappedBy = "offer")
    private Comment comment;
    @ManyToOne(optional = false)
    private Influencer influencer;
    @ManyToOne(optional = false)
    private BusinessOwner businessOwner;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date date= new Date();

    @Column
    private boolean isPaid=false;

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

    public Boolean getAccept() {
        return accept;
    }

    public void setAccept(Boolean accept) {
        this.accept = accept;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }
}
