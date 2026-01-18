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
        setBackground(ViewUtilities.BACKGROUND_COLOR);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(ViewUtilities.createTitleLabel("My Reservations"), BorderLayout.NORTH);

        reservationTable = new JTable(reservationTableModel);
        reservationTable.setFont(ViewUtilities.MAIN_FONT);
        reservationTable.getTableHeader().setFont(ViewUtilities.BOLD_FONT);
        add(new JScrollPane(reservationTable), BorderLayout.CENTER);

        JPanel controlsPanel = new JPanel();
        controlsPanel.setLayout(new BoxLayout(controlsPanel, BoxLayout.Y_AXIS));
        controlsPanel.setBackground(ViewUtilities.BACKGROUND_COLOR);

        JButton cancelReservationButton = ViewUtilities.createStyledButton("Cancel Selected Reservation");
        cancelReservationButton.setBackground(new Color(0xEF4444));
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

        JPanel eastPanel = new JPanel(new GridBagLayout());
        eastPanel.setBackground(ViewUtilities.BACKGROUND_COLOR);
        eastPanel.add(controlsPanel);

        add(eastPanel, BorderLayout.EAST);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setBackground(ViewUtilities.BACKGROUND_COLOR);
        JButton backButton = ViewUtilities.createStyledButton("Back to Dashboard");
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
            reservationTableModel.setReservations(java.util.List.of());
        }
    }
}
