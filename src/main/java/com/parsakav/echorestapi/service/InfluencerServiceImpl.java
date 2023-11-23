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

import java.util.LinkedList;
import java.util.List;
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

    @Override
    public List<InfluencerDTO> findAll() {
        List<InfluencerDTO> influencerDTOS = new LinkedList<>();
        List<Influencer> all = repository.findAll();
        copyProp(all,influencerDTOS);

        return influencerDTOS;
    }

    @Override
    public List<InfluencerDTO> findAllByFollowers(int followers) {
        List<InfluencerDTO> influencerDTOS = new LinkedList<>();
        List<Influencer> all = repository.findInfluencersFollowersGraterThan(followers);
     copyProp(all,influencerDTOS);
        return influencerDTOS;
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

    public void copyProp(List<Influencer> all, List<InfluencerDTO> influencerDTOS){
        for(Influencer a:all){
            InfluencerDTO returnValue=new InfluencerDTO();

            BeanUtils.copyProperties(a, returnValue);
            influencerDTOS.add(returnValue);
        }
    }

}
