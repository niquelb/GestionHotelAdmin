import utils.BDConnector;
import views.LoginView;

public class Main {

	public static void main(String[] args) {
		new LoginView();
		BDConnector.connectToBD();
	}

}
