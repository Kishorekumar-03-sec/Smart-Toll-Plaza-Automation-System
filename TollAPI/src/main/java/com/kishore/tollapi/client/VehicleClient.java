package com.kishore.tollapi.client;

import com.kishore.tollapi.dto.VehicleResponse;
import com.kishore.tollapi.exception.VehicleNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class VehicleClient {

    @Autowired
    private RestTemplate restTemplate;

    public VehicleResponse getVehicleByVehicleNumber(String vehicleNumber) {

        String url = "http://localhost:8080/api/v1/vehicles/" + vehicleNumber;

        System.out.println("Calling URL: " + url);

        try {
            ResponseEntity<VehicleResponse> response =
                    restTemplate.getForEntity(url, VehicleResponse.class);

            System.out.println("Vehicle Response: " + response.getBody());

            return response.getBody();

        } catch (HttpClientErrorException e) {

            System.out.println("Status Code: " + e.getStatusCode());
            System.out.println("Response Body: " + e.getResponseBodyAsString());

            throw new VehicleNotFoundException(
                    "Vehicle not found in vehicle number " + vehicleNumber);
        }
    }
}