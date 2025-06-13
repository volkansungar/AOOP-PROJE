package mvc.view;

/**
 * The UserView class, part of the View in MVC.
 * This class is responsible for updating the GUI based on changes from the controller.
 * It acts as an intermediary between the controller and the raw GUI components.
 */
public class UserView {
    private Gui gui;

    /**
     * Constructor for UserView.
     * @param gui The main GUI object that this view will manage.
     */
    public UserView(Gui gui) {
        this.gui = gui;
    }

    /**
     * Notifies the user with a popup message.
     * Can be used for confirmations, warnings, or errors.
     * @param message The message to display to the user.
     * @param isError If true, the message will be displayed as an error dialog.
     */
    public void popup(String message, boolean isError) {
        if (isError) {
            gui.showErrorMessage(message);
        } else {
            gui.showMessage(message);
        }
    }

    /**
     * Shows the administrator's control panel.
     */
    public void showAdminPanel() {
        gui.showPanel(Gui.ADMIN_PANEL);
    }

    /**
     * Shows the login page.
     */
    public void showLoginPage() {
        gui.showPanel(Gui.LOGIN_PANEL);
    }

    /**
     * Shows the standard user's control panel.
     */
    public void showUserPanel() {
        gui.showPanel(Gui.USER_PANEL);
    }

    /**
     * Shows the panel for adding/deleting voyages.
     */
    public void showAdminSchedulePanel() {
        gui.showPanel(Gui.ADMIN_SCHEDULE_PANEL);
    }

    /**
     * Shows the list of voyages.
     * @param fromAdmin A boolean indicating if the request is from the admin panel.
     * This helps in setting the correct "back" button behavior.
     */
    public void showScheduleListPanel(boolean fromAdmin) {
        String previousPanel = fromAdmin ? Gui.ADMIN_PANEL : Gui.USER_PANEL;
        gui.getScheduleListPanel().setPreviousPanel(previousPanel);
        gui.showPanel(Gui.SCHEDULE_LIST_PANEL);
    }

    /**
     * Shows the panel for managing reservations.
     */
    public void showReservationPanel() {
        gui.showPanel(Gui.RESERVATION_PANEL);
    }
}
