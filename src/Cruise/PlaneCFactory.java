package Cruise;

public class PlaneCFactory implements CruiseFactory {
    @Override
    public Cruise createCruise() {
        return new PlaneCruise();
    }
}
