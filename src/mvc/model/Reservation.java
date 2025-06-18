package mvc.model;

public class Reservation {
    private String reservationId;
    private String userId;
    private String scheduleId;
    private int seatNumber;
    // Added to store the specific seat
    private String scheduleDetails;
    private double price; // Added price field

    public Reservation(String userId, String scheduleId, int seatNumber, String scheduleDetails, double price) {
        this.reservationId = java.util.UUID.randomUUID().toString();
        this.userId = userId;
        this.scheduleId = scheduleId;
        this.seatNumber = seatNumber;
        this.scheduleDetails = scheduleDetails;
        this.price = price;
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

    public int getSeatNumber() {
        return seatNumber;
    }

    public String getScheduleDetails() {
        return scheduleDetails;
    }

    public double getPrice() {
        return price;
    }
}