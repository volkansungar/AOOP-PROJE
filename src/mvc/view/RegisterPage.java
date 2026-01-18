package mvc.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class RegisterPage extends JPanel {

    private final Gui gui;
    private final JTextField nameField;
    private final JPasswordField passField;
    private final JPasswordField passRepeatField;

    public RegisterPage(Gui gui) {
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

        formPanel.add(ViewUtilities.createTitleLabel("Register"), gbc);

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
        formPanel.add(ViewUtilities.createLabel("Repeat Password:"), gbc);

        gbc.gridx = 1;
        passRepeatField = new JPasswordField(20);
        passRepeatField.setFont(ViewUtilities.MAIN_FONT);
        formPanel.add(passRepeatField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JButton registerButton = ViewUtilities.createStyledButton("Register");

        ActionListener registerAction = e -> {
            String name = nameField.getText();
            String password = new String(passField.getPassword());
            String passRepeat = new String(passRepeatField.getPassword());
            if (gui.getController() != null) {
                gui.getController().tryRegister(name, password, passRepeat);
            }
        };

        registerButton.addActionListener(registerAction);
        passRepeatField.addActionListener(registerAction);
        formPanel.add(registerButton, gbc);

        gbc.gridy++;
        JButton returnButton = new JButton("Back to Login");
        returnButton.setFont(ViewUtilities.MAIN_FONT);
        returnButton.setForeground(ViewUtilities.PRIMARY_COLOR);
        returnButton.setBorderPainted(false);
        returnButton.setContentAreaFilled(false);
        returnButton.setFocusPainted(false);
        returnButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        returnButton.addActionListener(e -> gui.showPanel(Gui.LOGIN_PANEL));
        formPanel.add(returnButton, gbc);

        add(formPanel);
    }
}