package Cruise;

public class BusCFactory implements CruiseFactory {
    @Override
    public Cruise createCruise() {
        return new BusCruise();
    }
}
