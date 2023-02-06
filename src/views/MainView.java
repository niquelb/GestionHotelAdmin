package views;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import controllers.MainViewController;
import utils.title_bar.SimpleTitleBar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class MainView extends JFrame {
	
	public static final String USER_VIEW="user_view";
	public static final String ROOM_VIEW="room_view";
	public static final String BOOKING_VIEW="user_view";
	
	private final MainViewController controller;

	private final JPanel main_panel=new JPanel();
	private final JPanel center_panel=new JPanel();
	private final JPanel side_panel=new JPanel();
	
	private final SimpleTitleBar title_bar=new SimpleTitleBar();
	private final UserListView center_content=new UserListView();
	private final SideNavigationView side_navigation=new SideNavigationView();
	private final JLabel lblUser_id = new JLabel("user_id");

	/**
	 * Constructor for the view
	 */
	public MainView(String user_id) {
		setJFrame();
        main_panel.setBackground(new Color(45, 45, 45));
        main_panel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		main_panel.setLayout(new BorderLayout(10, 0));
		
		JPanel header = new JPanel();
		header.setLayout(new BorderLayout(0, 0));
		header.add(title_bar, BorderLayout.NORTH);
		header.setBackground(new Color(25, 25, 25));
		
		main_panel.add(header, BorderLayout.NORTH);
		main_panel.add(side_panel, BorderLayout.WEST);
		center_panel.setBackground(new Color(45, 45, 45));
		main_panel.add(center_panel, BorderLayout.CENTER);
		
		center_panel.setPreferredSize(getMinimumSize());
		side_panel.setPreferredSize(new Dimension(220, main_panel.getHeight()));
		header.setPreferredSize(new Dimension(200, 75));
		
		lblUser_id.setFont(new Font("Roboto Light", Font.PLAIN, 24));
		lblUser_id.setHorizontalAlignment(SwingConstants.CENTER);
		lblUser_id.setForeground(new Color(255, 255, 255));
		
		header.add(lblUser_id, BorderLayout.CENTER);
		
		side_panel.setLayout(new BorderLayout(0, 0));
		side_navigation.setBackground(new Color(25, 25, 25));
		side_panel.add(side_navigation);
		
		center_panel.setLayout(new CardLayout(0, 0));
		center_content.setBackground(new Color(45, 45, 45));
		center_panel.add(center_content, USER_VIEW);
		
		title_bar.init(this);
		
		controller=new MainViewController(this, user_id);
	}
	
	public void setJFrame() {
		setTitle("Gestion Hotelera");
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setUndecorated(true);
        setSize(1144,802);
        setMinimumSize(new Dimension(1000, 750));
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
//        setResizable(false);
        setContentPane(main_panel);
		setVisible(true);
	}

	/**
	 * @return the lblUser_id
	 */
	public JLabel getLblUser_id() {
		return lblUser_id;
	}
	
	
	
}
