package controllers;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JLabel;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import utils.ParameterStringBuilder;
import views.LoginView;
import views.MainView;

/**
 * Controller class for the login
 * 
 * @author Nico
 *
 */
public class LoginViewController implements ActionListener, MouseListener{
	
	private LoginView view;
	private String user_id, password;
	
	private final URL URL;
	private final HttpURLConnection CONNECTION;
	private int conn_status;
	private JSONParser parser = new JSONParser();
	
	/**
	 * Constructor, initializes the view, the URL for the connection
	 * and the connection itself
	 * 
	 * @param view View class of the login
	 * @throws IOException 
	 */
	public LoginViewController(LoginView view) throws IOException {
		super();
		this.view = view;
		URL=new URL("http://localhost/gestionhotelera/sw_user.php");
		CONNECTION = (HttpURLConnection) URL.openConnection();
	}

	/**
	 * Action listener
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "login":
			user_id = view.getTextFieldEmail().getText();
			password = String.valueOf(view.getPasswordField().getPassword());

			try {
				if(login(user_id,password)) {
					view.dispose();
					new MainView(user_id);
					
				} else {
					view.getLblError().setVisible(true);
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				CONNECTION.disconnect();
			}
				
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + e.getActionCommand());
		}
	}
	
	/**
	 * Login method
	 * @param user_id ID of the user
	 * @param password Password of the user
	 * @return true if login successful, false if not
	 * @throws IOException 
	 */
	public boolean login(String user_id, String password) throws IOException {
		
		CONNECTION.setRequestMethod("POST");
		
		
		//TODO Implement BCrypt
//		String password_hashed=BCrypt.withDefaults().hashToString(12, password.toCharArray());
//		String password_hashed="$2a$12$tlOgATQKDOjgh.OLtfhZP.4K7bADlGC78CdFANhEQYHVADJhRHJve";

		
//		System.out.println(BCrypt.verifyer().verify(password.toCharArray(), "$2a$12$tlOgATQKDOjgh.OLtfhZP.4K7bADlGC78CdFANhEQYHVADJhRHJve").verified);

		Map<String, String> parameters = new HashMap<>();
		parameters.put("action", "login");
		parameters.put("user", "{\"email\":\""+user_id+"\", \"password\":\""+password+"\"}");

		CONNECTION.setDoOutput(true);
		DataOutputStream out = new DataOutputStream(CONNECTION.getOutputStream());
		out.writeBytes(ParameterStringBuilder.getParamsString(parameters));
		out.flush();
		out.close();

		CONNECTION.setConnectTimeout(5000);
		CONNECTION.setReadTimeout(5000);

		conn_status = CONNECTION.getResponseCode();
		
		BufferedReader in = new BufferedReader(
				  new InputStreamReader(CONNECTION.getInputStream()));
		String input_line;
		StringBuffer content = new StringBuffer();
		while ((input_line = in.readLine()) != null) {
		    content.append(input_line);
		}
		in.close();

		if (conn_status==HttpURLConnection.HTTP_OK) {
			//TODO fix "connection in progress" exception when pressing the login button 2 times
			try {
				// Parse JSON string using JSON parser.
				JSONObject object = (JSONObject) parser.parse(content.toString());

				if (object.get("success").toString().equals("true")) {
					System.out.println(object.get("msg"));
					CONNECTION.disconnect();
					return true;
				} else {
					System.out.println(object.get("msg"));
					CONNECTION.disconnect();
					return false;
				}
				
			  } catch (ParseException e) {
				System.out.println(e.getMessage());
				CONNECTION.disconnect();
				return false;
			  }
		} else {
			System.out.println("Error en la conexion: "+conn_status);
			CONNECTION.disconnect();
			return false;
		}

		
		
	}
	
	/**
	 * Sign in method
	 */
	public void register() {
		//TODO implement this method
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource().getClass()==JLabel.class) {			
			view.dispose();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		view.setCursor(Cursor.HAND_CURSOR);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		view.setCursor(Cursor.DEFAULT_CURSOR);
	}
	
}

	
