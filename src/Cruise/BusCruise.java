package Cruise;

public class BusCruise implements Cruise {
    String koltuk_no, plaka;

    @Override
    public String seferSorgula() {
        return koltuk_no +" " + plaka;
    }
}
