package org.oobootcamp.parkinglot;

import org.oobootcamp.parkinglot.exceptions.FullyParkedException;
import org.oobootcamp.parkinglot.exceptions.InvalidTicketException;

import java.util.Arrays;
import java.util.List;


public class GraduateParkingBoy extends BaseParkingBoy {
    
    public GraduateParkingBoy(ParkingLot... parkingLots) {
        super(parkingLots);
    }

    public Ticket park(Car car) {
        return parkingLots.stream()
                .filter(ParkingLot::hasVacancy).findFirst()
                .orElseThrow(FullyParkedException::new)
                .park(car);
    }

}
