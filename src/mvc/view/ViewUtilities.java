package mvc.view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * A utility class providing static methods for creating and styling Swing components.
 * This ensures a consistent look and feel across the application.
 */
public class ViewUtilities {

    // Define the color palette for the application
    public static final Color PRIMARY_COLOR = new Color(0x3B82F6); // A modern blue
    public static final Color SECONDARY_COLOR = new Color(0x10B981); // A vibrant green for success
    public static final Color BACKGROUND_COLOR = new Color(0xF3F4F6); // A light gray background
    public static final Color FOREGROUND_COLOR = new Color(0x111827); // A dark gray for text
    public static final Color BORDER_COLOR = new Color(0xD1D5DB); // A light gray for borders

    // Define the fonts for the application
    public static final Font MAIN_FONT = new Font("Inter", Font.PLAIN, 14);
    public static final Font BOLD_FONT = new Font("Inter", Font.BOLD, 14);
    public static final Font TITLE_FONT = new Font("Inter", Font.BOLD, 28);

    /**
     * Creates a styled JButton.
     *
     * @param text The text for the button.
     * @return A styled JButton.
     */
    public static JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(BOLD_FONT);
        button.setBackground(PRIMARY_COLOR);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        return button;
    }

    /**
     * Creates a styled JPanel with a consistent background color and padding.
     *
     * @return A styled JPanel.
     */
    public static JPanel createStyledPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(BACKGROUND_COLOR);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        return panel;
    }

    /**
     * Creates a styled JLabel for titles.
     *
     * @param text The text for the label.
     * @return A styled JLabel.
     */
    public static JLabel createTitleLabel(String text) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(TITLE_FONT);
        label.setForeground(FOREGROUND_COLOR);
        return label;
    }

    /**
     * Creates a styled JLabel for regular text.
     *
     * @param text The text for the label.
     * @return A styled JLabel.
     */
    public static JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(MAIN_FONT);
        label.setForeground(FOREGROUND_COLOR);
        return label;
    }
}
