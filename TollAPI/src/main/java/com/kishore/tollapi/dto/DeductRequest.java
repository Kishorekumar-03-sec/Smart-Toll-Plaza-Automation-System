package com.kishore.tollapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeductRequest {
    private String fastagId;
    private double amount;
}
