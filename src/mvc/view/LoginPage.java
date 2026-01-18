package mvc.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginPage extends JPanel {

    private final Gui gui;
    private final JTextField nameField;
    private final JPasswordField passField;

    public LoginPage(Gui gui) {
        this.gui = gui;
        setLayout(new GridBagLayout());
        setBackground(ViewUtilities.BACKGROUND_COLOR);

        JPanel formPanel = ViewUtilities.createStyledPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ViewUtilities.BORDER_COLOR, 1, true),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;

        formPanel.add(ViewUtilities.createTitleLabel("Login"), gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(ViewUtilities.createLabel("Username:"), gbc);

        gbc.gridx = 1;
        nameField = new JTextField(20);
        nameField.setFont(ViewUtilities.MAIN_FONT);
        formPanel.add(nameField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        formPanel.add(ViewUtilities.createLabel("Password:"), gbc);

        gbc.gridx = 1;
        passField = new JPasswordField(20);
        passField.setFont(ViewUtilities.MAIN_FONT);
        formPanel.add(passField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JButton loginButton = ViewUtilities.createStyledButton("Login");

        ActionListener loginAction = e -> {
            String name = nameField.getText();
            String password = new String(passField.getPassword());
            if (gui.getController() != null) {
                gui.getController().tryLogin(name, password);
            }
        };

        loginButton.addActionListener(loginAction);
        passField.addActionListener(loginAction);
        formPanel.add(loginButton, gbc);

        gbc.gridy++;
        JButton registerButton = new JButton("Don't have an account? Register");
        registerButton.setFont(ViewUtilities.MAIN_FONT);
        registerButton.setForeground(ViewUtilities.PRIMARY_COLOR);
        registerButton.setBorderPainted(false);
        registerButton.setContentAreaFilled(false);
        registerButton.setFocusPainted(false);
        registerButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        registerButton.addActionListener(e -> gui.showPanel(Gui.REGISTER_PANEL));
        formPanel.add(registerButton, gbc);

        add(formPanel);
    }
}
