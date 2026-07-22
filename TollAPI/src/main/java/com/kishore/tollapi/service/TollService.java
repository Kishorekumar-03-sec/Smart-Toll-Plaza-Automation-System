package com.kishore.tollapi.service;

import com.kishore.tollapi.client.JourneyClient;
import com.kishore.tollapi.client.VehicleClient;
import com.kishore.tollapi.client.WalletClient;
import com.kishore.tollapi.dto.DeductRequest;
import com.kishore.tollapi.dto.JourneyRequest;
import com.kishore.tollapi.dto.JourneyResponse;
import com.kishore.tollapi.dto.TollRequest;
import com.kishore.tollapi.dto.TollResponse;
import com.kishore.tollapi.dto.VehicleResponse;
import com.kishore.tollapi.dto.WalletResponse;
import com.kishore.tollapi.exception.InsufficientBalanceException;
import com.kishore.tollapi.exception.VehicleNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.kishore.tollapi.enums.PaymentStatus.PAID;

@Service
@Slf4j
public class TollService {

    @Autowired
    private VehicleClient vehicleClient;

    @Autowired
    private WalletClient walletClient;

    @Autowired
    private JourneyClient journeyClient;

    public ResponseEntity<TollResponse> payToll(TollRequest request) {

        try {

            log.info("Received Toll Request: {}", request);

            // Step 1 - Get Vehicle Details
            VehicleResponse vehicle =
                    vehicleClient.getVehicleByVehicleNumber(request.getVehicleNumber());

            log.info("Vehicle Response : {}", vehicle);

            if (vehicle == null) {
                throw new VehicleNotFoundException("Vehicle not found");
            }

            // Step 2 - Deduct Wallet Balance
            DeductRequest deductRequest = new DeductRequest();
            deductRequest.setFastagId(vehicle.getFastagId());
            deductRequest.setAmount(request.getAmount());

            WalletResponse walletResponse =
                    walletClient.deductBalance(deductRequest);

            log.info("Wallet Response : {}", walletResponse);

            if (walletResponse == null) {
                throw new InsufficientBalanceException("Insufficient wallet balance");
            }

            // Step 3 - Save Journey
            JourneyRequest journeyRequest = new JourneyRequest();
            journeyRequest.setVehicleNumber(vehicle.getVehicleNumber());
            journeyRequest.setPlaza(request.getPlaza());
            journeyRequest.setAmount(request.getAmount());
            journeyRequest.setPaymentStatus(PAID);
            journeyRequest.setStartTime(LocalDateTime.now());
            journeyRequest.setEndTime(LocalDateTime.now());

            log.info("Journey Request : {}", journeyRequest);

            JourneyResponse journeyResponse =
                    journeyClient.saveJourney(journeyRequest);

            log.info("Journey Response : {}", journeyResponse);

            // Step 4 - Prepare Response
            TollResponse tollResponse = new TollResponse();
            tollResponse.setVehicleNumber(journeyResponse.getVehicleNumber());
            tollResponse.setFastagId(walletResponse.getFastagId());
            tollResponse.setPlaza(journeyResponse.getPlaza());
            tollResponse.setAmount(journeyResponse.getAmount());
            tollResponse.setPaymentStatus(journeyResponse.getPaymentStatus());
            tollResponse.setMessage("Toll deducted successfully");

            return ResponseEntity.status(HttpStatus.OK).body(tollResponse);

        } catch (Exception e) {

            log.error("Exception occurred while processing toll payment", e);

            throw e;
        }
    }
}