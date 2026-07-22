package com.kishore.vehicleapi.service;

import com.kishore.vehicleapi.dto.VehicleDTO;
import com.kishore.vehicleapi.entity.Vehicle;
import com.kishore.vehicleapi.exception.DuplicateVehicleException;
import com.kishore.vehicleapi.exception.VehicleNotFoundException;
import com.kishore.vehicleapi.repository.VehicleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    public ResponseEntity<Vehicle> postVehicle(VehicleDTO vehicleDTO) {

        log.info("Register request received for vehicle : {}", vehicleDTO.getVehicleType());

        if (vehicleRepository.existsByVehicleNumber(vehicleDTO.getVehicleNumber())) {
            log.warn("Duplicate vehicle number : {}", vehicleDTO.getVehicleNumber());
            throw new DuplicateVehicleException("In this vehicle number, already vehicle exists");
        }

        if (vehicleRepository.existsByFastagId(vehicleDTO.getFastagId())) {
            log.warn("Duplicate FASTag id : {}", vehicleDTO.getFastagId());
            throw new DuplicateVehicleException("In this FASTag id, already vehicle exists");
        }

        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleNumber(vehicleDTO.getVehicleNumber());
        vehicle.setOwnerName(vehicleDTO.getOwnerName());
        vehicle.setVehicleType(vehicleDTO.getVehicleType());
        vehicle.setFastagId(vehicleDTO.getFastagId());

        vehicle = vehicleRepository.save(vehicle);

        log.info("Vehicle registered successfully with id : {}", vehicle.getId());

        return ResponseEntity.status(HttpStatus.CREATED).body(vehicle);
    }

    public ResponseEntity<List<Vehicle>> getAllVehicles() {

        log.info("Fetching all vehicles");

        List<Vehicle> vehicles = vehicleRepository.findAll();

        log.info("Total vehicles : {}", vehicles.size());

        return ResponseEntity.ok(vehicles);
    }

    public ResponseEntity<Vehicle> getVehicleByVehicleNumber(String vehicleNumber) {

        log.info("Fetching vehicle : {}", vehicleNumber);

        Vehicle vehicle = vehicleRepository.findByVehicleNumber(vehicleNumber);

        if (vehicle == null) {
            throw new VehicleNotFoundException("Vehicle not found");
        }

        return ResponseEntity.ok(vehicle);
    }

    public ResponseEntity<Vehicle> updateVehicle(int id, VehicleDTO vehicleDTO) {

        log.info("Updating vehicle with id : {}", id);

        Vehicle vehicle = vehicleRepository.findById(id).orElse(null);

        if (vehicle == null) {
            throw new VehicleNotFoundException("Vehicle not found at given Id");
        }

        if (!vehicle.getVehicleNumber().equals(vehicleDTO.getVehicleNumber())
                && vehicleRepository.existsByVehicleNumber(vehicleDTO.getVehicleNumber())) {

            throw new DuplicateVehicleException("Vehicle number already exists");
        }

        if (!vehicle.getFastagId().equals(vehicleDTO.getFastagId())
                && vehicleRepository.existsByFastagId(vehicleDTO.getFastagId())) {

            throw new DuplicateVehicleException("FASTag ID already exists");
        }

        vehicle.setVehicleNumber(vehicleDTO.getVehicleNumber());
        vehicle.setOwnerName(vehicleDTO.getOwnerName());
        vehicle.setVehicleType(vehicleDTO.getVehicleType());
        vehicle.setFastagId(vehicleDTO.getFastagId());

        vehicle = vehicleRepository.save(vehicle);

        log.info("Vehicle updated successfully with id : {}", vehicle.getId());

        return ResponseEntity.ok(vehicle);
    }

    public ResponseEntity<String> deleteById(int id) {

        log.info("Deleting vehicle with id : {}", id);

        Vehicle vehicle = vehicleRepository.findById(id).orElse(null);

        if (vehicle == null) {
            throw new VehicleNotFoundException("Vehicle not found at given Id");
        }

        vehicleRepository.deleteById(id);

        log.info("Vehicle deleted successfully with id : {}", id);

        return ResponseEntity.ok("Vehicle Successfully deleted");
    }
}