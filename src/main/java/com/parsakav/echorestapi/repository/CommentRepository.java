package com.parsakav.echorestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.parsakav.echorestapi.entity.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer> {

    @Query("from Comment c where c.offer.influencer.phoneNumber=:phonenumber")
    List<Comment> getComments(@Param("phonenumber")long phonenumber);
    @Query("from Comment c where c.offer.influencer.phoneNumber=:phonenumber and c.accept =true")
    List<Comment> getAcceptedComments(@Param("phonenumber")long phonenumber);
    @Query("from Comment c where c.offer.businessOwner.phoneNumber=:phonenumber and c.accept =true")
    List<Comment> getBusinessOwnerAcceptedComments(@Param("phonenumber")long phonenumber);
    @Query("from Comment c where c.offer.businessOwner.phoneNumber=:phonenumber")
    List<Comment> getBussinessOwnerComments(@Param("phonenumber")long phonenumber);

}
