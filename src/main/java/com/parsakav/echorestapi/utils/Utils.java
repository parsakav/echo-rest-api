package com.parsakav.echorestapi.utils;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

import com.parsakav.echorestapi.dto.InfluencerDTO;
import com.parsakav.echorestapi.entity.Influencer;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class Utils {
	
    private static final Random RANDOM = new SecureRandom();
    private static final String ALPHABET="0123456789abcdefghijklmnopqrstuvwxyz";

	
	public String generateUserId(int length) {
		return generateRandomString(length);
	}
   private  String generateRandomString(int length){
        StringBuilder userIdGenerated = new StringBuilder(length);
        for(int i=0;i<length;i++){
            userIdGenerated.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }
        return userIdGenerated.toString();

    }




}
