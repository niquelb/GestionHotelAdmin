package controllers;

import models.UserModel;
import views.MainView;

public class MainViewController {
	
	private final MainView view;
	
	private String user_id;

	public MainViewController(MainView view, String user_id) {
		super();
		this.user_id = user_id;
		this.view=view;
		
		
		
		view.getLblUser_id().setText(UserModel.getUser(user_id).getName()+" / "+user_id);
		
	}
	
}
