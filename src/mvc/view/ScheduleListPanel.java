package mvc.view;

import mvc.controller.Controller;
import mvc.model.ScheduleTableModel;
import mvc.patterns.Observer;
import Schedule.Schedule;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class ScheduleListPanel extends JPanel implements Observer {

    private final Gui gui;
    private final ScheduleTableModel scheduleTableModel;
    private final JTable scheduleTable;
    private final JButton selectSeatButton;
    private String previousPanel;

    public ScheduleListPanel(Gui gui) {
        this.gui = gui;
        this.scheduleTableModel = new ScheduleTableModel();

        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel("Available Schedules", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        scheduleTable = new JTable(scheduleTableModel);
        scheduleTable.getColumnModel().getColumn(6).setCellRenderer(new AvailabilityRenderer());
        scheduleTable.setRowHeight(25);

        add(new JScrollPane(scheduleTable), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        selectSeatButton = new JButton("Select Seat");
        selectSeatButton.addActionListener(e -> {
            int selectedRow = scheduleTable.getSelectedRow();
            if (selectedRow >= 0) {
                Controller controller = gui.getController();
                Schedule selectedSchedule = ((ScheduleTableModel)scheduleTable.getModel()).getScheduleAt(selectedRow);
                if (controller != null && selectedSchedule != null) {
                    SeatSelectionDialog dialog = new SeatSelectionDialog(
                            (Frame) SwingUtilities.getWindowAncestor(this),
                            selectedSchedule,
                            controller
                    );
                    dialog.setVisible(true);
                }
            } else {
                gui.showMessage("Please select a schedule to view its seats.");
            }
        });

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            if (previousPanel != null) {
                gui.showPanel(previousPanel);
            }
        });

        bottomPanel.add(selectSeatButton);
        bottomPanel.add(backButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    public void setPreviousPanel(String previousPanel) {
        this.previousPanel = previousPanel;
        boolean isAdminViewing = Gui.ADMIN_PANEL.equals(previousPanel);
        selectSeatButton.setVisible(!isAdminViewing);
    }

    @Override
    public void update() {
        Controller controller = gui.getController();
        if (controller != null) {
            List<Schedule> allSchedules = controller.getSchedules();
            boolean isAdminViewing = Gui.ADMIN_PANEL.equals(previousPanel);

            if (!isAdminViewing) {
                List<Schedule> availableSchedules = allSchedules.stream()
                        .filter(s -> s.getReservedSeats() < s.get_capacity())
                        .collect(Collectors.toList());
                scheduleTableModel.setSchedules(availableSchedules);
            } else {
                scheduleTableModel.setSchedules(allSchedules);
            }
        }
    }
}
