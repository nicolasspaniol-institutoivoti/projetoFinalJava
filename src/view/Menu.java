package view;

import dao.*;
import util.DAOTableModel;

import javax.swing.*;
import java.util.Objects;

public class Menu extends JFrame {
    private JPanel mainPanel;
    private JTable tabela;
    private JComboBox<String> tabelaComboBox;

    public Menu() {
        setContentPane(mainPanel);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setTitle("Painel do administrador");

        tabelaComboBox.setModel(new DefaultComboBoxModel<>(DAOFactory.listaTabelas()));
        tabelaComboBox.addActionListener(e -> {
            if (Objects.equals(e.getActionCommand(), "comboBoxChanged")) {
                alterarTabela();
            }
        });
        alterarTabela();
    }

    public void alterarTabela() {
        String selecionado = tabelaComboBox.getSelectedItem().toString();
        //DataAccessObject daoTabela = DAOFactory.CriarDAO(selecionado, new DataSource());
        tabela.setModel(new DAOTableModel());
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        }
        catch (Exception ex) {
            System.err.println("Erro ao definir estilo de janela: " + ex.getMessage());
        }
        new Menu();
    }
}
