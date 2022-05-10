package view;

import dao.DAO;
import dao.DataSource;
import model.Municipio;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellEditor;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;

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

    public Class<?> getColumnClass(int col) {
        return daoTabela.tipoRegistro().getDeclaredFields()[col].getType();
    }

    public boolean isCellEditable(int row, int col) {
        return col > 0;
    }

    public void fireTableRowsDeleted(int firstRow, int lastRow) {
        for (int i = firstRow; i < lastRow; i++) {
            registros.remove(firstRow);
        }
        JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "delete");
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
