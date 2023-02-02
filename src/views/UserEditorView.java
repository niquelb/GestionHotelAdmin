package views;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controllers.UserEditorViewController;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;

public class UserEditorView extends JFrame {

	private final UserEditorViewController controller=new UserEditorViewController(this);
	
	private JPanel pane;
	private JTextField textFieldEmail;
	private JTextField textFieldName;
	private JTextField textFieldLastNames;
	private JTextField textFieldPhone;
	private JPasswordField passwordField;
	private JButton btnSubmit = new JButton("Enviar");

	/**
	 * Create the frame.
	 */
	public UserEditorView() {
		pane = new JPanel();
		pane.setBorder(new EmptyBorder(5, 5, 5, 5));

		
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setUndecorated(true);
        setSize(500,500);
        setContentPane(pane);
        pane.setLayout(null);
        
        JLabel lblEmail = new JLabel("Email");
        lblEmail.setFont(new Font("Roboto Light", Font.PLAIN, 18));
        lblEmail.setBounds(20, 60, 69, 14);
        pane.add(lblEmail);
        
        textFieldEmail = new JTextField();
        textFieldEmail.setColumns(10);
        textFieldEmail.setBounds(30, 85, 440, 20);
        pane.add(textFieldEmail);
        
        JLabel lblTitle = new JLabel("Editar Usuario");
        lblTitle.setFont(new Font("Roboto Black", Font.BOLD, 24));
        lblTitle.setBounds(10, 11, 222, 38);
        pane.add(lblTitle);
        
        JLabel lblName = new JLabel("Nombre");
        lblName.setFont(new Font("Roboto Light", Font.PLAIN, 18));
        lblName.setBounds(20, 116, 69, 14);
        pane.add(lblName);
        
        JLabel lblLastNames = new JLabel("Apellidos");
        lblLastNames.setFont(new Font("Roboto Light", Font.PLAIN, 18));
        lblLastNames.setBounds(20, 172, 79, 14);
        pane.add(lblLastNames);
        
        JLabel lblPhone = new JLabel("Telefono");
        lblPhone.setFont(new Font("Roboto Light", Font.PLAIN, 18));
        lblPhone.setBounds(20, 228, 69, 14);
        pane.add(lblPhone);
        
        textFieldName = new JTextField();
        textFieldName.setColumns(10);
        textFieldName.setBounds(30, 141, 440, 20);
        pane.add(textFieldName);
        
        textFieldLastNames = new JTextField();
        textFieldLastNames.setColumns(10);
        textFieldLastNames.setBounds(30, 197, 440, 20);
        pane.add(textFieldLastNames);
        
        textFieldPhone = new JTextField();
        textFieldPhone.setColumns(10);
        textFieldPhone.setBounds(30, 253, 440, 20);
        pane.add(textFieldPhone);
        
        JLabel lblPassword = new JLabel("Contraseña");
        lblPassword.setFont(new Font("Roboto Light", Font.PLAIN, 18));
        lblPassword.setBounds(20, 284, 92, 14);
        pane.add(lblPassword);
        
        passwordField = new JPasswordField();
        passwordField.setBounds(30, 309, 440, 20);
        pane.add(passwordField);
        
        setButtonProperties(btnSubmit);
        btnSubmit.setActionCommand("submit");
        btnSubmit.setBounds(150, 366, 154, 31);
        pane.add(btnSubmit);
        setResizable(false);
		setVisible(true);
	}
	
	public void setButtonProperties(JButton button) {
		button.setForeground(new Color(0, 0, 0));
		button.setBackground(new Color(255, 255, 255));
		button.setFocusable(false);
		button.setFont(new Font("Roboto Light", Font.BOLD, 18));
		
		button.addActionListener(controller);
	}

	/**
	 * @return the textFieldEmail
	 */
	public JTextField getTextFieldEmail() {
		return textFieldEmail;
	}

	/**
	 * @return the textFieldName
	 */
	public JTextField getTextFieldName() {
		return textFieldName;
	}

	/**
	 * @return the textFieldLastNames
	 */
	public JTextField getTextFieldLastNames() {
		return textFieldLastNames;
	}

	/**
	 * @return the textFieldPhone
	 */
	public JTextField getTextFieldPhone() {
		return textFieldPhone;
	}

	/**
	 * @return the passwordField
	 */
	public JPasswordField getPasswordField() {
		return passwordField;
	}

	/**
	 * @return the btnSubmit
	 */
	public JButton getBtnSubmit() {
		return btnSubmit;
	}

	/**
	 * @param textFieldEmail the textFieldEmail to set
	 */
	public void setTextFieldEmail(JTextField textFieldEmail) {
		this.textFieldEmail = textFieldEmail;
	}

	/**
	 * @param textFieldName the textFieldName to set
	 */
	public void setTextFieldName(JTextField textFieldName) {
		this.textFieldName = textFieldName;
	}

	/**
	 * @param textFieldLastNames the textFieldLastNames to set
	 */
	public void setTextFieldLastNames(JTextField textFieldLastNames) {
		this.textFieldLastNames = textFieldLastNames;
	}

	/**
	 * @param textFieldPhone the textFieldPhone to set
	 */
	public void setTextFieldPhone(JTextField textFieldPhone) {
		this.textFieldPhone = textFieldPhone;
	}

	/**
	 * @param passwordField the passwordField to set
	 */
	public void setPasswordField(JPasswordField passwordField) {
		this.passwordField = passwordField;
	}

	/**
	 * @param btnSubmit the btnSubmit to set
	 */
	public void setBtnSubmit(JButton btnSubmit) {
		this.btnSubmit = btnSubmit;
	}
	
	
}
