package com.parsakav.echorestapi.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class CommentRequest {
    @Min(0)
    private long offerId;

    @Min(0)
    @Max(5)
    private int rate;
    @NotNull
    @NotEmpty
    private String text;

    public long getOfferId() {
        return offerId;
    }

    public void setOfferId(long offerId) {
        this.offerId = offerId;
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

    @Override
    public String toString() {
        return "CommentRequest{" +
                "offerId=" + offerId +
                ", rate=" + rate +
                ", text='" + text + '\'' +
                '}';
    }
}
