package mvc.view;

import Schedule.Schedule;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
/* Implemented for seat availability */
public class AvailabilityRenderer extends JProgressBar implements TableCellRenderer {

    public AvailabilityRenderer() {
        super(0, 100);
        setStringPainted(true);
        setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus,
                                                   int row, int column) {
        if (value instanceof Schedule) {
            Schedule schedule = (Schedule) value;
            int capacity = schedule.get_capacity();
            int reserved = schedule.getReservedSeats();
            int percentFull = 0; // Declared here to be in scope for the whole method

            if (capacity > 0) {
                percentFull = (int) (((double) reserved / capacity) * 100);
                setValue(percentFull);
                setString(reserved + "/" + capacity);
            } else {
                // Handle case for zero capacity to avoid division by zero
                setValue(0);
                setString("N/A");
            }

            // Change color based on availability
            if (percentFull > 85) {
                setForeground(new Color(0xD32F2F)); // Red
            } else if (percentFull > 50) {
                setForeground(new Color(0xFBC02D)); // Yellow
            } else {
                setForeground(new Color(0x388E3C)); // Green
            }
        }
        return this;
    }
}
