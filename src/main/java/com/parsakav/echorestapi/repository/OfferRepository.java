package com.parsakav.echorestapi.repository;

import com.parsakav.echorestapi.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface OfferRepository extends JpaRepository<Offer,Integer> {
/*  @Query(
            " from  Offer o where o.businessOwner.phoneNumber not in (select c.offer.businessOwner.phoneNumber from Comment c) and o.businessOwner.phoneNumber =:phoneNumber")*/
/*@Query("select * from offer o left join comment c on c.offer_id=o.id where c.id is NULL")*/
@Query("from Offer o left join o.comment c on c.offer.id=o.id where c.offer.id is null")
    public Set<Offer> getOfferWithoutComment(@Param("phoneNumber")long phoneNumber);
    @Query(
            " from  Offer o where o.businessOwner.phoneNumber =:phoneNumber")
public Set<Offer> getAllOffers(@Param("phoneNumber")long phoneNumber);
}
