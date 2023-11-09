package com.parsakav.echorestapi.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/influencer")
public class InfluencerController {

    @PostMapping
    public ResponseEntity save(){

    }
}
