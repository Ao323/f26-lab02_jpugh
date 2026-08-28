package edu.cmu.cs214.availability;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;

class AvailabilityCalculatorTest {

    private static final int DAY_START = 540;  // 9:00
    private static final int DAY_END = 1020;   // 17:00

    private final AvailabilityCalculator calc = new AvailabilityCalculator();

    private List<TimeInterval> free(List<TimeInterval> bookings) {
        return calc.freeSlots(DAY_START, DAY_END, bookings);
    }

    @Test
    void fullyBookedDayHasNoFreeSlots() {
        assertTrue(free(List.of(new TimeInterval(DAY_START, DAY_END))).isEmpty());
    }

    @Test
    void bookingUntilEndOfDayLeavesTheMorningFree() {
        assertEquals(List.of(new TimeInterval(540, 720)),
            free(List.of(new TimeInterval(720, DAY_END))));
    }

    @Test
    void gapsBetweenBookingsAreReturned() {
        assertEquals(List.of(new TimeInterval(540, 600), new TimeInterval(660, 900)),
            free(List.of(new TimeInterval(600, 660), new TimeInterval(900, DAY_END))));
    }

    @Test
    void unsortedBookingsAreHandled() {
        assertEquals(List.of(new TimeInterval(540, 600), new TimeInterval(660, 900)),
            free(List.of(new TimeInterval(900, DAY_END), new TimeInterval(600, 660))));
    }

    @Test
    void overlappingBookingsAreMerged() {
        assertEquals(List.of(new TimeInterval(540, 600)),
            free(List.of(new TimeInterval(600, 720), new TimeInterval(660, DAY_END))));
    }

    @Test
    void returnedSlotsNeverOverlapABooking() {
        TimeInterval booking = new TimeInterval(600, 660);
        for (TimeInterval slot : free(List.of(booking))) {
            assertFalse(slot.overlaps(booking));
        }
    }
}
