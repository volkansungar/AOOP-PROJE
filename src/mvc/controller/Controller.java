package mvc.controller;

import Schedule.Schedule;
import Schedule.BusFactory;
import Schedule.PlaneSchedule;
import Schedule.PlaneFactory;
import mvc.model.ScheduleManager;
import mvc.model.User;
import mvc.model.UserModel;
import mvc.patterns.Observer;
import mvc.view.UserView;

import java.util.List;

/**
 * The UserController class, part of the Controller in MVC.
 * It handles user input from the View, interacts with the Model,
 * and updates the View accordingly.
 */
public class Controller {
    private UserModel model;
    private ScheduleManager scheduleManager;
    private UserView view;
    private BusFactory busFactory;
    private PlaneFactory planeFactory;

    /**
     * Constructor for UserController.
     * @param model The data model for users.
     * @param view The view for user-related interactions.
     */
    public Controller(UserModel model, ScheduleManager scheduleManager, UserView view, BusFactory busFactory, PlaneFactory planeFactory) {
        this.model = model;
        this.scheduleManager = scheduleManager;
        this.view = view;
        this.busFactory = busFactory;
        this.planeFactory = planeFactory;
    }

    /**
     * Attempts to log in a user with the provided credentials.
     * If successful, it directs the user to the appropriate panel (admin or user).
     * If not, it notifies the user of the failure.
     * @param name The username.
     * @param password The password.
     */
    public void tryLogin(String name, String password) {
        if (name.trim().isEmpty() || password.trim().isEmpty()) {
            view.popup("Username and password cannot be empty.", true);
            return;
        }

        User user = model.searchUser(name);
        if (user != null) {
            if (password.equals(user.getPassword())) {
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

    /**
     * Attempts to register a new user.
     * Performs validation checks and notifies the user of the outcome.
     * @param name The desired username.
     * @param password The desired password.
     * @param pass_repeat The password confirmation.
     */
    public void tryRegister(String name, String password, String pass_repeat) {
        if (name.trim().isEmpty() || password.trim().isEmpty()) {
            view.popup("Username and password cannot be empty.", true);
            return;
        }

        User user = model.searchUser(name);
        if (user != null) {
            view.popup("A user with this name already exists!", true);
        } else if (!password.equals(pass_repeat)) {
            view.popup("Passwords do not match.", true);
        } else {
            model.addUser(new User(name, password, false));
            view.popup("Registration successful! Please log in.", false);
            view.showLoginPage();
        }
    }

    /**
     * Logs the current user out and returns to the login page.
     */
    public void logOut() {
        view.showLoginPage();
    }

    /**
     * Starts the application by displaying the login page.
     * The frame itself is made visible in the Main class.
     */
    public void startApplication() {
        view.showLoginPage();
    }

    /**
     * Creates and adds a new schedule to the model.
     * Notifies the user of the outcome.
     * @param name The name for the new schedule.
     */
    public void addSchedule(String name) {
        if (name == null || name.trim().isEmpty()) {
            view.popup("Schedule name cannot be empty.", true);
            return;
        }

        // Use PlaneSchedule as a concrete implementation.
        // The voyage name from the UI is stored in the 'koltuk_no' field.
        Schedule newSchedule = planeFactory.createSchedule();
        newSchedule.set_seat(name);

        // Assuming voyageManager is an instance of ScheduleManager or a compatible class.
        scheduleManager.addSchedule(newSchedule);

        // The model (ScheduleManager) notifies observers to update the list view.
        // Provide immediate feedback via a popup.
        view.popup("Schedule '" + name + "' added successfully!", false);
    }

    public void deleteSchedule(String id) {
        Schedule schedule = scheduleManager.lookupSchedule(id);
        if(schedule != null) {scheduleManager.deleteSchedule(schedule);}
    }




    // --- Admin Panel Functions ---

    /** Navigates to the admin page for adding/deleting voyages. */
    public void goto_adminAddDeleteSchedules() {
        view.showAdminSchedulePanel();
    }

    /** Navigates to the voyage list view from the admin perspective. */
    public void goto_adminScheduleList() {
        view.showScheduleListPanel(true); // true indicates it's from admin
    }

    /** * Navigates to the reservation list view.
     * For now, this is a placeholder. A dedicated panel would be needed.
     */
    public void adminViewAllReservations() {
        view.popup("Admin: View All Reservations - Not implemented yet.", false);
    }

    // --- User Panel Functions ---

    /** Navigates to the voyage list view from the user perspective. */
    public void userListQueryVoyages() {
        view.showScheduleListPanel(false); // false indicates it's from user
    }

    /** Navigates to the page for creating/canceling reservations. */
    public void userCreateCancelReservations() {
        view.showReservationPanel();
    }

    public List<Schedule> getSchedules() {
        return scheduleManager.getAllSchedules();
    }

    public void addObserver(Observer o) {scheduleManager.addObserver(o);}
}