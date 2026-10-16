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
        validateRequiredObjects(booking, room);
        validateIdentity(booking, room);
    }

    private static void validateRequiredObjects(Booking booking, Room room) {
        if (booking == null) {
            throw new IllegalArgumentException("Booking is required.");
        }
        if (room == null) {
            throw new IllegalArgumentException("Room is required.");
        }
    }

    private static void validateIdentity(Booking booking, Room room) {
        if (isBlank(booking.getUserId())) {
            throw new IllegalArgumentException("User ID is required.");
        }
        if (isBlank(booking.getRoomId())) {
            throw new IllegalArgumentException("Booking room ID is required.");
        }
        if (!booking.getRoomId().equals(room.getId())) {
            throw new IllegalArgumentException("Booking room ID must match the supplied room.");
        }
    }

    private static boolean isBlank(String value) {
        return value == null || value.isBlank();
    }
}
