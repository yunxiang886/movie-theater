package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DiscountProviderTests {

    // test: no discount at all
    @Test
    void testGetZeroDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 3);
        Showing showing = new Showing(spiderMan, 5, LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 0)));
        assertEquals(0, DiscountProvider.singleton().getMaximumDiscount(showing));
    }

    // test: only special discount available, should return 20% off
    @Test
    void testGetSpeicalDiscountOnly() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 1);
        Showing showing = new Showing(spiderMan, 5, LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 0)));
        assertEquals(2.5, DiscountProvider.singleton().getMaximumDiscount(showing));
    }

    // test: only sequence discount available (sequence = 1), should return $3 off
    @Test
    void testGetSequenceDiscountEqualsOneOnly() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 2);
        Showing showing = new Showing(spiderMan, 1, LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 0)));
        assertEquals(3, DiscountProvider.singleton().getMaximumDiscount(showing));
    }
    // test: only sequence discount available (sequence = 2), should return $2 off
    @Test
    void testGetSequenceDiscountEqualsTwoOnly() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 2);
        Showing showing = new Showing(spiderMan, 2, LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 0)));
        assertEquals(2, DiscountProvider.singleton().getMaximumDiscount(showing));
    }
    // test: only sequence discount available (sequence = 7), should return $1 off
    @Test
    void testGetSequenceDiscountEqualsSevenOnly() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 2);
        Showing showing = new Showing(spiderMan, 7, LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 0)));
        assertEquals(1, DiscountProvider.singleton().getMaximumDiscount(showing));
    }

    // test: only non-peak discount available (between 11am-4pm), should return 25% off
    @Test
    void testGetNonPeakDiscountOnly() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 2);
        Showing showing = new Showing(spiderMan, 5, LocalDateTime.of(LocalDate.now(), LocalTime.of(12, 0)));
        assertEquals(3.125, DiscountProvider.singleton().getMaximumDiscount(showing));
    }

    // test: meet mutiple discount requirements, return maximum discount only
    @Test
    void testGetMaximumDiscountOnlyWhenMutipleDiscountMeets() {
        // here it meet 3 discount: speicalDiscount = 2.5, squenceDiscount = 3, nonPeakDiscount = 3.125, it should return the maximum = 3.125
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 1);
        Showing showing = new Showing(spiderMan, 2, LocalDateTime.of(LocalDate.now(), LocalTime.of(12, 0)));
        assertEquals(3.125, DiscountProvider.singleton().getMaximumDiscount(showing));
    }
}
