package edu.cmu.cs214.availability;

/**
 * A half-open time interval {@code [start, end)}. Times are integer minutes
 * since midnight (600 = 10:00), which keeps the interval arithmetic easy to read.
 */
public record TimeInterval(int start, int end) {

    public TimeInterval {
        if (start >= end) {
            throw new IllegalArgumentException(
                "Interval must be non-empty: [" + start + ", " + end + ")");
        }
    }

    /**
     * Do this interval and {@code other} overlap? Two half-open intervals overlap
     * iff each starts before the other ends. Intervals that merely touch (one ends
     * exactly when the next begins) do not overlap.
     */
    public boolean overlaps(TimeInterval other) {
        return start < other.end && other.start < end;
    }
}
