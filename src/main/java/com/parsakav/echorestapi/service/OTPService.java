package com.parsakav.echorestapi.service;

import java.nio.file.Path;

public interface OTPService {

     public String getStoredOtp(String accountId);
     boolean validateOTP(String accountId, String providedOTP);
     String generateAndSaveOTP(String accountId) ;
}
