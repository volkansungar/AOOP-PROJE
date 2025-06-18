package mvc.patterns;

import Schedule.Schedule;

/**
 * The Strategy interface for calculating the price of a reservation.
 */
public interface PricingStrategy {
    /**
     * Calculates the price for a given schedule.
     * @param schedule The schedule for which to calculate the price.
     * @return The calculated price.
     */
    double calculatePrice(Schedule schedule);
}