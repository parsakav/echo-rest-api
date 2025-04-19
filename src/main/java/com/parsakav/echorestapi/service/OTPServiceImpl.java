package com.parsakav.echorestapi.service;

import com.parsakav.echorestapi.dto.OTPInfo;
import com.parsakav.echorestapi.exceptions.UserServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Random;

@Service
public class OTPServiceImpl implements OTPService {

    private final RedisTemplate<String, OTPInfo> redisTemplate;

    @Autowired
    public OTPServiceImpl(RedisTemplate<String, OTPInfo> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }



    @Override
    public String generateAndSaveOTP(String accountId) {
        String otp = String.valueOf(new Random().nextInt(899999) + 100000);
        OTPInfo otpInfo = new OTPInfo(otp, accountId);
        redisTemplate.opsForValue().set(accountId, otpInfo, Duration.ofMinutes(10));
        return otp;
    }

    @Override
    public boolean validateOTP(String accountId, String providedOTP) {
        OTPInfo stored = redisTemplate.opsForValue().get(accountId);
        return stored != null && stored.getOtp().equals(providedOTP);
    }

    public String getStoredOtp(String accountId) {
        OTPInfo info = redisTemplate.opsForValue().get(accountId);
        if (info == null) {
            throw new UserServiceException("No OTP found (or expired) for account " + accountId);
        }
        return info.getOtp();
    }


}
