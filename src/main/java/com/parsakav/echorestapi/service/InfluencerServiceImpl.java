package com.parsakav.echorestapi.service;

import com.parsakav.echorestapi.dto.InfluencerDTO;
import com.parsakav.echorestapi.entity.Influencer;
import com.parsakav.echorestapi.exceptions.UserServiceException;
import com.parsakav.echorestapi.infrastructure.InstagramClient;
import com.parsakav.echorestapi.repository.InfluencerRepository;
import com.parsakav.echorestapi.repository.OfferRepository;
import com.parsakav.echorestapi.response.ErrorMessages;
import com.parsakav.echorestapi.response.ProfileResponse;
import com.parsakav.echorestapi.response.VerifyResponse;
import com.parsakav.echorestapi.utils.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.LinkedList;
import java.util.List;

@Service
public class InfluencerServiceImpl implements InfluencerService{
    private final OTPService otpService;
    private final InstagramClient instagramClient;

    private final InfluencerRepository repository;
    private final PasswordEncoder encoder;
    private final Utils utils;
    private final OfferRepository offerRepository;

    public InfluencerServiceImpl(OTPService otpService, InstagramClient instagramClient, InfluencerRepository repository, PasswordEncoder encoder, Utils utils, OfferRepository offerRepository) {
        this.otpService = otpService;
        this.instagramClient = instagramClient;
        this.repository = repository;
        this.encoder = encoder;
        this.utils = utils;
        this.offerRepository = offerRepository;
    }

    @Transactional
    public InfluencerDTO save(InfluencerDTO influencerDTO) {
        if (repository.existsById(influencerDTO.getPhoneNumber())) {
            throw new UserServiceException(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage());
        }
        System.out.println("123");
        Influencer influencer = new Influencer();
        BeanUtils.copyProperties(influencerDTO, influencer);
        influencer.setPassword(encoder.encode(influencerDTO.getPassword()));

        String otp = otpService.generateAndSaveOTP(influencerDTO.getAccountId());
        System.out.println("Generated OTP: " + otp); // در عمل باید به کاربر نمایش داده بشه

        influencer = repository.save(influencer);
        InfluencerDTO result = new InfluencerDTO();
        BeanUtils.copyProperties(influencer, result);
        return result;
    }

    public boolean checkOTP(String accountId, String otp) {

        return otpService.validateOTP(accountId, otp);
    }
    @Override
    public List<InfluencerDTO> findAll() {
        List<InfluencerDTO> influencerDTOS = new LinkedList<>();
        List<Influencer> all = repository.findAll();
        copyProp(all,influencerDTOS);

        return influencerDTOS;
    }

    @Override
    public List<InfluencerDTO> findAllByFollowers(int followers) {
        List<InfluencerDTO> influencerDTOS = new LinkedList<>();
        List<Influencer> all = repository.findInfluencersFollowersGraterThan(followers);
     copyProp(all,influencerDTOS);
        return influencerDTOS;
    }


    //@Transactional(readOnly = true)
    @Override
    public InfluencerDTO findByPhonenumber(Long phoneNumber) {
        InfluencerDTO returnValue=new InfluencerDTO();

       Influencer influencer= repository.getReferenceById(phoneNumber);
        System.out.println(influencer.getFullName());
if(influencer!=null) {
    BeanUtils.copyProperties(influencer, returnValue);
    System.out.println(influencer.getPhoneNumber());
    System.out.println(returnValue.getPhoneNumber());

    return returnValue;
}
return null;
    }

    public void copyProp(List<Influencer> all, List<InfluencerDTO> influencerDTOS){
        for(Influencer a:all){
            InfluencerDTO returnValue=new InfluencerDTO();

            BeanUtils.copyProperties(a, returnValue);
            influencerDTOS.add(returnValue);
        }
    }

    public VerifyResponse verifyAccountOwnership(String accountId) {
        String otp = otpService.getStoredOtp(accountId);

        ProfileResponse profile = instagramClient.fetchProfile(accountId)
                .blockOptional()
                .orElseThrow(() -> new UserServiceException("Failed to fetch Instagram profile"));

        if (!otp.equals(profile.getBiography())) {
            throw new UserServiceException("OTP mismatch: biography does not match stored OTP");
        }

        byte[] imageBytes = new RestTemplate()
                .getForObject(profile.getProfilePicUrl(), byte[].class);

        Path dir = Paths.get("downloaded-profiles");
        try {
            Files.createDirectories(dir);
            Path file = dir.resolve(accountId + ".jpg");
            Files.write(file, imageBytes,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING);
            Influencer influencer=this.repository.findInfluencerByAccountId(accountId).orElseThrow();
            influencer.setVerify(true);
            influencer.setImageUrl(file.toUri().toString());
            repository.save(influencer);
            return new VerifyResponse(
                    "Verified and profile pic downloaded",
                    profile.getBiography(),
                    profile.getProfilePicUrl(),
                    file.toString()
            );
        } catch (IOException e) {
            throw new UserServiceException("Failed to save profile picture", e);
        }
    }

}
