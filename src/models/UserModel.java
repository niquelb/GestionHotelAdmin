package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;
import org.mariadb.jdbc.Connection;

import main.Main;
import utils.BDConnector;
import utils.UserDataChecker;
import utils.ResultSetGen;

/**
 * Model class for the users table
 * 
 * @author Nico
 *
 */
public class UserModel {
	private String  email,
	password,
	token,
	token_valid_date,
	name,
	last_names,
	phone_num;

	/**
	 * Number of rows for each page
	 */
	public final static int ROWS_PER_PAGE=10;

	/**
	 * DB Table name
	 */
	public final static String TABLE_NAME="users";

	/**
	 * ResultSet with all of the users
	 */
	private static ResultSet rs=ResultSetGen.generateResultSet(null, TABLE_NAME);


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
	public UserModel(@NonNull String email, String password, String token, String token_valid_date, String name,
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
	public UserModel(@NonNull String email, String name, String last_names, String phone_num) {
		super();
		this.email = email;
		this.name = name;
		this.last_names = last_names;
		this.phone_num = phone_num;
	}

	/**
	 * Constructor with password but without
	 * token fields
	 * @param email user's email
	 * @param password user's password
	 * @param name user's name
	 * @param last_names user's last names
	 * @param phone_num user's phone number
	 */
	public UserModel(@NonNull String email, String password, String name,
			String last_names, String phone_num) {
		super();
		this.email = email;
		this.password = password;
		this.name = name;
		this.last_names = last_names;
		this.phone_num = phone_num;
	}


	/**
	 * Method for getting an ArrayList with all users or with
	 * specified filters
	 * 
	 * @param email email field for filtering
	 * @param name name field for filtering
	 * @param page_num page_number ! MUST START FROM 0 ! (First page is number 0, second number 1 etc.)
	 * @return ArrayList with UserModel objects, null if error
	 */
	public static ArrayList<UserModel> getUserList(Map<String, Object> params, int page_num) {
		ArrayList<UserModel> al=new ArrayList<>();
		page_num=page_num*ROWS_PER_PAGE;

		params = params==null
				? new HashMap<String, Object>()
						: params;
		try {

			if (params.isEmpty()) {
				rs.absolute(page_num);

				int i = 0;

				while (rs.next() && i < ROWS_PER_PAGE) {
					al.add(new UserModel(rs.getString("email"), rs.getString("nombre"),
							rs.getString("apellidos"), rs.getString("telefono")));
					i++;
				}

				return al;
			} 

			ResultSet rs=ResultSetGen.generateResultSet(params,TABLE_NAME);

			int i=0;

			while (rs.next() && i < ROWS_PER_PAGE) {
				al.add(new UserModel(rs.getString("email"), rs.getString("nombre"),
						rs.getString("apellidos"), rs.getString("telefono")));
				i++;
			}

			return al;


		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Method for getting 1 UserModel object from the ResultSet
	 * based on the user_id given, If it is null or empty, the
	 * first user will be returned
	 * @param user_id user's email
	 * @return UserModel object, null if error
	 */
	public static UserModel getUser(Map<String, Object> params) {

		params = params==null
				? new HashMap<String, Object>()
				: params;
		
		try {
			
			if (params.isEmpty()) {
				rs.first();
				return new UserModel(rs.getString("email"),rs.getString("password") , rs.getString("nombre"), rs.getString("apellidos"), rs.getString("telefono"));
			}

			ResultSet rs=ResultSetGen.generateResultSet(params,TABLE_NAME);

			if (rs.first()) {
				return new UserModel(rs.getString("email"),rs.getString("password") , rs.getString("nombre"), rs.getString("apellidos"), rs.getString("telefono"));
			}

			return null;

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Method for creating users through the
	 * ResultSet with a UserModel Object
	 * !! OBJECT CANNOT HAVE NULL EMAIL FIELD !!
	 * 
	 * @param new_user UserModel object
	 */
	public void createUser() {
		String email=this.getEmail();

		if (email==null||email.equals("")) {
			throw new IllegalArgumentException("Email cannot be null");
		}

		String name=(this.getName()!=null)
				? this.getName()
				: "";
		String last_names=(this.getLast_names()!=null)
				? this.getLast_names()
				: "";
		String phone=(this.getPhone_num()!=null)
				? this.getPhone_num()
				: "";
		String password=(this.getPassword()!=null)
				? this.getPassword()
				: "";
		//TODO Implement BCrypt
		//		String password_hashed=BCrypt.withDefaults().hashToString(12, password.toCharArray());

		try {
			rs.moveToInsertRow();

			rs.updateString("email", email);
			rs.updateString("nombre", name);
			rs.updateString("apellidos", last_names);
			rs.updateString("telefono", phone);
			rs.updateString("password", password);

			rs.insertRow();
			
			refreshResultSet();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	/**
	 * Method for updating existing users with a given
	 * UserModel object
	 * @param new_user UserModel object to be used for the updating process
	 */
	public void updateUser() {
		String email=(this.getEmail().equals(""))
				? null
				: this.getEmail();
		if (email==null) {
			throw new IllegalArgumentException("Name cannot be null");
		}
		
		String name=(this.getName()!=null)
				? this.getName()
				: "";
		String last_names=(this.getLast_names()!=null)
				? this.getLast_names()
				: "";
		String phone=(this.getPhone_num()!=null)
				? this.getPhone_num()
				: "";
		String password=this.getPassword();


		Map<String, String> update_fields=new HashMap<String, String>();

		update_fields.put("email", email);
		update_fields.put("nombre", name);
		update_fields.put("apellidos", last_names);
		update_fields.put("telefono", phone);
		if (password==null || (!UserDataChecker.validatePassword(password)))
			update_fields.put("password", password);

		Map<String, Object> params=new HashMap<String, Object>();

		params.put("email", email);

		try {
			ResultSet rs=ResultSetGen.generateResultSet(params, TABLE_NAME);
			if (rs.first()) {
				for (Map.Entry<String, String> entry : update_fields.entrySet()) {
					rs.updateString(entry.getKey(), entry.getValue());
				}

				rs.updateRow();

				refreshResultSet();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Method for getting the total number of rows in the ResultSet
	 * @return number of rows, -1 if error
	 */
	public static int getTotalRows() {
		try {
			rs.last();
			return rs.getRow();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return -1;
		}
	}

	/**
	 * Method for refreshing the Result Set
	 */
	public static void refreshResultSet() {
		rs=ResultSetGen.generateResultSet(null, TABLE_NAME);
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

	@Override
	public String toString() {
		return "UserModel [email=" + email + ", password=" + password + ", token=" + token + ", token_valid_date="
				+ token_valid_date + ", name=" + name + ", last_names=" + last_names + ", phone_num=" + phone_num + "]";
	}



}
