package mvc.controller;

import Schedule.Schedule;
import Schedule.BusFactory;
import Schedule.PlaneFactory;
import mvc.model.Reservation;
import mvc.model.ReservationManager;
import mvc.model.ScheduleManager;
import mvc.model.User;
import mvc.model.UserModel;
import mvc.model.pricing.DynamicPricingStrategy;
import mvc.patterns.PricingStrategy;
import mvc.model.pricing.StandardPricingStrategy;
import mvc.patterns.Observer;
import mvc.view.UserView;

import java.util.List;

public class Controller {
    private UserModel userModel;
    private ScheduleManager scheduleManager;
    private ReservationManager reservationManager;
    private UserView view;
    private BusFactory busFactory;
    private PlaneFactory planeFactory;
    private User currentUser;
    public Controller(UserModel userModel, ScheduleManager scheduleManager, ReservationManager reservationManager, UserView view, BusFactory busFactory, PlaneFactory planeFactory) {
        this.userModel = userModel;
        this.scheduleManager = scheduleManager;
        this.reservationManager = reservationManager;
        this.view = view;
        this.busFactory = busFactory;
        this.planeFactory = planeFactory;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void tryLogin(String name, String password) {
        if (name.trim().isEmpty() || password.trim().isEmpty()) {
            view.popup("Username and password cannot be empty.", true);
            return;
        }

        User user = userModel.searchUser(name);
        if (user != null) {
            if (password.equals(user.getPassword())) {
                this.currentUser = user;
                if (user.checkAdmin()) {
                    view.showAdminPanel();
                } else {
                    view.showUserPanel();
                }
            } else {
                view.popup("Wrong password.", true);
            }
        } else {
            view.popup("No user found with that name.", true);
        }
    }

    public void tryRegister(String name, String password, String pass_repeat) {
        if (name.trim().isEmpty() || password.trim().isEmpty()) {
            view.popup("Username and password cannot be empty.", true);
            return;
        }

        User user = userModel.searchUser(name);
        if (user != null) {
            view.popup("A user with this name already exists!", true);
        } else if (!password.equals(pass_repeat)) {
            view.popup("Passwords do not match.", true);
        } else {
            userModel.addUser(new User(name, password, false));
            view.popup("Registration successful! Please log in.", false);
            view.showLoginPage();
        }
    }

    public void logOut() {
        this.currentUser = null;
        view.showLoginPage();
    }

    public void startApplication() {
        view.showLoginPage();
    }

    public void addSchedule(String scheduleType, String name, String source, String destination, String date, int capacity) {
        if (name == null || name.trim().isEmpty() || source.trim().isEmpty() || destination.trim().isEmpty() || date.trim().isEmpty()) {
            view.popup("Schedule name, source, destination, and date cannot be empty.", true);
            return;
        }
        if (capacity <= 0) {
            view.popup("Capacity must be a positive number.", true);
            return;
        }

        Schedule newSchedule;
        if ("Plane".equals(scheduleType)) {
            newSchedule = planeFactory.createSchedule();
        } else if ("Bus".equals(scheduleType)) {
            newSchedule = busFactory.createSchedule();
        } else {
            view.popup("Invalid schedule type selected.", true);
            return;
        }

        newSchedule.set_name(name);
        newSchedule.set_source(source);
        newSchedule.set_destination(destination);
        newSchedule.set_date(date);
        newSchedule.set_capacity(capacity);

        scheduleManager.addSchedule(newSchedule);
        view.popup(scheduleType + " Schedule '" + name + "' added successfully!", false);
    }

    public void deleteSchedule(String id) {
        Schedule schedule = scheduleManager.lookupSchedule(id);
        if(schedule != null) {
            scheduleManager.deleteSchedule(schedule);
            view.popup("Schedule '" + schedule.get_name() + "' (ID: " + id + ") deleted successfully!", false);
        } else {
            view.popup("Schedule with ID '" + id + "' not found.", true);
        }
    }

    public void makeReservation(String scheduleId, int seatNumber) {
        if (currentUser == null) {
            view.popup("You must be logged in to make a reservation.", true);
            return;
        }

        Schedule schedule = scheduleManager.lookupSchedule(scheduleId);
        if (schedule == null) {
            view.popup("The selected schedule does not exist.", true);
            return;
        }

        if (reservationManager.getReservedSeatNumbers(scheduleId).contains(seatNumber)) {
            view.popup("Seat " + seatNumber + " is already taken.", true);
            return;
        }

        // --- Strategy Pattern Implementation ---
        PricingStrategy pricingStrategy;
        // Decide which strategy to use. For example, use dynamic pricing if the plane/bus is more than 50% full.
        if ((double) schedule.getReservedSeats() / schedule.get_capacity() > 0.5) {
            pricingStrategy = new DynamicPricingStrategy();
        } else {
            pricingStrategy = new StandardPricingStrategy();
        }
        double price = pricingStrategy.calculatePrice(schedule);
        // --- End of Strategy Pattern Implementation ---

        String scheduleDetails = schedule.getType() + ": " + schedule.get_name() + " from " + schedule.get_source() + " to " + schedule.get_destination();
        Reservation newReservation = new Reservation(currentUser.getUserId(), scheduleId, seatNumber, scheduleDetails, price); // Pass price to constructor
        reservationManager.addReservation(newReservation);

        scheduleManager.notifyObservers();

        view.popup("Reservation for seat " + seatNumber + " successful! Price: " + String.format("%.2f", price), false);
    }

    public void cancelReservation(String reservationId) {
        Reservation reservation = reservationManager.findReservationById(reservationId);
        if (reservation != null) {
            reservationManager.cancelReservation(reservationId);
            scheduleManager.notifyObservers();
            view.popup("Reservation cancelled successfully.", false);
        } else {
            view.popup("Could not find the selected reservation.", true);
        }
    }

    public List<Integer> getReservedSeatsForSchedule(String scheduleId) {
        return reservationManager.getReservedSeatNumbers(scheduleId);
    }

    public void goto_adminAddDeleteSchedules() {
        view.showAdminSchedulePanel();
    }

    public void goto_adminScheduleList() {
        view.showScheduleListPanel(true);
    }

    public void adminViewAllReservations() {
        view.popup("Admin: View All Reservations - Not implemented yet.", false);
    }

    public void userListQueryVoyages() {
        view.showScheduleListPanel(false);
    }



    public void userCreateCancelReservations() {
        view.showReservationPanel();
    }

    public List<Schedule> getSchedules() {
        List<Schedule> allSchedules = scheduleManager.getAllSchedules();
        for (Schedule schedule : allSchedules) {
            int reservedCount = reservationManager.getReservationCountForSchedule(schedule.get_id());
            schedule.setReservedSeats(reservedCount);
        }
        return allSchedules;
    }

    public List<Reservation> getUserReservations() {
        if (currentUser != null) {
            return reservationManager.getReservationsForUser(currentUser.getUserId());
        }
        return List.of();
    }

    public void addScheduleObserver(Observer o) {
        scheduleManager.addObserver(o);
    }

    public void addReservationObserver(Observer o) {
        reservationManager.addObserver(o);
    }
}