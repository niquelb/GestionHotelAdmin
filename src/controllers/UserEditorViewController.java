package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import main.Main;
import models.UserModel;
import views.UserEditorView;

public class UserEditorViewController implements ActionListener {
	private final UserEditorView view;
	private final UserModel user;

	public UserEditorViewController(UserEditorView view, String u_id) {
		super();
		this.view = view;
		String stmt="email LIKE \""+u_id+'"';
		user=UserModel.getUser(Main.conn, stmt);
		if (user==null) {
			System.err.println("User is null.");
		}
		
		updateFields();
	}
	
	public void updateFields() {
		// variable = (condition) ? expressionTrue :  expressionFalse;
		view.getTextFieldEmail().setText(user.getEmail()!=null?user.getEmail():"");
		view.getTextFieldName().setText(user.getName()!=null?user.getName():"");
		view.getTextFieldLastNames().setText(user.getLast_names()!=null?user.getLast_names():"");
		view.getTextFieldPhone().setText(user.getPhone_num()!=null?user.getPhone_num():"");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "submit":
			String new_pass=(String.valueOf(view.getPasswordField().getPassword())!=null&&!String.valueOf(view.getPasswordField().getPassword()).equals(""))?String.valueOf(view.getPasswordField().getPassword()):null;
			System.out.println(new_pass);
			if (new_pass.length()<8) {
				JOptionPane.showMessageDialog(view,
					    "La contraseña debe tener al menos 8 caracteres.",
					    "Error",
					    JOptionPane.PLAIN_MESSAGE);
				break;
			}
			
			String[] arr= {"Confirmar","Cancelar"};
			int n = JOptionPane.showOptionDialog(view,
				    "Desea confirmar la edicion del usuario "+user.getName(),
				    "Confirmar Edicion?",
				    JOptionPane.YES_NO_OPTION,
				    JOptionPane.PLAIN_MESSAGE,
				    null,
				    arr,
				    arr[0]
				    );
			
			if (n==0) {
				if (new_pass!=null) {
					user.setPassword(new_pass);
				}
				UserModel.updateUser(user.getEmail(), user);
				view.dispose();
			} else {
				System.out.println("Cancel");
				view.dispose();
			}
			
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + e.getActionCommand());
		}
		
	}	

}
