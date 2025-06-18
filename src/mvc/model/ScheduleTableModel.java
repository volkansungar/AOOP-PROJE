package mvc.model;

import Schedule.Schedule;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ScheduleTableModel extends AbstractTableModel {

    private List<Schedule> schedules;
    private final String[] columnNames = {"ID", "Type", "Name", "Source", "Destination", "Date", "Availability"};

    public ScheduleTableModel() {
        this.schedules = new ArrayList<>();
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = new ArrayList<>(schedules);
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return schedules.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 6) { // Availability column
            return Schedule.class;
        }
        return super.getColumnClass(columnIndex);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (rowIndex < 0 || rowIndex >= schedules.size()) {
            return null;
        }
        Schedule schedule = schedules.get(rowIndex);

        switch (columnIndex) {
            case 0: return schedule.get_id();
            case 1: return schedule.getType();
            case 2: return schedule.get_name();
            case 3: return schedule.get_source();
            case 4: return schedule.get_destination();
            case 5: return schedule.get_date();
            case 6:
                // Return the whole schedule object for the renderer to use
                return schedule;
            default: return null;
        }
    }

    public String getScheduleIdAt(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < schedules.size()) {
            return schedules.get(rowIndex).get_id();
        }
        return null;
    }

    public Schedule getScheduleAt(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < schedules.size()) {
            return schedules.get(rowIndex);
        }
        return null;
    }
}
