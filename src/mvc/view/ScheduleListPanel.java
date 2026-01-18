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
        setBackground(ViewUtilities.BACKGROUND_COLOR);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(ViewUtilities.createTitleLabel("Available Schedules"), BorderLayout.NORTH);

        scheduleTable = new JTable(scheduleTableModel);
        scheduleTable.setFont(ViewUtilities.MAIN_FONT);
        scheduleTable.getTableHeader().setFont(ViewUtilities.BOLD_FONT);
        scheduleTable.getColumnModel().getColumn(6).setCellRenderer(new AvailabilityRenderer());
        scheduleTable.setRowHeight(30);
        scheduleTable.setGridColor(ViewUtilities.BORDER_COLOR);
        scheduleTable.setShowGrid(true);

        add(new JScrollPane(scheduleTable), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setBackground(ViewUtilities.BACKGROUND_COLOR);

        selectSeatButton = ViewUtilities.createStyledButton("Select Seat");
        selectSeatButton.setBackground(ViewUtilities.SECONDARY_COLOR);
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

        JButton backButton = ViewUtilities.createStyledButton("Back");
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