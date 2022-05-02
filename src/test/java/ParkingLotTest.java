import com.parkingLot.ParkingLotMain;
import com.parkingLot.service.strategy.FindSlot.BaseFindSlotStrategy;
import com.parkingLot.service.strategy.FindSlot.FindSlotStrategy;
import org.junit.Test;
import com.parkingLot.service.*;
import com.parkingLot.model.*;

import java.util.ArrayList;
import java.util.HashMap;

public class ParkingLotTest {


    @Test
    public void defaultTest() {
        ParkingLot parkingLot = new ParkingLot("The Park parking", new ArrayList<>());
        AdminServices adminServices = new AdminServices(parkingLot);
        TicketService ticketService = new TicketService(new HashMap<>(), " ");
        FindSlotStrategy findSlotStrategy = new BaseFindSlotStrategy(parkingLot);
        ParkingService parkingService = new ParkingService(parkingLot, ticketService, findSlotStrategy);

        adminServices.addFloor();
        adminServices.addFloor();
        adminServices.addFloor();
        adminServices.addFloor();

    }

    void createSlots() {
        String id = "";

    }

}
