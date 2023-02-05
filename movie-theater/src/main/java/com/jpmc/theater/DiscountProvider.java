package com.jpmc.theater;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

// Here, create DiscountProvider to do the calculate the results of maximum discount
// Seprate the discount from Movie.class as it should be showing under Reservation.class part
public class DiscountProvider {
    DiscountProvider(){
    }
    private static DiscountProvider instance = null;
    public static DiscountProvider singleton() {
        if (instance == null) {
            instance = new DiscountProvider();
        }
        return instance;
    }
    // case 1: special discount (20% off)
    private double getSpeicalDiscount(Showing showing){
        double specialDiscount = 0;
        if (showing.getMovie().getMovieCodeSpecial() == showing.getMovie().getSpecialCode()) {
            specialDiscount = showing.getMovie().getTicketPrice() * 0.2;  // 20% discount for special movie
        }
        return specialDiscount;
    }
    // case 2: speical day discount
    private double getSequenceDiscount(Showing showing){
        double sequenceDiscount = 0;
        double showSequence = showing.getSequenceOfTheDay();
        if (showSequence == 1) {
            sequenceDiscount = 3; // $3 discount for 1st show
        } else if (showSequence == 2) {
            sequenceDiscount = 2; // $2 discount for 2nd show
        } else if (showSequence == 7){
            sequenceDiscount = 1; // $1 discount for 7th show
        }
        return sequenceDiscount;
    }
    // case 1: special non-peak discount (25% off)
    private double getNonPeakDiscount(Showing showing){
        double nonPeakDiscount = 0;
        LocalDateTime startTime = showing.getStartTime();
        if (startTime.getHour() >= 11 && startTime.getHour() < 16){
            nonPeakDiscount = showing.getMovie().getTicketPrice() * 0.25;
        }
        return nonPeakDiscount;
    }
    public double getMaximumDiscount(Showing showing){
        double specialDiscount = getSpeicalDiscount(showing);
        double sequenceDiscount = getSequenceDiscount(showing);
        double nonPeakDiscount = getNonPeakDiscount(showing);

        double [] discounts = {specialDiscount, sequenceDiscount, nonPeakDiscount};
        return Arrays.stream(discounts).max().getAsDouble();
    }
}
