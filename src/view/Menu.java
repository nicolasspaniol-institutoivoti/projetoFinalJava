package view;

import dao.DAO;
import dao.DataSource;
import util.Estado;
import util.TipoFornecedor;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Menu extends JFrame {
    private JPanel mainPanel;
    private JTable tabela;
    private JComboBox<String> tabelaCB;

    private DataSource ds;

    public Menu() {
        // Inicializa a janela
        setContentPane(mainPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setTitle("Painel do administrador");
        setVisible(true);

        // Define as opcoes da combobox de selecao de tabela
        tabelaCB.setModel(new DefaultComboBoxModel<>(DAO.tabelas));
        // Quando a selecao for alterada, carrega a tabela novamente
        tabelaCB.addActionListener(e -> carregarTabela());

        // Inicia a conexao com o BD
        ds = new DataSource();

        // Encerra a conexao quando a janela for fechada
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                ds.fecharConexao();
                super.windowClosing(e);
            }
        });

        // Inicializa a JTable com os valores da tabela selecionada
        prepararTabela();
        carregarTabela();
    }

    void prepararTabela() {
        tabela.setDefaultEditor(Estado.class, new DefaultCellEditor(new JComboBox(Estado.values())));

        var tipofornCB = new JComboBox(new Boolean[] {true, false});
        tabela.setDefaultEditor(TipoFornecedor.class, new DefaultCellEditor(tipofornCB));
    }

    void carregarTabela() {
        // Cria um DAO da tabela selecionada
        String selecionado = tabelaCB.getSelectedItem().toString();
        DAO<?> daoSelecionado = DAO.criar(selecionado, ds);

        // Define um novo modelo de tabela a partir do DAO
        TableModel model = new DAOTableModel(daoSelecionado);
        tabela.setModel(model);
    }

    public static void main(String[] args) {
        // Deixa a janela com o visual padrao do Windows
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        }
        catch (Exception ex) {
            System.err.println("Erro ao definir estilo de janela: " + ex.getMessage());
        }

        // Cria uma nova instancia da janela
        new Menu();
    }
}
