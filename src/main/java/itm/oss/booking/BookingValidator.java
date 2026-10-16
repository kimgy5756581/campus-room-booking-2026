package itm.oss.booking;

import java.time.Duration;
import java.time.LocalTime;

public final class BookingValidator {

    private BookingValidator() {
    }

    /**
     * Issue 1.
     * Implement the behavior defined in the assigned GitHub Issue.
     */
    public static void validate(
            Booking booking,
            Room room,
            LocalTime openTime,
            LocalTime closeTime,
            long maxDurationMinutes) {

        validateBookingTimes(booking);
        validateOperatingHours(booking, openTime, closeTime);
        validateDuration(booking, maxDurationMinutes);
    }

    private static void validateBookingTimes(Booking booking) {
        if (booking.getStart() == null || booking.getEnd() == null) {
            throw new IllegalArgumentException("Booking start and end times are required.");
        }
        if (!booking.getStart().isBefore(booking.getEnd())) {
            throw new IllegalArgumentException("Booking start must be before its end.");
        }
        if (!booking.getStart().toLocalDate().equals(booking.getEnd().toLocalDate())) {
            throw new IllegalArgumentException("Booking must start and end on the same date.");
        }
    }

    private static void validateOperatingHours(
            Booking booking, LocalTime openTime, LocalTime closeTime) {
        if (openTime == null || closeTime == null || !openTime.isBefore(closeTime)) {
            throw new IllegalArgumentException("Operating hours are invalid.");
        }

        LocalTime startTime = booking.getStart().toLocalTime();
        LocalTime endTime = booking.getEnd().toLocalTime();
        if (startTime.isBefore(openTime)) {
            throw new IllegalArgumentException("Booking starts before opening time.");
        }
        if (endTime.isAfter(closeTime)) {
            throw new IllegalArgumentException("Booking ends after closing time.");
        }
    }

    private static void validateDuration(Booking booking, long maxDurationMinutes) {
        if (maxDurationMinutes <= 0) {
            throw new IllegalArgumentException("Maximum duration must be positive.");
        }
        long durationMinutes = Duration.between(booking.getStart(), booking.getEnd()).toMinutes();
        if (durationMinutes > maxDurationMinutes) {
            throw new IllegalArgumentException("Booking exceeds the maximum duration.");
        }
    }
}