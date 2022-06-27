package org.oobootcamp.parkinglot;

import org.oobootcamp.Parkable;
import org.oobootcamp.parkinglot.exceptions.FullyParkedException;

import java.util.Comparator;

public class SmartParkingBoy extends BaseParkingBoy implements Parkable {

    public SmartParkingBoy(ParkingLot... parkingLots) {
        super(parkingLots);
    }

    public Ticket park(Car car) {
        return parkingLots.stream()
                .filter(ParkingLot::hasVacancy)
                .max(Comparator.comparingInt(ParkingLot::getVacancies))
                .orElseThrow(FullyParkedException::new).park(car);
    }
}
