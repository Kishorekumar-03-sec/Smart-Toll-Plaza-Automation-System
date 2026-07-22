package com.kishore.tollapi.dto;

import com.kishore.tollapi.enums.VehicleType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehicleResponse {
    private int id;
    private String vehicleNumber;
    private String ownerName;
    private VehicleType vehicleType;
    private String fastagId;
}
