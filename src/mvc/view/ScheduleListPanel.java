package mvc.view;

import Schedule.Schedule;
import mvc.model.ScheduleTableModel; // Import the new custom TableModel
import mvc.patterns.Observer;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * A panel for both users and admins to view and query a list of available schedules.
 * This view is an Observer, so it updates automatically when the schedule data changes.
 */
public class ScheduleListPanel extends JPanel implements Observer {

    private final Gui gui;
    private String previousPanel;
    // The JTable that will display the schedules.
    private final JTable scheduleTable;
    // The custom table model that will manage the data for the JTable.
    private final ScheduleTableModel tableModel;

    /**
     * Constructor for the ScheduleListPanel.
     * @param gui The main GUI controller.
     */
    public ScheduleListPanel(Gui gui) {
        this.gui = gui;

        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Title
        JLabel titleLabel = new JLabel("Available Schedules", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        // 1. Create an instance of our new table model.
        tableModel = new ScheduleTableModel();
        // 2. Create the JTable and pass the custom model to its constructor.
        scheduleTable = new JTable(tableModel);

        scheduleTable.setFillsViewportHeight(true);
        scheduleTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(scheduleTable), BorderLayout.CENTER);

        // Bottom panel with a dynamic back button
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            if (previousPanel != null) {
                gui.showPanel(previousPanel);
            }
        });
        bottomPanel.add(backButton);
        add(bottomPanel, BorderLayout.SOUTH);

        // Initial data load when the panel is first created.
        refreshScheduleList();
    }

    /**
     * This method is called by the Subject (ScheduleManager) when data changes.
     */
    @Override
    public void update() {
        System.out.println("ScheduleListPanel received an update notification. Refreshing view...");
        // Swing GUI updates should be done on the Event Dispatch Thread (EDT)
        // for thread safety. SwingUtilities.invokeLater handles this.
        SwingUtilities.invokeLater(this::refreshScheduleList);
    }

    /**
     * Fetches fresh data from the model and tells the TableModel to update.
     * The JTable will then redraw itself automatically.
     */
    private void refreshScheduleList() {
        System.out.println("Fetching updated schedule list from the controller.");
        // a. Get the latest data from the controller
        List<Schedule> schedules = gui.getController().getSchedules();

        // b. Update the TableModel with the new data.
        //    The model will then notify the JTable to repaint.
        tableModel.setSchedules(schedules);

        System.out.println("Schedule list view has been updated!");
    }

    /**
     * Sets the panel to return to when the back button is clicked.
     * @param previousPanel The name of the panel to go back to (e.g., Gui.ADMIN_PANEL).
     */
    public void setPreviousPanel(String previousPanel) {
        this.previousPanel = previousPanel;
    }
}
