package mvc.model;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ReservationTableModel extends AbstractTableModel {

    private List<Reservation> reservations;
    private final String[] columnNames = {"Reservation ID", "Schedule Details"};

    public ReservationTableModel() {
        this.reservations = new ArrayList<>();
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = new ArrayList<>(reservations);
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return reservations.size();
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
    public Object getValueAt(int rowIndex, int columnIndex) {
        Reservation reservation = reservations.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return reservation.getReservationId();
            case 1:
                return reservation.getScheduleDetails();
            default:
                return null;
        }
    }

    public String getReservationIdAt(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < reservations.size()) {
            return reservations.get(rowIndex).getReservationId();
        }
        return null;
    }
}
