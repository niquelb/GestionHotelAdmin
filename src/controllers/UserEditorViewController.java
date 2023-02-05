package controllers;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import at.favre.lib.crypto.bcrypt.BCrypt;
import main.Main;
import models.UserModel;
import utils.UserDataChecker;
import views.UserEditorView;

public class UserEditorViewController implements ActionListener, MouseListener {
	private final UserEditorView view;
	private final UserModel user;

	public UserEditorViewController(UserEditorView view, String u_id) {
		super();
		this.view = view;
		user=UserModel.getUser(u_id);
		if (!(user==null)) {
			updateFields();
		} else {
			System.err.println("User is null.");
			view.dispose();
		}
	}
	
	public void updateFields() {
		// variable = (condition) ? expressionTrue :  expressionFalse;
		view.getTextFieldEmail().setText(user.getEmail());
		view.getTextFieldName().setText(user.getName()!=null?user.getName():"");
		view.getTextFieldLastNames().setText(user.getLast_names()!=null?user.getLast_names():"");
		view.getTextFieldPhone().setText(user.getPhone_num()!=null?user.getPhone_num():"");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "submit":
			String password=String.valueOf(view.getPasswordFieldConfirm().getPassword());
			String password_new=String.valueOf(view.getPasswordField().getPassword());
			
			String name=String.valueOf(view.getTextFieldName().getText());
			String last_names=String.valueOf(view.getTextFieldLastNames().getText());
			String phone=String.valueOf(view.getTextFieldPhone().getText());
			
			if (password==null||password.equals("")) {
				JOptionPane.showMessageDialog(view,
					    "La contraseña no puede estar vacia.",
					    "Error",
					    JOptionPane.PLAIN_MESSAGE);
				break;
			}
			
			if (!BCrypt.verifyer().verify(password.toCharArray(), user.getPassword()).verified) {
				JOptionPane.showMessageDialog(view,
					    "La contraseña es incorrecta.",
					    "Error",
					    JOptionPane.PLAIN_MESSAGE);
				break;
			}
			
			if (!password_new.equals("")) {
				if (!UserDataChecker.validatePassword(password_new)) {
					JOptionPane.showMessageDialog(view,
						    "La nueva contraseña no es valida. Asegurate de que cumple con los siguientes requisitos:\n\t"
						    + "La contraseña debe ser de 8 caracteres o mas.\n\t"
						    + "La contraseña debe contener 1 digito, 1 mayuscula y 1 minuscula.",
						    "Error",
						    JOptionPane.PLAIN_MESSAGE);
					break;
				}
			}

			String[] arr= {"Confirmar","Cancelar"};
			int n = JOptionPane.showOptionDialog(view,
				    "Desea confirmar la edicion del usuario "+user.getName()+'?',
				    "Confirmar Edicion?",
				    JOptionPane.YES_NO_OPTION,
				    JOptionPane.PLAIN_MESSAGE,
				    null,
				    arr,
				    arr[0]
				    );
			
			if (n==0) {
				user.setName(name);
				user.setLast_names(last_names);
				user.setPhone_num(phone);
				
				user.setPassword((!password_new.equals("")?password_new:null));
				
				UserModel.updateUser(user);
				
				view.dispose();
			} else {
				view.dispose();
			}
			
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + e.getActionCommand());
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource().getClass()==JLabel.class) {			
			view.dispose();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		view.setCursor(Cursor.HAND_CURSOR);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		view.setCursor(Cursor.DEFAULT_CURSOR);
	}

}
