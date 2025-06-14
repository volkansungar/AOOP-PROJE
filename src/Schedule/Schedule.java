package Schedule;

public interface Schedule {
    String seferSorgula();
    void set_id();
    String get_id();
    void set_seat(String koltuk_no);
    String get_seat();
    void set_destination(String destination);
    String get_destination();
    void set_date(String date);
    String get_date();
    void set_capacity(int capacity);
    int get_capacity();
    void set_source(String source);
    String get_source();
    String getType();
    int getReservedSeats();
    void setReservedSeats(int count);
}
