package mvc.view;

import mvc.controller.Controller;
import mvc.model.ScheduleTableModel;
import mvc.patterns.Observer;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class AdminSchedulePanel extends JPanel implements Observer {

    private final Gui gui;
    private final JTextField scheduleNameField;
    private final JTextField sourceField;
    private final JTextField destinationField;
    private final JTextField dateField;
    private final JTextField capacityField;
    private final JComboBox<String> scheduleTypeComboBox;

    private final ScheduleTableModel scheduleTableModel;
    private final JTable scheduleTable;

    public AdminSchedulePanel(Gui gui) {
        this.gui = gui;
        this.scheduleTableModel = new ScheduleTableModel();

        setLayout(new BorderLayout(10, 10));
        setBackground(ViewUtilities.BACKGROUND_COLOR);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(ViewUtilities.createTitleLabel("Voyage Management"), BorderLayout.NORTH);

        scheduleTable = new JTable(scheduleTableModel);
        scheduleTable.setFont(ViewUtilities.MAIN_FONT);
        scheduleTable.getTableHeader().setFont(ViewUtilities.BOLD_FONT);
        JScrollPane scrollPane = new JScrollPane(scheduleTable);
        add(scrollPane, BorderLayout.CENTER);

        JPanel controlsPanel = new JPanel(new GridBagLayout());
        controlsPanel.setBackground(ViewUtilities.BACKGROUND_COLOR);
        controlsPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(ViewUtilities.BORDER_COLOR),
                "Add/Delete Schedule",
                TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION,
                ViewUtilities.BOLD_FONT,
                ViewUtilities.FOREGROUND_COLOR
        ));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        controlsPanel.add(ViewUtilities.createLabel("Schedule Type:"), gbc);
        gbc.gridx = 1;
        scheduleTypeComboBox = new JComboBox<>(new String[]{"Plane", "Bus"});
        scheduleTypeComboBox.setFont(ViewUtilities.MAIN_FONT);
        controlsPanel.add(scheduleTypeComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        controlsPanel.add(ViewUtilities.createLabel("Schedule Name:"), gbc);
        gbc.gridx = 1;
        scheduleNameField = new JTextField(20);
        scheduleNameField.setFont(ViewUtilities.MAIN_FONT);
        controlsPanel.add(scheduleNameField, gbc);

        gbc.gridx = 0; gbc.gridy++; controlsPanel.add(ViewUtilities.createLabel("Source:"), gbc);
        gbc.gridx = 1; sourceField = new JTextField(20); sourceField.setFont(ViewUtilities.MAIN_FONT); controlsPanel.add(sourceField, gbc);
        gbc.gridx = 0; gbc.gridy++; controlsPanel.add(ViewUtilities.createLabel("Destination:"), gbc);
        gbc.gridx = 1; destinationField = new JTextField(20); destinationField.setFont(ViewUtilities.MAIN_FONT); controlsPanel.add(destinationField, gbc);
        gbc.gridx = 0; gbc.gridy++; controlsPanel.add(ViewUtilities.createLabel("Date (DD-MM-YYYY):"), gbc);
        gbc.gridx = 1; dateField = new JTextField(20); dateField.setFont(ViewUtilities.MAIN_FONT); controlsPanel.add(dateField, gbc);
        gbc.gridx = 0; gbc.gridy++; controlsPanel.add(ViewUtilities.createLabel("Capacity:"), gbc);
        gbc.gridx = 1; capacityField = new JTextField(20); capacityField.setFont(ViewUtilities.MAIN_FONT); controlsPanel.add(capacityField, gbc);


        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        JButton addScheduleButton = ViewUtilities.createStyledButton("Add Schedule");
        addScheduleButton.setBackground(ViewUtilities.SECONDARY_COLOR);

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

        gbc.gridy++;
        JButton deleteScheduleButton = ViewUtilities.createStyledButton("Delete Selected Schedule");
        deleteScheduleButton.setBackground(new Color(0xEF4444));
        deleteScheduleButton.addActionListener(e -> {
            int selectedRow = scheduleTable.getSelectedRow();
            if (selectedRow >= 0) {
                String scheduleIdToDelete = scheduleTableModel.getScheduleIdAt(selectedRow);
                Controller controller = gui.getController();
                if (controller != null && scheduleIdToDelete != null) {
                    controller.deleteSchedule(scheduleIdToDelete);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a schedule to delete.", "No Schedule Selected", JOptionPane.WARNING_MESSAGE);
            }
        });
        controlsPanel.add(deleteScheduleButton, gbc);

        add(controlsPanel, BorderLayout.EAST);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setBackground(ViewUtilities.BACKGROUND_COLOR);
        JButton backButton = ViewUtilities.createStyledButton("Back to Admin Panel");
        backButton.addActionListener(e -> gui.showPanel(Gui.ADMIN_PANEL));
        bottomPanel.add(backButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    @Override
    public void update() {
        Controller controller = gui.getController();
        if (controller != null) {
            scheduleTableModel.setSchedules(controller.getSchedules());
        }
    }
}