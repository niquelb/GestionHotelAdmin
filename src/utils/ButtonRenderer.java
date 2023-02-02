package utils;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class ButtonRenderer extends JButton implements TableCellRenderer {

    public ButtonRenderer() {
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
    	setForeground(new Color(0, 0, 0));
		setBackground(new Color(255, 255, 255));
		setFocusable(false);
		setFont(new Font("Roboto Light", Font.PLAIN, 14));
        setText((value == null) ? "" : value.toString());
        return this;
    }
}

