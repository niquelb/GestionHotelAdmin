package utils;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;

import controllers.RoomListViewController;
import controllers.UserListViewController;
import models.RoomModel;
import models.UserModel;

public class ButtonEditor extends DefaultCellEditor {

    protected JButton button;
    private String label;
    private boolean isPushed;
    private JTable table;
    private ArrayList<UserModel> al_user=new ArrayList<>();
    private ArrayList<RoomModel> al_room=new ArrayList<>();

    /**
     * Constructor
     * @param checkBox
     * @param table
     * @param al_user !! USER !!
     * @param al_room !! ROOM !!
     */
    public ButtonEditor(JCheckBox checkBox, JTable table, ArrayList<UserModel> al_user, ArrayList<RoomModel> al_room) {
        super(checkBox);
        this.table=table;
        this.al_user=al_user;
        this.al_room=al_room;
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
            if (al_user!=null) {
            	UserListViewController.editUser(al_user.get(table.getSelectedRow()).getEmail());
			} else if (al_room!=null) {
				//TODO this
			}
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
