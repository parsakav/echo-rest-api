package com.parsakav.echorestapi.service;

import com.parsakav.echorestapi.dto.InfluencerDTO;
import com.parsakav.echorestapi.entity.Influencer;
import com.parsakav.echorestapi.exceptions.UserServiceException;
import com.parsakav.echorestapi.repository.InfluencerRepository;
import com.parsakav.echorestapi.response.ErrorMessages;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.beans.Beans;

@Service
public class InfluencerServiceImpl implements InfluencerService {
    @Autowired
    private InfluencerRepository repository;

    public InfluencerDTO save(final InfluencerDTO influencerDTO){
        if(repository.existsById(influencerDTO.getPhoneNumber())){
            throw new UserServiceException(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage());
        }
        InfluencerDTO returnValue=new InfluencerDTO();
        Influencer influencer = new Influencer();
        BeanUtils.copyProperties(influencerDTO,influencer);
       influencer= repository.save(influencer);
        BeanUtils.copyProperties(influencer,returnValue);

        return returnValue;
    }
}
