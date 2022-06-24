package org.oobootcamp.parkinglot;

import org.oobootcamp.parkinglot.exceptions.FullyParkedException;
import org.oobootcamp.parkinglot.exceptions.InvalidTicketException;

import java.util.Arrays;
import java.util.List;


public class GraduateParkingBoy extends BaseParkingBoy {

    public List<ParkingLot> parkingLotList;

    public GraduateParkingBoy(ParkingLot... parkingLots) {
        this.parkingLotList = List.of(parkingLots);
    }

    public Ticket park(Car car) {
        return parkingLotList.stream()
                .filter(ParkingLot::hasVacancy).findFirst()
                .orElseThrow(FullyParkedException::new)
                .park(car);
    }

    public Car pickUp(Ticket ticket) {
        return parkingLotList.stream()
                .filter(parkingLot -> parkingLot.parked(ticket)).findFirst()
                .orElseThrow(InvalidTicketException::new)
                .pickUp(ticket);
    }
}
