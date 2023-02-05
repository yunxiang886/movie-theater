package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TheaterTests {
    @Test
    void totalFeeForCustomerWithNonPeakDiscount() {
        Theater theater = new Theater(LocalDateProvider.singleton());
        Customer john = new Customer("John Doe", "id-12345");
        Reservation reservation = theater.reserve(john, 2, 4);
        // original total is 12.5*4 =50, but since it starts at 11am, so it has 25% discount = 37.5
        assertEquals(37.5, reservation.totalFee());
    }

    @Test
    void totalFeeForCustomerWithSpecicalDiscount() {
        Theater theater = new Theater(LocalDateProvider.singleton());
        Customer john = new Customer("John Doe", "id-12345");
        Reservation reservation = theater.reserve(john, 5, 4);
        // original total is 12.5*4 =50, but since it spider-man(special code), so it has 20% discount = 40
        assertEquals(40, reservation.totalFee());
    }

    @Test
    void totalFeeForCustomerWithSequenceDiscount() {
        Theater theater = new Theater(LocalDateProvider.singleton());
        Customer john = new Customer("John Doe", "id-12345");
        Reservation reservation = theater.reserve(john, 1, 4);
        // original total is 11*4 =44, but since it meet sequence , so it has $3 off per discount = 32
        assertEquals(32, reservation.totalFee());
    }

    @Test
    void totalFeeForCustomerWithMaximumDiscount() {
        Theater theater = new Theater(LocalDateProvider.singleton());
        Customer john = new Customer("John Doe", "id-12345");
        Reservation reservation = theater.reserve(john, 4, 4);
        // original total is 11*4 =44, it meet sequence discount and non peak discount , so it has 25% off per discount = 33
        assertEquals(33, reservation.totalFee());
    }

    @Test
    void printMovieSchedule() {
        Theater theater = new Theater(LocalDateProvider.singleton());
        theater.printSchedule();
    }

    @Test
    void printMovieJsonSchedule() {
        Theater theater = new Theater(LocalDateProvider.singleton());
        theater.printJsonSchedule();
    }
}
