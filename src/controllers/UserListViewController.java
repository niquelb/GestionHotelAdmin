package controllers;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import models.UserModel;
import views.UserListView;
import views.UserCreatorView;
import views.UserEditorView;
import utils.ButtonEditor;
import utils.ButtonRenderer;

public class UserListViewController implements ActionListener {
	private final UserListView view;
	private String email=null,name=null;
	private int page_num=0;
	private int total_pages=(int) Math.ceil(UserModel.getTotalRows()/10)-1;
	
	public UserListViewController(UserListView view) {
		super();
		this.view = view;
		
	}
	
	public void buildTable(String email, String name) {
		JTable table=view.getTable();
		JScrollPane pane=view.getPane();
		
		ArrayList<UserModel> al=UserModel.getUserList(email, name, page_num);
		
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
			Object[] row = new Object[5];
			row[0]=userModel.getEmail();
			row[1]=userModel.getName();
			row[2]=userModel.getLast_names();
			row[3]=userModel.getPhone_num();
			row[4]="Editar";
			model.addRow(row);
		}
		
		table.getColumn("Modificar").setCellRenderer(new ButtonRenderer());
		table.getColumn("Modificar").setCellEditor(new ButtonEditor(new JCheckBox(), table, al, null));
		
		// The width is -18 to compensate for the vertical scrollbar
		table.setPreferredSize(new Dimension(pane.getWidth()-18, pane.getHeight()));
		
		pane.setViewportView(table);
		pane.setSize(pane.getWidth(), table.getRowHeight()*12);
	}
	
	public static void editUser(String u_id) {
		new UserEditorView(u_id);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		switch (e.getActionCommand()) {
		case "filter": 
			name=String.valueOf(view.getTextFieldName().getText());
			email=String.valueOf(view.getTextFieldEmail().getText());
			
			page_num=0;
			updatePageTextField();
			
			buildTable(email, name);
			view.getBtnClearFilters().setEnabled(true);
			
			break;
		case "clear":
			name=null;
			email=null;
			view.getBtnClearFilters().setEnabled(false);
			view.getTextFieldName().setText("");
			view.getTextFieldEmail().setText("");
			
			page_num=0;
			updatePageTextField();
			
			buildTable(null, null);
			
			break;
		case "add_user":
			new UserCreatorView();
			
			break;
			
			/**
			 * Page Navigation
			 */
		case "prev_page":
			page_num=!(page_num==0)?--page_num:0;
			updatePageTextField();
			buildTable(email, name);
			
			break;
		case "next_page":
			page_num=!(page_num==total_pages)?++page_num:total_pages;
			updatePageTextField();
			buildTable(email, name);
			
			break;
		case "first_page":
			page_num=0;
			updatePageTextField();
			buildTable(email, name);
			
			break;
		case "last_page":
			page_num=total_pages;
			updatePageTextField();
			buildTable(email, name);
			
			break;
		
		default:
			throw new IllegalArgumentException("Unexpected value: " + e.getActionCommand());
		}
		
	}
	
	public void updatePageTextField() {
		view.getTextFieldCurrentPage().setText(String.valueOf(page_num+1));
	}
}
