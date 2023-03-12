package net.gruppa.main;

import net.gruppa.ui.Login;

public class Main {

    public static void main(String[] args) {
        UIHandler uiHandler = new UIHandler();
        DataHandler dataHandler = new DataHandler();
        CustomerHandler customerHandler = new CustomerHandler();
        uiHandler.registerPanel(new Login(uiHandler, dataHandler, customerHandler));
        uiHandler.startWindow();
    }
}
