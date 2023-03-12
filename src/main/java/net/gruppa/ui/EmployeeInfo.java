package net.gruppa.ui;

import net.gruppa.entity.Address;
import net.gruppa.entity.Person;
import net.gruppa.main.CustomerHandler;
import net.gruppa.main.DataHandler;
import net.gruppa.main.UIHandler;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeInfo extends JPanel {

    private final UIHandler uiHandler;
    private final DataHandler dataHandler;
    private final CustomerHandler customerHandler;
    private final int employeeId;

    public EmployeeInfo(UIHandler uiHandler, DataHandler dataHandler, CustomerHandler customerHandler, int employeeId) {
        this.uiHandler = uiHandler;
        this.dataHandler = dataHandler;
        this.customerHandler = customerHandler;
        this.employeeId = employeeId;
        setup();
    }

    private static JPanel employeeInfoPanel;
    private static JLabel firstnameLabel, lastnameLabel, emailLabel, usernameLabel, streetLabel, houseNumberLabel, postCodeLabel, cityLabel;
    private static JTextField firstnameField, lastnameField, emailField, usernameField, streetField, houseNumberField, postCodeField, cityField;
    private static JButton goBackButton, deleteEmployeeButton;

    private void setup() {
        String borderTitle = "Mitarbeiter: " + dataHandler.getSavedPerson().getPersonList().get(employeeId).getName() + " " +
                dataHandler.getSavedPerson().getPersonList().get(employeeId).getLastname();

        setLayout(null);
        // Employee Info Panel
        employeeInfoPanel = new JPanel();
        employeeInfoPanel.setLayout(null);
        employeeInfoPanel.setBounds(20, 20, 540, 250);
        employeeInfoPanel.setBorder(BorderFactory.createTitledBorder(borderTitle));
        ((javax.swing.border.TitledBorder) employeeInfoPanel.getBorder()).
                setTitleFont(new Font("Arial", Font.BOLD, 14));

        // General Employee Info
        firstnameLabel = new JLabel("Vorname");
        firstnameLabel.setBounds(25, 30, 100, 20);
        employeeInfoPanel.add(firstnameLabel);
        firstnameField = new JTextField();
        firstnameField.setBounds(25, 50, 200, 25);
        firstnameField.setText(dataHandler.getSavedPerson().getPersonList().get(employeeId).getName());
        employeeInfoPanel.add(firstnameField);
        lastnameLabel = new JLabel("Nachname");
        lastnameLabel.setBounds(25, 80, 100, 20);
        employeeInfoPanel.add(lastnameLabel);
        lastnameField = new JTextField();
        lastnameField.setBounds(25, 100, 200, 25);
        lastnameField.setText(dataHandler.getSavedPerson().getPersonList().get(employeeId).getLastname());
        employeeInfoPanel.add(lastnameField);
        emailLabel = new JLabel("E-Mail");
        emailLabel.setBounds(25, 130, 100, 20);
        employeeInfoPanel.add(emailLabel);
        emailField = new JTextField();
        emailField.setBounds(25, 150, 200, 25);
        emailField.setText(dataHandler.getSavedPerson().getPersonList().get(employeeId).getEmail());
        employeeInfoPanel.add(emailField);
        usernameLabel = new JLabel("Benutzername");
        usernameLabel.setBounds(25, 180, 100, 20);
        employeeInfoPanel.add(usernameLabel);
        usernameField = new JTextField();
        usernameField.setBounds(25, 200, 200, 25);
        usernameField.setText(dataHandler.getSavedPerson().getPersonList().get(employeeId).getUsername());
        employeeInfoPanel.add(usernameField);

        // Employee Address
        streetLabel = new JLabel("Stra\u00dfe");
        streetLabel.setBounds(250, 30, 100, 20);
        employeeInfoPanel.add(streetLabel);
        streetField = new JTextField();
        streetField.setBounds(250, 50, 200, 25);
        streetField.setText(dataHandler.getSavedPerson().getPersonList().get(employeeId).getAddress().getStreet());
        employeeInfoPanel.add(streetField);
        houseNumberLabel = new JLabel("Hausnummer");
        houseNumberLabel.setBounds(250, 80, 100, 20);
        employeeInfoPanel.add(houseNumberLabel);
        houseNumberField = new JTextField();
        houseNumberField.setBounds(250, 100, 75, 25);
        houseNumberField.setText(String.valueOf(dataHandler.getSavedPerson().getPersonList().get(employeeId).getAddress().getHouseNumber()));
        employeeInfoPanel.add(houseNumberField);
        postCodeLabel = new JLabel("Postleitzahl");
        postCodeLabel.setBounds(250, 130, 100, 20);
        employeeInfoPanel.add(postCodeLabel);
        postCodeField = new JTextField();
        postCodeField.setBounds(250, 150, 75, 25);
        postCodeField.setText(String.valueOf(dataHandler.getSavedPerson().getPersonList().get(employeeId).getAddress().getPostCode()));
        employeeInfoPanel.add(postCodeField);
        cityLabel = new JLabel("Stadt");
        cityLabel.setBounds(250, 180, 100, 20);
        employeeInfoPanel.add(cityLabel);
        cityField = new JTextField();
        cityField.setBounds(250, 200, 200, 25);
        cityField.setText(dataHandler.getSavedPerson().getPersonList().get(employeeId).getAddress().getCity());
        employeeInfoPanel.add(cityField);

        // Go Back Button
        goBackButton = new JButton("Zur\u00fcck zur Mitarbeiter\u00fcbersicht");
        goBackButton.setBounds(180, 275, 230, 25);
        goBackButton.setForeground(Color.WHITE);
        goBackButton.setBackground(Color.DARK_GRAY);
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!checkEmployeeChanges()) {
                    return;
                }
                destruct();
                startEmployeeOverview();
            }
        });

        // Delete Customer Button
        deleteEmployeeButton = new JButton("Mitarbeiter l\u00f6schen");
        deleteEmployeeButton.setBounds(220, 305, 150, 25);
        deleteEmployeeButton.setForeground(Color.WHITE);
        deleteEmployeeButton.setBackground(Color.DARK_GRAY);
        deleteEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int dialogResult = JOptionPane.showConfirmDialog(null,
                        "M\u00f6chtest du den Mitarbeiter wirklich l\u00f6schen?", "Meldung", JOptionPane.YES_NO_OPTION);
                if (dialogResult == JOptionPane.YES_OPTION) {
                    dataHandler.deletePerson(dataHandler.getSavedPerson().getPersonList().get(employeeId));
                    destruct();
                    startEmployeeOverview();
                }
            }
        });

        // Frame Settings
        add(employeeInfoPanel);
        add(goBackButton);
        add(deleteEmployeeButton);
        setVisible(true);
    }

    private boolean checkEmployeeChanges() {
        if (isNotNumeric(houseNumberField.getText())) {
            JOptionPane.showMessageDialog(null, "Hausnummer muss eine Zahl sein!");
            return false;
        } else if (isNotNumeric(postCodeField.getText())) {
            JOptionPane.showMessageDialog(null, "Postleitzahl darf nur aus Zahlen bestehen!");
            return false;
        }

        Person employee = dataHandler.getSavedPerson().getPersonList().get(employeeId);
        if (!employee.getName().equals(firstnameField.getText())) {
            employee.setName(firstnameField.getText());
        }
        if (!employee.getLastname().equals(lastnameField.getText())) {
            employee.setLastname(lastnameField.getText());
        }
        if (!employee.getEmail().equals(emailField.getText())) {
            employee.setEmail(emailField.getText());
        }
        if (!employee.getUsername().equals(usernameField.getText())) {
            employee.setUsername(usernameField.getText());
        }
        if (!employee.getAddress().getStreet().equals(streetField.getText()) || !String.valueOf(employee.getAddress().getHouseNumber()).equals(houseNumberField.getText()) ||
                !String.valueOf(employee.getAddress().getPostCode()).equals(postCodeField.getText()) || !employee.getAddress().getCity().equals(cityField.getText())) {
            Address newAddress = new Address(streetField.getText(), Integer.parseInt(houseNumberField.getText()), Integer.parseInt(postCodeField.getText()), cityField.getText());
            employee.setAddress(newAddress);
        }
        return true;
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
