package org.example.views;

import org.example.utils.HttpUtils;

import javax.swing.*;
import java.awt.*;

public class LoginUI extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginUI() {
        super();
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Login");
        setSize(500, 300);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 10, 10, 10);

        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");

        usernameField = new JTextField();
        usernameField.setPreferredSize(new Dimension(200, 30));

        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(200, 30));

        JButton loginButton = new JButton("Login");
        loginButton.setBackground(Color.ORANGE);
        loginButton.setForeground(Color.BLACK);
        loginButton.setPreferredSize(new Dimension(100, 40));

        JButton exitButton = new JButton("Exit");
        exitButton.setBackground(Color.RED);
        exitButton.setForeground(Color.BLACK);
        exitButton.setPreferredSize(new Dimension(100, 40));

        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(usernameLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        panel.add(usernameField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(passwordLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        panel.add(passwordField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        panel.add(loginButton, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        panel.add(exitButton, constraints);

        setContentPane(panel);

        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            char[] password = passwordField.getPassword();
            HttpUtils httpUtils = new HttpUtils();
            httpUtils.sendGet("http://localhost:8080/api/users");
            if (isValidLogin(username, password)) {
                // Authentication successful, you can proceed to open the main application window
                dispose(); // Close the login window
                new AdminUI(); // Open the main application window
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password", "Login Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        exitButton.addActionListener(e -> System.exit(0));

        setVisible(true);
    }

    private boolean isValidLogin(String username, char[] password) {
        return username.equals("admin") && new String(password).equals("admin");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginUI::new);
    }
}
