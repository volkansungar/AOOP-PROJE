package mvc.view;

import javax.swing.*;
import java.awt.*;

/**
 * The user control panel.
 * Provides standard users with options to view voyages and manage their reservations.
 */
public class UserPanel extends JPanel {

    private Gui gui;

    /**
     * Constructor for the UserPanel.
     * @param gui The main GUI controller.
     */
    public UserPanel(Gui gui) {
        this.gui = gui;
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("User Dashboard", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        // This is where you would add components for user functionalities
        JPanel userFunctions = new JPanel(new GridLayout(2, 1, 10, 10));
        userFunctions.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton listVoyagesButton = new JButton("List & Query Voyages");
        listVoyagesButton.addActionListener(e -> gui.getController().userListQueryVoyages());

        JButton myReservationsButton = new JButton("Create/Cancel My Reservations");
        myReservationsButton.addActionListener(e -> gui.getController().userCreateCancelReservations());

        userFunctions.add(listVoyagesButton);
        userFunctions.add(myReservationsButton);
        add(userFunctions, BorderLayout.CENTER);

        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> gui.getController().logOut());

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(logoutButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}
