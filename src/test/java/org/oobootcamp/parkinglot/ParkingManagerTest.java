package org.oobootcamp.parkinglot;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ParkingManagerTest {
//    -  Given 经理管理了停车场a，1个空位，小弟boy，他管理的停车场b有1个空位， When 停车时, Then 车停在停车场a，并返回车票

    @Test
    void should_return_ticket_when_park_given_manager_manages_a_parking_lot_with_1_vacancy_and_a_parking_boy_with_1_vacancy(){
        ParkingLot parkingLotA = new ParkingLot(1);
        ParkingLot parkingLotB = new ParkingLot(1);
        GraduateParkingBoy parkingBoy = new GraduateParkingBoy(parkingLotB);
        ParkingManager parkingManager = new ParkingManager(parkingLotA, parkingBoy);
        Car car = new Car();

        Ticket ticket = parkingManager.park(car);

        Car pickedCar = parkingLotA.pickUp(ticket);
        assertThat(pickedCar).isSameAs(car);

    }
//    -  Given 经理管理了小弟x，他管理的停车场xa有1个空位， 停车场a，1个空位，When 停车时, Then 车停在停车场xa，并返回车票
}
