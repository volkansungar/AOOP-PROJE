package test;

import mvc.model.Reservation;
import mvc.model.ReservationManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ReservationManagerTest {

    private ReservationManager reservationManager;
    private Reservation res1;
    private Reservation res2;

    @BeforeEach
    void setUp() {
        reservationManager = new ReservationManager();
        // Sample reservations for testing
        res1 = new Reservation("user123", "scheduleABC", 5, "Flight F-101", 10);
        res2 = new Reservation("user456", "scheduleABC", 8, "Flight F-101", 10);
    }

    @Test
    void testAddReservation() {
        // Initially, there should be no reservations
        assertTrue(reservationManager.getAllReservations().isEmpty(), "Reservation list should be initially empty.");

        // Add a reservation
        reservationManager.addReservation(res1);

        // Verify it was added
        assertEquals(1, reservationManager.getAllReservations().size(), "Reservation list should have 1 item after adding.");
        assertEquals(res1, reservationManager.findReservationById(res1.getReservationId()), "The added reservation should be findable by its ID.");
    }

    @Test
    void testCancelReservation() {
        // Add two reservations
        reservationManager.addReservation(res1);
        reservationManager.addReservation(res2);
        assertEquals(2, reservationManager.getAllReservations().size(), "Should have 2 reservations before cancelling.");

        // Cancel the first reservation
        reservationManager.cancelReservation(res1.getReservationId());

        // Verify it was removed
        assertEquals(1, reservationManager.getAllReservations().size(), "Should have 1 reservation after cancelling.");
        assertNull(reservationManager.findReservationById(res1.getReservationId()), "Cancelled reservation should not be findable.");
        assertNotNull(reservationManager.findReservationById(res2.getReservationId()), "Other reservation should still exist.");
    }

    @Test
    void testGetReservationsForUser() {
        Reservation resUser1Another = new Reservation("user123", "scheduleXYZ", 1, "Bus B-202", 10);
        reservationManager.addReservation(res1); // user123
        reservationManager.addReservation(res2); // user456
        reservationManager.addReservation(resUser1Another); // user123

        // Get reservations for user123
        var user1Reservations = reservationManager.getReservationsForUser("user123");
        assertEquals(2, user1Reservations.size(), "User 'user123' should have 2 reservations.");

        // Get reservations for a user with no reservations
        var noUserReservations = reservationManager.getReservationsForUser("nonexistentUser");
        assertTrue(noUserReservations.isEmpty(), "A user with no reservations should have an empty list.");
    }

    @Test
    void testGetReservationCountForSchedule() {
        reservationManager.addReservation(res1); // scheduleABC
        reservationManager.addReservation(res2); // scheduleABC

        // Check count for scheduleABC
        assertEquals(2, reservationManager.getReservationCountForSchedule("scheduleABC"), "Schedule 'scheduleABC' should have 2 reservations.");

        // Check count for a schedule with no reservations
        assertEquals(0, reservationManager.getReservationCountForSchedule("scheduleXYZ"), "A schedule with no reservations should have a count of 0.");
    }

    @Test
    void testGetReservedSeatNumbers() {
        reservationManager.addReservation(res1); // Seat 5
        reservationManager.addReservation(res2); // Seat 8

        var reservedSeats = reservationManager.getReservedSeatNumbers("scheduleABC");
        assertEquals(2, reservedSeats.size(), "There should be 2 reserved seats for the schedule.");
        assertTrue(reservedSeats.contains(5), "List of reserved seats should contain seat 5.");
        assertTrue(reservedSeats.contains(8), "List of reserved seats should contain seat 8.");
        assertFalse(reservedSeats.contains(10), "List should not contain a non-reserved seat.");
    }
}