package com.parsakav.echorestapi;

import com.parsakav.echorestapi.repository.InfluencerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EchoRestApiApplicationTests {

    @Autowired
    InfluencerRepository influencerRepository;
    @Test
    void contextLoads() {
        System.out.println(influencerRepository.findInfluencersFollowersGraterThan(1000).size());

    }

}
