package net.gruppa.ui;

import net.gruppa.entity.Address;
import net.gruppa.entity.Customer;
import net.gruppa.main.CustomerHandler;
import net.gruppa.main.DataHandler;
import net.gruppa.main.UIHandler;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerInfo extends JPanel {

    private final UIHandler uiHandler;
    private final DataHandler dataHandler;
    private final CustomerHandler customerHandler;
    private final int customerId;

    public CustomerInfo(UIHandler uiHandler, DataHandler dataHandler, CustomerHandler customerHandler, int customerId) {
        this.uiHandler = uiHandler;
        this.dataHandler = dataHandler;
        this.customerHandler = customerHandler;
        this.customerId = customerId;
        setup();
    }

    private  static JPanel customerInfoPanel;
    private static JLabel companyNameLabel, contactPersonLabel, emailLabel, streetLabel, houseNumberLabel, postCodeLabel, cityLabel;
    private static JTextField companyNameField, contactPersonField, emailField, streetField, houseNumberField, postCodeField, cityField;
    private static JButton goBackButton, deleteCustomerButton;

    private void setup() {
        String borderTitle = "Kunde: " + customerHandler.getSavedCustomer().getCustomerList().get(customerId).getCompanyName();

        setLayout(null);
        // User Info Panel
        customerInfoPanel = new JPanel();
        customerInfoPanel.setLayout(null);
        customerInfoPanel.setBounds(20, 20, 540, 250);
        customerInfoPanel.setBorder(BorderFactory.createTitledBorder(borderTitle));
        ((javax.swing.border.TitledBorder) customerInfoPanel.getBorder()).
                setTitleFont(new Font("Arial", Font.BOLD, 14));

        // General Customer Info
        companyNameLabel = new JLabel("Firma");
        companyNameLabel.setBounds(25, 30, 100, 20);
        customerInfoPanel.add(companyNameLabel);
        companyNameField = new JTextField();
        companyNameField.setBounds(25, 50, 200, 25);
        companyNameField.setText(customerHandler.getSavedCustomer().getCustomerList().get(customerId).getCompanyName());
        customerInfoPanel.add(companyNameField);
        contactPersonLabel = new JLabel("Kontaktperson");
        contactPersonLabel.setBounds(25, 80, 100, 20);
        customerInfoPanel.add(contactPersonLabel);
        contactPersonField = new JTextField();
        contactPersonField.setBounds(25, 100, 200, 25);
        contactPersonField.setText(customerHandler.getSavedCustomer().getCustomerList().get(customerId).getContactPerson());
        customerInfoPanel.add(contactPersonField);
        emailLabel = new JLabel("E-Mail");
        emailLabel.setBounds(25, 130, 100, 20);
        customerInfoPanel.add(emailLabel);
        emailField = new JTextField();
        emailField.setBounds(25, 150, 200, 25);
        emailField.setText(customerHandler.getSavedCustomer().getCustomerList().get(customerId).getEmail());
        customerInfoPanel.add(emailField);

        // Customer Address Info
        streetLabel = new JLabel("Stra\u00dfe");
        streetLabel.setBounds(250, 30, 100, 20);
        customerInfoPanel.add(streetLabel);
        streetField = new JTextField();
        streetField.setBounds(250, 50, 200, 25);
        streetField.setText(customerHandler.getSavedCustomer().getCustomerList().get(customerId).getAddress().getStreet());
        customerInfoPanel.add(streetField);
        houseNumberLabel = new JLabel("Hausnummer");
        houseNumberLabel.setBounds(250, 80, 100, 20);
        customerInfoPanel.add(houseNumberLabel);
        houseNumberField = new JTextField();
        houseNumberField.setBounds(250, 100, 75, 25);
        houseNumberField.setText(String.valueOf(customerHandler.getSavedCustomer().getCustomerList().get(customerId).getAddress().getHouseNumber()));
        customerInfoPanel.add(houseNumberField);
        postCodeLabel = new JLabel("Postleitzahl");
        postCodeLabel.setBounds(250, 130, 100, 20);
        customerInfoPanel.add(postCodeLabel);
        postCodeField = new JTextField();
        postCodeField.setBounds(250, 150, 75, 25);
        postCodeField.setText(String.valueOf(customerHandler.getSavedCustomer().getCustomerList().get(customerId).getAddress().getPostCode()));
        customerInfoPanel.add(postCodeField);
        cityLabel = new JLabel("Stadt");
        cityLabel.setBounds(250, 180, 100, 20);
        customerInfoPanel.add(cityLabel);
        cityField = new JTextField();
        cityField.setBounds(250, 200, 200, 25);
        cityField.setText(customerHandler.getSavedCustomer().getCustomerList().get(customerId).getAddress().getCity());
        customerInfoPanel.add(cityField);

        // Go Back Button
        goBackButton = new JButton("Zur\u00fcck zur Kunden\u00fcbersicht");
        goBackButton.setBounds(180, 275, 210, 25);
        goBackButton.setForeground(Color.WHITE);
        goBackButton.setBackground(Color.DARK_GRAY);
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!checkCustomerChanges()) {
                    return;
                }
                destruct();
                startCustomerOverview();
            }
        });

        // Delete Customer Button
        deleteCustomerButton = new JButton("Kunde l\u00f6schen");
        deleteCustomerButton.setBounds(220, 305, 130, 25);
        deleteCustomerButton.setForeground(Color.WHITE);
        deleteCustomerButton.setBackground(Color.DARK_GRAY);
        deleteCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int dialogResult = JOptionPane.showConfirmDialog(null,
                        "M\u00f6chtest du den Kunden wirklich l\u00f6schen?", "Meldung", JOptionPane.YES_NO_OPTION);
                if (dialogResult == JOptionPane.YES_OPTION) {
                    customerHandler.deleteCustomer(customerHandler.getSavedCustomer().getCustomerList().get(customerId));
                    destruct();
                    startCustomerOverview();
                }
            }
        });

        // Frame Settings
        add(customerInfoPanel);
        add(goBackButton);
        add(deleteCustomerButton);
        setVisible(true);
    }

    private boolean checkCustomerChanges() {
        if (isNotNumeric(houseNumberField.getText())) {
            JOptionPane.showMessageDialog(null, "Hausnummer muss eine Zahl sein!");
            return false;
        } else if (isNotNumeric(postCodeField.getText())) {
            JOptionPane.showMessageDialog(null, "Postleitzahl darf nur aus Zahlen bestehen!");
            return false;
        }

        Customer customer = customerHandler.getSavedCustomer().getCustomerList().get(customerId);
        if (!customer.getCompanyName().equals(companyNameField.getText())) {
            customer.setCompanyName(companyNameField.getText());
        }
        if (!customer.getContactPerson().equals(contactPersonField.getText())) {
            customer.setContactPerson(contactPersonField.getText());
        }
        if (!customer.getEmail().equals(emailField.getText())) {
            customer.setEmail(emailField.getText());
        }
        if (!customer.getAddress().getStreet().equals(streetField.getText()) || !String.valueOf(customer.getAddress().getHouseNumber()).equals(houseNumberField.getText()) ||
                !String.valueOf(customer.getAddress().getPostCode()).equals(postCodeField.getText()) || !customer.getAddress().getCity().equals(cityField.getText())) {
            Address newAddress = new Address(streetField.getText(), Integer.parseInt(houseNumberField.getText()), Integer.parseInt(postCodeField.getText()), cityField.getText());
            customer.setAddress(newAddress);
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

    private void startCustomerOverview() {
        uiHandler.registerPanel(new Dashboard(uiHandler, dataHandler, customerHandler, 2));
        uiHandler.startWindow();
    }
}
