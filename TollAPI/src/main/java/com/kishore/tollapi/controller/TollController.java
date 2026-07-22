package com.kishore.tollapi.controller;

import com.kishore.tollapi.dto.TollRequest;
import com.kishore.tollapi.dto.TollResponse;
import com.kishore.tollapi.service.TollService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TollController {

    @Autowired
    private TollService tollService;

    @PostMapping("/api/v1/tolls")
    public ResponseEntity<TollResponse> payTol(@Valid @RequestBody TollRequest tollRequest) {

        System.out.println(">>> Toll API called");

        return tollService.payToll(tollRequest);
    }
}