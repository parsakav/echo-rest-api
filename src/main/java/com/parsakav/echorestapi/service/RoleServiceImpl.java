package com.parsakav.echorestapi.service;

import com.parsakav.echorestapi.repository.BusinessOwnerRepository;
import com.parsakav.echorestapi.repository.InfluencerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {

    public  static final String ROLE_INFLUENCER="ROLE_INFLUENCER";
    public  static final String ROLE_BUISNESSOWNER="ROLE_BUISNESS";
    @Autowired
    private InfluencerRepository influencerRepository;
    @Autowired
    private BusinessOwnerRepository businessOwnerRepository;
    @Override
    public String findRole(String phoneNumber) {
        com.parsakav.echorestapi.entity.User user= influencerRepository.findById(Long.parseLong(phoneNumber)).orElse(null);

        if(user!=null) {
return    ROLE_INFLUENCER;
        }
        com.parsakav.echorestapi.entity.BusinessOwner businessOwner= businessOwnerRepository.findById(Long.parseLong(phoneNumber)).orElse(null);
        if(businessOwner!=null){

return ROLE_BUISNESSOWNER;
        }
        return null;
    }
}
