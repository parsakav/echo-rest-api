package com.parsakav.echorestapi.repository;

import com.parsakav.echorestapi.entity.Influencer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfluencerRepository extends JpaRepository<Influencer,Long> {
public abstract Influencer findByMail(String mail);

}
