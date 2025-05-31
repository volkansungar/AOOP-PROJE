package Cruise;

public class PlaneCruise implements Cruise {
    String koltuk_no;


    @Override
    public String seferSorgula() {
        return koltuk_no;
    }
}
