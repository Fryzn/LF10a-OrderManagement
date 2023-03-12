package net.gruppa.ui;

import net.gruppa.main.CustomerHandler;
import net.gruppa.main.DataHandler;
import net.gruppa.main.UIHandler;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dashboard extends JPanel {

    private final UIHandler uiHandler;
    private final DataHandler dataHandler;
    private final CustomerHandler customerHandler;
    public Dashboard(UIHandler uiHandler, DataHandler dataHandler, CustomerHandler customerHandler) {
        this.uiHandler = uiHandler;
        this.dataHandler = dataHandler;
        this.customerHandler = customerHandler;
        setup();
    }

    public Dashboard(UIHandler uiHandler, DataHandler dataHandler, CustomerHandler customerHandler, int selectedIndex) {
        this.uiHandler = uiHandler;
        this.dataHandler = dataHandler;
        this.customerHandler = customerHandler;
        this.selectedIndex = selectedIndex;
        setup();
    }

    private JPanel dispatcherPanel, employeePanel, customerPanel, dischargePanel;
    public JTabbedPane tabPane;
    private int selectedIndex;

    public void setup() {
        // Dispatcher Start Panel
        dispatcherPanel = new JPanel();
        JButton logoutButton = new JButton("Logout");
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setBackground(Color.DARK_GRAY);
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeDashboard();
                uiHandler.registerPanel(new Login(uiHandler, dataHandler, customerHandler));
                uiHandler.startWindow();
            }
        });
        dispatcherPanel.add(logoutButton);
        String fullEmployeeName = dataHandler.getLoginedPerson().getName() + " " + dataHandler.getLoginedPerson().getLastname();
        // Employee Panel
        employeePanel = new JPanel();
        employeePanel.add(new EmployeeOverview(uiHandler, dataHandler, customerHandler, this));
        // Customer Panel
        customerPanel = new JPanel();
        customerPanel.add(new CustomerOverview(uiHandler, dataHandler, customerHandler, this));
        // Discharge Overview
        //dischargePanel = new JPanel();
        //dischargePanel.add(new JPanel());

        // Tab Panel
        tabPane = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
        tabPane.addTab(fullEmployeeName, dispatcherPanel);
        tabPane.addTab("Mitarbeiter\u00fcbersicht", employeePanel);
        tabPane.addTab("Kunden\u00fcbersicht", customerPanel);
        //tabPane.addTab("Auftrags\u00fcbersicht", dischargePanel);
        if (selectedIndex != 0) {
            tabPane.setSelectedIndex(selectedIndex);
        }

        setLayout(new BorderLayout());
        add(tabPane);
    }

    public void removeDashboard() {
        remove(tabPane);
    }
}
