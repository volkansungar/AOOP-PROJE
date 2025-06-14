package mvc.model;

import mvc.patterns.Observer;
import mvc.patterns.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReservationManager implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private List<Reservation> reservations = new ArrayList<>();

    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
        notifyObservers();
    }

    public void cancelReservation(String reservationId) {
        reservations.removeIf(reservation -> reservation.getReservationId().equals(reservationId));
        notifyObservers();
    }

    public List<Reservation> getReservationsForUser(String userId) {
        return reservations.stream()
                .filter(r -> r.getUserId().equals(userId))
                .collect(Collectors.toList());
    }

    public int getReservationCountForSchedule(String scheduleId) {
        return (int) reservations.stream()
                .filter(r -> r.getScheduleId().equals(scheduleId))
                .count();
    }

    public Reservation findReservationById(String reservationId) {
        return reservations.stream()
                .filter(r -> r.getReservationId().equals(reservationId))
                .findFirst()
                .orElse(null);
    }
}
