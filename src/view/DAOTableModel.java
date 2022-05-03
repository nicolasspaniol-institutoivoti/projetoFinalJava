package view;

import dao.DAO;
import dao.DataSource;
import model.Municipio;

import javax.swing.table.AbstractTableModel;
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

    public String getColumnName(int column) {
        return nomeColunas[column];
    }

    public int getColumnCount() {
        return nomeColunas.length;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            return daoTabela.lerValor(registros[rowIndex], columnIndex);
        }
        catch (IllegalAccessException ex) {
            System.err.println("Erro de acesso [linha %d, coluna %d]".formatted(rowIndex, columnIndex));
            return null;
        }
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }
}
