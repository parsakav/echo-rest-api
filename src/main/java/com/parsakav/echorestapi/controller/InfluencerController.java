package com.parsakav.echorestapi.controller;


import com.parsakav.echorestapi.exceptions.UserServiceException;
import com.parsakav.echorestapi.request.InfluencerRequest;
import com.parsakav.echorestapi.response.ErrorMessages;
import com.parsakav.echorestapi.response.InfluencerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/influencer")
public class InfluencerController {
    private static final Logger logger = LoggerFactory.getLogger(InfluencerController.class);

    @PostMapping
    public ResponseEntity<InfluencerResponse> save(@RequestBody @Valid InfluencerRequest influencerRequest, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(System.out::println);
            throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
        }
return  null;
    }
}
