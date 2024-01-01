package com.parsakav.echorestapi.service;

import com.parsakav.echorestapi.dto.InfluencerDTO;
import com.parsakav.echorestapi.dto.OfferDTO;

import java.util.List;

public interface InfluencerService {
    public InfluencerDTO save(InfluencerDTO influencerDTO);

    List<InfluencerDTO> findAll();

    List<InfluencerDTO> findAllByFollowers(int followers);

    InfluencerDTO findByPhonenumber(Long phoneNumber);


}
