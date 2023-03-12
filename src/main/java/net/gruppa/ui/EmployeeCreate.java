package net.gruppa.ui;

import net.gruppa.entity.Address;
import net.gruppa.main.CustomerHandler;
import net.gruppa.main.DataHandler;
import net.gruppa.main.UIHandler;
import net.gruppa.entity.Person;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeCreate extends JPanel {

    private final UIHandler uiHandler;
    private final DataHandler dataHandler;
    private final CustomerHandler customerHandler;
    private Person employee;
    private Address address;

    public EmployeeCreate(UIHandler uiHandler, DataHandler dataHandler, CustomerHandler customerHandler) {
        this.uiHandler = uiHandler;
        this.dataHandler = dataHandler;
        this.customerHandler = customerHandler;
        setup();
    }

    private static JPanel userInfoPanel, userAddressPanel;
    private static JLabel firstnameLabel, lastnameLabel, emailLabel, usernameLabel, passwordLabel, streetLabel, houseNumberLabel, postCodeLabel, cityLabel;
    private static JTextField firstnameField, lastnameField, emailField, usernameField, streetField, houseNumberField, postCodeField, cityField;
    private static JPasswordField passwordField;
    private static JButton createEmployeeButton, goBackButton;

    private void setup() {
        setLayout(null);
        // General User Info Panel
        userInfoPanel = new JPanel();
        userInfoPanel.setLayout(null);
        userInfoPanel.setBounds(20, 20, 260, 300);
        userInfoPanel.setBorder(BorderFactory.createTitledBorder("Allgemeine Daten"));
        ((javax.swing.border.TitledBorder) userInfoPanel.getBorder()).
                setTitleFont(new Font("Arial", Font.BOLD, 14));

        firstnameLabel = new JLabel("Vorname");
        firstnameLabel.setBounds(25, 30, 100, 20);
        userInfoPanel.add(firstnameLabel);
        firstnameField = new JTextField();
        firstnameField.setBounds(25, 50, 200, 25);
        userInfoPanel.add(firstnameField);
        lastnameLabel = new JLabel("Nachname");
        lastnameLabel.setBounds(25, 80, 100, 20);
        userInfoPanel.add(lastnameLabel);
        lastnameField = new JTextField();
        lastnameField.setBounds(25, 100, 200, 25);
        userInfoPanel.add(lastnameField);
        emailLabel = new JLabel("E-Mail");
        emailLabel.setBounds(25, 130, 100, 20);
        userInfoPanel.add(emailLabel);
        emailField = new JTextField();
        emailField.setBounds(25, 150, 200, 25);
        userInfoPanel.add(emailField);
        usernameLabel = new JLabel("Benutzername");
        usernameLabel.setBounds(25, 180, 100, 20);
        userInfoPanel.add(usernameLabel);
        usernameField = new JTextField();
        usernameField.setBounds(25, 200, 200, 25);
        userInfoPanel.add(usernameField);
        passwordLabel = new JLabel("Passwort");
        passwordLabel.setBounds(25, 230, 100, 20);
        userInfoPanel.add(passwordLabel);
        passwordField = new JPasswordField();
        passwordField.setBounds(25, 250, 200, 25);
        userInfoPanel.add(passwordField);

        // User Address Panel
        userAddressPanel = new JPanel();
        userAddressPanel.setLayout(null);
        userAddressPanel.setBounds(290, 20, 260, 300);
        userAddressPanel.setBorder(BorderFactory.createTitledBorder("Adresse"));
        ((javax.swing.border.TitledBorder) userAddressPanel.getBorder()).
                setTitleFont(new Font("Arial", Font.BOLD, 14));

        streetLabel = new JLabel("Stra\u00dfe");
        streetLabel.setBounds(25, 30, 100, 20);
        userAddressPanel.add(streetLabel);
        streetField = new JTextField();
        streetField.setBounds(25, 50, 200, 25);
        userAddressPanel.add(streetField);
        houseNumberLabel = new JLabel("Hausnummer");
        houseNumberLabel.setBounds(25, 80, 100, 20);
        userAddressPanel.add(houseNumberLabel);
        houseNumberField = new JTextField();
        houseNumberField.setBounds(25, 100, 75, 25);
        userAddressPanel.add(houseNumberField);
        postCodeLabel = new JLabel("Postleitzahl");
        postCodeLabel.setBounds(25, 130, 100, 20);
        userAddressPanel.add(postCodeLabel);
        postCodeField = new JTextField();
        postCodeField.setBounds(25, 150, 75, 25);
        userAddressPanel.add(postCodeField);
        cityLabel = new JLabel("Stadt");
        cityLabel.setBounds(25, 180, 100, 20);
        userAddressPanel.add(cityLabel);
        cityField = new JTextField();
        cityField.setBounds(25, 200, 200, 25);
        userAddressPanel.add(cityField);

        // Create Employee Button
        createEmployeeButton = new JButton("Neuen Mitarbeiter erstellen");
        createEmployeeButton.setBounds(190, 330, 200, 25);
        createEmployeeButton.setForeground(Color.WHITE);
        createEmployeeButton.setBackground(Color.DARK_GRAY);
        createEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (firstnameField.getText().equals("") || lastnameField.getText().equals("") || emailField.getText().equals("") || usernameField.getText().equals("") ||
                        passwordField.getText().equals("") || streetField.getText().equals("") || houseNumberField.getText().equals("") ||
                        postCodeField.getText().equals("") || cityField.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Bitte f\u00fcllen Sie alle Felder aus!");
                    return;
                } else if (isNotNumeric(houseNumberField.getText())) {
                    JOptionPane.showMessageDialog(null, "Hausnummer muss eine Zahl sein!");
                    return;
                } else if (isNotNumeric(postCodeField.getText())) {
                    JOptionPane.showMessageDialog(null, "Postleitzahl darf nur aus Zahlen bestehen!");
                    return;
                }

                address = new Address(streetField.getText(), Integer.parseInt(houseNumberField.getText()),
                        Integer.parseInt(postCodeField.getText()), cityField.getText());
                employee = new Person(dataHandler.getSavedPerson().getPersonList().size(), usernameField.getText(), firstnameField.getText(),
                        lastnameField.getText(), passwordField.getText(), emailField.getText(), address);
                dataHandler.createPerson(employee);
                destruct();
                startEmployeeOverview();
            }
        });

        // Go Back Button
        goBackButton = new JButton("Abbrechen");
        goBackButton.setBounds(240, 360, 100, 25);
        goBackButton.setForeground(Color.WHITE);
        goBackButton.setBackground(Color.DARK_GRAY);
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                destruct();
                startEmployeeOverview();
            }
        });

        // Frame Settings
        add(userInfoPanel);
        add(userAddressPanel);
        add(createEmployeeButton);
        add(goBackButton);
        setVisible(true);
    }

    private boolean isNotNumeric(String value) {
        try {
            Double.parseDouble(value);
            return false;
        } catch (Exception ex) {
            return true;
        }
    }

    private void destruct() {
        uiHandler.removeSelf(this);
    }

    private void startEmployeeOverview() {
        uiHandler.registerPanel(new Dashboard(uiHandler, dataHandler, customerHandler, 1));
        uiHandler.startWindow();
    }
}
