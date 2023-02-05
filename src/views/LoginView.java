package views;

import java.awt.Font;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import controllers.LoginViewController;
import utils.ComponentInit;
import utils.title_bar.SimpleTitleBar;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.intellijthemes.FlatDarkFlatIJTheme;
import javax.swing.LayoutStyle.ComponentPlacement;

/**
 * View class for the login
 * @author Nico
 *
 */
public class LoginView extends JFrame{

	/**
	 * Controller for this view
	 */
	private LoginViewController controller;
//	private SimpleTitleBar title_bar=new SimpleTitleBar();
	
	public static final int WINDOW_WIDTH=900;
	public static final int WINDOW_HEIGHT=450;
	private javaswingdev.GoogleMaterialIcon iconClose=new javaswingdev.GoogleMaterialIcon();
	
	private JPanel main_panel=new JPanel();

	private JButton btnSubmit;
	private JLabel lblError;
	private JTextField textFieldEmail;
	private JPasswordField passwordField;

	/**
	 * Constructor, initializes and styles the window
	 */
	public LoginView(){
		setBackground(new Color(45, 45, 45));
		setJFrame();
		try {
			controller=new LoginViewController(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		main_panel.setBackground(new Color(45, 45, 45));
		main_panel.setLayout(null);
//		title_bar.setBounds(-43, 0, main_panel.getWidth(), 29);
		
//		main_panel.add(title_bar);
		
		JPanel side_panel = new JPanel();
		side_panel.setBackground(new Color(51, 0, 64));
		side_panel.setBounds(0, 0, 250, WINDOW_HEIGHT);
		main_panel.add(side_panel);
		side_panel.setLayout(null);
		
		JLabel lblTempLogo = new JLabel("LOGO");
		lblTempLogo.setBounds(77, 184, 92, 43);
		lblTempLogo.setForeground(new Color(255, 255, 255));
		lblTempLogo.setFont(new Font("Roboto Thin", Font.PLAIN, 36));
		side_panel.add(lblTempLogo);
		
		JPanel content_panel = new JPanel();
		content_panel.setBackground(new Color(45, 45, 45));
		content_panel.setBounds(250, 0, 650, WINDOW_HEIGHT);
		main_panel.add(content_panel);
		
		JLabel lblTitle = new JLabel("Iniciar Sesion");
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Roboto Light", Font.PLAIN, 36));
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setFont(new Font("Roboto Thin", Font.PLAIN, 26));
		
		JLabel lblPassword = new JLabel("Contraseña");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Roboto Thin", Font.PLAIN, 26));
		
		textFieldEmail = new JTextField();
		textFieldEmail.setColumns(10);
		
		passwordField = new JPasswordField();
		
		btnSubmit = new JButton("Iniciar Sesion");
		btnSubmit.setActionCommand("login");
		ComponentInit.setButtonProperties(btnSubmit, controller);
		btnSubmit.addMouseListener(controller);
		
		lblError = new JLabel("ERROR AL INICIAR SESION");
		lblError.setForeground(new Color(255, 0, 0));
		lblError.setFont(new Font("Roboto Light", Font.PLAIN, 26));
		lblError.setVisible(false);
		
		iconClose.setColor1(new java.awt.Color(111, 111, 111));
        iconClose.setColor2(new java.awt.Color(215, 215, 215));
        iconClose.setIcon(javaswingdev.GoogleMaterialDesignIcon.CLOSE);
        iconClose.setSize(18);
		
		JLabel windowClose = new JLabel("");
		windowClose.setIcon(iconClose.toIcon());
		windowClose.addMouseListener(controller);
		
		GroupLayout gl_content_panel = new GroupLayout(content_panel);
		gl_content_panel.setHorizontalGroup(
			gl_content_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_content_panel.createSequentialGroup()
					.addGroup(gl_content_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_content_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblTitle))
						.addGroup(gl_content_panel.createSequentialGroup()
							.addGap(19)
							.addGroup(gl_content_panel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblEmail)
								.addComponent(lblPassword)
								.addGroup(gl_content_panel.createSequentialGroup()
									.addGap(10)
									.addComponent(textFieldEmail, 192, 192, Short.MAX_VALUE))
								.addGroup(gl_content_panel.createSequentialGroup()
									.addGap(10)
									.addComponent(passwordField)))))
					.addGap(89)
					.addComponent(lblError, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(31))
				.addGroup(gl_content_panel.createSequentialGroup()
					.addGap(188)
					.addComponent(btnSubmit, GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
					.addGap(188))
				.addGroup(Alignment.TRAILING, gl_content_panel.createSequentialGroup()
					.addContainerGap(594, Short.MAX_VALUE)
					.addComponent(windowClose)
					.addContainerGap())
		);
		gl_content_panel.setVerticalGroup(
			gl_content_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_content_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(windowClose)
					.addGap(5)
					.addComponent(lblTitle, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(textFieldEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_content_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblError))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(62)
					.addComponent(btnSubmit, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(115, Short.MAX_VALUE))
		);
		content_panel.setLayout(gl_content_panel);
	}
	
	public void setJFrame() {
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setUndecorated(true);
        setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        setResizable(false);
		setVisible(true);
		setContentPane(main_panel);
	}
	
	/**
	 * @return the btnSubmit
	 */
	public JButton getBtnSubmit() {
		return btnSubmit;
	}

	/**
	 * @return the lblError
	 */
	public JLabel getLblError() {
		return lblError;
	}

	/**
	 * @return the textFieldEmail
	 */
	public JTextField getTextFieldEmail() {
		return textFieldEmail;
	}

	/**
	 * @return the passwordField
	 */
	public JPasswordField getPasswordField() {
		return passwordField;
	}

	/**
	 * @param btnSubmit the btnSubmit to set
	 */
	public void setBtnSubmit(JButton btnSubmit) {
		this.btnSubmit = btnSubmit;
	}

	/**
	 * @param lblError the lblError to set
	 */
	public void setLblError(JLabel lblError) {
		this.lblError = lblError;
	}

	/**
	 * @param textFieldEmail the textFieldEmail to set
	 */
	public void setTextFieldEmail(JTextField textFieldEmail) {
		this.textFieldEmail = textFieldEmail;
	}

	/**
	 * @param passwordField the passwordField to set
	 */
	public void setPasswordField(JPasswordField passwordField) {
		this.passwordField = passwordField;
	}

	
}