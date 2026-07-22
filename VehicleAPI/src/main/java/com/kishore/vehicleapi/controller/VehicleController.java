package com.kishore.vehicleapi.controller;

import com.kishore.vehicleapi.dto.VehicleDTO;
import com.kishore.vehicleapi.entity.Vehicle;
import com.kishore.vehicleapi.service.VehicleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping("/api/v1/vehicles")
    public ResponseEntity<Vehicle> postVehicle(@Valid @RequestBody VehicleDTO vehicleDTO) {
        return vehicleService.postVehicle(vehicleDTO);
    }

    @GetMapping("/api/v1/vehicles")
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    @GetMapping("/api/v1/vehicles/{vehicleNumber}")
    public ResponseEntity<Vehicle> getVehicleByVehicleNumber(
            @PathVariable String vehicleNumber) {

        return vehicleService.getVehicleByVehicleNumber(vehicleNumber);
    }

    @PutMapping("/api/v1/vehicles/{id}")
    public ResponseEntity<Vehicle> updateVehicle(
            @PathVariable int id,
            @Valid @RequestBody VehicleDTO vehicleDTO) {

        return vehicleService.updateVehicle(id, vehicleDTO);
    }

    @DeleteMapping("/api/v1/vehicles/{id}")
    public ResponseEntity<String> deleteVehicle(@PathVariable int id) {

        return vehicleService.deleteById(id);
    }
}