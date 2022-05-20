package view;

import dao.DAO;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.sql.SQLException;
import java.util.ArrayList;

// Classe responsável por refletir as alterações feitas na JTable no DAO
public class DAOTableModel extends AbstractTableModel {
    DAO<?> daoTabela;
    String[] nomeColunas;
    ArrayList<Object> registros;

    public DAOTableModel(DAO<?> daoTabela) {
        this.daoTabela = daoTabela;
        this.nomeColunas = daoTabela.nomesColunas();

        try {
            // Lê os registros do banco de dados para uma lista local
            registros = (ArrayList<Object>) daoTabela.lerTudo();
        }
        catch (SQLException ex) {
            System.err.println("Erro ao criar tabela: " + ex.getMessage());
        }
    }

    public int getRowCount() {
        return registros.size();
    }

    public String getColumnName(int col) {
        return nomeColunas[col];
    }

    public int getColumnCount() {
        return nomeColunas.length;
    }

    public Object getValueAt(int linha, int col) {
        try {
            // Retorna, para o objeto na linha passada, o valor do campo de índice 'col' deste objeto
            var obj = registros.get(linha);
            return obj.getClass().getDeclaredFields()[col].get(obj);
        }
        catch (IllegalAccessException ex) {
            System.err.printf("Erro de acesso [linha %d, coluna %d]%n", linha, col);
            // Se a leitura do campo falhar, retorna null
            return null;
        }
    }

    public void removerRegistros(int indiceInicial, int indiceFinal) {
        // Repete a ação pela quantidade de registros entre os índices passados
        for (int i = indiceInicial; i <= indiceFinal; i++) {
            try {
                // Armazena o registro no primeiro índice passado
                // Conforme cada registro é removido, o próximo toma seu lugar
                // Dessa forma, apenas o índice do primeiro é necessário
                Object obj = registros.get(indiceInicial);
                // Faz a remoção desse registro do banco de dados
                registros.remove(indiceInicial);
                daoTabela.deletar(obj);
            }
            catch (Exception ex) {
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), String.format("Erro ao remover registro (linha %d): " + ex.getMessage(), i));
            }
        }
        // Reporta à interface as linhas que foram removidas
        fireTableRowsDeleted(indiceInicial, indiceFinal);
        fireTableDataChanged();
    }

    public Class<?> getColumnClass(int col) {
        return daoTabela.tipoRegistro().getDeclaredFields()[col].getType();
    }

    public boolean isCellEditable(int row, int col) {
        // Impede que a primeira coluna (ID) seja editada
        return col > 0;
    }

    public void setValueAt(Object novoValor, int linha, int col) {
        // Retorna caso o valor não tenha sido alterado
        Object valorAnterior = getValueAt(linha, col);
        if (novoValor.equals(valorAnterior)) return;

        // Pede a confirmação do usuário, caso contrário cancela a alteração
        int confirmation = JOptionPane.showConfirmDialog(JOptionPane.getRootFrame(), "Atualizar registro da tabela?", "Confirmar alteração", JOptionPane.YES_NO_OPTION);
        if (confirmation != 0) return;

        if (alterarCelula(linha, col, novoValor)) {
            fireTableCellUpdated(linha, col);
            fireTableDataChanged();
        }
        else {
            alterarCelula(linha, col, valorAnterior);
        }
    }

    public boolean alterarCelula(int linha, int col, Object valor) {
        try {
            // Armazena o registro a ser alterado
            var obj = registros.get(linha);
            // Altera o valor da coluna nesse registro armazenado
            var coluna = obj.getClass().getDeclaredFields()[col];
            coluna.set(obj, valor);
            // Envia o objeto alterado ao banco de dados
            daoTabela.alterar(obj);
            registros.set(linha, obj);

            return true;
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), String.format("Erro ao alterar registro (linha %d, coluna \"%s\"): " + ex.getMessage(), linha, this.nomeColunas[col]));
            return false;
        }
    }

    public void adicionarRegistro() {
        // Pede a confirmação do usuário, caso contrário cancela a inserção
        int confirmacao = JOptionPane.showConfirmDialog(JOptionPane.getRootFrame(), "Adicionar novo registro na tabela?", "Confirmar inserção", JOptionPane.YES_NO_OPTION);
        if (confirmacao != 0) return;

        try {
            // Cria um novo objeto da classe envolta pelo DAO
            Object registro = daoTabela.tipoRegistro().getConstructor(null).newInstance();
            // Insere esse objeto no banco de dados
            daoTabela.inserir(registro);
            registros.add(registro);

            // Reporta à interface que um item foi adicionado
            int indice = registros.size() - 1;
            fireTableRowsInserted(indice, indice);
            fireTableDataChanged();
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Erro ao adicionar registro: " + ex.getMessage());
        }
    }
}
