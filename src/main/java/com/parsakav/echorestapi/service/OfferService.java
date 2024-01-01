package com.parsakav.echorestapi.service;

import com.parsakav.echorestapi.dto.OfferDTO;

import java.util.List;

public interface OfferService {
    public List<OfferDTO> getOffers(String phoneNumber);
    public List<OfferDTO> getAcceptedOffers(String phoneNumber);

    boolean rejectAnOffer(String phoneNumber, int id);
    boolean agreeAnOffer(String phoneNumber, int id);
    public OfferDTO save(OfferDTO offerDTO);

    List<OfferDTO> getUnCommentedOffers(long bownerPhoneNumber);
}
