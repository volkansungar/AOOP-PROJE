package mvc.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The registration page for the application.
 * Allows new users to create an account.
 */
public class RegisterPage extends JPanel {

    private Gui gui;
    private JTextField nameField;
    private JPasswordField passField;
    private JPasswordField passRepeatField;

    /**
     * Constructor for the RegisterPage.
     * @param gui The main GUI controller.
     */
    public RegisterPage(Gui gui) {
        this.gui = gui;
        setLayout(new GridBagLayout());
        setBackground(new Color(245, 245, 245));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        JLabel titleLabel = new JLabel("Register");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;
        gbc.gridx = 0;
        add(new JLabel("Username:"), gbc);

        gbc.gridx = 1;
        nameField = new JTextField(20);
        add(nameField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        add(new JLabel("Password:"), gbc);

        gbc.gridx = 1;
        passField = new JPasswordField(20);
        add(passField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        add(new JLabel("Repeat Password:"), gbc);

        gbc.gridx = 1;
        passRepeatField = new JPasswordField(20);
        add(passRepeatField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JButton registerButton = new JButton("Register");

        // Create a single ActionListener for the registration action.
        // This can be triggered by the button or by pressing Enter in the final password field.
        ActionListener registerAction = e -> {
            String name = nameField.getText();
            String password = new String(passField.getPassword());
            String passRepeat = new String(passRepeatField.getPassword());
            if (gui.getController() != null) {
                gui.getController().tryRegister(name, password, passRepeat);
            }
        };

        // Add the action listener to both the button and the final password field.
        registerButton.addActionListener(registerAction);
        passRepeatField.addActionListener(registerAction);

        add(registerButton, gbc);

        gbc.gridy++;
        JButton returnButton = new JButton("Back to Login");
        returnButton.addActionListener(e -> gui.showPanel(Gui.LOGIN_PANEL));
        add(returnButton, gbc);
    }
}
