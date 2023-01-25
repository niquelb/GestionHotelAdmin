package views;

import java.awt.Font;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controllers.LoginController;

/**
 * View class for the login
 * @author Nico
 *
 */
public class LoginView {

	/**
	 * Controller for this view
	 */
	private LoginController login_controller;

	private JFrame frame = new JFrame();

	private JButton login_btn = new JButton("Login");
	private JButton register_btn = new JButton("Reset");
	private JTextField uID_field = new JTextField();
	private JPasswordField passwd_field = new JPasswordField();
	private JLabel uID_label = new JLabel("userID:");
	private JLabel passwd_label = new JLabel("password:");
	private JLabel msg_label = new JLabel();

	/**
	 * Constructor, initializes and styles the window
	 */
	public LoginView(){

		try {
			login_controller=new LoginController(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		uID_label.setBounds(50,100,75,25);
		passwd_label.setBounds(50,150,75,25);	

		msg_label.setBounds(125,250,250,35);
		msg_label.setFont(new Font(null,Font.ITALIC,25));		

		uID_field.setBounds(125,100,200,25);
		passwd_field.setBounds(125,150,200,25);	

		login_btn.setBounds(125,200,100,25);
		login_btn.setFocusable(false);
		login_btn.addActionListener(login_controller);
		login_btn.setActionCommand("login");

		register_btn.setBounds(225,200,100,25);
		register_btn.setFocusable(false);
//		register_btn.addActionListener(login_controller);
//		register_btn.setActionCommand("register");

		frame.add(uID_label);
		frame.add(passwd_label);
		frame.add(msg_label);
		frame.add(uID_field);
		frame.add(passwd_field);
		frame.add(login_btn);
		frame.add(register_btn);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500,500);
		frame.setLayout(null);
		frame.setVisible(true);

	}
	

	
	public JButton getLogin_btn() {
		return login_btn;
	}

	public void setLogin_btn(JButton login_btn) {
		this.login_btn = login_btn;
	}

	public JButton getRegister_btn() {
		return register_btn;
	}

	public void setRegister_btn(JButton reset_btn) {
		this.register_btn = reset_btn;
	}

	public JTextField getuID_field() {
		return uID_field;
	}

	public void setuID_field(JTextField uID_field) {
		this.uID_field = uID_field;
	}

	public JPasswordField getPasswd_field() {
		return passwd_field;
	}

	public void setPasswd_field(JPasswordField passwd_field) {
		this.passwd_field = passwd_field;
	}

	public JLabel getMsg_label() {
		return msg_label;
	}

	public void setMsg_label(JLabel msg_label) {
		this.msg_label = msg_label;
	}

}
