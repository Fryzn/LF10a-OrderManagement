package net.gruppa.main;

import javax.swing.*;
import java.awt.*;

public class UIHandler extends JFrame {

    private final JFrame mainFrame;
    public UIHandler() {
        this.mainFrame = new JFrame("Auftragsverwaltung");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(600, 600);
        mainFrame.setResizable(false);
        mainFrame.setLocationRelativeTo(null);
    }
    
    public void registerPanel(Component comp) { mainFrame.add(comp); }

    public void removeSelf(Component comp) {
        mainFrame.remove(comp);
    }

    public void startWindow() {
        mainFrame.setVisible(true);
    }
}
