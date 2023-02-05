package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import controllers.SideNavigationViewController;
import utils.ComponentInit;

public class SideNavigationView extends JPanel {
	
	private SideNavigationViewController controller=new SideNavigationViewController(this);

	private JButton users_button=new JButton("USUARIOS");
	private JButton rooms_button=new JButton("HABITACIONES");
	private JButton bookings_button=new JButton("RESERVAS");
	private JButton log_out_button=new JButton("CERRAR SESION");
	
	/**
	 * Create the panel.
	 */
	public SideNavigationView() {
		setBackground(new Color(128, 128, 255));
		
		ComponentInit.setButtonProperties(users_button,controller);
		ComponentInit.setButtonProperties(rooms_button,controller);
		ComponentInit.setButtonProperties(bookings_button,controller);
		ComponentInit.setButtonProperties(log_out_button,controller);
		
		users_button.setBounds(10, 11, 200, 40);
		rooms_button.setBounds(10, 62, 200, 40);
		bookings_button.setBounds(10, 113, 200, 40);
		log_out_button.setBounds(10, 164, 200, 40);
		
		users_button.setActionCommand("users");
		rooms_button.setActionCommand("rooms");
		bookings_button.setActionCommand("bookings");
		log_out_button.setActionCommand("log_out");
		
		
		setLayout(null);
		
		add(users_button);
		add(rooms_button);
		add(bookings_button);
		add(log_out_button);
	}
	

}
