package views;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;

public class MainView extends JFrame {

	private final JPanel main_panel=new JPanel();
	private final CenterContentView center_content=new CenterContentView();
	private final SideNavigationView side_navigation=new SideNavigationView();

	/**
	 * Constructor for the view
	 */
	public MainView() {
		main_panel.setLayout(null);
		
		side_navigation.setBounds(0, 86, 221, 614);
		main_panel.add(side_navigation);
		
		center_content.setBounds(220, 86, 780, 614);
		main_panel.add(center_content);
		
		JPanel header = new JPanel();
		header.setBackground(new Color(128, 128, 255));
		header.setBounds(0, 0, 1000, 87);
		main_panel.add(header);
		
		setJFrame();
	}
	
	public void setJFrame() {
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setUndecorated(true);
        setSize(1000,700);
        setResizable(false);
        setContentPane(this.main_panel);
		setVisible(true);
	}
}
