package com.parkingLot.model;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ParkingFloor {
    private final List<ParkingSlot> slots;

    public ParkingFloor() {
        slots = new ArrayList<>();
    }
}