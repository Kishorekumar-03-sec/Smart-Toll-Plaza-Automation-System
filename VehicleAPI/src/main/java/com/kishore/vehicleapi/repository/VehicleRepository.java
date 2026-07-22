package com.kishore.vehicleapi.repository;

import com.kishore.vehicleapi.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle,Integer> {
    boolean existsByVehicleNumber(String vehicleNumber);
    boolean existsByFastagId(String fastagId);
    Vehicle findByVehicleNumber(String vehicleNumber);
    Vehicle findByFastagId(String fastagId);
}
