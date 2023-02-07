package controllers;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import models.UserModel;
import utils.UserDataChecker;
import views.UserCreatorView;

public class UserCreatorViewController implements ActionListener, MouseListener{

	private final UserCreatorView view;

	public UserCreatorViewController(UserCreatorView view) {
		super();
		this.view = view;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "submit":
			String email=String.valueOf(view.getTextFieldEmail().getText());
			String name=String.valueOf(view.getTextFieldName().getText());
			String last_names=String.valueOf(view.getTextFieldLastNames().getText());
			String phone=String.valueOf(view.getTextFieldPhone().getText());
			
			String password=String.valueOf(view.getPasswordField().getPassword());
			String password_confirm=String.valueOf(view.getPasswordConfirmField().getPassword());
			
			if (email.equals("")) {
				JOptionPane.showMessageDialog(view,
					    "El email no puede estar vacio.",
					    "Error",
					    JOptionPane.PLAIN_MESSAGE);
				break;
			}
			
			if (password.equals("")) {
				JOptionPane.showMessageDialog(view,
					    "La contraseña no puede estar vacia.",
					    "Error",
					    JOptionPane.PLAIN_MESSAGE);
				break;
			}
			
			if (password_confirm.equals("")) {
				JOptionPane.showMessageDialog(view,
					    "La contraseña debe ser confirmada.",
					    "Error",
					    JOptionPane.PLAIN_MESSAGE);
				break;
			}
			
			if (!UserDataChecker.validateEmail(email)) {
				JOptionPane.showMessageDialog(view,
					    "El email no es correcto, asegurate de que sigue el siguiente patron:"
					    + "XXXXXXXX@XXXX.XXX Ej. correo@gmail.com",
					    "Error",
					    JOptionPane.PLAIN_MESSAGE);
				break;
			}
			
			if (!UserDataChecker.validatePassword(password)) {
				JOptionPane.showMessageDialog(view,
					    "La contraseña no es valida. Asegurate de que cumple con los siguientes requisitos:\n\t"
					    + "La contraseña debe ser de 8 caracteres o mas.\n\t"
					    + "La contraseña debe contener 1 digito, 1 mayuscula y 1 minuscula.",
					    "Error",
					    JOptionPane.PLAIN_MESSAGE);
				break;
			}
			
			if (!password.equals(password_confirm)) {
				JOptionPane.showMessageDialog(view,
					    "Las contraseñas no coinciden.",
					    "Error",
					    JOptionPane.PLAIN_MESSAGE);
				break;
			}
			
			if (UserModel.getUser(email)!=null) {
				JOptionPane.showMessageDialog(view,
					    "Este email ya esta registrado, por favor, elija otro.",
					    "Error",
					    JOptionPane.PLAIN_MESSAGE);
				break;
			}
			
			String[] arr= {"Confirmar","Cancelar"};
			int n = JOptionPane.showOptionDialog(view,
				    "Desea confirmar la creacion del usuario "+name+'?',
				    "Confirmar Edicion?",
				    JOptionPane.YES_NO_OPTION,
				    JOptionPane.PLAIN_MESSAGE,
				    null,
				    arr,
				    arr[0]
				    );
			
			if (n==0) {
				System.out.println("Creation Start");
				UserModel.createUser(new UserModel(email, password, name, last_names, phone));
				view.dispose();
			} else {
				view.dispose();
			}
			
			break;

		default:
			break;
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
