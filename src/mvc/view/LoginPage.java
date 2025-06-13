package mvc.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The login page for the application.
 * Allows users to enter their credentials to log in or switch to the registration page.
 */
public class LoginPage extends JPanel {

    private Gui gui;
    private JTextField nameField;
    private JPasswordField passField;

    /**
     * Constructor for the LoginPage.
     * @param gui The main GUI controller.
     */
    public LoginPage(Gui gui) {
        this.gui = gui;
        setLayout(new GridBagLayout());
        setBackground(new Color(245, 245, 245));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        JLabel titleLabel = new JLabel("Login");
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
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JButton loginButton = new JButton("Login");

        // Create a single ActionListener for the login action.
        // This can be triggered by the button or by pressing Enter in the password field.
        ActionListener loginAction = e -> {
            String name = nameField.getText();
            String password = new String(passField.getPassword());
            if (gui.getController() != null) {
                gui.getController().tryLogin(name, password);
            }
        };

        // Add the action listener to both the button and the password field.
        loginButton.addActionListener(loginAction);
        passField.addActionListener(loginAction);

        add(loginButton, gbc);

        gbc.gridy++;
        JButton registerButton = new JButton("Don't have an account? Register");
        registerButton.addActionListener(e -> gui.showPanel(Gui.REGISTER_PANEL));
        add(registerButton, gbc);
    }
}
