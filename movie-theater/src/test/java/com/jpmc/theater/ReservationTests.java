package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReservationTests {

    @Test
    void totalFee() {
        var customer = new Customer("John Doe", "unused-id");
        var showing = new Showing(
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1),
                1,
                LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 0))
                // we can't use current for movie start time as it will keep changing depends on when we start to run the test
        );
        assertTrue(new Reservation(customer, showing, 3).totalFee() == 28.5);
        // becuase its sequence day = 1 which is $3 off, so total = 12.5*3 - 3*3 = 28.5
    }
}
