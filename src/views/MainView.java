package views;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import controllers.MainViewController;
import utils.ComponentInit;
import utils.title_bar.SimpleTitleBar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class MainView extends JFrame {
	
	public static final String USER_VIEW="user_view";
	public static final String ROOM_VIEW="room_view";
	public static final String BOOKING_VIEW="booking_view";
	
	private final MainViewController controller;

	private final JPanel main_panel=new JPanel();
	private final JPanel center_panel=new JPanel();
	private final JPanel navi_panel=new JPanel();
	private final JPanel side_panel = new JPanel();
	
	private final SimpleTitleBar title_bar=new SimpleTitleBar();
	private final UserListView user_list=new UserListView();
	private final RoomListView room_list=new RoomListView();
	private final BookingListView booking_list;
	private final JLabel lblUser_id = new JLabel("user_id");
	private final JButton btnUsers = new JButton("USUARIOS");
	private final JButton btnRooms = new JButton("HABITACIONES");
	private final JButton btnBookings = new JButton("RESERVAS");
	private final JButton btnLog_out = new JButton("CERRAR SESION");

	/**
	 * Constructor for the view
	 */
	public MainView(String user_id) {
		booking_list=new BookingListView(user_id);
		
		setJFrame();
        main_panel.setBackground(new Color(45, 45, 45));
        main_panel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		main_panel.setLayout(new BorderLayout(10, 0));
		
		JPanel header = new JPanel();
		header.setLayout(new BorderLayout(0, 0));
		header.add(title_bar, BorderLayout.NORTH);
		header.setBackground(new Color(25, 25, 25));
		
		main_panel.add(header, BorderLayout.NORTH);
		navi_panel.setBackground(new Color(25, 25, 25));
		main_panel.add(navi_panel, BorderLayout.WEST);
		center_panel.setBackground(new Color(45, 45, 45));
		main_panel.add(center_panel, BorderLayout.CENTER);
		side_panel.setBackground(new Color(45, 45, 45));
		main_panel.add(side_panel, BorderLayout.EAST);
		
		side_panel.setPreferredSize(new Dimension(75, main_panel.getHeight()));
		
		JButton btnChat = new JButton("Chat");
		GroupLayout gl_side_panel = new GroupLayout(side_panel);
		gl_side_panel.setHorizontalGroup(
			gl_side_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_side_panel.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(btnChat, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
					.addGap(37))
		);
		gl_side_panel.setVerticalGroup(
			gl_side_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_side_panel.createSequentialGroup()
					.addContainerGap(689, Short.MAX_VALUE)
					.addComponent(btnChat)
					.addContainerGap())
		);
		side_panel.setLayout(gl_side_panel);
		
		center_panel.setPreferredSize(getMinimumSize());
		navi_panel.setPreferredSize(new Dimension(220, main_panel.getHeight()));
		
		header.setPreferredSize(new Dimension(main_panel.getWidth(), 75));
		
		lblUser_id.setFont(new Font("Roboto Light", Font.PLAIN, 24));
		lblUser_id.setHorizontalAlignment(SwingConstants.CENTER);
		lblUser_id.setForeground(new Color(255, 255, 255));
		
		header.add(lblUser_id, BorderLayout.CENTER);
		
		
		center_panel.setLayout(new CardLayout(0, 0));
		user_list.setBackground(new Color(45, 45, 45));
		room_list.setBackground(new Color(45, 45, 45));
		center_panel.add(user_list, USER_VIEW);
		center_panel.add(booking_list, BOOKING_VIEW);
		center_panel.add(room_list, ROOM_VIEW);

		GroupLayout gl_navi_panel = new GroupLayout(navi_panel);
		gl_navi_panel.setHorizontalGroup(
			gl_navi_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_navi_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_navi_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnUsers, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
						.addComponent(btnRooms, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
						.addComponent(btnBookings, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
						.addComponent(btnLog_out, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_navi_panel.setVerticalGroup(
			gl_navi_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_navi_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnUsers, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnRooms, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnBookings, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnLog_out, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(514, Short.MAX_VALUE))
		);
		navi_panel.setLayout(gl_navi_panel);
		
		title_bar.init(this);
		
		controller=new MainViewController(this, user_id);
		
		ComponentInit.setButtonProperties(btnUsers, controller);
		btnUsers.setActionCommand("users");
		ComponentInit.setButtonProperties(btnRooms, controller);
		btnRooms.setActionCommand("rooms");
		ComponentInit.setButtonProperties(btnBookings, controller);
		btnBookings.setActionCommand("bookings");
		ComponentInit.setButtonProperties(btnLog_out, controller);
		btnLog_out.setActionCommand("log_out");
		ComponentInit.setButtonProperties(btnChat, controller);
		btnChat.setActionCommand("chat");
		
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
	
	/**
	 * Method for switching the content inside the
	 * center_panel (card layout)
	 * @param content_id Content ID: USER_VIEW / ROOM_VIEW / BOOKING_VIEW
	 */
	public void switchMainContent(String content_id) {
		((CardLayout) center_panel.getLayout()).show(center_panel, content_id);
	}
}
