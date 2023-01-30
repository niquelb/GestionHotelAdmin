package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;

public class SideNavigationView extends JPanel {

	private JButton users_button=new JButton("USUARIOS");
	private JButton rooms_button=new JButton("HABITACIONES");
	private JButton bookings_button=new JButton("RESERVAS");
	private JButton log_out_button=new JButton("CERRAR SESION");
	
	/**
	 * Create the panel.
	 */
	public SideNavigationView() {
		setBackground(new Color(128, 128, 255));
		
		users_button.setBounds(10, 11, 163, 40);
		users_button.setForeground(new Color(0, 0, 0));
		users_button.setBackground(new Color(255, 255, 255));
		users_button.setFocusable(false);
		
		rooms_button.setBounds(10, 62, 163, 40);
		rooms_button.setForeground(new Color(0, 0, 0));
		rooms_button.setBackground(new Color(255, 255, 255));
		rooms_button.setFocusable(false);
		
		bookings_button.setBounds(10, 113, 163, 40);
		bookings_button.setForeground(new Color(0, 0, 0));
		bookings_button.setBackground(new Color(255, 255, 255));
		bookings_button.setFocusable(false);
				
		log_out_button.setBounds(10, 164, 163, 40);
		log_out_button.setForeground(new Color(0, 0, 0));
		log_out_button.setBackground(new Color(255, 255, 255));
		log_out_button.setFocusable(false);
		
		setLayout(null);
		
		add(users_button);
		add(rooms_button);
		add(bookings_button);
		add(log_out_button);
	}
	

}
