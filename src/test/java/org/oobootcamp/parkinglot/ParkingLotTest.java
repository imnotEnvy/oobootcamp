package org.oobootcamp.parkinglot;

import org.junit.jupiter.api.Test;
import org.oobootcamp.parkinglot.exceptions.FullyParkedException;
import org.oobootcamp.parkinglot.exceptions.InvalidTicketException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


public class ParkingLotTest {

    //        -  Given 停车场有1个空位, When 停车 Then 成功，返回停车票
    @Test
    public void should_return_ticket_when_park_given_1_vacancies() {
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car();

        Ticket ticket = parkingLot.park(car);

        assertThat(ticket).isNotNull();
    }

    //        -  Given 停车场没有空位, When 停车 Then 失败，提示停车场已满
    @Test
    public void should_throw_fully_parked_exception_when_park_given_no_vacancies() {
        ParkingLot parkingLot = new ParkingLot(0);
        Car car = new Car();

        assertThatThrownBy(() -> parkingLot.park(car)).isInstanceOf(FullyParkedException.class);
    }

    //     -  Given 我的车在停车场并且有对应停车票, When 取车, Then 可以取走
    @Test
    public void should_return_same_car_when_pick_up_given_a_parked_car_and_corresponding_ticket() {
        ParkingLot parkingLot = new ParkingLot(2);
        Car expectedCar = new Car();
        Ticket ticket = parkingLot.park(expectedCar);

        Car actualCar = parkingLot.pickUp(ticket);

        assertThat(actualCar).isEqualTo(expectedCar);
    }

    //     -  Given 有别的停车场的停车票 When 取车 Then 不能取车，提示无效票
    @Test
    public void should_throw_invalid_ticket_exception_when_pick_up_given_a_ticket_from_other_parking_lot() {
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingLot anotherParkingLot = new ParkingLot(1);
        Car car = new Car();
        Ticket ticket = anotherParkingLot.park(car);

        assertThatThrownBy(() -> parkingLot.pickUp(ticket)).isInstanceOf(InvalidTicketException.class);
    }

    //     -  Given 有本停车场使用过的停车票, When 取车, Then 不能取车，提示无效票
    @Test
    public void should_throw_invalid_ticket_exception_when_pick_up_given_a_used_ticket() {
        ParkingLot parkingLot = new ParkingLot(2);
        Car car = new Car();
        Ticket ticket = parkingLot.park(car);
        parkingLot.pickUp(ticket);

        assertThatThrownBy(() -> parkingLot.pickUp(ticket)).isInstanceOf(InvalidTicketException.class);
    }
}

