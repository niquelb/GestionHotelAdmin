package views;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import controllers.UserCreatorViewController;
import utils.ComponentInit;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class UserCreatorView extends JFrame {

	private final UserCreatorViewController controller;

	private javaswingdev.GoogleMaterialIcon iconClose=new javaswingdev.GoogleMaterialIcon();
	
	private JPanel pane;
	private JTextField textFieldEmail;
	private JTextField textFieldName;
	private JTextField textFieldLastNames;
	private JTextField textFieldPhone;
	private JPasswordField passwordField;
	private JPasswordField passwordConfirmField;
	private JButton btnSubmit;

	/**
	 * Create the frame.
	 */
	public UserCreatorView() {
		setTitle("Crear Usuario");
		pane = new JPanel();
		pane.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setResizable(false);
		setUndecorated(true);
		setAlwaysOnTop(true);
        setLocationByPlatform(true);
        setVisible(true);
        setSize(500,500);
        
        setContentPane(pane);
        
        
        JLabel lblCrearUsuario = new JLabel("Crear Usuario");
        lblCrearUsuario.setFont(new Font("Roboto Black", Font.BOLD, 24));
        
        JLabel lblEmail = new JLabel("Email (Obligatorio)");
        lblEmail.setFont(new Font("Roboto Light", Font.PLAIN, 18));
        
        textFieldEmail = new JTextField();
        textFieldEmail.setColumns(10);
        
        JLabel lblName = new JLabel("Nombre");
        lblName.setFont(new Font("Roboto Light", Font.PLAIN, 18));
        
        textFieldName = new JTextField();
        textFieldName.setColumns(10);
        
        JLabel lblLastNames = new JLabel("Apellidos");
        lblLastNames.setFont(new Font("Roboto Light", Font.PLAIN, 18));
        
        textFieldLastNames = new JTextField();
        textFieldLastNames.setColumns(10);
        
        JLabel lblPhone = new JLabel("Telefono");
        lblPhone.setFont(new Font("Roboto Light", Font.PLAIN, 18));
        
        textFieldPhone = new JTextField();
        textFieldPhone.setColumns(10);
        
        JLabel lblPassword = new JLabel("Contraseña (Obligatorio)");
        lblPassword.setFont(new Font("Roboto Light", Font.PLAIN, 18));
        
        passwordField = new JPasswordField();
        
        JLabel lblPasswordConfirm = new JLabel("Confirmar Contraseña (Obligatorio)");
        lblPasswordConfirm.setFont(new Font("Roboto Light", Font.PLAIN, 18));
        
        passwordConfirmField = new JPasswordField();
        
        btnSubmit = new JButton("Enviar");
        btnSubmit.setActionCommand("submit");
        
        iconClose.setColor1(new java.awt.Color(111, 111, 111));
        iconClose.setColor2(new java.awt.Color(215, 215, 215));
        iconClose.setIcon(javaswingdev.GoogleMaterialDesignIcon.CLOSE);
        iconClose.setSize(18);
        
        JLabel lblCloseBtn = new JLabel("");
		lblCloseBtn.setIcon(iconClose.toIcon());
        
        GroupLayout gl_pane = new GroupLayout(pane);
        gl_pane.setHorizontalGroup(
        	gl_pane.createParallelGroup(Alignment.TRAILING)
        		.addGroup(gl_pane.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(gl_pane.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_pane.createSequentialGroup()
        					.addComponent(lblCrearUsuario)
        					.addPreferredGap(ComponentPlacement.RELATED, 296, Short.MAX_VALUE)
        					.addComponent(lblCloseBtn, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
        				.addGroup(gl_pane.createSequentialGroup()
        					.addGap(10)
        					.addGroup(gl_pane.createParallelGroup(Alignment.LEADING)
        						.addGroup(gl_pane.createSequentialGroup()
        							.addGap(10)
        							.addComponent(textFieldEmail, GroupLayout.PREFERRED_SIZE, 440, GroupLayout.PREFERRED_SIZE))
        						.addComponent(lblEmail)
        						.addComponent(lblName, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
        						.addGroup(gl_pane.createSequentialGroup()
        							.addGap(10)
        							.addComponent(textFieldName, GroupLayout.PREFERRED_SIZE, 440, GroupLayout.PREFERRED_SIZE))
        						.addComponent(lblLastNames, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
        						.addGroup(gl_pane.createSequentialGroup()
        							.addGap(10)
        							.addComponent(textFieldLastNames, GroupLayout.PREFERRED_SIZE, 440, GroupLayout.PREFERRED_SIZE))
        						.addComponent(lblPhone, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
        						.addGroup(gl_pane.createSequentialGroup()
        							.addGap(10)
        							.addComponent(textFieldPhone, GroupLayout.PREFERRED_SIZE, 440, GroupLayout.PREFERRED_SIZE))
        						.addComponent(lblPassword)
        						.addGroup(gl_pane.createSequentialGroup()
        							.addGap(10)
        							.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 440, GroupLayout.PREFERRED_SIZE))
        						.addComponent(lblPasswordConfirm)
        						.addGroup(gl_pane.createSequentialGroup()
        							.addGap(10)
        							.addComponent(passwordConfirmField, GroupLayout.PREFERRED_SIZE, 440, GroupLayout.PREFERRED_SIZE)))))
        			.addContainerGap())
        		.addGroup(gl_pane.createSequentialGroup()
        			.addGap(173)
        			.addComponent(btnSubmit, GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
        			.addGap(163))
        );
        gl_pane.setVerticalGroup(
        	gl_pane.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_pane.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(gl_pane.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_pane.createSequentialGroup()
        					.addComponent(lblCrearUsuario, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
        				.addComponent(lblCloseBtn, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(textFieldEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(lblName, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(textFieldName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(lblLastNames, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(textFieldLastNames, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(lblPhone, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(textFieldPhone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(lblPasswordConfirm, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(passwordConfirmField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addGap(18)
        			.addComponent(btnSubmit, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(55, Short.MAX_VALUE))
        );
        pane.setLayout(gl_pane);
        
        controller=new UserCreatorViewController(this);
        

		lblCloseBtn.addMouseListener(controller);
		btnSubmit.setActionCommand("submit");
        ComponentInit.setButtonProperties(btnSubmit, controller);
        
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
	 * @return the passwordConfirmField
	 */
	public JPasswordField getPasswordConfirmField() {
		return passwordConfirmField;
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
	 * @param passwordConfirmField the passwordConfirmField to set
	 */
	public void setPasswordConfirmField(JPasswordField passwordConfirmField) {
		this.passwordConfirmField = passwordConfirmField;
	}

	/**
	 * @param btnSubmit the btnSubmit to set
	 */
	public void setBtnSubmit(JButton btnSubmit) {
		this.btnSubmit = btnSubmit;
	}
	
	
}
