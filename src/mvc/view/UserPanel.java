package mvc.view;

import javax.swing.*;
import java.awt.*;

public class UserPanel extends JPanel {

    private final Gui gui;

    public UserPanel(Gui gui) {
        this.gui = gui;
        setLayout(new BorderLayout(20, 20));
        setBackground(ViewUtilities.BACKGROUND_COLOR);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        add(ViewUtilities.createTitleLabel("User Dashboard"), BorderLayout.NORTH);

        JPanel userFunctions = new JPanel(new GridLayout(0, 1, 10, 10));
        userFunctions.setBackground(ViewUtilities.BACKGROUND_COLOR);

        JButton listSchedulesButton = ViewUtilities.createStyledButton("List & Query Schedules");
        listSchedulesButton.addActionListener(e -> gui.getController().userListQuerySchedules());

        JButton myReservationsButton = ViewUtilities.createStyledButton("Cancel My Reservations");
        myReservationsButton.addActionListener(e -> gui.getController().userCreateCancelReservations());

        userFunctions.add(listSchedulesButton);
        userFunctions.add(myReservationsButton);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(ViewUtilities.BACKGROUND_COLOR);
        centerPanel.add(userFunctions);

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