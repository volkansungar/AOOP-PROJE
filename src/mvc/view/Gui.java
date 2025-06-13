package mvc.view;
import mvc.controller.UserController;
import javax.swing.*;
import java.awt.*;

/**
 * The main GUI class that holds the main frame and manages the different panels (pages)
 * of the application using a CardLayout.
 */
public class Gui {

    private JFrame frame;
    private JPanel mainPanel;
    private CardLayout cardLayout;

    private LoginPage loginPage;
    private RegisterPage registerPage;
    private AdminPanel adminPanel;
    private UserPanel userPanel;
    // New Panels
    private AdminSchedulePanel adminSchedulePanel;
    private ScheduleListPanel scheduleListPanel;
    private ReservationPanel reservationPanel;


    private UserController controller;

    // Constants for panel names to be used with CardLayout.
    public static final String LOGIN_PANEL = "LoginPanel";
    public static final String REGISTER_PANEL = "RegisterPanel";
    public static final String ADMIN_PANEL = "AdminPanel";
    public static final String USER_PANEL = "UserPanel";
    // New Panel Constants
    public static final String ADMIN_SCHEDULE_PANEL = "AdminSchedulePanel";
    public static final String SCHEDULE_LIST_PANEL = "ScheduleListPanel";
    public static final String RESERVATION_PANEL = "ReservationPanel";


    /**
     * Constructor for the Gui.
     * Initializes the main frame and sets up the CardLayout to manage different views.
     */
    public Gui() {
        // Initialize the main frame
        frame = new JFrame("Online Reservation System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600); // Set a default size
        frame.setLocationRelativeTo(null); // Center the frame on the screen

        // Use CardLayout to switch between panels
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Initialize the different pages (panels) of the application
        loginPage = new LoginPage(this);
        registerPage = new RegisterPage(this);
        adminPanel = new AdminPanel(this);
        userPanel = new UserPanel(this);
        adminSchedulePanel = new AdminSchedulePanel(this);
        scheduleListPanel = new ScheduleListPanel(this);
        reservationPanel = new ReservationPanel(this);

        // Add panels to the main panel with their respective names
        mainPanel.add(loginPage, LOGIN_PANEL);
        mainPanel.add(registerPage, REGISTER_PANEL);
        mainPanel.add(adminPanel, ADMIN_PANEL);
        mainPanel.add(userPanel, USER_PANEL);
        mainPanel.add(adminSchedulePanel, ADMIN_SCHEDULE_PANEL);
        mainPanel.add(scheduleListPanel, SCHEDULE_LIST_PANEL);
        mainPanel.add(reservationPanel, RESERVATION_PANEL);

        // Add the main panel to the frame's content pane
        frame.getContentPane().add(mainPanel);
    }

    /**
     * Sets the controller for the GUI. The controller handles the application's logic.
     * @param controller The UserController instance.
     */
    public void setController(UserController controller) {
        this.controller = controller;
    }

    /**
     * Returns the controller associated with this GUI.
     * @return The UserController instance.
     */
    public UserController getController() {
        return controller;
    }

    /**
     * Provides access to the VoyageListPanel instance.
     * This is needed to set the "previous panel" for the back button.
     * @return The VoyageListPanel instance.
     */
    public ScheduleListPanel getScheduleListPanel() {
        return scheduleListPanel;
    }

    /**
     * Shows a specific panel in the main window.
     * @param panelName The name of the panel to show (use the constants like LOGIN_PANEL).
     */
    public void showPanel(String panelName) {
        cardLayout.show(mainPanel, panelName);
    }

    /**
     * Makes the main frame visible.
     */
    public void showFrame() {
        frame.setVisible(true);
    }

    /**
     * Displays an informational message to the user.
     * @param message The message to display.
     */
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(frame, message);
    }

    /**
     * Displays an error message to the user.
     * @param message The error message to display.
     */
    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(frame, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
