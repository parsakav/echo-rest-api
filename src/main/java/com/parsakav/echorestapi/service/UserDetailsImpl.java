package com.parsakav.echorestapi.service;

import com.parsakav.echorestapi.repository.BusinessOwnerRepository;
import com.parsakav.echorestapi.repository.InfluencerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static com.parsakav.echorestapi.service.RoleServiceImpl.ROLE_BUISNESSOWNER;
import static com.parsakav.echorestapi.service.RoleServiceImpl.ROLE_INFLUENCER;

@Service()
public class UserDetailsImpl implements  UserDetailsService {

    @Autowired
    private InfluencerRepository influencerRepository;
    @Autowired
    private BusinessOwnerRepository businessOwnerRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.parsakav.echorestapi.entity.User user= influencerRepository.findById(Long.parseLong(username)).orElse(null);
        List<GrantedAuthority> authority = new ArrayList<>();
        if(user!=null) {
            authority.add(new SimpleGrantedAuthority(ROLE_INFLUENCER));
            System.out.println(authority);
            return new User(String.valueOf(user.getPhoneNumber()), user.getPassword(), authority);
        }
        com.parsakav.echorestapi.entity.BusinessOwner businessOwner= businessOwnerRepository.findById(Long.parseLong(username)).orElse(null);
         if(businessOwner!=null){
             authority.add(new SimpleGrantedAuthority(ROLE_BUISNESSOWNER));

             return new User(String.valueOf(businessOwner.getPhoneNumber()), businessOwner.getPassword(), authority);

        }
         throw new UsernameNotFoundException("User not found");

    }
}
