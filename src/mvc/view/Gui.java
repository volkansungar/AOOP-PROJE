package mvc.view;

import mvc.controller.UserController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui {
    public UserPanel userPanel;
    private JFrame frame;
    public LoginPage loginPage;
    public ControlPanel controlPanel;
    public Page currentPage;
    public RegisterPage registerPage;
    private UserView view;
    private UserController controller;

    // GUI CONSTRUCTOR
    public Gui() {
        loginPage = new LoginPage();
        controlPanel = new ControlPanel();
        userPanel = new UserPanel();
        registerPage = new RegisterPage();
        // 1. JFrame oluşturma (ana pencere)
        frame = new JFrame("Online Rezervasyon Sistemi");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        // 5. Pencere boyutunu ayarla ve görünür yap
        frame.setLocationRelativeTo(null);
    }

    public void setController(UserController controller) {
        this.controller = controller;
    }

    public void setView(UserView view) {
        this.view = view;
    }

    public void showFrame() {
        // Frame'i yenile
        frame.revalidate();
        frame.repaint();
        frame.pack();
        frame.setVisible(true);
    }

    abstract class Page {
        protected JLabel messageBox = new JLabel("");
        abstract void renderPage();
    }

    // LOGIN PAGE
    class LoginPage extends Page {
        JPanel top_panel, center_panel, bottom_panel;

        private LoginPage() {
            // top panel
            top_panel = new JPanel();
            top_panel.setBackground(Color.LIGHT_GRAY);
            top_panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            JLabel label = new JLabel("Login Page");
            label.setFont(new Font("Arial", Font.BOLD, 16));
            top_panel.add(label);

            // center panel with better layout
            center_panel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);

            JTextField nameField = new JTextField(20);
            JPasswordField passField = new JPasswordField(20); // Fixed!

            JButton login_button = new JButton("Login");
            // ... action listener code ...
            login_button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String name = nameField.getText();
                    String password = passField.getText();
                    if (controller != null) {
                        controller.tryLogin(name, password);
                    }
                }
            });

            // name fild
            gbc.gridx = 0; gbc.gridy = 0;
            center_panel.add(new JLabel("Kullanıcı Adı:"), gbc);
            gbc.gridx = 1; gbc.gridy = 0;
            center_panel.add(nameField, gbc);

            // password field
            gbc.gridx = 0; gbc.gridy = 1;
            center_panel.add(new JLabel("Şifre:"), gbc);
            gbc.gridx = 1; gbc.gridy = 1;
            center_panel.add(passField, gbc);

            // login button
            gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
            center_panel.add(login_button, gbc);

            // bottom panel
            bottom_panel = new JPanel();
            JButton register_button = new JButton("Register");
            register_button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    view.retrieveRegisterPage();
                }
            });
            bottom_panel.add(register_button);
            bottom_panel.add(messageBox);


        }

        @Override
        public void renderPage() {
            // Önceki içeriği temizle
            frame.getContentPane().removeAll();

            // Yeni panelleri ekle
            frame.getContentPane().add(top_panel, BorderLayout.NORTH);
            frame.getContentPane().add(center_panel, BorderLayout.CENTER);
            frame.getContentPane().add(bottom_panel, BorderLayout.SOUTH);

            currentPage = this;
        }
    }

    // CONTROL PANEL PAGE
    class ControlPanel extends Page {
        JPanel top_panel, center_panel, bottom_panel;

        private ControlPanel() {
            top_panel = new JPanel();
            top_panel.setBackground(Color.LIGHT_GRAY);

            JLabel label = new JLabel("Control Panel");
            label.setFont(new Font("Arial", Font.BOLD, 16));
            top_panel.add(label);

            center_panel = new JPanel();
            center_panel.setLayout(new GridLayout(2,4));

            JButton option1 = new JButton("Sefer ekle/sil");
            option1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(frame, "Düğmeye tıklandı!");
                }
            });
            center_panel.add(option1);

            JButton option2 = new JButton("Sefer İşlemleri");
            option2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(frame, "Düğmeye tıklandı!");
                }
            });
            center_panel.add(option2);

            JButton option3 = new JButton("Rezervasyon İşlemleri");
            option1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(frame, "Düğmeye tıklandı!");
                }
            });
            center_panel.add(option3);

            bottom_panel = new JPanel();
            JButton logoutButton = new JButton("Log Out");
            logoutButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    controller.logOut();
                }
            });
            bottom_panel.add(logoutButton);

        }

        @Override
        public void renderPage() {
            // Önceki içeriği temizle
            frame.getContentPane().removeAll();

            // Yeni paneli ekle
            frame.getContentPane().add(top_panel, BorderLayout.NORTH);
            frame.getContentPane().add(center_panel, BorderLayout.CENTER);
            frame.getContentPane().add(bottom_panel, BorderLayout.SOUTH);

            currentPage = this;
        }
    }

    // USER PAGE
    class UserPanel extends Page {
        JPanel top_panel, center_panel, bottom_panel;

        private UserPanel() {
            top_panel = new JPanel();
            top_panel.setBackground(Color.LIGHT_GRAY);

            JLabel label = new JLabel("User Panel");
            label.setFont(new Font("Arial", Font.BOLD, 16));
            top_panel.add(label);

            center_panel = new JPanel();
            center_panel.setLayout(new GridLayout(2,4));


            JButton option2 = new JButton("Sefer İşlemleri");
            option2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(frame, "Düğmeye tıklandı!");
                }
            });
            center_panel.add(option2);

            JButton option3 = new JButton("Rezervasyon İşlemleri");
            option3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(frame, "Düğmeye tıklandı!");
                }
            });
            center_panel.add(option3);

            bottom_panel = new JPanel();
            JButton logoutButton = new JButton("Log Out");
            logoutButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    controller.logOut();
                }
            });
            bottom_panel.add(logoutButton);
        }

        @Override
        public void renderPage() {
            // Önceki içeriği temizle
            frame.getContentPane().removeAll();

            // Yeni paneli ekle
            frame.getContentPane().add(top_panel, BorderLayout.NORTH);
            frame.getContentPane().add(center_panel, BorderLayout.CENTER);
            frame.getContentPane().add(bottom_panel, BorderLayout.SOUTH);

            currentPage = this;
        }
    }

    // REGISTER PAGE
    class RegisterPage extends Page {
        JPanel top_panel, center_panel, bottom_panel;
        private RegisterPage() {
            // top panel
            top_panel = new JPanel();
            top_panel.setBackground(Color.LIGHT_GRAY);

            JLabel label = new JLabel("Register Page");
            label.setFont(new Font("Arial", Font.BOLD, 16));
            top_panel.add(label);

            // center panel
            center_panel = new JPanel();
            center_panel.setLayout(new BoxLayout(center_panel, BoxLayout.PAGE_AXIS));

            JTextField nameField = new JTextField(20);
            JTextField passField = new JPasswordField(20); // Şifre için JPasswordField kullan
            JTextField passRepeatField = new JPasswordField(20);


            JButton login_button = new JButton("Register");
            login_button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String name = nameField.getText();
                    String password = passField.getText();
                    String pass_repeat = passRepeatField.getText();
                    if (controller != null) {
                        controller.tryRegister(name, password, pass_repeat);
                    }
                }
            });

            JButton return_button = new JButton("Return to Login");
            return_button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    view.retrieveLoginPage();
                }
            });


            center_panel.add(new JLabel("Kullanıcı Adı:"));
            center_panel.add(nameField);
            center_panel.add(new JLabel("Şifre:"));
            center_panel.add(passField);
            center_panel.add(new JLabel("Şifre Tekrar:"));
            center_panel.add(passRepeatField);
            center_panel.add(login_button);
            center_panel.add(return_button);
            center_panel.add(messageBox);

        }

        @Override
        public void renderPage() {
            // Önceki içeriği temizle
            frame.getContentPane().removeAll();
            // Yeni panelleri ekle
            frame.getContentPane().add(top_panel, BorderLayout.NORTH);
            frame.getContentPane().add(center_panel, BorderLayout.CENTER);


            currentPage = this;
        }
    }
}