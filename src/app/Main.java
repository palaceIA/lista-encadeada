package app;

import javax.swing.SwingUtilities;
import app.gui.ListGUI;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ListGUI().setVisible(true);
        });
    }
}