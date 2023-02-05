package views;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import controllers.UserEditorViewController;
import utils.ComponentInit;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;

public class UserEditorView extends JFrame {

	private final UserEditorViewController controller;

	private javaswingdev.GoogleMaterialIcon iconClose=new javaswingdev.GoogleMaterialIcon();
	
	private JPanel pane;
	private JTextField textFieldEmail;
	private JTextField textFieldName;
	private JTextField textFieldLastNames;
	private JTextField textFieldPhone;
	private JPasswordField passwordField;
	private JPasswordField passwordFieldConfirm;
	private JButton btnSubmit = new JButton("Enviar");

	/**
	 * Create the frame.
	 */
	public UserEditorView(String u_id) {
		pane = new JPanel();
		pane.setBorder(new EmptyBorder(5, 5, 5, 5));

		
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocationByPlatform(true);
        setUndecorated(true);
        setSize(500,500);
        
        setContentPane(pane);
        pane.setLayout(null);
        pane.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
        
        JLabel lblEmail = new JLabel("Email");
        lblEmail.setFont(new Font("Roboto Light", Font.PLAIN, 18));
        lblEmail.setBounds(20, 60, 69, 20);
        pane.add(lblEmail);
        
        textFieldEmail = new JTextField();
        textFieldEmail.setEditable(false);
        textFieldEmail.setColumns(10);
        textFieldEmail.setBounds(30, 85, 440, 20);
        pane.add(textFieldEmail);
        
        JLabel lblTitle = new JLabel("Editar Usuario");
        lblTitle.setFont(new Font("Roboto Black", Font.BOLD, 24));
        lblTitle.setBounds(10, 11, 222, 38);
        pane.add(lblTitle);
        
        JLabel lblName = new JLabel("Nombre");
        lblName.setFont(new Font("Roboto Light", Font.PLAIN, 18));
        lblName.setBounds(20, 116, 69, 20);
        pane.add(lblName);
        
        JLabel lblLastNames = new JLabel("Apellidos");
        lblLastNames.setFont(new Font("Roboto Light", Font.PLAIN, 18));
        lblLastNames.setBounds(20, 172, 79, 20);
        pane.add(lblLastNames);
        
        JLabel lblPhone = new JLabel("Telefono");
        lblPhone.setFont(new Font("Roboto Light", Font.PLAIN, 18));
        lblPhone.setBounds(20, 228, 69, 20);
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
        
        JLabel lblPassword = new JLabel("Nueva Contraseña (Opcional)");
        lblPassword.setFont(new Font("Roboto Light", Font.PLAIN, 18));
        lblPassword.setBounds(20, 284, 232, 20);
        pane.add(lblPassword);
        
        passwordField = new JPasswordField();
        passwordField.setBounds(30, 309, 440, 20);
        pane.add(passwordField);
        
        btnSubmit.setActionCommand("submit");
        btnSubmit.setBounds(152, 415, 154, 31);
        pane.add(btnSubmit);
        setResizable(false);
		setVisible(true);

		JLabel lblPasswordConfirm = new JLabel("Contraseña Actual (Obligatoria)");
		lblPasswordConfirm.setFont(new Font("Roboto Light", Font.PLAIN, 18));
		lblPasswordConfirm.setBounds(20, 340, 248, 20);
		pane.add(lblPasswordConfirm);
		
		passwordFieldConfirm = new JPasswordField();
		passwordFieldConfirm.setBounds(30, 364, 440, 20);
		pane.add(passwordFieldConfirm);

		iconClose.setColor1(new java.awt.Color(111, 111, 111));
        iconClose.setColor2(new java.awt.Color(215, 215, 215));
        iconClose.setIcon(javaswingdev.GoogleMaterialDesignIcon.CLOSE);
        iconClose.setSize(18);
		
		JLabel lblCloseButton = new JLabel("");
		lblCloseButton.setIcon(iconClose.toIcon());
		lblCloseButton.setBounds(474, 11, 16, 14);
		pane.add(lblCloseButton);

		controller=new UserEditorViewController(this, u_id);
		lblCloseButton.addMouseListener(controller);
		ComponentInit.setButtonProperties(btnSubmit,controller);
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
	 * @return the passwordFieldConfirm
	 */
	public JPasswordField getPasswordFieldConfirm() {
		return passwordFieldConfirm;
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
	 * @param passwordFieldConfirm the passwordFieldConfirm to set
	 */
	public void setPasswordFieldConfirm(JPasswordField passwordFieldConfirm) {
		this.passwordFieldConfirm = passwordFieldConfirm;
	}

	/**
	 * @param btnSubmit the btnSubmit to set
	 */
	public void setBtnSubmit(JButton btnSubmit) {
		this.btnSubmit = btnSubmit;
	}

	
}
