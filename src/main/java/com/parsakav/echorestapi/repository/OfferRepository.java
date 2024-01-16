package com.parsakav.echorestapi.repository;

import com.parsakav.echorestapi.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface OfferRepository extends JpaRepository<Offer,Integer> {
    @Query(
            " from  Offer o where o.businessOwner.phoneNumber not in (select c.offer.businessOwner.phoneNumber from Comment c) and o.businessOwner.phoneNumber =:phoneNumber")
public Set<Offer> getOfferWithoutComment(@Param("phoneNumber")long phoneNumber);
    @Query(
            " from  Offer o where o.businessOwner.phoneNumber =:phoneNumber")
public Set<Offer> getAllOffers(@Param("phoneNumber")long phoneNumber);
}
