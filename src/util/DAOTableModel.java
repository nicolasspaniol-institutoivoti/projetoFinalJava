package util;

import dao.DataAccessObject;

import javax.swing.table.AbstractTableModel;
import java.sql.SQLException;

public class DAOTableModel extends AbstractTableModel {
    DataAccessObject<?> daoTabela;
    String[] nomeColunas;
    Object[] registros;

    public DAOTableModel(DataAccessObject<?> daoTabela) {
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

    public String getColumnName(int column) {
        return nomeColunas[column];
    }

    public int getColumnCount() {
        return nomeColunas.length;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        return daoTabela.valores(registros[rowIndex])[columnIndex];
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }
}
