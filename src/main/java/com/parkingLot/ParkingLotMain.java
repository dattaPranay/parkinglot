package com.parkingLot;

import com.parkingLot.exceptions.NoTicketFound;
import com.parkingLot.exceptions.ParkingLotFull;
import com.parkingLot.model.*;
import com.parkingLot.service.AdminServices;
import com.parkingLot.service.ParkingService;
import com.parkingLot.service.TicketService;
import com.parkingLot.service.strategy.FindSlot.BaseFindSlotStrategy;
import com.parkingLot.service.strategy.FindSlot.FindSlotStrategy;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.IntStream;


@RequiredArgsConstructor
public class ParkingLotMain {
    private AdminServices adminServices;
    private TicketService ticketService;
    private ParkingService parkingService;

    private void createParkingLot(String name, int floors, int slots) {
        ParkingLot parkingLot = new ParkingLot(name, new ArrayList<>());
        adminServices = new AdminServices(parkingLot);
        ticketService = new TicketService(new HashMap<>(), name);
        FindSlotStrategy findSlotStrategy = new BaseFindSlotStrategy(parkingLot);
        parkingService = new ParkingService(parkingLot, ticketService, findSlotStrategy);
        IntStream.range(0, floors).forEach(n-> {
            adminServices.addFloor();
            IntStream.range(0, slots).forEach(slotted -> {
                ParkingSlot p = new ParkingSlot(slotted, n, VehicleType.BIKE);
                adminServices.addParkingSlot(n,p);
            });
        });
    }

    private Ticket parkVehicle(VehicleType vehicleType, String reg, Color color) throws ParkingLotFull {
        Vehicle vehicle = new Vehicle(reg,vehicleType, color);
        Ticket ticket = parkingService.bookTicket(vehicle);
        System.out.println(ticket.toString());
        return ticket;
    }

    private void unpackVehicle(String ticketId) throws NoTicketFound {
        System.out.println( ticketService.unpackVehicle(ticketId));
    }

    private void displayInfo(VehicleType vehicleType, String type) {
        switch (type) {
            case "free_slots" :
                parkingService.getSlotsByType(vehicleType, SlotStatus.AVAILABLE).stream()
                        .forEach(System.out::println);
                break;
            case "free_count":
                parkingService.getSlotCountByType(vehicleType,SlotStatus.AVAILABLE).stream()
                        .forEach(System.out::println);
                break;
        }
    }

    public static void main(String[] args) throws ParkingLotFull, NoTicketFound {
        ParkingLotMain pk = new ParkingLotMain();
        pk.createParkingLot("the Park", 4, 10);
        Ticket t = pk.parkVehicle(VehicleType.BIKE,"12345", Color.GREEN);
        pk.parkVehicle(VehicleType.BIKE,"12335", Color.BLUE);
        //pk.parkVehicle(VehicleType.TRUCK,"12245", Color.WHITE);
        pk.displayInfo(VehicleType.BIKE,"free_slots");
        pk.unpackVehicle(t.getId());
        pk.unpackVehicle(t.getId());
        pk.unpackVehicle("jj");

    }

}
