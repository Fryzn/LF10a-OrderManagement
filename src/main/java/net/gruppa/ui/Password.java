package net.gruppa.ui;

import net.gruppa.main.CustomerHandler;
import net.gruppa.main.DataHandler;
import net.gruppa.main.UIHandler;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Password extends JPanel {

    private final UIHandler uiHandler;
    private final DataHandler dataHandler;
    private final CustomerHandler customerHandler;

    public Password(UIHandler uiHandler, DataHandler dataHandler, CustomerHandler customerHandler) {
        this.uiHandler = uiHandler;
        this.dataHandler = dataHandler;
        this.customerHandler = customerHandler;
        setup();
    }

    private static JLabel headlineLabel, usernameLabel, emailLabel, newPasswordLabel, confirmNewPasswordLabel;
    private static  JTextField usernameField, emailField;
    private static JPasswordField newPasswordField, confirmNewPasswordField;
    private static JButton confirmNewPasswordButton, backToLoginButton;

    public void setup() {
        setLayout(null);
        headlineLabel = new JLabel();
        headlineLabel.setText("Passwort vergessen?");
        headlineLabel.setFont(new Font("Arial", Font.BOLD,18));
        headlineLabel.setBounds(190, 50, 200, 150);
        add(headlineLabel);

        usernameLabel = new JLabel("Benutzername");
        usernameLabel.setBounds(190, 140, 200, 20);
        add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(190, 160, 200, 25);
        add(usernameField);

        emailLabel = new JLabel("E-Mail Adresse");
        emailLabel.setBounds(190, 190, 200, 20);
        add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(190, 210, 200, 25);
        add(emailField);

        newPasswordLabel = new JLabel("Neues Passwort");
        newPasswordLabel.setBounds(190, 240, 200, 20);
        add(newPasswordLabel);

        newPasswordField = new JPasswordField();
        newPasswordField.setBounds(190, 260, 200, 25);
        add(newPasswordField);

        confirmNewPasswordLabel = new JLabel("Neues Passwort best\u00e4tigen");
        confirmNewPasswordLabel.setBounds(190, 290, 200, 20);
        add(confirmNewPasswordLabel);

        confirmNewPasswordField = new JPasswordField();
        confirmNewPasswordField.setBounds(190, 310, 200, 25);
        add(confirmNewPasswordField);

        confirmNewPasswordButton = new JButton("Neues Passwort setzen");
        confirmNewPasswordButton.setBounds(205, 340, 170, 25);
        confirmNewPasswordButton.setForeground(Color.WHITE);
        confirmNewPasswordButton.setBackground(Color.DARK_GRAY);
        confirmNewPasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (usernameField.getText().equals("") && emailField.getText().equals("") &&
                        newPasswordField.getText().equals("") && confirmNewPasswordField.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Bitte f\u00fcllen Sie alle Felder aus!");
                    return;
                } else if (!newPasswordField.getText().equals(confirmNewPasswordField.getText())) {
                    JOptionPane.showMessageDialog(null, "Die Passw\u00f6rter stimmen nicht \u00fcberein!");
                    return;
                }

                for(int i = 0; i < dataHandler.getSavedPerson().getPersonList().size(); i++) {
                    if (dataHandler.getSavedPerson().getPersonList().get(i).getUsername().equals(usernameField.getText()) &&
                            dataHandler.getSavedPerson().getPersonList().get(i).getEmail().equals(emailField.getText())) {
                        dataHandler.getSavedPerson().getPersonList().get(i).setPassword(newPasswordField.getText());
                        destruct();
                        startLogin();
                        return;
                    }
                }
                JOptionPane.showMessageDialog(null, "Benutzername und E-Mail stimmen nicht \u00fcberein!");
            }
        });
        add(confirmNewPasswordButton);

        backToLoginButton = new JButton("Zur\u00fcck zum Login");
        backToLoginButton.setBounds(215, 370, 150, 25);
        backToLoginButton.setForeground(Color.WHITE);
        backToLoginButton.setBackground(Color.DARK_GRAY);
        backToLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                destruct();
                startLogin();
            }
        });
        add(backToLoginButton);

        //region Old reset password
        /*
        // Password Section
        Panel oldPasswordPanel = new Panel();
        oldPasswordPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
        Label oldPassword = new Label("Neues Passwort:");
        oldPasswordPanel.add(oldPassword);
        TextField oldPasswordText = new TextField(10);
        oldPasswordPanel.add(oldPasswordText);

        // Password Section:
        Panel passwordPanel = new Panel();
        passwordPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
        Label password = new Label("Passwort bestätigen:        ");
        passwordPanel.add(password);
        TextField passwordText = new TextField(10);
        passwordPanel.add(passwordText);

        // Save Button:
        Panel confirmPanel = new Panel();
        confirmPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        Button confirmButton = new Button("Confirm");
        confirmPanel.add(confirmButton);

        setLayout(new BorderLayout());
        add(oldPasswordPanel, BorderLayout.NORTH);
        add(passwordPanel, BorderLayout.CENTER);
        add(confirmButton, BorderLayout.SOUTH);

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(dataHandler.getSavedPerson().checkInformation(oldPasswordText.getText(), passwordText.getText())) {
                    dataHandler.setLoginedPerson(dataHandler.getSavedPerson().getPersonFromLogin(oldPasswordText.getText(), passwordText.getText()));
                    destruct();
                    startDashboard();
                }
            }
        });
        */
        //endregion
    }

    private void destruct() {
        uiHandler.removeSelf(this);
    }
    private void startLogin() {
        uiHandler.registerPanel(new Login(uiHandler, dataHandler, customerHandler));
        uiHandler.startWindow();
    }

}

