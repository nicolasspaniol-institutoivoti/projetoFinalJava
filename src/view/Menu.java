package view;

import dao.DAO;
import dao.DAOCategoriaReporte;
import dao.DAOMunicipio;
import dao.DataSource;
import util.Estado;

import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Objects;

public class Menu extends JFrame {
    private JPanel painel;
    private JTable tabela;
    private JComboBox<String> comboBoxTabelas;
    private JButton botaoAdicionar;
    private JButton botaoRecarregar;

    private final DataSource ds;
    private final HashMap<String, Class<? extends DAO<?>>> mapaDAOs;

    public Menu() {
        // Inicializa a janela
        setContentPane(painel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setTitle("Painel do administrador");
        setVisible(true);

        // Inicia a conexao com o BD
        ds = new DataSource();

        // Encerra a conexao quando a janela for fechada
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                ds.fecharConexao();
                super.windowClosing(e);
            }
        });

        // Cria os DAOs e os armazena em um mapa
        mapaDAOs = new HashMap<>();
        mapaDAOs.put("Municípios", DAOMunicipio.class);
        mapaDAOs.put("Categorias de reporte", DAOCategoriaReporte.class);

        // Define as opções da ComboBox de seleção da tabela
        comboBoxTabelas.setModel(new DefaultComboBoxModel<>());
        for (String key : mapaDAOs.keySet()) {
            comboBoxTabelas.addItem(key);
        }

        // Recarrega a tabela quando a selecao for alterada
        comboBoxTabelas.addActionListener(e -> carregarTabela());
        // Recarrega a tabela quando o botão de recarregar for clicado
        botaoRecarregar.addActionListener(e -> carregarTabela());

        // Cria uma nova linha na tabela quando o botão de adicionar for clicado
        botaoAdicionar.addActionListener(e -> adicionarLinha());

        // Inicializa a tabela com o DAO selecionado na tabelaComboBox
        prepararTabela();
        carregarTabela();
    }

    private void removerLinhasSelecionadas() {
        // Armazena as linhas selecionadas
        var selection = tabela.getSelectionModel();

        // Pede ao usuário que confirme a ação, caso contrário a cancela
        int confirmation = JOptionPane.showConfirmDialog(JOptionPane.getRootFrame(), String.format("Apagar %d registro(s)?", selection.getSelectedItemsCount()), "Confirmar remoção", JOptionPane.YES_NO_OPTION);
        if (confirmation != 0) return;

        // Deleta as linhas armazenadas
        ((DAOTableModel) tabela.getModel()).removerRegistros(selection.getMinSelectionIndex(), selection.getMaxSelectionIndex());
    }

    void adicionarLinha() {
        // Adiciona o registro
        ((DAOTableModel) tabela.getModel()).adicionarRegistro();

        // Recarrega a tabela para atualizar os IDs
        carregarTabela();
    }

    void prepararDeletarLinhas() {
        // Cria um popup na tabela com a opção de apagar registros
        final JPopupMenu popupDeletar = new JPopupMenu();
        tabela.setComponentPopupMenu(popupDeletar);
        JMenuItem itemMenuDeletar = new JMenuItem("Apagar registro(s)");
        popupDeletar.add(itemMenuDeletar);

        // Quando o botão direito for clicado, garante que a linha em que o cursor está vai estar selecionada
        popupDeletar.addPopupMenuListener(new PopupMenuListener() {
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                SwingUtilities.invokeLater(() -> {
                    // Descobre a linha em que o cursor está
                    int rowAtPoint = tabela.rowAtPoint(SwingUtilities.convertPoint(popupDeletar, new Point(0, 0), tabela));

                    // Se a linha não fizer parte da seleção atual, seleciona apenas ela
                    if (rowAtPoint > -1 && !tabela.getSelectionModel().isSelectedIndex(rowAtPoint)) {
                        tabela.setRowSelectionInterval(rowAtPoint, rowAtPoint);
                    }
                });
            }
            // Ignorados
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {}
            public void popupMenuCanceled(PopupMenuEvent e) {}
        });

        // Remove as linhas selecionadas quando a opção de apagar registros for clicada
        itemMenuDeletar.addActionListener(e -> removerLinhasSelecionadas());

        // Faz o mesmo quando a tecla "delete" for pressionada
        tabela.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_DELETE) {
                    removerLinhasSelecionadas();
                }
            }
        });
    }

    void prepararTabela() {
        // Adiciona a funcionalidade de deletar linhas da tabela
        prepararDeletarLinhas();

        // Define os editores das células para algumas classes especiais
        tabela.setDefaultEditor(Estado.class, new DefaultCellEditor(new JComboBox<>(Estado.values())));

        // Impede que as colunas sejam reordenadas
        tabela.getTableHeader().setReorderingAllowed(false);
    }

    void carregarTabela() {
        try {
            // Cria um DAO para a tabela selecionada na tabelaComboBox
            String selecionado = Objects.requireNonNull(comboBoxTabelas.getSelectedItem()).toString();
            DAO<?> daoSelecionado = mapaDAOs.get(selecionado).getConstructor(DataSource.class).newInstance(ds);

            // Define um novo TabelModel a partir desse DAO
            TableModel model = new DAOTableModel(daoSelecionado);
            tabela.setModel(model);

            // Identifica o tamanho das colunas dessa tabela
            Integer[] larguraColunas = daoSelecionado.largurasColunas();
            for (int i = 0; i < larguraColunas.length; i++) {
                int largura = larguraColunas[i];

                // Se o tamanho for válido, o aplica na coluna
                if (largura >= 0) {
                    tabela.getColumnModel().getColumn(i).setMinWidth(largura);
                    tabela.getColumnModel().getColumn(i).setMaxWidth(largura);
                }
            }
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Erro ao carregar tabela");
        }
    }

    public static void main(String[] args) {
        // Deixa a janela com o visual padrão do Windows
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        }
        catch (Exception ex) {
            System.err.println("Erro ao definir estilo de janela: " + ex.getMessage());
        }

        // Cria uma nova instância da janela
        // Usa essa instância como base para os menus do JOptionPane
        JOptionPane.setRootFrame(new Menu());
    }
}
