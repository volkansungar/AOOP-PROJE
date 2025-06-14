package mvc.model;

import Schedule.Schedule;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * A custom TableModel to adapt the List<Schedule> data for use in a JTable.
 * This class acts as the bridge between the application's data model (ScheduleManager)
 * and the view component (JTable in ScheduleListPanel).
 */
public class ScheduleTableModel extends AbstractTableModel {

    // A list to hold the schedule data that the table will display.
    private List<Schedule> schedules;
    // The names of the columns for the table header.
    private final String[] columnNames = new String[] {
            "Voyage Name", "Departure", "Destination", "Date", "Capacity"
    };

    /**
     * Constructor for ScheduleTableModel. Initializes with an empty list.
     */
    public ScheduleTableModel() {
        this.schedules = new ArrayList<>();
    }

    /**
     * Sets the data for the table. This is called when the list needs to be refreshed.
     * After setting the data, it notifies the JTable that the entire table structure
     * might have changed, causing the table to completely redraw itself.
     *
     * @param schedules The new list of schedules to display.
     */
    public void setSchedules(List<Schedule> schedules) {
        this.schedules = new ArrayList<>(schedules); // Use a copy to be safe
        fireTableDataChanged(); // Essential: Notifies the JTable to refresh!
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public int getRowCount() {
        // The number of rows is the size of our schedule list.
        return schedules.size();
    }

    @Override
    public int getColumnCount() {
        // The number of columns is fixed based on our columnNames array.
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        // This is the most important method. It tells the JTable what data
        // to display in each individual cell.

        // Get the specific schedule object for the given row.
        Schedule schedule = schedules.get(rowIndex);

        // Use a switch statement to return the correct data for the requested column.
        switch (columnIndex) {
            case 0:
                // We are assuming the "voyage name" is stored in the seat/koltuk field.
                // You might need to adjust this based on your final Schedule class design.
                return schedule.get_seat();
            case 1:
                return schedule.get_source();
            case 2:
                return schedule.get_destination();
            case 3:
                return schedule.get_date();
            case 4:
                return schedule.get_capacity();
            default:
                // Return null for any unexpected column index.
                return null;
        }
    }
    public String get_table_string() {
        String table = "";
        for (int i = 0; i < getColumnCount(); i++) {
            table += "\n" + getColumnName(i);
        }
        return table;
    }
}
