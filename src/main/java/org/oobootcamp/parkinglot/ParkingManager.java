package org.oobootcamp.parkinglot;

import org.oobootcamp.Parkable;
import org.oobootcamp.parkinglot.exceptions.FullyParkedException;

import java.util.List;


public class ParkingManager {

    private List<Parkable> parkables;

    public ParkingManager(Parkable... parkables) {
        this.parkables = List.of(parkables);
    }

    public Ticket park(Car car) {
        return parkables.stream()
                .filter((Parkable::hasVacancy)).findFirst()
                .orElseThrow(FullyParkedException::new)
                .park(car);
    }
}
