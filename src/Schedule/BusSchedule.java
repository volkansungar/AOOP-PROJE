package Schedule;

public class BusSchedule implements Schedule {
    String koltuk_no, plaka;

    @Override
    public String seferSorgula() {
        return koltuk_no +" " + plaka;
    }
}
