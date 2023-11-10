package com.parsakav.echorestapi.service;

import com.parsakav.echorestapi.dto.InfluencerDTO;
import com.parsakav.echorestapi.entity.Influencer;
import com.parsakav.echorestapi.exceptions.UserServiceException;
import com.parsakav.echorestapi.repository.InfluencerRepository;
import com.parsakav.echorestapi.response.ErrorMessages;
import com.parsakav.echorestapi.utils.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class InfluencerServiceImpl implements InfluencerService{
    @Autowired
    private InfluencerRepository repository;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private Utils utils;
    @Transactional
    public InfluencerDTO save(final InfluencerDTO influencerDTO){
        if(repository.existsById(influencerDTO.getPhoneNumber())){
            throw new UserServiceException(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage());
        }
        InfluencerDTO returnValue=new InfluencerDTO();
        Influencer influencer = new Influencer();
        BeanUtils.copyProperties(influencerDTO,influencer);
        influencer.setPassword(encoder.encode(influencer.getPassword()));

            influencer = repository.save(influencer);

        BeanUtils.copyProperties(influencer,returnValue);

        return returnValue;
    }

    //@Transactional(readOnly = true)
    @Override
    public InfluencerDTO findByPhonenumber(Long principal) {
        InfluencerDTO returnValue=new InfluencerDTO();

       Influencer influencer= repository.getReferenceById(principal);
        System.out.println(influencer.getFullName());
if(influencer!=null) {
    BeanUtils.copyProperties(influencer, returnValue);
    System.out.println(influencer.getPhoneNumber());
    System.out.println(returnValue.getPhoneNumber());

    return returnValue;
}
return null;
    }


}
