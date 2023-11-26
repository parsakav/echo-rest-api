package com.parsakav.echorestapi.service;

import com.parsakav.echorestapi.repository.BusinessOwnerRepository;
import com.parsakav.echorestapi.repository.InfluencerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.function.Supplier;

@Service()
public class UserDetailsImpl implements  UserDetailsService {
    @Autowired
    private InfluencerRepository influencerRepository;
    @Autowired
    private BusinessOwnerRepository businessOwnerRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.parsakav.echorestapi.entity.User user= influencerRepository.findById(Long.parseLong(username)).get();
        if(user!=null) {
            return new User(String.valueOf(user.getPhoneNumber()), user.getPassword(), new ArrayList<>());
        }
        com.parsakav.echorestapi.entity.BusinessOwner businessOwner= businessOwnerRepository.findById(Long.parseLong(username)).get();

         if(businessOwner!=null){
            return new User(String.valueOf(user.getPhoneNumber()), user.getPassword(), new ArrayList<>());

        }
         throw new UsernameNotFoundException("User not found");

    }
}
