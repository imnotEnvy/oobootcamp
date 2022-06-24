package org.oobootcamp.parkinglot;

import org.oobootcamp.parkinglot.exceptions.FullyParkedException;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class SmartParkingBoy extends GraduateParkingBoy {


    public SmartParkingBoy(ParkingLot... parkingLots) {
        super(parkingLots);
    }

    public Ticket park(Car car) {
        Optional<ParkingLot> parkingLot = parkingLotList.stream().filter(ParkingLot::hasVacancy).max(Comparator.comparingInt(ParkingLot::getVacanciesCount));
        if (parkingLot.isPresent()) {
            return parkingLot.get().park(car);
        } else {
            throw new FullyParkedException();
        }

    }
}
