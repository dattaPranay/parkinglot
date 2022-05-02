package com.parkingLot.service.strategy.FindSlot;


import com.parkingLot.model.ParkingSlot;
import com.parkingLot.model.Vehicle;

public interface FindSlotStrategy {

    ParkingSlot findSlot(Vehicle vehicle);
}
