package org.oobootcamp.parkinglot;

import org.oobootcamp.Parkable;
import org.oobootcamp.parkinglot.exceptions.FullyParkedException;
import org.oobootcamp.parkinglot.exceptions.InvalidTicketException;

import java.util.List;


public class ParkingManager {

    private final List<Parkable> parkables;

    public ParkingManager(Parkable... parkables) {
        this.parkables = List.of(parkables);
    }

    public Ticket park(Car car) {
        return parkables.stream()
                .filter((Parkable::hasVacancy)).findFirst()
                .orElseThrow(FullyParkedException::new)
                .park(car);
    }

    public Car pickUp(Ticket ticket) {
        return parkables.stream()
                .filter(parkable -> parkable.parked(ticket)).findFirst()
                .orElseThrow(InvalidTicketException::new)
                .pickUp(ticket);
    }


}
