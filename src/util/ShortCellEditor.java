package util;

public class ShortCellEditor extends NumberCellEditor {
    public ShortCellEditor(int minino, int maximo) {
        super(minino, maximo);
    }

    @Override
    public Object getCellEditorValue() {
        return Short.parseShort(component.getText());
    }
}
