package com.parkingLot.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class ParkingSlot {
    private final int id;
    private final int floor;
    private final VehicleType vehicleType;
    private SlotStatus slotStatus;
    private Ticket ticket;

    public ParkingSlot(int id, int floor, VehicleType vehicleType) {
        this.id = id;
        this.floor = floor;
        this.vehicleType = vehicleType;
        this.slotStatus = SlotStatus.AVAILABLE;
        this.ticket = null;
    }
}