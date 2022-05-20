package util;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class NumberCellEditor extends AbstractCellEditor implements TableCellEditor, ActionListener {
    JTextField component;
    int minimo, maximo;

    public NumberCellEditor(int minino, int maximo) {
        this.minimo = minino;
        this.maximo = maximo;
        component = new JTextField();
        component.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        try {
            int n = Integer.parseInt(e.getActionCommand());
            assert n >= minimo && n <= maximo;
            component.setText(String.valueOf(n));
            fireEditingStopped();
        }
        catch (Exception ignored) {
            fireEditingCanceled();
        }
    }

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int rowIndex, int vColIndex) {
        try {
            int n = Integer.parseInt(value.toString());
            assert n >= minimo && n <= maximo;
            component.setText(String.valueOf(n));
        }
        catch (Exception ignored) {
            fireEditingCanceled();
        }

        return component;
    }

    public abstract Object getCellEditorValue();
}
