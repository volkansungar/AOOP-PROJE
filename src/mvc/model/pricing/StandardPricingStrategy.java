package mvc.model.pricing;

import Schedule.Schedule;
import mvc.patterns.PricingStrategy;

/**
 * A standard pricing strategy.
 * For this example, Bus tickets are a flat 50.0 and Plane tickets are a flat 200.0.
 */
public class StandardPricingStrategy implements PricingStrategy {
    private static final double BUS_PRICE = 50.0;
    private static final double PLANE_PRICE = 200.0;

    @Override
    public double calculatePrice(Schedule schedule) {
        if ("Bus".equals(schedule.getType())) {
            return BUS_PRICE;
        } else if ("Plane".equals(schedule.getType())) {
            return PLANE_PRICE;
        }
        return 0.0; // Default price
    }
}