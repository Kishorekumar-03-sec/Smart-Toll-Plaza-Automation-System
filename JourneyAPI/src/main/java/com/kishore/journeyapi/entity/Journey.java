package com.kishore.journeyapi.entity;

import com.kishore.journeyapi.enums.PaymentStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Journey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String vehicleNumber;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String plaza;
    private double amount;
    private PaymentStatus paymentStatus;
}
