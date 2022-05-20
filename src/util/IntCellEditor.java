package util;

public class IntCellEditor extends NumberCellEditor {
    public IntCellEditor(int minino, int maximo) {
        super(minino, maximo);
    }

    @Override
    public Object getCellEditorValue() {
        return Integer.parseInt(component.getText());
    }
}
