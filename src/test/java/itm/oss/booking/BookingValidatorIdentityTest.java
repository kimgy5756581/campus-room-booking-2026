package itm.oss.booking;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

import org.junit.jupiter.api.Test;

class BookingValidatorIdentityTest {

    private static final Room ROOM = new Room("R101", "Study Room", 4, Set.of());

    @Test
    void acceptsBookingWithMatchingRequiredIdentityValues() {
        assertDoesNotThrow(() -> BookingValidator.validate(
                booking("s2026001", "R101"), ROOM, LocalTime.of(9, 0), LocalTime.of(18, 0), 120));
    }

    @Test
    void rejectsMissingBookingOrRoom() {
        assertThrows(IllegalArgumentException.class, () -> BookingValidator.validate(
                null, ROOM, LocalTime.of(9, 0), LocalTime.of(18, 0), 120));
        assertThrows(IllegalArgumentException.class, () -> BookingValidator.validate(
                booking("s2026001", "R101"), null, LocalTime.of(9, 0), LocalTime.of(18, 0), 120));
    }

    @Test
    void rejectsBlankUserOrRoomIdAndMismatchedRoom() {
        assertThrows(IllegalArgumentException.class, () -> BookingValidator.validate(
                booking(" ", "R101"), ROOM, LocalTime.of(9, 0), LocalTime.of(18, 0), 120));
        assertThrows(IllegalArgumentException.class, () -> BookingValidator.validate(
                booking("s2026001", ""), ROOM, LocalTime.of(9, 0), LocalTime.of(18, 0), 120));
        assertThrows(IllegalArgumentException.class, () -> BookingValidator.validate(
                booking("s2026001", "R102"), ROOM, LocalTime.of(9, 0), LocalTime.of(18, 0), 120));
    }

    private Booking booking(String userId, String roomId) {
        return new Booking(1, userId, roomId,
                LocalDateTime.of(2026, 10, 2, 10, 0),
                LocalDateTime.of(2026, 10, 2, 11, 0),
                2, "Study", BookingStatus.ACTIVE);
    }
}
