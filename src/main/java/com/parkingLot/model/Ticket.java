package com.parkingLot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;

@Data
@RequiredArgsConstructor
public class Ticket {
    private final String id;
    private final Vehicle vehicle;
    private final ParkingSlot slot;
    private final Timestamp entryTime;
    private Timestamp exitTime;
}
