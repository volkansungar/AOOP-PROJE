package mvc.view;

import Schedule.Schedule;
import mvc.controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SeatSelectionDialog extends JDialog {

    private int selectedSeat = -1;
    private JButton selectedSeatButton = null;

    public SeatSelectionDialog(Frame owner, Schedule schedule, Controller controller) {
        super(owner, "Select a Seat for " + schedule.get_name(), true);

        int capacity = schedule.get_capacity();
        List<Integer> reservedSeats = controller.getReservedSeatsForSchedule(schedule.get_id());

        setLayout(new BorderLayout(10, 10));

        JPanel seatPanel = new JPanel();
        int columns = 4;
        int rows = (int) Math.ceil((double) capacity / columns);
        seatPanel.setLayout(new GridLayout(rows, columns, 5, 5));

        for (int i = 1; i <= capacity; i++) {
            final int seatNumber = i;
            JButton seatButton = new JButton(String.valueOf(seatNumber));
            seatButton.setPreferredSize(new Dimension(60, 60));

            if (reservedSeats.contains(seatNumber)) {
                seatButton.setBackground(Color.RED);
                seatButton.setEnabled(false);
            } else {
                seatButton.setBackground(Color.GREEN);
                seatButton.addActionListener(e -> {
                    // Deselect previous seat if one was selected
                    if (selectedSeatButton != null) {
                        selectedSeatButton.setBackground(Color.GREEN);
                    }
                    // Select new seat
                    selectedSeat = seatNumber;
                    selectedSeatButton = (JButton) e.getSource();
                    selectedSeatButton.setBackground(Color.CYAN); // Highlight selected seat
                });
            }
            seatPanel.add(seatButton);
        }

        add(new JScrollPane(seatPanel), BorderLayout.CENTER);

        JButton confirmButton = new JButton("Confirm Reservation");
        confirmButton.addActionListener(e -> {
            if (selectedSeat != -1) {
                controller.makeReservation(schedule.get_id(), selectedSeat);
                dispose(); // Close the dialog after reservation
            } else {
                JOptionPane.showMessageDialog(this, "Please select a seat.", "No Seat Selected", JOptionPane.WARNING_MESSAGE);
            }
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> dispose());

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(cancelButton);
        bottomPanel.add(confirmButton);
        add(bottomPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(owner);
    }
}
