package com.parkingLot.service;

import com.parkingLot.model.ParkingFloor;
import com.parkingLot.model.ParkingLot;
import com.parkingLot.model.ParkingSlot;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@RequiredArgsConstructor
public class AdminServices {
    private final ParkingLot parkingLot;
    public boolean addFloor() {
        ParkingFloor newFloor = new ParkingFloor();
        parkingLot.getFloors().add(newFloor);
        return true;
    }

    public boolean addParkingSlot(int floorNumber, ParkingSlot slot) {
        parkingLot.getFloors().get(floorNumber).getSlots().add(slot);
        return true;
    }


}
