package com.parkingLot.service;

import com.parkingLot.exceptions.NoTicketFound;
import com.parkingLot.exceptions.ParkingLotFull;
import com.parkingLot.model.*;

import com.parkingLot.service.strategy.FindSlot.FindSlotStrategy;
import lombok.RequiredArgsConstructor;


import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ParkingService {
    private final ParkingLot parkingLot;
    private final TicketService ticketService;
    private final FindSlotStrategy findSlotStrategy;
    private ParkingSlot findSlot(Vehicle vehicle) {
        return findSlotStrategy.findSlot(vehicle);
    }

    public Ticket bookTicket(Vehicle vehicle) throws ParkingLotFull {
        ParkingSlot freeSlot = findSlot(vehicle);
        if (freeSlot == null) {
            throw new ParkingLotFull();
        }
        freeSlot.setSlotStatus(SlotStatus.OCCUPIED);
        Ticket ticket = ticketService.createTicket(vehicle, freeSlot);
        return ticket;
    }

    public boolean unpackVehicle(String ticket) throws NoTicketFound {
        return ticketService.unpackVehicle(ticket);
    }

    public List<Long> getSlotCountByType(VehicleType vehicleType, SlotStatus slotStatus) {
        List<Long> freeSlotList = parkingLot
                .getFloors()
                .stream()
                .map(parkingFloor ->
                parkingFloor
                        .getSlots()
                        .stream()
                        .filter(slot -> slot.getVehicleType().equals(vehicleType) &&
                                slot.getSlotStatus().equals(slotStatus))
                        .count())
                .collect(Collectors.toList());
        return freeSlotList;
    }

    public List<ParkingSlot> getSlotsByType(VehicleType vehicleType, SlotStatus slotStatus) {
        List<ParkingSlot> freeSlotList = parkingLot
                .getFloors()
                .stream()
                .flatMap(parkingFloor ->
                        parkingFloor
                                .getSlots()
                                .stream()
                                .filter(slot -> slot.getVehicleType().equals(vehicleType) &&
                                                slot.getSlotStatus().equals(slotStatus)))
                .collect(Collectors.toList());
        return freeSlotList;
    }



}
