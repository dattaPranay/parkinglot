package com.parkingLot.model;

import lombok.Data;

import java.util.List;

@Data
public class ParkingLot {

    private final String name;
    private final List<ParkingFloor> floors;
}
