package mvc.model.pricing;

import Schedule.Schedule;
import mvc.patterns.PricingStrategy;

/**
 * A dynamic pricing strategy where the price increases as the vehicle fills up.
 */
public class DynamicPricingStrategy implements PricingStrategy {
    private static final double BASE_BUS_PRICE = 40.0;
    private static final double BASE_PLANE_PRICE = 180.0;

    @Override
    public double calculatePrice(Schedule schedule) {
        double basePrice = 0;
        if ("Bus".equals(schedule.getType())) {
            basePrice = BASE_BUS_PRICE;
        } else if ("Plane".equals(schedule.getType())) {
            basePrice = BASE_PLANE_PRICE;
        }

        int capacity = schedule.get_capacity();
        int reserved = schedule.getReservedSeats();
        double fullness = (double) reserved / capacity;

        // Increase price by up to 50% based on fullness
        return basePrice * (1 + (fullness * 0.5));
    }
}