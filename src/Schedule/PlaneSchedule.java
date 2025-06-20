package Schedule;

public class PlaneSchedule implements Schedule {
    private String id;
    private String name;
    private String destination;
    private String date;
    private String source;
    private int capacity;
    private int reserved_seats = 0;

    public PlaneSchedule() {
        this.id = java.util.UUID.randomUUID().toString();
    }

    @Override
    public String seferSorgula() {
        return "Plane Voyage: " + name + " from " + source + " to " + destination + " on " + date + " (Capacity: " + capacity + ")";
    }

    @Override
    public void set_id() {
        this.id = java.util.UUID.randomUUID().toString();
    }

    @Override
    public String get_id() {
        return this.id;
    }

    @Override
    public void set_name(String name) {
        this.name = name;
    }

    @Override
    public String get_name() {
        return this.name;
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

    @Override
    public String getType() {
        return "Plane";
    }

    @Override
    public int getReservedSeats() {
        return reserved_seats;
    }

    @Override
    public void setReservedSeats(int reserved_seats) {
        this.reserved_seats = reserved_seats;
    }
}
