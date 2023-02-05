package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.mariadb.jdbc.Connection;

import at.favre.lib.crypto.bcrypt.BCrypt;
import utils.BDConnector;

/**
 * Model class for the users table
 * 
 * @author Nico
 *
 */
public class UserModel {
	private String email,
		password,
		token,
		token_valid_date,
		name,
		last_names,
		phone_num;

	/**
	 * Default constructor
	 * @param email user's email
	 * @param password user's password
	 * @param token user's token
	 * @param token_valid_date token's valid date
	 * @param name user's name
	 * @param last_names user's last names
	 * @param phone_num user's phone number
	 */
	public UserModel(String email, String password, String token, String token_valid_date, String name,
			String last_names, String phone_num) {
		super();
		this.email = email;
		this.password = password;
		this.token = token;
		this.token_valid_date = token_valid_date;
		this.name = name;
		this.last_names = last_names;
		this.phone_num = phone_num;
	}
	
	/**
	 * Constructor without password and token fields
	 * @param email user's email
	 * @param name user's name
	 * @param last_names user's last names
	 * @param phone_num user's phone number
	 */
	public UserModel(String email, String name, String last_names, String phone_num) {
		super();
		this.email = email;
		this.name = name;
		this.last_names = last_names;
		this.phone_num = phone_num;
	}


	/**
	 * Method for getting an ArrayList with all users
	 * @param conn SQL Connection object
	 * @param params "WHERE" parameters for filtering results
	 * @return ArrayList with all users
	 */
	public static ArrayList<UserModel> getUserList(Connection conn, String params) {
		String stmt=null;
		if (params!=null && !params.equals("")) 
			stmt="SELECT * FROM users WHERE "+params+";";
		else 
			stmt="SELECT * FROM users;";
		
		ResultSet rs=BDConnector.execStmt(stmt, conn);
		ArrayList<UserModel> al=new ArrayList<>();
		
		try {
			while (rs.next()) {
				al.add(new UserModel(rs.getString("email"), rs.getString("nombre"),
						rs.getString("apellidos"), rs.getString("telefono")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return al;
	}
	
	/**
	 * Method for getting 1 UserModel object for the DB, if
	 * there are multiple results from the query, only the
	 * first one is returned
	 * @param conn SQL Connection object
	 * @param params "WHERE" parameters for filtering results
	 * @return UserModel object
	 */
	public static UserModel getUser(Connection conn, String params) {
		String stmt=null;
		if (params==null || params.equals("")) {
			stmt="SELECT * FROM users LIMIT 1;";
		} else {
			stmt="SELECT * FROM users WHERE "+params+"LIMIT 1;";
		}
		ResultSet rs=BDConnector.execStmt(stmt, conn);
		try {
			if(rs.next()) 
				return new UserModel(rs.getString("email"), rs.getString("nombre"), rs.getString("apellidos"), rs.getString("telefono"));
			else return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	public static void updateUser(String u_id, UserModel new_user) {
		String email=(new_user.getEmail()!=null)?new_user.getEmail():"";
		String name=(new_user.getName()!=null)?new_user.getName():"";
		String last_names=(new_user.getLast_names()!=null)?new_user.getLast_names():"";
		String phone=(new_user.getPhone_num()!=null)?new_user.getPhone_num():"";
		String password=(new_user.getPassword()!=null)?new_user.getPassword():"";
		String password_hashed=BCrypt.withDefaults().hashToString(12, password.toCharArray());
		
		
		
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @return the token_valid_date
	 */
	public String getToken_valid_date() {
		return token_valid_date;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the last_names
	 */
	public String getLast_names() {
		return last_names;
	}

	/**
	 * @return the phone_num
	 */
	public String getPhone_num() {
		return phone_num;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * @param token_valid_date the token_valid_date to set
	 */
	public void setToken_valid_date(String token_valid_date) {
		this.token_valid_date = token_valid_date;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param last_names the last_names to set
	 */
	public void setLast_names(String last_names) {
		this.last_names = last_names;
	}

	/**
	 * @param phone_num the phone_num to set
	 */
	public void setPhone_num(String phone_num) {
		this.phone_num = phone_num;
	}
	
	
	
}