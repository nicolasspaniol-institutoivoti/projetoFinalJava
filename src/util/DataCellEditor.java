package util;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DataCellEditor extends AbstractCellEditor implements TableCellEditor, ActionListener {
    JTextField component;
    SimpleDateFormat formatoData;

    public DataCellEditor() {
        component = new JTextField();
        component.addActionListener(this);
        formatoData = new SimpleDateFormat("dd/MM/yyyy");
    }

    public void actionPerformed(ActionEvent e) {
        component.setText(e.getActionCommand());
        fireEditingStopped();
    }

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int rowIndex, int vColIndex) {
        component.setText(formatoData.format(value));

        return component;
    }

    public Date getCellEditorValue() {
        try {
            var dataUtil = formatoData.parse(component.getText());
            return new Date(dataUtil.getTime());
        } catch (ParseException e) {
            return new Date(0);
        }
    }
}