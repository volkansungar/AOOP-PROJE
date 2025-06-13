package Schedule;


public class PlaneSchedule implements Schedule {
    String koltuk_no;


    @Override
    public String seferSorgula() {
        return koltuk_no;
    }
}
