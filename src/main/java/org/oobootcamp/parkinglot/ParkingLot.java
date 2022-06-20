package org.oobootcamp.parkinglot;

import org.oobootcamp.parkinglot.exceptions.FullyParkedException;
import org.oobootcamp.parkinglot.exceptions.InvalidTicketException;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private int vacancies = 0;
    private final Map<Ticket, Car> lot;

    public ParkingLot(int vacancies) {
        this.vacancies = vacancies;
        this.lot = new HashMap<Ticket, Car>();
    }

    public Ticket park(Car car) {
        if (this.vacancies == 0) {
            throw new FullyParkedException();
        }
        Ticket ticket = new Ticket();
        lot.put(ticket, car);
        return ticket;
    }

    public Car pickUp(Ticket ticket) {
        Car car = lot.get(ticket);
        if (car == null) {
            throw new InvalidTicketException();
        }
        lot.remove(ticket);
        return car;
    }
}
