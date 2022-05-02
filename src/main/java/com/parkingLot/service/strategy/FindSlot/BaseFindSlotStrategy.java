package com.parkingLot.service.strategy.FindSlot;

import lombok.RequiredArgsConstructor;
import com.parkingLot.model.*;
import com.parkingLot.service.ParkingService;

@RequiredArgsConstructor
public class BaseFindSlotStrategy implements FindSlotStrategy{

    private final ParkingLot parkingLot;


    @Override
    public ParkingSlot findSlot(Vehicle vehicle) {
        ParkingSlot freeSlot = null;
        for (ParkingFloor parkingFloor: parkingLot.getFloors()) {
            freeSlot = parkingFloor.getSlots()
                    .stream()
                    .filter(slot -> slot.getSlotStatus() == SlotStatus.AVAILABLE && slot.getVehicleType() == vehicle.getVehicleType())
                    .findFirst().orElse(null);
            if (freeSlot != null) break;
        }
        return freeSlot;
    }


}
