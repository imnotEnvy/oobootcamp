package org.oobootcamp.parkinglot;

import org.junit.jupiter.api.Test;
import org.oobootcamp.parkinglot.exceptions.FullyParkedException;
import org.oobootcamp.parkinglot.exceptions.InvalidTicketException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GraduateParkingBoyTest {
    //-  Given 两个停车场，第一个停车场有1个空位, When 停车 Then 车停在第一个停车场，返回停车票

    @Test
    void should_return_ticket_when_park_given_has_space_in_first_parking_lot() {
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(1);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(firstParkingLot, secondParkingLot);
        Car car = new Car();

        Ticket ticket = graduateParkingBoy.park(car);

        assertThat(ticket).isNotNull();
        assertThat(firstParkingLot.pickUp(ticket)).isSameAs(car);
    }

    //-  Given 第一个停车场已满，第二个停车场有1个空位, When 停车 Then 车停在第二个停车场，返回停车票
    @Test
    void should_return_ticket_when_park_given_the_first_parking_lot_is_full_and_the_second_parking_lot_has_vacancy() {
        ParkingLot firstParkingLot = new ParkingLot(0);
        ParkingLot secondParkingLot = new ParkingLot(1);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(firstParkingLot, secondParkingLot);
        Car car = new Car();

        Ticket ticket = graduateParkingBoy.park(car);

        assertThat(ticket).isNotNull();
        assertThat(secondParkingLot.pickUp(ticket)).isSameAs(car);
    }

    // -  Given 两个停车场都没有空位, When 停车 Then 失败，提示停车场已满
    @Test
    void should_throw_fully_parked_exception_when_park_given_all_parking_lots_has_no_vacancy() {
        ParkingLot firstParkingLot = new ParkingLot(0);
        ParkingLot secondParkingLot = new ParkingLot(0);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(firstParkingLot, secondParkingLot);
        Car car = new Car();

        assertThrows(FullyParkedException.class, () -> graduateParkingBoy.park(car));
    }

    // -  Given 我的车在停车场中并且有对应停车票, When 取车, Then 可以取走
    @Test
    public void should_return_same_car_when_pick_up_given_a_parked_car_and_corresponding_ticket() {
        ParkingLot parkingLot = new ParkingLot(1);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLot);
        Car car = new Car();
        Ticket ticket = graduateParkingBoy.park(car);

        Car pickedCar = graduateParkingBoy.pickUp(ticket);

        assertThat(pickedCar).isSameAs(car);
    }

    //     -  Given 有别的停车场的停车票 When 取车 Then 不能取车，提示无效票
    @Test
    public void should_throw_invalid_ticket_exception_when_pick_up_given_a_ticket_from_other_parking_lot() {
        ParkingLot parkingLot = new ParkingLot(1);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLot);
        Car car = new Car();
        ParkingLot anotherParkingLot = new ParkingLot(1);
        Ticket ticket = anotherParkingLot.park(car);

        assertThrows(InvalidTicketException.class, () -> graduateParkingBoy.pickUp(ticket));
    }

    //     -  Given 有本停车场使用过的停车票, When 取车, Then 不能取车，提示无效票
    @Test
    public void should_throw_invalid_ticket_exception_when_pick_up_given_a_used_ticket() {
        ParkingLot parkingLot = new ParkingLot(1);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLot);
        Car car = new Car();
        Ticket ticket = graduateParkingBoy.park(car);
        graduateParkingBoy.pickUp(ticket);

        assertThrows(InvalidTicketException.class, () -> graduateParkingBoy.pickUp(ticket));
    }
}
