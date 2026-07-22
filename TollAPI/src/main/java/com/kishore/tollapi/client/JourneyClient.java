package com.kishore.tollapi.client;

import com.kishore.tollapi.dto.JourneyRequest;
import com.kishore.tollapi.dto.JourneyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class JourneyClient {

    @Autowired
    private RestTemplate restTemplate;

    public JourneyResponse saveJourney(JourneyRequest journeyRequest) {

        String url = "http://localhost:8080/api/v1/journeys";

        HttpEntity<JourneyRequest> httpEntity = new HttpEntity<>(journeyRequest);

        ResponseEntity<JourneyResponse> response =
                restTemplate.postForEntity(url, httpEntity, JourneyResponse.class);

        return response.getBody();
    }
}