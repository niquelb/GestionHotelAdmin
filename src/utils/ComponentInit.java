package utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * Class for styling and initializing swing
 * components
 * 
 * @author Nico
 *
 */
public class ComponentInit {
	/**
	 * Method for styling and adding an action listener
	 * to JButton elements
	 * @param button JButton object to style
	 * @param controller Controller responsible for the action listener
	 */
	public static void setButtonProperties(JButton button, ActionListener controller) {
		button.setForeground(new Color(0, 0, 0));
		button.setBackground(new Color(255, 255, 255));
		button.setBorder(null);
		button.setFocusable(false);
		button.setFont(new Font("Roboto Light", Font.BOLD, 18));
		
		button.addActionListener(controller);
	}
}
