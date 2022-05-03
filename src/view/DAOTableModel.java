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
        catch (Exception ex) {
            System.err.printf("Erro de acesso [linha %d, coluna %d]%n", rowIndex, columnIndex);
            return null;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return daoTabela.lerClasse(columnIndex);
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex > 0;
    }
}
