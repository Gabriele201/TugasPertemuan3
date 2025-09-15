package untar.java.tugas1;

import javax.swing.SwingUtilities;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            InventoryApp app = new InventoryApp();
            app.setVisible(true);
        });
    }
}