package mvc.view;

import Schedule.Schedule;
import javax.swing.*;
import java.awt.*;

public class SeatSelectionDialog extends JDialog {

    public SeatSelectionDialog(Frame owner, Schedule schedule) {
        super(owner, "Seat Availability for " + schedule.get_seat(), true);

        int capacity = schedule.get_capacity();
        int reserved = schedule.getReservedSeats();

        setLayout(new BorderLayout(10, 10));

        // Simple visual representation
        JPanel seatPanel = new JPanel();
        // A rough grid layout
        int columns = 4;
        int rows = (int) Math.ceil((double) capacity / columns);
        seatPanel.setLayout(new GridLayout(rows, columns, 5, 5));

        for (int i = 1; i <= capacity; i++) {
            JButton seatButton = new JButton(String.valueOf(i));
            seatButton.setPreferredSize(new Dimension(50, 50));
            if (i <= reserved) {
                seatButton.setBackground(Color.RED);
                seatButton.setEnabled(false);
            } else {
                seatButton.setBackground(Color.GREEN);
            }
            seatPanel.add(seatButton);
        }

        add(new JScrollPane(seatPanel), BorderLayout.CENTER);

        // Close button
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> dispose());
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(closeButton);
        add(bottomPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(owner);
    }
}
