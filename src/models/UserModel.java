package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.eclipse.jdt.annotation.NonNull;
import org.mariadb.jdbc.Connection;

import at.favre.lib.crypto.bcrypt.BCrypt;
import main.Main;
import utils.BDConnector;
import utils.UserDataChecker;

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
	public static int ROWS_PER_PAGE=10;
	
	/**
	 * Connection object
	 */
	private static Connection conn=Main.conn;
	
	/**
	 * ResultSet with all of the users
	 */
	private static ResultSet rs=BDConnector.execStmt("SELECT * FROM users;", conn);
	

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
	public static ArrayList<UserModel> getUserList(String email, String name, int page_num) {
		ArrayList<UserModel> al=new ArrayList<>();
		page_num=page_num*10+1;
		
		name=(name==null)?"":name;
		email=(email==null)?"":email;
		
		int rows_per_page=(getTotalRows()<ROWS_PER_PAGE)?getTotalRows():ROWS_PER_PAGE;
		
		try {
			rs.absolute(page_num);
			if (email.equals("") && name.equals("")) {
				for (int i = 0; i < rows_per_page; i++) {
					al.add(new UserModel(rs.getString("email"), rs.getString("nombre"),
							rs.getString("apellidos"), rs.getString("telefono")));
					
					rs.next();
				}
				
				return al;
			
			} else if (!email.equals("")) {
				for (int i = 0; i < rows_per_page; i++) {
					if (rs.getString("email").toUpperCase().contains(email.toUpperCase()))
						al.add(new UserModel(rs.getString("email"), rs.getString("nombre"),
								rs.getString("apellidos"), rs.getString("telefono")));
					
					rs.next();
				}
				return al;
			
			} else {
				for (int i = 0; i < rows_per_page; i++) {
					if (rs.getString("nombre")!=null && rs.getString("nombre").toUpperCase().contains(name.toUpperCase()))
						al.add(new UserModel(rs.getString("email"), rs.getString("nombre"),
								rs.getString("apellidos"), rs.getString("telefono")));
					rs.next();
				}
				return al;
			
			}
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
	public static UserModel getUser(String user_id) {
		try {
		if (user_id==null || user_id.equals("")) {
			rs.first();
			return new UserModel(rs.getString("email"),rs.getString("password") , rs.getString("nombre"), rs.getString("apellidos"), rs.getString("telefono"));
		}
		
		rs.beforeFirst();
		while (rs.next()) {
			if (rs.getString("email").toUpperCase().equals(user_id.toUpperCase())) {
				return new UserModel(rs.getString("email"),rs.getString("password"), rs.getString("nombre"), rs.getString("apellidos"), rs.getString("telefono"));
			} 
		}
		rs.first();
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
	public static void createUser(UserModel new_user) {
		String email=new_user.getEmail();
		
		if (email==null||email.equals("")) {
			throw new IllegalArgumentException("Email cannot be null");
		}
		
		String name=(new_user.getName()!=null)?new_user.getName():"";
		String last_names=(new_user.getLast_names()!=null)?new_user.getLast_names():"";
		String phone=(new_user.getPhone_num()!=null)?new_user.getPhone_num():"";
		String password=(new_user.getPassword()!=null)?new_user.getPassword():"";
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
			
//			rs=BDConnector.execStmt("SELECT * FROM users;", conn);
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
	public static void updateUser(UserModel new_user) {
		String email=new_user.getEmail();
		String name=(new_user.getName()!=null)?new_user.getName():"";
		String last_names=(new_user.getLast_names()!=null)?new_user.getLast_names():"";
		String phone=(new_user.getPhone_num()!=null)?new_user.getPhone_num():"";
		String password=new_user.getPassword();

		if (password==null || (!UserDataChecker.validatePassword(password))) {
			
			try {
				rs.beforeFirst();
				while (rs.next()) {
					if (rs.getString("email").equals(email)) {
						rs.updateString("nombre", name);
						rs.updateString("apellidos", last_names);
						rs.updateString("telefono", phone);
						
						rs.updateRow();
						
						System.out.println("Updated without password");
						
						break;
					}
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else {
			String password_hashed=BCrypt.withDefaults().hashToString(12, password.toCharArray());
			
			try {
				rs.first();
				while (rs.next()) {
					if (rs.getString("email").equals(email)) {
						rs.updateString("nombre", name);
						rs.updateString("apellidos", last_names);
						rs.updateString("telefono", phone);
						rs.updateString("password", password_hashed);
						rs.updateRow();
						rs.first();
						
						System.out.println("Updated");
						
						break;
					}
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
	/**
	 * Method for getting the total number
	 * of rows in the ResultSet
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
