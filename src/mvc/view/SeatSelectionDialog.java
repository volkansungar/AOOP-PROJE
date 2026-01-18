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
        getContentPane().setBackground(ViewUtilities.BACKGROUND_COLOR);


        JPanel seatPanel = new JPanel();
        int columns = 4;
        int rows = (int) Math.ceil((double) capacity / columns);
        seatPanel.setLayout(new GridLayout(rows, columns, 10, 10));
        seatPanel.setBackground(ViewUtilities.BACKGROUND_COLOR);
        seatPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        for (int i = 1; i <= capacity; i++) {
            final int seatNumber = i;
            JButton seatButton = new JButton(String.valueOf(seatNumber));
            seatButton.setFont(ViewUtilities.BOLD_FONT);
            seatButton.setPreferredSize(new Dimension(80, 80));
            seatButton.setFocusPainted(false);

            if (reservedSeats.contains(seatNumber)) {
                seatButton.setBackground(new Color(0xEF4444));
                seatButton.setForeground(Color.WHITE);
                seatButton.setEnabled(false);
            } else {
                seatButton.setBackground(ViewUtilities.SECONDARY_COLOR);
                seatButton.setForeground(Color.WHITE);
                seatButton.addActionListener(e -> {
                    if (selectedSeatButton != null) {
                        selectedSeatButton.setBackground(ViewUtilities.SECONDARY_COLOR);
                    }
                    selectedSeat = seatNumber;
                    selectedSeatButton = (JButton) e.getSource();
                    selectedSeatButton.setBackground(ViewUtilities.PRIMARY_COLOR);
                });
            }
            seatPanel.add(seatButton);
        }

        add(new JScrollPane(seatPanel), BorderLayout.CENTER);

        JButton confirmButton = ViewUtilities.createStyledButton("Confirm Reservation");
        confirmButton.addActionListener(e -> {
            if (selectedSeat != -1) {
                controller.makeReservation(schedule.get_id(), selectedSeat);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Please select a seat.", "No Seat Selected", JOptionPane.WARNING_MESSAGE);
            }
        });

        JButton cancelButton = ViewUtilities.createStyledButton("Cancel");
        cancelButton.setBackground(new Color(0x6B7280)); // A gray color
        cancelButton.addActionListener(e -> dispose());

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setBackground(ViewUtilities.BACKGROUND_COLOR);
        bottomPanel.add(cancelButton);
        bottomPanel.add(confirmButton);
        add(bottomPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(owner);
    }
}
