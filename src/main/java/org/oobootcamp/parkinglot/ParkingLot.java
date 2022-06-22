package org.oobootcamp.parkinglot;

import org.oobootcamp.parkinglot.exceptions.FullyParkedException;
import org.oobootcamp.parkinglot.exceptions.InvalidTicketException;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private final int capacity;
    private final Map<Ticket, Car> parkedCars;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        this.parkedCars = new HashMap<>();
    }

    public Ticket park(Car car) {
        if (!hasVacancy()) {
            throw new FullyParkedException();
        }
        Ticket ticket = new Ticket();
        parkedCars.put(ticket, car);
        return ticket;
    }

    public Car pickUp(Ticket ticket) {
        if (!parked(ticket)) {
            throw new InvalidTicketException();
        }
        Car car = parkedCars.get(ticket);
        parkedCars.remove(ticket);
        return car;
    }

    public boolean hasVacancy() {
        return parkedCars.size() < capacity;
    }

    public boolean parked(Ticket ticket) {
        return parkedCars.containsKey(ticket);
    }
}
