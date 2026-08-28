# Architecture

One small component and its tests. Read this before the code.

## The component (`edu.cmu.cs214.availability`)

- `TimeInterval`: a half-open interval `[start, end)` in minutes since midnight
  (600 = 10:00), with an `overlaps` check. Half-open means `start` is inside the
  interval and `end` is not, so `[600, 660)` is 10:00 up to but not including 11:00,
  and a meeting ending at 11:00 does not overlap one starting at 11:00.
- `AvailabilityCalculator.freeSlots(dayStart, dayEnd, bookings)`: returns the free gaps
  in the business day `[dayStart, dayEnd)` that no booking covers. Bookings may arrive
  unsorted, may overlap each other, and may extend outside business hours. The
  calculator is meant to clip them to the day and merge them before computing the gaps.

## The tests

- `AvailabilityCalculatorTest`: an example-based suite that was generated for this
  component. It passes, and its line coverage is high.
- `AvailabilityProperties`: property-based tests (jqwik). One property is provided as an
  example, and it passes too. This is the file you add to in Milestone 1.

## What "correct" means

The calculator has one job, to report exactly the free time. Every minute of the business
day is either covered by a booking or reported as free. Never both, and never neither.
Turning that sentence into a property you can check is a big part of this lab.
