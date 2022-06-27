package org.oobootcamp.parkinglot;

import org.junit.jupiter.api.Test;
import org.oobootcamp.parkinglot.exceptions.FullyParkedException;
import org.oobootcamp.parkinglot.exceptions.InvalidTicketException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

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


    //    -  Given 经理管理了小弟x，他管理的停车场a有1个空位， 经理管理停车场b有1个空位，When 停车时, Then 车停在停车场a，并返回车票
    @Test
    void should_return_ticket_and_parked_at_parking_lot_a_when_park_given_a_parking_boy_x_and_a_parking_lot_b_both_with_1_vacancy() {
        ParkingLot parkingLotA = new ParkingLot(1);
        ParkingLot parkingLotB = new ParkingLot(1);
        GraduateParkingBoy parkingBoy = new GraduateParkingBoy(parkingLotA);
        ParkingManager parkingManager = new ParkingManager(parkingBoy, parkingLotB);
        Car car = new Car();

        Ticket ticket = parkingManager.park(car);

        Car pickedCar = parkingLotA.pickUp(ticket);
        assertThat(pickedCar).isSameAs(car);
    }

    //-  Given 经理管理两个停车场，第一个停车场已满，第二个停车场有1个空位, When 停车 Then 车停在第二个停车场，返回停车票
    @Test
    void should_return_ticket_when_park_given_the_first_parking_lot_is_full_and_the_second_parking_lot_has_vacancy() {
        ParkingLot parkingLotA = new ParkingLot(1);
        ParkingLot parkingLotB = new ParkingLot(1);
        ParkingManager parkingManager = new ParkingManager(parkingLotA, parkingLotB);
        parkingLotA.park(new Car());
        Car car = new Car();

        Ticket ticket = parkingManager.park(car);

        Car pickedCar = parkingLotB.pickUp(ticket);
        assertThat(pickedCar).isSameAs(car);
    }

    // -  Given 一个经理的停车场a和小弟管理的停车场b都没有空位, When 停车 Then 失败，提示停车场已满
    @Test
    void should_throw_fully_parked_exception_when_park_given_all_parking_lots_has_no_vacancy() {
        ParkingLot parkingLotA = new ParkingLot(1);
        ParkingLot parkingLotB = new ParkingLot(1);
        parkingLotA.park(new Car());
        parkingLotB.park(new Car());
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLotB);
        ParkingManager parkingManager = new ParkingManager(parkingLotA, graduateParkingBoy);
        Car car = new Car();

        assertThrows(FullyParkedException.class, () -> parkingManager.park(car));
    }

    // -  Given 我的车在停车场中并且有对应停车票, When 取车, Then 可以取走
    @Test
    public void should_return_same_car_when_pick_up_given_a_parked_car_and_corresponding_ticket() {
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingManager parkingManager = new ParkingManager(parkingLot);
        Car car = new Car();
        Ticket ticket = parkingLot.park(car);

        Car pickedCar = parkingManager.pickUp(ticket);

        assertThat(pickedCar).isSameAs(car);
    }

    //     -  Given 有别的停车场的停车票 When 取车 Then 不能取车，提示无效票
    @Test
    public void should_throw_invalid_ticket_exception_when_pick_up_given_a_ticket_from_other_parking_lot() {
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingManager parkingManager = new ParkingManager(parkingLot);
        Car car = new Car();
        ParkingLot anotherParkingLot = new ParkingLot(1);
        Ticket ticket = anotherParkingLot.park(car);

        assertThrows(InvalidTicketException.class, () -> parkingManager.pickUp(ticket));
    }

    //     -  Given 有本停车场使用过的停车票, When 取车, Then 不能取车，提示无效票
    @Test
    public void should_throw_invalid_ticket_exception_when_pick_up_given_a_used_ticket() {
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingManager parkingManager = new ParkingManager(parkingLot);
        Car car = new Car();
        Ticket ticket = parkingManager.park(car);
        parkingManager.pickUp(ticket);

        assertThrows(InvalidTicketException.class, () -> parkingManager.pickUp(ticket));
    }

}
