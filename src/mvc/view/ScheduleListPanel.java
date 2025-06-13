package mvc.view;

import mvc.patterns.Observer;

import javax.swing.*;
import java.awt.*;

/**
 * A panel for both users and admins to view and query a list of available voyages.
 * This view is read-only but could include search and filter functionality.
 */
public class ScheduleListPanel extends JPanel implements Observer {

    private Gui gui;
    // We store the previous panel to implement a universal "back" button.
    private String previousPanel;

    /**
     * Constructor for the VoyageListPanel.
     * @param gui The main GUI controller.
     */
    public ScheduleListPanel(Gui gui) {
        this.gui = gui;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Title
        JLabel titleLabel = new JLabel("Available Voyages", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        // Placeholder for the list/table of voyages
        String[] columnNames = {"ID", "From", "To", "Date", "Seats Available"};
        Object[][] data = {
                {"V101", "Istanbul", "Ankara", "2025-10-20", "15"},
                {"V102", "Izmir", "Bursa", "2025-10-21", "5"},
                {"V103", "Ankara", "Antalya", "2025-10-22", "20"}
        };
        JTable voyageTable = new JTable(data, columnNames);
        voyageTable.setFillsViewportHeight(true);
        add(new JScrollPane(voyageTable), BorderLayout.CENTER);

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
    }

    @Override
    public void update() {
        System.out.println("VoyageListPanel received an update notification. Refreshing view...");
        refreshScheduleList();
    }

    // A method to get fresh data from the model and redraw the table
    private void refreshScheduleList() {
        // This logic needs to be properly implemented with a TableModel
        System.out.println("Fetching updated voyage list from the model.");
        // Example of what would happen here:
        // List<Voyage> voyages = gui.getController().getVoyages();
        // MyTableModel model = (MyTableModel)voyageTable.getModel();
        // model.setVoyages(voyages);
        // table.repaint();
        JOptionPane.showMessageDialog(this, "Voyage list has been updated!");
    }
    /**
     * Sets the panel to return to when the back button is clicked.
     * @param previousPanel The name of the panel to go back to (e.g., Gui.ADMIN_PANEL).
     */
    public void setPreviousPanel(String previousPanel) {
        this.previousPanel = previousPanel;
    }


}
