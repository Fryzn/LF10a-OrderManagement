package net.gruppa.ui;

import net.gruppa.main.CustomerHandler;
import net.gruppa.main.DataHandler;
import net.gruppa.main.UIHandler;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JPanel {

    private final UIHandler uiHandler;
    private final DataHandler dataHandler;
    private final CustomerHandler customerHandler;

    public Login(UIHandler uiHandler, DataHandler dataHandler, CustomerHandler customerHandler) {
        this.dataHandler = dataHandler;
        this.uiHandler = uiHandler;
        this.customerHandler = customerHandler;
        setup();
    }

    private static JLabel headlineLabel, usernameLabel, passwordLabel;
    private static JTextField usernameField;
    private static JPasswordField passwordField;
    private static JButton loginButton;
    private static  JButton forgotPasswordButton;

    public void setup() {
        setLayout(null);
        headlineLabel = new JLabel();
        headlineLabel.setText("Login");
        headlineLabel.setFont(new Font("Arial", Font.BOLD,20));
        headlineLabel.setBounds(190, 90, 100, 150);
        add(headlineLabel);

        usernameLabel = new JLabel("Benutzername");
        usernameLabel.setBounds(190, 180, 100, 20);
        add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(190, 200, 200, 25);
        add(usernameField);

        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(190, 230, 100, 20);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(190, 250, 200, 25);
        add(passwordField);

        forgotPasswordButton = new JButton("Passwort vergessen?");
        forgotPasswordButton.setBounds(205, 280, 170, 25);
        forgotPasswordButton.setForeground(Color.WHITE);
        forgotPasswordButton.setBackground(Color.DARK_GRAY);
        forgotPasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                destruct();
                startPassword();
            }
        });
        add(forgotPasswordButton);

        loginButton = new JButton("Login");
        loginButton.setBounds(245, 310, 90, 25);
        loginButton.setForeground(Color.WHITE);
        loginButton.setBackground(Color.DARK_GRAY);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(dataHandler.getSavedPerson().checkInformation(usernameField.getText(), passwordField.getText())) {
                    dataHandler.setLoginedPerson(dataHandler.getSavedPerson().getPersonFromLogin(usernameField.getText(), passwordField.getText()));
                    destruct();
                    startDashboard();
                } else {
                    JOptionPane.showMessageDialog(null, "Benutzername oder Passwort\nstimmen nicht \u00fcberein!");
                }
            }
        });
        add(loginButton);

        // Nur zum Debuggen
        usernameField.setText("jofoltin");
        passwordField.setText("1414");
    }

    private void destruct() {
        uiHandler.removeSelf(this);
    }

    private void startDashboard() {
        uiHandler.registerPanel(new Dashboard(uiHandler, dataHandler, customerHandler));
        uiHandler.startWindow();
    }

    private void startPassword() {
        uiHandler.registerPanel(new Password(uiHandler, dataHandler, customerHandler));
        uiHandler.startWindow();
    }
}