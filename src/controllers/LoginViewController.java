package controllers;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import utils.ParameterStringBuilder;
import views.LoginView;

/**
 * Controller class for the login
 * 
 * @author Nico
 *
 */
public class LoginViewController implements ActionListener{
	
	private LoginView login_view;
	private String user_id, password;
	
	private final URL URL;
	private final HttpURLConnection CONNECTION;
	private int conn_status;
	private JSONParser parser = new JSONParser();
	
	/**
	 * Constructor, initializes the view, the URL for the connection
	 * and the connection itself
	 * 
	 * @param login_view View class of the login
	 * @throws IOException 
	 */
	public LoginViewController(LoginView login_view) throws IOException {
		super();
		this.login_view = login_view;
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
			user_id = login_view.getuID_field().getText();
			password = String.valueOf(login_view.getPasswd_field().getPassword());

			try {
				if(login(user_id,password)) {
					login_view.getMsg_label().setForeground(Color.green);
					login_view.getMsg_label().setText("Login exitoso.");
					login_view.disposeWindow();
					
				} else {
					login_view.getMsg_label().setForeground(Color.red);
					login_view.getMsg_label().setText("Datos incorrectos.");
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
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
	
}

	
