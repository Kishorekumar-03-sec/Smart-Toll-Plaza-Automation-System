package com.kishore.journeyapi.controller;

import com.kishore.journeyapi.dto.JourneyDTO;
import com.kishore.journeyapi.entity.Journey;
import com.kishore.journeyapi.service.JourneyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/journeys")
public class JourneyController {

    @Autowired
    private JourneyService journeyService;

    @GetMapping
    public ResponseEntity<List<Journey>> getAllJourneys() {
        return journeyService.getAllJourneys();
    }

    @GetMapping("/{vehicleNumber}")
    public ResponseEntity<List<Journey>> getJourney(@PathVariable String vehicleNumber) {
        return journeyService.getJourneysByVehicleNumber(vehicleNumber);
    }

    @PostMapping
    public ResponseEntity<Journey> createJourney(@Valid @RequestBody JourneyDTO journeyDTO) {
        return journeyService.saveJourney(journeyDTO);
    }
}