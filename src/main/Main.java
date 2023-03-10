package main;
import org.mariadb.jdbc.Connection;

import models.UserModel;
import utils.BDConnector;
import views.BookingRoomCreatorView;
import views.LoginView;
import views.MainView;

public class Main {
	public static final Connection conn=BDConnector.connectToBD();

	public static void main(String[] args) {
//		new LoginView();
		new MainView("admin@elcampico.org");
	}
}
