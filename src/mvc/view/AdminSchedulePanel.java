package mvc.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * A dedicated panel for administrators to add or delete voyages.
 * Contains forms for input and a table to display existing voyages.
 */
public class AdminSchedulePanel extends JPanel {

    private Gui gui;

    /**
     * Constructor for the AdminVoyagePanel.
     * @param gui The main GUI controller.
     */
    public AdminSchedulePanel(Gui gui) {
        this.gui = gui;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Title
        JLabel titleLabel = new JLabel("Voyage Management", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        // Placeholder for the list of voyages (e.g., a JTable)
        // For now, it's just a simple text area.
        JTextArea voyageList = new JTextArea("Placeholder for voyage list...\nVoyage 1\nVoyage 2");
        voyageList.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(voyageList);
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
        JButton addVoyageButton = new JButton("Add Voyage");
        ActionListener addVoyageAction = e -> {
            String voyage = scheduleNameField.getText();
            if (!lookupSchedule()) {
            }
        };
        addVoyageButton.addActionListener(addVoyageAction);
        controlsPanel.add(addVoyageButton, gbc);

        gbc.gridy++;
        JButton deleteVoyageButton = new JButton("Delete Selected Voyage");
        // TODO: Add ActionListener to call a controller method to delete a voyage
        controlsPanel.add(deleteVoyageButton, gbc);

        add(controlsPanel, BorderLayout.EAST);


        // Bottom panel for navigation
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton backButton = new JButton("Back to Admin Panel");
        backButton.addActionListener(e -> gui.showPanel(Gui.ADMIN_PANEL));
        bottomPanel.add(backButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}
