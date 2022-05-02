package com.parkingLot.service;

import com.parkingLot.exceptions.NoTicketFound;

import lombok.RequiredArgsConstructor;
import com.parkingLot.model.*;


import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashMap;
import java.util.UUID;
import java.util.function.ToDoubleBiFunction;

@RequiredArgsConstructor
public class TicketService {
    private final HashMap<String, Ticket> ticketDb;
    private final String parkingLotName;

    public Ticket createTicket(Vehicle vehicle, ParkingSlot slot) {
        String ticketId = parkingLotName + "_" + slot.getFloor() + "_" + slot.getId() + UUID.randomUUID().toString();
        Ticket newTicket = new Ticket(ticketId, vehicle, slot, Timestamp.from(Instant.now()));
        ticketDb.put(ticketId, newTicket);
        return newTicket;
    }

    public Ticket getTicket(String ticketId) throws NoTicketFound {
        if(!ticketDb.containsKey(ticketId)) {

            throw new NoTicketFound();
        }
        return ticketDb.get(ticketId);
    }

    public boolean unpackVehicle(String ticketId) throws NoTicketFound {
        Ticket ticketObject = getTicket(ticketId);
        if (ticketObject.getExitTime() != null) return false;
        ticketObject.getSlot().setSlotStatus(SlotStatus.AVAILABLE);
        ticketObject.setExitTime(Timestamp.from(Instant.now()));
        return true;
    }
}
