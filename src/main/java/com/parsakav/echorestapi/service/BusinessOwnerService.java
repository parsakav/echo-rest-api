package com.parsakav.echorestapi.service;

import com.parsakav.echorestapi.dto.BusinessOwnerDTO;
import com.parsakav.echorestapi.dto.InfluencerDTO;

public interface BusinessOwnerService {
    public BusinessOwnerDTO save(BusinessOwnerDTO influencerDTO);
    BusinessOwnerDTO findByPhonenumber(Long phoneNumber);


}
