package mvc.view;

import mvc.controller.Controller;
import mvc.model.ScheduleTableModel;
import mvc.patterns.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Collections;

/**
 * A dedicated panel for administrators to add or delete voyages.
 * Contains forms for input and a table to display existing voyages.
 */
public class AdminSchedulePanel extends JPanel implements Observer {

    private final Gui gui;
    private final JTextField scheduleNameField;
    private final JTextField sourceField;
    private final JTextField destinationField;
    private final JTextField dateField;
    private final JTextField capacityField;
    private final JComboBox<String> scheduleTypeComboBox;

    // It's better to use a JTable with its model for dynamic data.
    private final ScheduleTableModel scheduleTableModel;
    private final JTable scheduleTable;


    /**
     * Constructor for the AdminVoyagePanel.
     * @param gui The main GUI controller.
     */
    public AdminSchedulePanel(Gui gui) {
        this.gui = gui;

        // Initialize the model that holds the schedule data
        this.scheduleTableModel = new ScheduleTableModel();
        // The View (this panel) registers itself as an Observer of the Model.
        // The Controller now manages adding the AdminSchedulePanel as an observer
        // to the ScheduleManager (Model) when the app starts.

        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Title
        JLabel titleLabel = new JLabel("Voyage Management", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        // A JTable is much better for displaying tabular data than a JTextArea.
        // It works directly with a TableModel.
        scheduleTable = new JTable(scheduleTableModel);
        JScrollPane scrollPane = new JScrollPane(scheduleTable);
        add(scrollPane, BorderLayout.CENTER);

        // Panel for the "Add Voyage" form and other controls
        JPanel controlsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Schedule Type Dropdown
        gbc.gridx = 0;
        gbc.gridy = 0;
        controlsPanel.add(new JLabel("Schedule Type:"), gbc);
        gbc.gridx = 1;
        scheduleTypeComboBox = new JComboBox<>(new String[]{"Plane", "Bus"});
        controlsPanel.add(scheduleTypeComboBox, gbc);

        // Schedule Name Field
        gbc.gridx = 0;
        gbc.gridy++;
        controlsPanel.add(new JLabel("Schedule Name:"), gbc);
        gbc.gridx = 1;
        scheduleNameField = new JTextField(20);
        controlsPanel.add(scheduleNameField, gbc);

        // Source Field
        gbc.gridx = 0;
        gbc.gridy++;
        controlsPanel.add(new JLabel("Source:"), gbc);
        gbc.gridx = 1;
        sourceField = new JTextField(20);
        controlsPanel.add(sourceField, gbc);

        // Destination Field
        gbc.gridx = 0;
        gbc.gridy++;
        controlsPanel.add(new JLabel("Destination:"), gbc);
        gbc.gridx = 1;
        destinationField = new JTextField(20);
        controlsPanel.add(destinationField, gbc);

        // Date Field
        gbc.gridx = 0;
        gbc.gridy++;
        controlsPanel.add(new JLabel("Date (DD-MM-YYYY):"), gbc);
        gbc.gridx = 1;
        dateField = new JTextField(20);
        controlsPanel.add(dateField, gbc);

        // Capacity Field
        gbc.gridx = 0;
        gbc.gridy++;
        controlsPanel.add(new JLabel("Capacity:"), gbc);
        gbc.gridx = 1;
        capacityField = new JTextField(20);
        controlsPanel.add(capacityField, gbc);

        // Add Schedule Button
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        JButton addScheduleButton = new JButton("Add Schedule");

        // Implemented ActionListener to add a new voyage
        ActionListener addScheduleAction = e -> {
            String scheduleType = (String) scheduleTypeComboBox.getSelectedItem();
            String scheduleName = scheduleNameField.getText();
            String source = sourceField.getText();
            String destination = destinationField.getText();
            String date = dateField.getText();
            int capacity = 0;
            try {
                capacity = Integer.parseInt(capacityField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number for capacity.", "Invalid Input", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Controller controller = gui.getController();
            if (controller != null) {
                controller.addSchedule(scheduleType, scheduleName, source, destination, date, capacity);
                // Clear fields after adding
                scheduleNameField.setText("");
                sourceField.setText("");
                destinationField.setText("");
                dateField.setText("");
                capacityField.setText("");
            }
        };
        addScheduleButton.addActionListener(addScheduleAction);
        controlsPanel.add(addScheduleButton, gbc);

        // Delete Schedule Button
        gbc.gridy++;
        JButton deleteVoyageButton = new JButton("Delete Selected Voyage");
        // ActionListener to delete the selected voyage from the JTable
        deleteVoyageButton.addActionListener(e -> {
            int selectedRow = scheduleTable.getSelectedRow();
            if (selectedRow >= 0) {
                // Get the ID from the ScheduleTableModel using the selected row index
                String scheduleIdToDelete = scheduleTableModel.getScheduleIdAt(selectedRow);
                Controller controller = gui.getController();
                if (controller != null && scheduleIdToDelete != null) {
                    controller.deleteSchedule(scheduleIdToDelete);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a voyage to delete.", "No Voyage Selected", JOptionPane.WARNING_MESSAGE);
            }
        });
        controlsPanel.add(deleteVoyageButton, gbc);

        add(controlsPanel, BorderLayout.EAST);

        // Bottom panel for navigation
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton backButton = new JButton("Back to Admin Panel");
        backButton.addActionListener(e -> gui.showPanel(Gui.ADMIN_PANEL));
        bottomPanel.add(backButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    /**
     * This method is called by the Observable (the Model) when data has changed.
     * Its job is to refresh the view to reflect the latest state of the model.
     */
    @Override
    public void update() {
        Controller controller = gui.getController();
        if (controller != null) {
            // Get the updated list from the controller and set it on the table model.
            // The setSchedules method in the model will call fireTableDataChanged()
            // which tells the JTable to refresh.
            scheduleTableModel.setSchedules(controller.getSchedules());
        }

        // The JTable component listening to this model will automatically repaint.
        System.out.println("View updated with latest schedule data.");
    }
}