package com.parsakav.echorestapi.service;

import com.parsakav.echorestapi.dto.BusinessOwnerDTO;
import com.parsakav.echorestapi.dto.InfluencerDTO;
import com.parsakav.echorestapi.entity.BusinessOwner;
import com.parsakav.echorestapi.entity.Influencer;
import com.parsakav.echorestapi.exceptions.UserServiceException;
import com.parsakav.echorestapi.repository.BusinessOwnerRepository;
import com.parsakav.echorestapi.response.ErrorMessages;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class BusinessOwnerServiceImpl implements BusinessOwnerService {
   @Autowired
    private BusinessOwnerRepository repository;
   @Autowired
   private PasswordEncoder encoder;
    @Override
    public BusinessOwnerDTO save(BusinessOwnerDTO influencerDTO) {
        if(repository.existsById(influencerDTO.getPhoneNumber())){
            throw new UserServiceException(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage());
        }
        BusinessOwnerDTO returnValue=new BusinessOwnerDTO();
        BusinessOwner influencer = new BusinessOwner();
        BeanUtils.copyProperties(influencerDTO,influencer);
        influencer.setPassword(encoder.encode(influencer.getPassword()));

        influencer = repository.save(influencer);

        BeanUtils.copyProperties(influencer,returnValue);

        return returnValue;
    }

    @Override
    public BusinessOwnerDTO findByPhonenumber(Long phoneNumber) {
        BusinessOwnerDTO returnValue=new BusinessOwnerDTO();

        BusinessOwner influencer= repository.getReferenceById(phoneNumber);
        if(influencer!=null) {
            BeanUtils.copyProperties(influencer, returnValue);
            System.out.println(influencer.getPhoneNumber());
            System.out.println(returnValue.getPhoneNumber());

            return returnValue;
        }
        return null;

    }
}
