package utils;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;

import controllers.UserListViewController;
import models.UserModel;

public class ButtonEditor extends DefaultCellEditor {

    protected JButton button;
    private String label;
    private boolean isPushed;
    private JTable table;
    private ArrayList<UserModel> al=new ArrayList<>();

    public ButtonEditor(JCheckBox checkBox, JTable table, ArrayList<UserModel> al) {
        super(checkBox);
        this.table=table;
        this.al=al;
        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
    	
        label = (value == null) ? "" : value.toString();
        button.setText(label);
        isPushed = true;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        if (isPushed) {
        	// Passes the email that correlates the table row with the user in the ArrayList, which is the user of that row in the table
        	// The AL index is +1 because the first row in the DB is null
            UserListViewController.editUser(al.get(table.getSelectedRow()).getEmail());
            }
        isPushed = false;
        return label;
    }

    @Override
    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }
}
