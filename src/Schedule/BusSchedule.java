package Schedule;

public class BusSchedule implements Schedule {
    private String koltuk_no, id, destination, date;
    private int capacity;

    @Override
    public String seferSorgula() {
        return koltuk_no;
    }

    @Override
    public void set_id() {

    }

    @Override
    public String get_id() {
        return null;
    }

    @Override
    public void set_seat(String seat) {

    }

    @Override
    public String get_seat() {
        return null;
    }

    @Override
    public void set_destination(String destination) {

    }

    @Override
    public String get_destination() {
        return null;
    }

    @Override
    public void set_date(String date) {

    }

    @Override
    public String get_date() {
        return null;
    }

    @Override
    public void set_capacity(int capacity) {

    }

    @Override
    public int get_capacity() {
        return 0;
    }

    @Override
    public void set_source(String source) {

    }

    @Override
    public String get_source() {
        return null;
    }


}
