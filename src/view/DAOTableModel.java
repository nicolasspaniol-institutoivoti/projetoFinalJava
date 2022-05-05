package view;

import dao.DAO;
import dao.DataSource;
import model.Municipio;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellEditor;
import java.sql.SQLException;

public class DAOTableModel extends AbstractTableModel {
    DAO<?> daoTabela;
    String[] nomeColunas;
    Object[] registros;

    public DAOTableModel(DAO<?> daoTabela) {
        this.daoTabela = daoTabela;
        this.nomeColunas = daoTabela.colunas();

        try {
            registros = daoTabela.lerTudo().toArray();
        }
        catch (SQLException ex) {
            System.err.println("Erro ao criar tabela: " + ex.getMessage());
        }
    }

    public int getRowCount() {
        return registros.length;
    }

    public String getColumnName(int col) {
        return nomeColunas[col];
    }

    public int getColumnCount() {
        return nomeColunas.length;
    }

    public Object getValueAt(int row, int col) {
        try {
            var obj = registros[row];
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

    public void setValueAt(Object aValue, int row, int col) {
        try {
            var obj = registros[row];
            obj.getClass().getDeclaredFields()[col].set(obj, aValue);
            daoTabela.alterar(obj);
            registros[row] = obj;
        } catch (Exception e) {
            System.err.printf("Erro de alteração [linha %d, coluna %d]%n", row, col);
        }
    }
}
