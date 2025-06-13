package mvc.view;

import javax.swing.*;
import java.awt.*;

/**
 * A panel for users to create new reservations or view and cancel their existing ones.
 */
public class ReservationPanel extends JPanel {

    private Gui gui;

    /**
     * Constructor for the ReservationPanel.
     * @param gui The main GUI controller.
     */
    public ReservationPanel(Gui gui) {
        this.gui = gui;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Title
        JLabel titleLabel = new JLabel("My Reservations", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        // Placeholder for user's reservations
        JTextArea reservationList = new JTextArea("Placeholder for your reservations...\nReservation for V101 - Seat 5A\n");
        reservationList.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(reservationList);
        add(scrollPane, BorderLayout.CENTER);

        // Panel for controls like creating or canceling a reservation
        JPanel controlsPanel = new JPanel();
        controlsPanel.setLayout(new BoxLayout(controlsPanel, BoxLayout.Y_AXIS));

        JButton createReservationButton = new JButton("Create New Reservation");
        // TODO: This could switch to the voyage list or open a new dialog

        JButton cancelReservationButton = new JButton("Cancel Selected Reservation");
        // TODO: Add ActionListener to cancel a reservation

        controlsPanel.add(createReservationButton);
        controlsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        controlsPanel.add(cancelReservationButton);

        add(controlsPanel, BorderLayout.EAST);

        // Bottom panel for navigation
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton backButton = new JButton("Back to Dashboard");
        backButton.addActionListener(e -> gui.showPanel(Gui.USER_PANEL));
        bottomPanel.add(backButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}
