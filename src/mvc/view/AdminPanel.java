package mvc.view;

import javax.swing.*;
import java.awt.*;

public class AdminPanel extends JPanel {

    private final Gui gui;

    public AdminPanel(Gui gui) {
        this.gui = gui;
        setLayout(new BorderLayout(20, 20));
        setBackground(ViewUtilities.BACKGROUND_COLOR);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        add(ViewUtilities.createTitleLabel("Admin Control Panel"), BorderLayout.NORTH);

        JPanel adminFunctions = new JPanel(new GridLayout(0, 1, 10, 10));
        adminFunctions.setBackground(ViewUtilities.BACKGROUND_COLOR);

        JButton addScheduleButton = ViewUtilities.createStyledButton("Add/Delete Schedules");
        addScheduleButton.addActionListener(e -> gui.getController().goto_adminAddDeleteSchedules());

        JButton listVoyagesButton = ViewUtilities.createStyledButton("List & Query Schedules");
        listVoyagesButton.addActionListener(e -> gui.getController().goto_adminScheduleList());

        JButton viewReservationsButton = ViewUtilities.createStyledButton("View All Reservations");
        viewReservationsButton.addActionListener(e -> gui.getController().adminViewAllReservations());

        adminFunctions.add(addScheduleButton);
        adminFunctions.add(listVoyagesButton);
        adminFunctions.add(viewReservationsButton);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(ViewUtilities.BACKGROUND_COLOR);
        centerPanel.add(adminFunctions);

        add(centerPanel, BorderLayout.CENTER);

        JButton logoutButton = ViewUtilities.createStyledButton("Logout");
        logoutButton.setBackground(new Color(0xEF4444)); // A red color for logout
        logoutButton.addActionListener(e -> gui.getController().logOut());

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setBackground(ViewUtilities.BACKGROUND_COLOR);
        bottomPanel.add(logoutButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}