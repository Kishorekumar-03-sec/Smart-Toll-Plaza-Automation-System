package com.kishore.journeyapi.advice;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ErrorResponse {
    private int  errorCode;
    private String errorMessage;
    private String errorType;
    private LocalDateTime timestamp;
}
