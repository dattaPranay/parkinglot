package com.parkingLot.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Vehicle {
    private String registrationNumber;
    private VehicleType vehicleType;
    private Color color;
}
