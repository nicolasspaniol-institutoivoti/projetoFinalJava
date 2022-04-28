package view;

import javax.swing.*;

public class Menu extends JFrame {
    private JPanel mainPanel;

    public Menu() {
        setContentPane(mainPanel);
        setVisible(true);
        this.pack();
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage());
        }
        Menu menu = new Menu();
    }
}
