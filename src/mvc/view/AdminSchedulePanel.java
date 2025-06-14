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

    private Gui gui;
    private JTextField scheduleNameField;
    private JTextArea scheduleList;
    // It's better to use a JTable with its model for dynamic data.
    private ScheduleTableModel scheduleTableModel;
    private JTable scheduleTable;


    /**
     * Constructor for the AdminVoyagePanel.
     * @param gui The main GUI controller.
     */
    public AdminSchedulePanel(Gui gui) {
        this.gui = gui;

        // Initialize the model that holds the schedule data
        this.scheduleTableModel = new ScheduleTableModel();
        // The View (this panel) registers itself as an Observer of the Model.
        // You will need to make your ScheduleTableModel an Observable (Subject)
        // and add a method like `addObserver(Observer o)`.
        // this.scheduleTableModel.addObserver(this);


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

        gbc.gridx = 0;
        gbc.gridy = 0;
        controlsPanel.add(new JLabel("Voyage Name:"), gbc);

        gbc.gridx = 1;
        scheduleNameField = new JTextField(20);
        controlsPanel.add(scheduleNameField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        JButton addScheduleButton = new JButton("Add Schedule");

        // Implemented ActionListener to add a new voyage
        ActionListener addScheduleAction = e -> {
            String scheduleName = scheduleNameField.getText();
            // Assumes a getter on the Gui object for the controller
            Controller controller = gui.getController();
            if (controller != null && !scheduleName.isBlank()) {
                // The controller should update the model.
                // The model will then notify observers (like this panel).
                controller.addSchedule(scheduleName);
                scheduleNameField.setText(""); // Clear the text field after adding
            }
        };
        addScheduleButton.addActionListener(addScheduleAction);
        controlsPanel.add(addScheduleButton, gbc);

        gbc.gridy++;
        JButton deleteVoyageButton = new JButton("Delete Selected Voyage");
        // ActionListener to delete the selected voyage from the JTable
        deleteVoyageButton.addActionListener(e -> {
            int selectedRow = scheduleTable.getSelectedRow();
            if (selectedRow >= 0) {
                // You would typically get an ID or the name from the selected row
                // For this example, let's assume the first column is the schedule name
                String scheduleNameToDelete = (String) scheduleTableModel.getValueAt(selectedRow, 0);
                Controller controller = gui.getController();
                if (controller != null) {
                    // The controller updates the model, which notifies observers
                    controller.deleteSchedule(scheduleNameToDelete);
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
