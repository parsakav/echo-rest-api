package com.parsakav.echorestapi.repository;

import com.parsakav.echorestapi.entity.Influencer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InfluencerRepository extends JpaRepository<Influencer,Long> {
public abstract Influencer findByMail(String mail);

    @Query(value = "select * from Influencer where `phonenumber`=:phoneNumber ;",nativeQuery = true)
    Optional<Influencer> findInfluencerByPhoneNumber(long phoneNumber);
    @Query(value = "select * from Influencer where `FOLLOWERS`>:followers ;",nativeQuery = true)
   List<Influencer> findInfluencersFollowersGraterThan(int followers);

}
