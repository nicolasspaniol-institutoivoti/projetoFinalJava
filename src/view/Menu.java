package view;

import dao.DAO;
import dao.DataSource;
import util.Estado;
import util.TipoFornecedor;

import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;

public class Menu extends JFrame {
    private JPanel mainPanel;
    private JTable mainTable;
    private JComboBox<String> tableComboBox;
    private JButton addButton;
    private JButton refreshButton;

    private final DataSource ds;

    public Menu() {
        // Inicializa a janela
        setContentPane(mainPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setTitle("Painel do administrador");
        setVisible(true);

        // Define as opcoes da combobox de selecao de mainTable
        tableComboBox.setModel(new DefaultComboBoxModel<>(DAO.tabelas));
        // Quando a selecao for alterada, carrega a mainTable novamente
        tableComboBox.addActionListener(e -> carregarTabela());

        // Quando o botão de adicionar for clicado, cria uma nova linha na tabela
        addButton.addActionListener(e -> addRow());

        // Recarrega a tabela quando o botão de recarregar for clicado
        refreshButton.addActionListener(e -> carregarTabela());

        // Adiciona opção de deletar registro;
        final JPopupMenu deletePopup = new JPopupMenu();
        JMenuItem deleteMenuItem = new JMenuItem("Apagar registro(s)");
        deletePopup.addPopupMenuListener(new PopupMenuListener() {
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                SwingUtilities.invokeLater(() -> {
                    int rowAtPoint = mainTable.rowAtPoint(SwingUtilities.convertPoint(deletePopup, new Point(0, 0), mainTable));
                    if (rowAtPoint > -1 && !mainTable.getSelectionModel().isSelectedIndex(rowAtPoint)) {
                        mainTable.setRowSelectionInterval(rowAtPoint, rowAtPoint);
                    }
                });
            }
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {}
            public void popupMenuCanceled(PopupMenuEvent e) {}
        });
        deletePopup.add(deleteMenuItem);
        mainTable.setComponentPopupMenu(deletePopup);
        deleteMenuItem.addActionListener(e -> deleteSelectedRows());

        // Inicia a conexao com o BD
        ds = new DataSource();

        // Encerra a conexao quando a janela for fechada
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                ds.fecharConexao();
                super.windowClosing(e);
            }
        });

        // Inicializa a JTable com os valores da mainTable selecionada
        prepararTabela();
        carregarTabela();
    }

    private void deleteSelectedRows() {
        var selection = mainTable.getSelectionModel(); 
        int confirmation = JOptionPane.showConfirmDialog(JOptionPane.getRootFrame(), String.format("Apagar %d registro(s)?", selection.getSelectedItemsCount()), "Confirmar alteração", JOptionPane.YES_NO_OPTION);
        if (confirmation != 0) return;

        ((DAOTableModel) mainTable.getModel()).deleteRows(selection.getMinSelectionIndex(), selection.getMaxSelectionIndex());
    }

    void addRow() {
        ((DAOTableModel) mainTable.getModel()).addRow();
    }

    void prepararTabela() {
        mainTable.setDefaultEditor(Estado.class, new DefaultCellEditor(new JComboBox<>(Estado.values())));

        var tipofornCB = new JComboBox<>(new Boolean[] {true, false});
        mainTable.setDefaultEditor(TipoFornecedor.class, new DefaultCellEditor(tipofornCB));

        mainTable.getTableHeader().setReorderingAllowed(false);

        mainTable.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_DELETE) {
                    deleteSelectedRows();
                }
            }
        });
    }

    void carregarTabela() {
        // Cria um DAO da mainTable selecionada
        String selecionado = tableComboBox.getSelectedItem().toString();
        DAO<?> daoSelecionado = DAO.criar(selecionado, ds);

        // Define um novo modelo de mainTable a partir do DAO
        TableModel model = new DAOTableModel(daoSelecionado);
        mainTable.setModel(model);
    }

    public static void main(String[] args) {
        // Deixa a janela com o visual padrao do Windows
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        }
        catch (Exception ex) {
            System.err.println("Erro ao definir estilo de janela: " + ex.getMessage());
        }

        // Cria uma nova instância da janela
        // Usa essa instância como base para os menus de confirmação
        JOptionPane.setRootFrame(new Menu());
    }
}
