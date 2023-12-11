package com.parsakav.echorestapi.service;

import com.parsakav.echorestapi.dto.BusinessOwnerDTO;
import com.parsakav.echorestapi.dto.InfluencerDTO;
import com.parsakav.echorestapi.dto.OfferDTO;

public interface BusinessOwnerService {
    public BusinessOwnerDTO save(BusinessOwnerDTO influencerDTO);
    BusinessOwnerDTO findByPhonenumber(Long phoneNumber);


    OfferDTO makeOffer(OfferDTO offerDTO);
}
