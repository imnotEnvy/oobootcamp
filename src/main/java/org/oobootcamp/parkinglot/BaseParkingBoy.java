package org.oobootcamp.parkinglot;

import org.oobootcamp.parkinglot.exceptions.InvalidTicketException;

import java.util.List;

public abstract class BaseParkingBoy {

    public List<ParkingLot> parkingLots;

    public BaseParkingBoy(ParkingLot... parkingLots) {
        this.parkingLots = List.of(parkingLots);
    }

    abstract public Ticket park(Car car);

    public Car pickUp(Ticket ticket) {
        return parkingLots.stream()
                .filter(parkingLot -> parkingLot.parked(ticket)).findFirst()
                .orElseThrow(InvalidTicketException::new)
                .pickUp(ticket);
    }

    public boolean hasVacancy() {
        return parkingLots.stream().anyMatch(ParkingLot::hasVacancy);
    }


    public boolean parked(Ticket ticket) {
        return parkingLots.stream().anyMatch(parkingLot -> parkingLot.parked(ticket));
    }

}
