package mvc.controller;

import mvc.model.User;
import mvc.model.UserModel;
import mvc.model.ScheduleManager;
import mvc.view.UserView;

/**
 * The UserController class, part of the Controller in MVC.
 * It handles user input from the View, interacts with the Model,
 * and updates the View accordingly.
 */
public class UserController {
    private UserModel model;
    private ScheduleManager scheduleManager;
    private UserView view;

    /**
     * Constructor for UserController.
     * @param model The data model for users.
     * @param view The view for user-related interactions.
     */
    public UserController(UserModel model, ScheduleManager voyageManager, UserView view) {
        this.model = model;
        this.scheduleManager = voyageManager;
        this.view = view;
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
}
