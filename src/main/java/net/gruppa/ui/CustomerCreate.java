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

public class CustomerCreate extends JPanel {

    private final UIHandler uiHandler;
    private final DataHandler dataHandler;
    private final CustomerHandler customerHandler;
    private Customer customer;
    private Address address;

    public CustomerCreate(UIHandler uiHandler, DataHandler dataHandler, CustomerHandler customerHandler) {
        this.uiHandler = uiHandler;
        this.dataHandler = dataHandler;
        this.customerHandler = customerHandler;
        setup();
    }

    private static JPanel customerInfoPanel, customerAddressPanel;
    private static JLabel companyNameLabel, contactPersonLabel, emailLabel, streetLabel, houseNumberLabel, postCodeLabel, cityLabel;
    private static JTextField companyNameField, contactPersonField, emailField, streetField, houseNumberField, postCodeField, cityField;
    private static JButton createCustomerButton, goBackButton;

    private void setup() {
        setLayout(null);
        // General Customer Info Panel
        customerInfoPanel = new JPanel();
        customerInfoPanel.setLayout(null);
        customerInfoPanel.setBounds(20, 20, 260, 250);
        customerInfoPanel.setBorder(BorderFactory.createTitledBorder("Allgemeine Daten"));
        ((javax.swing.border.TitledBorder) customerInfoPanel.getBorder()).
                setTitleFont(new Font("Arial", Font.BOLD, 14));

        companyNameLabel = new JLabel("Firma");
        companyNameLabel.setBounds(25, 30, 100, 20);
        customerInfoPanel.add(companyNameLabel);
        companyNameField = new JTextField();
        companyNameField.setBounds(25, 50, 200, 25);
        customerInfoPanel.add(companyNameField);
        contactPersonLabel = new JLabel("Kontaktperson");
        contactPersonLabel.setBounds(25, 80, 100, 20);
        customerInfoPanel.add(contactPersonLabel);
        contactPersonField = new JTextField();
        contactPersonField.setBounds(25, 100, 200, 25);
        customerInfoPanel.add(contactPersonField);
        emailLabel = new JLabel("E-Mail");
        emailLabel.setBounds(25, 130, 100, 20);
        customerInfoPanel.add(emailLabel);
        emailField = new JTextField();
        emailField.setBounds(25, 150, 200, 25);
        customerInfoPanel.add(emailField);

        // Customer Address Panel
        customerAddressPanel = new JPanel();
        customerAddressPanel.setLayout(null);
        customerAddressPanel.setBounds(290, 20, 260, 250);
        customerAddressPanel.setBorder(BorderFactory.createTitledBorder("Adresse"));
        ((javax.swing.border.TitledBorder) customerAddressPanel.getBorder()).
                setTitleFont(new Font("Arial", Font.BOLD, 14));

        streetLabel = new JLabel("Stra\u00dfe");
        streetLabel.setBounds(25, 30, 100, 20);
        customerAddressPanel.add(streetLabel);
        streetField = new JTextField();
        streetField.setBounds(25, 50, 200, 25);
        customerAddressPanel.add(streetField);
        houseNumberLabel = new JLabel("Hausnummer");
        houseNumberLabel.setBounds(25, 80, 100, 20);
        customerAddressPanel.add(houseNumberLabel);
        houseNumberField = new JTextField();
        houseNumberField.setBounds(25, 100, 75, 25);
        customerAddressPanel.add(houseNumberField);
        postCodeLabel = new JLabel("Postleitzahl");
        postCodeLabel.setBounds(25, 130, 100, 20);
        customerAddressPanel.add(postCodeLabel);
        postCodeField = new JTextField();
        postCodeField.setBounds(25, 150, 75, 25);
        customerAddressPanel.add(postCodeField);
        cityLabel = new JLabel("Stadt");
        cityLabel.setBounds(25, 180, 100, 20);
        customerAddressPanel.add(cityLabel);
        cityField = new JTextField();
        cityField.setBounds(25, 200, 200, 25);
        customerAddressPanel.add(cityField);

        // Create Employee Button
        createCustomerButton = new JButton("Neuen Kunden erstellen");
        createCustomerButton.setBounds(200, 280, 180, 25);
        createCustomerButton.setForeground(Color.WHITE);
        createCustomerButton.setBackground(Color.DARK_GRAY);
        createCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (companyNameField.getText().equals("") || contactPersonField.getText().equals("") || emailField.getText().equals("") || streetField.getText().equals("")
                        || houseNumberField.getText().equals("") || postCodeField.getText().equals("") || cityField.getText().equals("")) {
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
                customer = new Customer(customerHandler.getSavedCustomer().getCustomerList().size(), companyNameField.getText(), contactPersonField.getText(),
                        emailField.getText(), address);
                customerHandler.createCustomer(customer);
                destruct();
                startCustomerOverview();
            }
        });

        // Go Back Button
        goBackButton = new JButton("Abbrechen");
        goBackButton.setBounds(240, 310, 100, 25);
        goBackButton.setForeground(Color.WHITE);
        goBackButton.setBackground(Color.DARK_GRAY);
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                destruct();
                startCustomerOverview();
            }
        });

        // Frame Settings
        add(customerInfoPanel);
        add(customerAddressPanel);
        add(createCustomerButton);
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

    private void startCustomerOverview() {
        uiHandler.registerPanel(new Dashboard(uiHandler, dataHandler, customerHandler, 2));
        uiHandler.startWindow();
    }
}
