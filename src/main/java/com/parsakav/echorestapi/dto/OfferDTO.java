package com.parsakav.echorestapi.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;


public class OfferDTO {
    private Integer id=null;
        @Size(max=30)

                                @NotNull(message = "title must be not null")
                                @NotEmpty(message = "title must be not empty")
                              private   String title;
                                @NotNull(message = "text must be not null")
                                @NotEmpty(message = "text must be not empty")
                              private   String text;
                                @Min(1000000000) Long influencerPhoneNumber;
                            @Min(1000000000)
                            private Long  buisnessOwnerPhoneNumber;

                            private String fullName;

private Date date;

private boolean accept;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public Long getInfluencerPhoneNumber() {
            return influencerPhoneNumber;
        }

        public void setInfluencerPhoneNumber(Long influencerPhoneNumber) {
            this.influencerPhoneNumber = influencerPhoneNumber;
        }

        public Long getBuisnessOwnerPhoneNumber() {
            return buisnessOwnerPhoneNumber;
        }

        public void setBuisnessOwnerPhoneNumber(Long buisnessOwnerPhoneNumber) {
            this.buisnessOwnerPhoneNumber = buisnessOwnerPhoneNumber;
        }

    public String getFullName() {
        return fullName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean isAccept() {
        return accept;
    }

    public void setAccept(Boolean accept) {
        this.accept = accept;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}


