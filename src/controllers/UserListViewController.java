package controllers;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.mariadb.jdbc.Connection;

import main.Main;
import models.UserModel;
import views.UserListView;
import views.UserEditorView;
import utils.ButtonEditor;
import utils.ButtonRenderer;

public class UserListViewController implements ActionListener {
	private final UserListView view;
	private static final Connection conn=Main.conn;
	private String params=null;
	
	public UserListViewController(UserListView view) {
		super();
		this.view = view;
		
	}
	
	public void buildTable() {
		JTable table=view.getTable();
		JScrollPane pane=view.getPane();
		
		ArrayList<UserModel> al=UserModel.getUserList(conn, params);
		
		DefaultTableModel model=new DefaultTableModel();
		table.setModel(model);
		
		model.addColumn("Email");
		model.addColumn("Nombre");
		model.addColumn("Apellidos");
		model.addColumn("Telefono");
		model.addColumn("Modificar");
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getTableHeader().setReorderingAllowed(false);
		
		for (UserModel userModel : al) {
			if (!userModel.getEmail().equals("")) {
				Object[] row = new Object[5];
				row[0]=userModel.getEmail();
				row[1]=userModel.getName();
				row[2]=userModel.getLast_names();
				row[3]=userModel.getPhone_num();
				row[4]="Editar";
				model.addRow(row);
			}
		}
		
		table.getColumn("Modificar").setCellRenderer(new ButtonRenderer());
		table.getColumn("Modificar").setCellEditor(new ButtonEditor(new JCheckBox(), view.getTable(), al));
		
		// The width is -18 to compensate for the vertical scrollbar
		table.setPreferredSize(new Dimension(pane.getWidth()-18, table.getPreferredSize().height));
		
		pane.setViewportView(table);
	}
	
	public static void editUser(String u_id) {
		new UserEditorView(u_id);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		switch (e.getActionCommand()) {
		case "filter": 
			String name=String.valueOf(view.getTextFieldFiltersName().getText());
			String email=String.valueOf(view.getTextFieldFiltersEmail().getText());
			
			if (name.equals("") && email.equals("")) {
				buildTable();
				break;
			}
			
			if (name!=null && !name.equals("")) {
				params="nombre LIKE \""+name+"%\"";
				
				if (email!=null && !email.equals("")) {
					System.out.println(email);
//					params.concat("AND email LIKE "+email);
					params=params+" AND email LIKE \""+email+'"';
				}
				
			} else if (email!=null && !email.equals("")) {
				params="email LIKE \""+email+'"';
			}
		
			view.getBtnClearFilters().setVisible(true);
			buildTable();
			
			break;
		case "clear":
			params=null;
			view.getBtnClearFilters().setVisible(false);
			view.getTextFieldFiltersName().setText("");
			view.getTextFieldFiltersEmail().setText("");
			
			buildTable();
			
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + e.getActionCommand());
		}
		
	}
}
