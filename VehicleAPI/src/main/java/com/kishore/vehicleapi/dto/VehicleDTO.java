package com.kishore.vehicleapi.dto;
import com.kishore.vehicleapi.enums.VehicleType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehicleDTO {
    @NotBlank(message = "Vehicle number is mandatory")
    @Pattern(regexp = "^[A-Z]{2}\\d{2}[A-Z]{2}\\d{4}$" , message = "Vehicle number must be like TN42IK2007")
    private String vehicleNumber;

    @NotBlank(message = "Owner name is mandatory")
    @Size(min = 3, message = "Owner name should contain at least 3 characters")
    private String ownerName;

    @NotNull(message = "Vehicle type is mandatory")
    private VehicleType vehicleType;

    @NotBlank(message = "FASTag ID is mandatory")
    private String fastagId;
}
