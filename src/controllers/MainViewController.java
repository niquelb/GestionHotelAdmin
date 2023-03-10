package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import models.UserModel;
import views.ChatView;
import views.MainView;

public class MainViewController implements ActionListener {
	
	private final MainView view;
	private ChatView chat_view;
	
	private String user_id;
	private boolean isChatEnabled=false;

	public MainViewController(MainView view, String user_id) {
		super();
		this.user_id = user_id;
		this.view=view;
		

		
		Map<String, Object> params=new HashMap<String, Object>();
		
		params.put("email", user_id);

		view.getLblUser_id().setText(UserModel.getUser(params).getName()+" / "+user_id);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
			case "users":
				view.switchMainContent(MainView.USER_VIEW);
				break;
			case "rooms":
				view.switchMainContent(MainView.ROOM_VIEW);
				break;
			case "bookings":
				view.switchMainContent(MainView.BOOKING_VIEW);
				break;
			case "log_out":
				System.exit(0);
				break;
			case "chat":
				if (!isChatEnabled) {
					chat_view=new ChatView();
					isChatEnabled=true;
				} else {
					chat_view.setVisible(true);
				}
				break;
			default:
				System.err.println("Option not recognized");
		}
		
	}

	/**
	 * @return the user_id
	 */
	public String getUser_id() {
		return user_id;
	}

	/**
	 * @param user_id the user_id to set
	 */
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	
	
	
}
