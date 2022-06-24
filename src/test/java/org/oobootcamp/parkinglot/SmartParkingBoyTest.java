package org.oobootcamp.parkinglot;

import org.junit.jupiter.api.Test;
import org.oobootcamp.parkinglot.exceptions.FullyParkedException;
import org.oobootcamp.parkinglot.exceptions.InvalidTicketException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SmartParkingBoyTest {

    //     -  Given 停车场A有1个空位，停车场B有2个空位， When 停车时, Then 车停在停车场B，并返回车票
    @Test
    public void should_parked_in_parking_lot_b_when_park_given_parking_lot_a_has_1_vacancy_and_parking_lot_b_has_2_vacancy() {
        ParkingLot parkingLotA = new ParkingLot(1);
        ParkingLot parkingLotB = new ParkingLot(2);
        Car car = new Car();

        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLotA, parkingLotB);
        Ticket ticket = smartParkingBoy.park(car);

        assertThat(parkingLotB.pickUp(ticket)).isSameAs(car);
    }

    //     -  Given 停车场A,B都有2个空位, When 停车, Then 车停在停车场A上，并返回车票
    @Test
    public void should_parked_in_parking_log_a_when_park_given_parking_lot_a_has_1() {}


    // -  Given 两个停车场都没有空位, When 停车 Then 失败，提示停车场已满
    @Test
    void should_throw_fully_parked_exception_when_park_given_all_parking_lots_has_no_vacancy() {
        ParkingLot firstParkingLot = new ParkingLot(0);
        ParkingLot secondParkingLot = new ParkingLot(0);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(firstParkingLot, secondParkingLot);
        Car car = new Car();

        assertThrows(FullyParkedException.class, () -> smartParkingBoy.park(car));
    }

    // -  Given 我的车在停车场中并且有对应停车票, When 取车, Then 可以取走
    @Test
    public void should_return_same_car_when_pick_up_given_a_parked_car_and_corresponding_ticket() {
        ParkingLot parkingLot = new ParkingLot(1);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLot);
        Car car = new Car();
        Ticket ticket = smartParkingBoy.park(car);

        Car pickedCar = smartParkingBoy.pickUp(ticket);

        assertThat(pickedCar).isSameAs(car);
    }

    //     -  Given 有别的停车场的停车票 When 取车 Then 不能取车，提示无效票
    @Test
    public void should_throw_invalid_ticket_exception_when_pick_up_given_a_ticket_from_other_parking_lot() {
        ParkingLot parkingLot = new ParkingLot(1);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLot);
        Car car = new Car();
        ParkingLot anotherParkingLot = new ParkingLot(1);
        Ticket ticket = anotherParkingLot.park(car);

        assertThrows(InvalidTicketException.class, () -> smartParkingBoy.pickUp(ticket));
    }

    //     -  Given 有本停车场使用过的停车票, When 取车, Then 不能取车，提示无效票
    @Test
    public void should_throw_invalid_ticket_exception_when_pick_up_given_a_used_ticket() {
        ParkingLot parkingLot = new ParkingLot(1);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLot);
        Car car = new Car();
        Ticket ticket = smartParkingBoy.park(car);
        smartParkingBoy.pickUp(ticket);

        assertThrows(InvalidTicketException.class, () -> smartParkingBoy.pickUp(ticket));
    }
}
