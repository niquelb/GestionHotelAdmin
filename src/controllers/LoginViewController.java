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
	
	private final URL url=new URL("http://localhost/gestionhotelera/sw_user.php");
	private HttpURLConnection conn;
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
					conn.disconnect();
					new MainView(user_id);
					
				} else {
					view.getLblError().setVisible(true);
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				conn.disconnect();
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
		
		conn = (HttpURLConnection) url.openConnection();
		
		conn.setRequestMethod("POST");
		
		Map<String, String> parameters = new HashMap<>();
		parameters.put("action", "login");
		parameters.put("user", "{\"email\":\""+user_id+"\", \"password\":\""+password+"\"}");
		
		conn.setDoOutput(true);
		DataOutputStream out = new DataOutputStream(conn.getOutputStream());
		out.writeBytes(ParameterStringBuilder.getParamsString(parameters));
		out.flush();
		out.close();

		conn.setConnectTimeout(5000);
		conn.setReadTimeout(5000);

		conn_status = conn.getResponseCode();
		
		BufferedReader in = new BufferedReader(
				  new InputStreamReader(conn.getInputStream()));
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

				if (object.get("success").toString().equals("true")) 
					return true;
				else 
					return false;
				
			  } catch (ParseException e) {
				System.out.println(e.getMessage());
				conn.disconnect();
				return false;
			  }
		} else {
			System.out.println("Error en la conexion: "+conn_status);
			conn.disconnect();
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

	
