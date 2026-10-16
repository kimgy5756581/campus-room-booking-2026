package itm.oss.booking;

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
                // Validate room capacity and attendees
                validateAttendees(booking,room);
                // Validate booking
                if (booking.getStatus() == null) {
                    throw new IllegalArgumentException(
                        "Booking status cannot be null");
                }
    }

    private static void validateAttendees(
        Booking booking, Room room) {

            if (booking.getAttendees() <= 0) {
                throw new IllegalArgumentException("Attendees must be greater than zero");
            }
            
            if (booking.getAttendees() > room.getCapacity()) {
                throw new IllegalArgumentException(
                    "Room capacity exceeded");
                        }
        }
}
