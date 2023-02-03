package views;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.CardLayout;

public class MainView extends JFrame {
	
	public static final String USER_LIST="user_list";

	private final JPanel main_panel=new JPanel();
	private final JPanel center_panel=new JPanel();
	private final UserListView center_content=new UserListView();
	private final SideNavigationView side_navigation=new SideNavigationView();

	/**
	 * Constructor for the view
	 */
	public MainView() {
		main_panel.setLayout(null);
        main_panel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		
		side_navigation.setBounds(0, 86, 221, 614);
		main_panel.add(side_navigation);
		
		center_panel.setBounds(220, 86, 780, 614);
		main_panel.add(center_panel);
		
		center_panel.setLayout(new CardLayout(0, 0));
		center_panel.add(center_content, USER_LIST);
		
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
        setContentPane(main_panel);
		setVisible(true);
	}
}
