package org.oobootcamp.parkinglot;

import org.oobootcamp.parkinglot.exceptions.FullyParkedException;
import org.oobootcamp.parkinglot.exceptions.InvalidTicketException;

import java.util.Arrays;


public class GraduateParkingBoy {
    private final ParkingLot[] parkingLots;

    public GraduateParkingBoy(ParkingLot... parkingLots) {
        this.parkingLots = parkingLots;
    }

    public Ticket park(Car car) {
        return Arrays.stream(parkingLots)
                .filter(ParkingLot::hasVacancy).findFirst()
                .orElseThrow(FullyParkedException::new)
                .park(car);
    }

    public Car pickUp(Ticket ticket) {
        return Arrays.stream(parkingLots)
                .filter(parkingLot -> parkingLot.parked(ticket)).findFirst()
                .orElseThrow(InvalidTicketException::new)
                .pickUp(ticket);
    }
}
