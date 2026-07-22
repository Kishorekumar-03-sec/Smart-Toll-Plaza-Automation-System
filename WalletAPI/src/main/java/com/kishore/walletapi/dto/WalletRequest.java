package com.kishore.walletapi.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WalletRequest {
    @NotBlank(message="fastag Id is mandatory")
    private String fastagId;
}
