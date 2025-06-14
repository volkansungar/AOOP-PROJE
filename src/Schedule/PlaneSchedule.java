package Schedule;

/**
 * Corrected implementation of the Schedule interface for a Plane.
 * The getter and setter methods have been properly implemented to store and retrieve data.
 */
public class PlaneSchedule implements Schedule {
    // Private fields to hold the schedule's data.
    private String id; // It's good practice to have a unique ID.
    private String seat; // This will store the voyage name as per the logic in UserController.
    private String destination;
    private String date;
    private String source;
    private int capacity;

    // A simple constructor could be useful.
     PlaneSchedule() {
        // You could initialize default values here if needed.
        // For example, generate a unique ID.
        this.id = java.util.UUID.randomUUID().toString();
    }


    @Override
    public String seferSorgula() {
        // This method's implementation depends on its purpose.
        // For now, it can return a summary.
        return "Voyage: " + seat + " from " + source + " to " + destination;
    }

    @Override
    public void set_id() {
        // The ID is set in the constructor, but you could provide a manual override if needed.
        this.id = java.util.UUID.randomUUID().toString();
    }

    @Override
    public String get_id() {
        return this.id;
    }

    @Override
    public void set_seat(String seat) {
        // Set the instance's 'seat' variable to the value passed in.
        this.seat = seat;
    }

    @Override
    public String get_seat() {
        // Return the stored 'seat' value.
        return this.seat;
    }

    @Override
    public void set_destination(String destination) {
        this.destination = destination;
    }

    @Override
    public String get_destination() {
        return this.destination;
    }

    @Override
    public void set_date(String date) {
        this.date = date;
    }

    @Override
    public String get_date() {
        return this.date;
    }

    @Override
    public void set_capacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public int get_capacity() {
        return this.capacity;
    }

    @Override
    public void set_source(String source) {
        this.source = source;
    }

    @Override
    public String get_source() {
        return this.source;
    }
}
