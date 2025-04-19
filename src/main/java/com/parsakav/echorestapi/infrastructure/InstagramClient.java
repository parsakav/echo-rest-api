package com.parsakav.echorestapi.infrastructure;

import com.fasterxml.jackson.databind.JsonNode;
import com.parsakav.echorestapi.response.ProfileResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class InstagramClient {
    private final WebClient webClient;

    public InstagramClient(@Value("${rapidapi.key}") String apiKey) {
        this.webClient = WebClient.builder()
                .baseUrl("https://instagram-looter2.p.rapidapi.com")
                .defaultHeader("x-rapidapi-host", "instagram-looter2.p.rapidapi.com")
                .defaultHeader("x-rapidapi-key", apiKey)
                .build();
    }

    public Mono<ProfileResponse> fetchProfile(String username) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/profile2")
                        .queryParam("username", username)
                        .build())
                .retrieve()
                .bodyToMono(JsonNode.class)
                .map(root -> {
                    ProfileResponse p = new ProfileResponse();
                    p.setBiography(root.path("biography").asText());
                    p.setProfilePicUrl(root.path("profile_pic_url").asText());
                    return p;
                });
    }
}
