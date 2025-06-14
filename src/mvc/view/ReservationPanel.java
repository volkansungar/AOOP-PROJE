package mvc.view;

import mvc.controller.Controller;
import mvc.model.ReservationTableModel;
import mvc.patterns.Observer;

import javax.swing.*;
import java.awt.*;

public class ReservationPanel extends JPanel implements Observer {

    private final Gui gui;
    private final ReservationTableModel reservationTableModel;
    private final JTable reservationTable;

    public ReservationPanel(Gui gui) {
        this.gui = gui;
        this.reservationTableModel = new ReservationTableModel();

        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel("My Reservations", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        reservationTable = new JTable(reservationTableModel);
        add(new JScrollPane(reservationTable), BorderLayout.CENTER);

        JPanel controlsPanel = new JPanel();
        controlsPanel.setLayout(new BoxLayout(controlsPanel, BoxLayout.Y_AXIS));

        JButton cancelReservationButton = new JButton("Cancel Selected Reservation");
        cancelReservationButton.addActionListener(e -> {
            int selectedRow = reservationTable.getSelectedRow();
            if (selectedRow >= 0) {
                String reservationId = reservationTableModel.getReservationIdAt(selectedRow);
                Controller controller = gui.getController();
                if (controller != null) {
                    controller.cancelReservation(reservationId);
                }
            } else {
                gui.showMessage("Please select a reservation to cancel.");
            }
        });

        controlsPanel.add(cancelReservationButton);
        add(controlsPanel, BorderLayout.EAST);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton backButton = new JButton("Back to Dashboard");
        backButton.addActionListener(e -> gui.showPanel(Gui.USER_PANEL));
        bottomPanel.add(backButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    @Override
    public void update() {
        Controller controller = gui.getController();
        if (controller != null && controller.getCurrentUser() != null) {
            reservationTableModel.setReservations(controller.getUserReservations());
        } else {
            // Clear the table if no user is logged in
            reservationTableModel.setReservations(java.util.List.of());
        }
    }
}
