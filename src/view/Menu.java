package view;

import javax.swing.*;

public class Menu extends JFrame {
    private JPanel mainPanel;

    public Menu() {
        setContentPane(mainPanel);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setTitle("Painel do administrador");
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao definir estilo de janela: " + ex.getMessage());
        }
        Menu menu = new Menu();
    }
}
