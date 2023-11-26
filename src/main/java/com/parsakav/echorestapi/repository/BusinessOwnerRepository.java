package com.parsakav.echorestapi.repository;

import com.parsakav.echorestapi.entity.BusinessOwner;
import com.parsakav.echorestapi.entity.Influencer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BusinessOwnerRepository extends JpaRepository<BusinessOwner,Long> {
    @Query(value = "select * from BUSINESS_OWNER where `phonenumber`=:phoneNumber ;",nativeQuery = true)
    Optional<Influencer> findInfluencerByPhoneNumber(long phoneNumber);
}
