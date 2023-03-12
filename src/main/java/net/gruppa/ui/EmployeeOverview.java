package net.gruppa.ui;

import net.gruppa.main.CustomerHandler;
import net.gruppa.main.DataHandler;
import net.gruppa.main.UIHandler;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeOverview extends JPanel {

    private final UIHandler uiHandler;
    private final DataHandler dataHandler;
    private final CustomerHandler customerHandler;
    private final Dashboard dashboard;

    public EmployeeOverview(UIHandler uiHandler, DataHandler dataHandler, CustomerHandler customerHandler, Dashboard dashboard) {
        this.uiHandler = uiHandler;
        this.dataHandler = dataHandler;
        this.customerHandler = customerHandler;
        this.dashboard = dashboard;
        setup();
    }

    private static JTable employeeTable;
    private static JScrollPane scrollPane;
    private static JButton createEmployeeButton, employeeInfoButton;

    private void setup() {
        // Employee Table
        String[][] employeeData = new String[100][100];
        for(int i = 0; i < dataHandler.getSavedPerson().getPersonList().size(); i++) {
            // User Id
            employeeData[i][0] = String.valueOf(i);
            //employeeData[i][0] = String.valueOf(dataHandler.getSavedPerson().getPersonList().get(i).getId());
            // User Firstname
            employeeData[i][1] = dataHandler.getSavedPerson().getPersonList().get(i).getName();
            // User Lastname
            employeeData[i][2] = dataHandler.getSavedPerson().getPersonList().get(i).getLastname();
        }
        String[] columns = { "Id", "Vorname", "Nachname" };
        employeeTable = new JTable(employeeData, columns);
        employeeTable.getTableHeader().setReorderingAllowed(false);
        scrollPane = new JScrollPane(employeeTable);

        // Create Employee Button
        createEmployeeButton = new JButton("Neuen Mitarbeiter erstellen");
        createEmployeeButton.setForeground(Color.WHITE);
        createEmployeeButton.setBackground(Color.DARK_GRAY);
        createEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                destruct();
                startEmployeeCreate();
            }
        });

        // Employee Info Button
        employeeInfoButton = new JButton("Mitarbeiter Info");
        employeeInfoButton.setForeground(Color.WHITE);
        employeeInfoButton.setBackground(Color.DARK_GRAY);
        employeeInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (employeeTable.getSelectionModel().isSelectionEmpty()) {
                    JOptionPane.showMessageDialog(null, "Es wurde kein Mitarbeiter ausgew\u00e4hlt!");
                    return;
                } else if (((String) employeeTable.getValueAt(employeeTable.getSelectedRow(), 0)) == null) {
                    return;
                }

                int employeeId = Integer.valueOf((String) employeeTable.getValueAt(employeeTable.getSelectedRow(), 0));
                destruct();
                startEmployeeInfo(employeeId);
            }
        });

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(createEmployeeButton);
        buttonPanel.add(employeeInfoButton);

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

    private void startEmployeeCreate() {
        uiHandler.registerPanel(new EmployeeCreate(uiHandler, dataHandler, customerHandler));
        uiHandler.startWindow();
    }

    private void startEmployeeInfo(int employeeId) {
        uiHandler.registerPanel(new EmployeeInfo(uiHandler, dataHandler, customerHandler, employeeId));
        uiHandler.startWindow();
    }
}
