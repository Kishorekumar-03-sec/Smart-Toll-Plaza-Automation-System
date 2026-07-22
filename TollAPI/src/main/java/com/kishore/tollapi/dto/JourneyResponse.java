package com.kishore.tollapi.dto;

import com.kishore.tollapi.enums.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class JourneyResponse {
    private int id;

    private String vehicleNumber;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String plaza;
    private double amount;
    private PaymentStatus paymentStatus;
}
