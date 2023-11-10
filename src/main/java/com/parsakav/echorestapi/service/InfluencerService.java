package com.parsakav.echorestapi.service;

import com.parsakav.echorestapi.dto.InfluencerDTO;

public interface InfluencerService {
    public InfluencerDTO save(InfluencerDTO influencerDTO);

    InfluencerDTO findByPhonenumber(Long principal);
}
