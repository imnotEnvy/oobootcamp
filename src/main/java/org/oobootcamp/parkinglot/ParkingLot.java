package org.oobootcamp.parkinglot;

import org.oobootcamp.parkinglot.exceptions.FullyParkedException;
import org.oobootcamp.parkinglot.exceptions.InvalidTicketException;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private int vacancies;
    private final Map<Ticket, Car> lot;

    public ParkingLot(int vacancies) {
        this.vacancies = vacancies;
        this.lot = new HashMap<>();
    }

    public Ticket park(Car car) {
        if (!hasVacancy()) {
            throw new FullyParkedException();
        }
        vacancies --;
        Ticket ticket = new Ticket();
        lot.put(ticket, car);
        return ticket;
    }

    public Car pickUp(Ticket ticket) {
        if (!parked(ticket)) {
            throw new InvalidTicketException();
        }
        Car car = lot.get(ticket);
        vacancies ++;
        lot.remove(ticket);
        return car;
    }

    public boolean hasVacancy(){
        return vacancies > 0;
    }

    public boolean parked(Ticket ticket){
        return lot.containsKey(ticket);
    }
}
