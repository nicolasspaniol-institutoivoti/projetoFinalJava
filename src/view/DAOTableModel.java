package view;

import dao.DAO;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOTableModel extends AbstractTableModel {
    DAO<?> daoTabela;
    String[] nomeColunas;
    ArrayList<Object> registros;

    public DAOTableModel(DAO<?> daoTabela) {
        this.daoTabela = daoTabela;
        this.nomeColunas = daoTabela.colunas();

        try {
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

    public Object getValueAt(int row, int col) {
        try {
            var obj = registros.get(row);
            return obj.getClass().getDeclaredFields()[col].get(obj);
        }
        catch (IllegalAccessException ex) {
            System.err.printf("Erro de acesso [linha %d, coluna %d]%n", row, col);
            return null;
        }
    }

    public void deleteRows(int startIndex, int endIndex) {
        for (int i = startIndex; i <= endIndex; i++) {
            try {
                Object obj = registros.get(startIndex);
                registros.remove(startIndex);
                daoTabela.deletar(obj);
            }
            catch (Exception ex) {
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), String.format("Erro ao remover registro (linha %d): " + ex.getMessage(), i));
            }
        }
        fireTableRowsDeleted(startIndex, endIndex);
        fireTableDataChanged();
    }

    public Class<?> getColumnClass(int col) {
        return daoTabela.tipoRegistro().getDeclaredFields()[col].getType();
    }

    public boolean isCellEditable(int row, int col) {
        return col > 0;
    }

    public void setValueAt(Object aValue, int row, int col) {
        if (aValue.equals(getValueAt(row, col))) return;

        int confirmation = JOptionPane.showConfirmDialog(JOptionPane.getRootFrame(), "Atualizar registro da tabela?", "Confirmar alteração", JOptionPane.YES_NO_OPTION);
        if (confirmation != 0) return;

        try {
            var obj = registros.get(row);
            obj.getClass().getDeclaredFields()[col].set(obj, aValue);
            daoTabela.alterar(obj);
            registros.set(row, obj);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), String.format("Erro ao alterar registro (linha %d, coluna \"%s\"): " + ex.getMessage(), row, this.nomeColunas[col]));
        }
    }
}
