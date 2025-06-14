package mvc.model;

public class Reservation {
    private String reservationId;
    private String userId;
    private String scheduleId;
    private String scheduleDetails; // For easier display

    public Reservation(String userId, String scheduleId, String scheduleDetails) {
        this.reservationId = java.util.UUID.randomUUID().toString();
        this.userId = userId;
        this.scheduleId = scheduleId;
        this.scheduleDetails = scheduleDetails;
    }

    // Getters
    public String getReservationId() {
        return reservationId;
    }

    public String getUserId() {
        return userId;
    }

    public String getScheduleId() {
        return scheduleId;
    }

    public String getScheduleDetails() {
        return scheduleDetails;
    }
}
