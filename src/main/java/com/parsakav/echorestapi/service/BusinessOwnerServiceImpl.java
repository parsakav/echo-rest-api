package com.parsakav.echorestapi.service;

import com.parsakav.echorestapi.dto.BusinessOwnerDTO;
import com.parsakav.echorestapi.dto.InfluencerDTO;
import com.parsakav.echorestapi.dto.OfferDTO;
import com.parsakav.echorestapi.entity.BusinessOwner;
import com.parsakav.echorestapi.entity.Influencer;
import com.parsakav.echorestapi.entity.Offer;
import com.parsakav.echorestapi.exceptions.UserServiceException;
import com.parsakav.echorestapi.repository.BusinessOwnerRepository;
import com.parsakav.echorestapi.repository.OfferRepository;
import com.parsakav.echorestapi.response.ErrorMessages;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class BusinessOwnerServiceImpl implements BusinessOwnerService {
   @Autowired
    private BusinessOwnerRepository repository;
   @Autowired
   private OfferRepository offerRepository;
   @Autowired
   private PasswordEncoder encoder;
   @Transactional
   @Override
    public BusinessOwnerDTO save(BusinessOwnerDTO businessOwnerDTO) {
        if(repository.existsById(businessOwnerDTO.getPhoneNumber())){
            throw new UserServiceException(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage());
        }
        BusinessOwnerDTO returnValue=new BusinessOwnerDTO();
        BusinessOwner businessOwner = new BusinessOwner();

      //  BeanUtils.copyProperties(businessOwnerDTO,businessOwner);
        businessOwner.setFullName(businessOwnerDTO.getFullName());
        businessOwner.setPhoneNumber(businessOwnerDTO.getPhoneNumber());
        businessOwner.setMail(businessOwnerDTO.getMail());
        businessOwner.setPassword(encoder.encode(businessOwnerDTO.getPassword()));
        System.out.println(businessOwner.getMail());
        System.out.println(businessOwner.getPhoneNumber());
      repository.save(businessOwner);

        BeanUtils.copyProperties(businessOwner,returnValue);

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
