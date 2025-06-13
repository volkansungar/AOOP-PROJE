package mvc.view;

import javax.swing.*;
import java.awt.*;

/**
 * The admin control panel.
 * Provides administrators with options to manage voyages and other system settings.
 */
public class AdminPanel extends JPanel {

    private Gui gui;

    /**
     * Constructor for the AdminPanel.
     * @param gui The main GUI controller.
     */
    public AdminPanel(Gui gui) {
        this.gui = gui;
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Admin Control Panel", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        // This is where you would add components for admin functionalities
        JPanel adminFunctions = new JPanel(new GridLayout(3, 1, 10, 10));
        adminFunctions.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton addScheduleButton = new JButton("Add/Delete Schedules");
        addScheduleButton.addActionListener(e -> gui.getController().goto_adminAddDeleteSchedules());

        JButton listVoyagesButton = new JButton("List & Query Voyages");
        listVoyagesButton.addActionListener(e -> gui.getController().goto_adminScheduleList());

        JButton viewReservationsButton = new JButton("View All Reservations");
        viewReservationsButton.addActionListener(e -> gui.getController().adminViewAllReservations());

        adminFunctions.add(addScheduleButton);
        adminFunctions.add(listVoyagesButton);
        adminFunctions.add(viewReservationsButton);
        add(adminFunctions, BorderLayout.CENTER);

        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> gui.getController().logOut());

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(logoutButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}
