package org.oobootcamp;

import org.oobootcamp.parkinglot.Car;
import org.oobootcamp.parkinglot.Ticket;

public interface Parkable {

    Ticket park(Car car);

    Car pickUp(Ticket ticket);

    boolean hasVacancy();

    boolean parked(Ticket ticket);
}
