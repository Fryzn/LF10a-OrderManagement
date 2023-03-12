package net.gruppa.ui;

import net.gruppa.main.CustomerHandler;
import net.gruppa.main.DataHandler;
import net.gruppa.main.UIHandler;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerOverview extends JPanel {

    private final UIHandler uiHandler;
    private final DataHandler dataHandler;
    private final CustomerHandler customerHandler;
    private final Dashboard dashboard;

    public CustomerOverview(UIHandler uiHandler, DataHandler dataHandler, CustomerHandler customerHandler, Dashboard dashboard) {
        this.uiHandler = uiHandler;
        this.dataHandler = dataHandler;
        this.customerHandler = customerHandler;
        this.dashboard = dashboard;
        setup();
    }

    private static JTable customerTable;
    private static JScrollPane scrollPane;
    private static JPanel buttonPanel;
    private static JButton createCustomerButton, customerInfoButton;

    private void setup() {
        // Customer Table
        String[][] customerData = new String[100][100];
        for(int i = 0; i < customerHandler.getSavedCustomer().getCustomerList().size(); i++) {
            // Company id
            customerData[i][0] = String.valueOf(i);
            //customerData[i][0] = String.valueOf(customerHandler.getSavedCustomer().getCustomerList().get(i).getId());
            // Company name
            customerData[i][1] = customerHandler.getSavedCustomer().getCustomerList().get(i).getCompanyName();
            // Contact person
            customerData[i][2] = customerHandler.getSavedCustomer().getCustomerList().get(i).getContactPerson();
        }
        String[] columns = { "Id", "Firma", "Ansprechpartner" };
        customerTable = new JTable(customerData, columns);
        customerTable.getTableHeader().setReorderingAllowed(false);
        scrollPane = new JScrollPane(customerTable);

        // Create Customer Button
        createCustomerButton = new JButton("Neuen Kunden erstellen");
        createCustomerButton.setForeground(Color.WHITE);
        createCustomerButton.setBackground(Color.DARK_GRAY);
        createCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                destruct();
                startCustomerCreate();
            }
        });

        // Customer Info Button
        customerInfoButton = new JButton("Kunden Info");
        customerInfoButton.setForeground(Color.WHITE);
        customerInfoButton.setBackground(Color.DARK_GRAY);
        customerInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (customerTable.getSelectionModel().isSelectionEmpty()) {
                    JOptionPane.showMessageDialog(null, "Es wurde kein Kunde ausgew\u00e4hlt!");
                    return;
                } else if (((String) customerTable.getValueAt(customerTable.getSelectedRow(), 0)) == null) {
                    return;
                }

                int customerId = Integer.valueOf((String) customerTable.getValueAt(customerTable.getSelectedRow(), 0));
                destruct();
                startCustomerInfo(customerId);
            }
        });

        // Button Panel
        buttonPanel = new JPanel();
        buttonPanel.add(createCustomerButton);
        buttonPanel.add(customerInfoButton);

        // Frame Settings
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        setVisible(true);
    }

    private void destruct()  {
        uiHandler.removeSelf(this);
        dashboard.removeDashboard();
    }

    private void startCustomerCreate() {
        uiHandler.registerPanel(new CustomerCreate(uiHandler, dataHandler, customerHandler));
        uiHandler.startWindow();
    }

    private void startCustomerInfo(int customerId) {
        uiHandler.registerPanel(new CustomerInfo(uiHandler, dataHandler, customerHandler, customerId));
        uiHandler.startWindow();
    }
}
