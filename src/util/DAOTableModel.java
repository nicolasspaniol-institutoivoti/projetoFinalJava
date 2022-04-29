package util;

import javax.swing.table.AbstractTableModel;

public class DAOTableModel extends AbstractTableModel {
    public int getRowCount() {
        return 2;
    }

    public int getColumnCount() {
        return 2;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        return 0;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }
}
