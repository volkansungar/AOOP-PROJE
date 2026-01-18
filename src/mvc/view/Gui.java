package mvc.view;

import com.formdev.flatlaf.FlatIntelliJLaf;
import mvc.controller.Controller;
import javax.swing.*;
import java.awt.*;

public class Gui {

    private JFrame frame;
    private JPanel mainPanel;
    private CardLayout cardLayout;

    private LoginPage loginPage;
    private RegisterPage registerPage;
    private AdminPanel adminPanel;
    private UserPanel userPanel;
    private AdminSchedulePanel adminSchedulePanel;
    private ScheduleListPanel scheduleListPanel;
    private ReservationPanel reservationPanel;

    private Controller controller;

    public static final String LOGIN_PANEL = "LoginPanel";
    public static final String REGISTER_PANEL = "RegisterPanel";
    public static final String ADMIN_PANEL = "AdminPanel";
    public static final String USER_PANEL = "UserPanel";
    public static final String ADMIN_SCHEDULE_PANEL = "AdminSchedulePanel";
    public static final String SCHEDULE_LIST_PANEL = "ScheduleListPanel";
    public static final String RESERVATION_PANEL = "ReservationPanel";

    public Gui() {
        FlatIntelliJLaf.setup(); // Apply FlatLaf look and feel
        frame = new JFrame("Online Reservation System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(800, 600));
        frame.setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainPanel.setBackground(ViewUtilities.BACKGROUND_COLOR);


        frame.getContentPane().add(mainPanel);
    }

    public void initComponents() {
        loginPage = new LoginPage(this);
        registerPage = new RegisterPage(this);
        adminPanel = new AdminPanel(this);
        userPanel = new UserPanel(this);
        adminSchedulePanel = new AdminSchedulePanel(this);
        scheduleListPanel = new ScheduleListPanel(this);
        reservationPanel = new ReservationPanel(this);

        // Register views as observers
        controller.addScheduleObserver(adminSchedulePanel);
        controller.addScheduleObserver(scheduleListPanel);
        controller.addReservationObserver(reservationPanel);


        mainPanel.add(loginPage, LOGIN_PANEL);
        mainPanel.add(registerPage, REGISTER_PANEL);
        mainPanel.add(adminPanel, ADMIN_PANEL);
        mainPanel.add(userPanel, USER_PANEL);
        mainPanel.add(adminSchedulePanel, ADMIN_SCHEDULE_PANEL);
        mainPanel.add(scheduleListPanel, SCHEDULE_LIST_PANEL);
        mainPanel.add(reservationPanel, RESERVATION_PANEL);
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public Controller getController() {
        return controller;
    }

    public ScheduleListPanel getScheduleListPanel() {
        return scheduleListPanel;
    }

    public AdminSchedulePanel getAdminSchedulePanel() {
        return adminSchedulePanel;
    }

    public ReservationPanel getReservationPanel() {
        return reservationPanel;
    }

    public void showPanel(String panelName) {
        // When showing a panel that needs updated data, trigger its update method
        if (RESERVATION_PANEL.equals(panelName)) {
            reservationPanel.update();
        }
        if (SCHEDULE_LIST_PANEL.equals(panelName)) {
            scheduleListPanel.update();
        }
        cardLayout.show(mainPanel, panelName);
    }

    public void showFrame() {
        frame.pack();
        frame.setVisible(true);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(frame, message);
    }

    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(frame, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}