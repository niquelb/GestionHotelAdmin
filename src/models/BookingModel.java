package models;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.mariadb.jdbc.Connection;

import main.Main;
import utils.BDConnector;
import utils.ResultSetGen;

/**
 * Model class for the Bookings table
 * 
 * @author Nico
 *
 */
public class BookingModel {
	private int id,
		num_adults,
		num_children;
	private Timestamp date;
	private Date date_start, 
		date_end;
	private String user_id;
	
	/**
	 * Number of rows for each page
	 */
	public final static int ROWS_PER_PAGE=10;
	
	/**
	 * DB Table name
	 */
	public final static String TABLE_NAME="reservas";

	/**
	 * ResultSet with all of the users
	 */
	private static ResultSet rs=ResultSetGen.generateResultSet(null, TABLE_NAME);
	
	/**
	 * Default constructor
	 * @param id Booking ID
	 * @param num_adults Number of adults
	 * @param num_children Number of children
	 * @param date Creation date
	 * @param date_start Booking start date
	 * @param date_end Booking end date
	 * @param user_id User ID
	 */
	public BookingModel(int id, int num_adults, int num_children, Timestamp date, Date date_start,
			Date date_end, String user_id) {
		super();
		this.id = id;
		this.num_adults = num_adults;
		this.num_children = num_children;
		this.date = date;
		this.date_start = date_start;
		this.date_end = date_end;
		this.user_id = user_id;
	}

	/**
	 * Constructor without ID and date field
	 * (Auto generated)
	 * @param num_adults Number of adults
	 * @param num_children Number of children
	 * @param date_start Booking start date
	 * @param date_end Booking end date
	 * @param user_id User ID
	 */
	public BookingModel(int num_adults, int num_children, Date date_start, Date date_end,
			String user_id) {
		super();
		this.num_adults = num_adults;
		this.num_children = num_children;
		this.date_start = date_start;
		this.date_end = date_end;
		this.user_id = user_id;
	}
	
	/**
	 * Constructor and date field
	 * (Auto generated)
	 * @param id ID
	 * @param num_adults Number of adults
	 * @param num_children Number of children
	 * @param date_start Booking start date
	 * @param date_end Booking end date
	 * @param user_id User ID
	 */
	public BookingModel(int id, int num_adults, int num_children, Date date_start, Date date_end, String user_id) {
		super();
		this.id = id;
		this.num_adults = num_adults;
		this.num_children = num_children;
		this.date_start = date_start;
		this.date_end = date_end;
		this.user_id = user_id;
	}

	/**
	 * Method for getting an array list with all bookings or using specified parameters
	 * 
	 * @param params Map object with the column-name and the value for filtering
	 * @param page_num Page number
	 * @return ArrayList with BookingModel object, null if error
	 */
	public static ArrayList<BookingModel> getBookingList(Map<String, Object> params, int page_num) {
		ArrayList<BookingModel> al=new ArrayList<>();
		page_num=page_num*ROWS_PER_PAGE;
		
		params = params==null
				? new HashMap<String, Object>()
				: params;
		
		try {
			if (params.isEmpty()) {
				rs.absolute(page_num);

				int i = 0;

				while (rs.next() && i < ROWS_PER_PAGE) {
					al.add(new BookingModel(rs.getInt("id"), rs.getInt("numero_adultos"), rs.getInt("numero_ninyos"), rs.getTimestamp("fecha"),
							rs.getDate("fecha_entrada"), rs.getDate("fecha_salida"),
							rs.getString("user_id")));
					i++;
				}

				return al;
			} 
			
			ResultSet rs=ResultSetGen.generateResultSet(params,TABLE_NAME); 
			
			//TODO implement date ranges and check end date

			int i=0;

			while (rs.next() && i < ROWS_PER_PAGE) {
				al.add(new BookingModel(rs.getInt("id"), rs.getInt("numero_adultos"), rs.getInt("numero_ninyos"), rs.getTimestamp("fecha"),
						rs.getDate("fecha_entrada"), rs.getDate("fecha_salida"),
						rs.getString("user_id")));
				i++;
			}

			return al;

			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * Method for getting 1 BookingModel object with specified parameters, if none are given, the first result will be given
	 * 
	 * @param params Map object with the column-name and the value for filtering
	 * @return BookingModel object, null if error
	 */
	public static BookingModel getBooking(Map<String, Object> params) {

		params = params==null
				? new HashMap<String, Object>()
				: params;
		
		try {
			
			if (params.isEmpty()) {
				rs.first();
				return new BookingModel(rs.getInt("id"), rs.getInt("numero_adultos"), rs.getInt("numero_ninyos"), rs.getTimestamp("fecha"),
						rs.getDate("fecha_entrada"), rs.getDate("fecha_salida"),
						rs.getString("user_id"));
			
			}

			ResultSet rs=ResultSetGen.generateResultSet(params,TABLE_NAME);

			if (rs.first()) {
				return new BookingModel(rs.getInt("id"), rs.getInt("numero_adultos"), rs.getInt("numero_ninyos"), rs.getTimestamp("fecha"),
						rs.getDate("fecha_entrada"), rs.getDate("fecha_salida"),
						rs.getString("user_id"));
			
			}

			return null;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Method for creating bookings through the ResultSet
	 * 
	 * @throws Exception if end_date ISN'T after start_date
	 */
	public int createBooking() throws Exception {
		Date start_date=this.getDate_start();
		Date end_date=this.getDate_end();
		int num_adults=(this.getNum_adults()<=0)
				? 1
				: this.getNum_adults();
		int num_children=this.getNum_children();
		String user_id=this.getUser_id();
		
		if (!end_date.after(start_date)) {
			throw new Exception("End date is not after start date.");
		}
		
		try {
			rs.moveToInsertRow();
			
			rs.updateDate("fecha_entrada", start_date);
			rs.updateDate("fecha_salida", end_date);
			rs.updateInt("numero_adultos", num_adults);
			rs.updateInt("numero_ninyos", num_children);
			rs.updateString("user_id", user_id);
			
			rs.insertRow();
			
			rs.last();
			
			return rs.getInt("id");
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return -1;
		}
		
	}
	
	/**
	 * Method for updating a booking through the ResultSet
	 * 
	 * @throws Exception if end_date ISN'T after start_date or if ID isn't valid (less than 0)
	 */
	public void updateBooking() throws Exception {
		int id=this.getId();
		if (id<=0) {
			throw new IllegalArgumentException("ID not valid");
		}
		
		Date start_date=this.getDate_start();
		Date end_date=this.getDate_end();
		int num_adults=(this.getNum_adults()<=0)
				? 1
				: this.getNum_adults();
		int num_children=this.getNum_children();
		String user_id=this.getUser_id();
		
		if (!end_date.after(start_date)) {
			throw new Exception("End date is not after start date.");
		}
		
		Map<String, Object> update_fields=new HashMap<String, Object>();
		update_fields.put("fecha_entrada", start_date);
		update_fields.put("fecha_salida", end_date);
		update_fields.put("numero_adultos", num_adults);
		update_fields.put("numero_ninyos", num_children);
		update_fields.put("user_id", user_id);
		
		Map<String, Object> params=new HashMap<String, Object>();

		params.put("id", id);
		
		try {
			ResultSet rs=ResultSetGen.generateResultSet(params, TABLE_NAME);
			if (rs.first()) {
				for (Map.Entry<String, Object> entry : update_fields.entrySet()) {
					if (entry.getValue() instanceof String) {
						rs.updateString(entry.getKey(), (String) entry.getValue());
						break;
					}
					if (entry.getValue() instanceof Integer) {
						rs.updateInt(entry.getKey(), (Integer) entry.getValue());
						break;
					}
					if (entry.getValue() instanceof Date) {
						rs.updateDate(entry.getKey(), (Date) entry.getValue());
						break;
					}
				}

				rs.updateRow();

				refreshResultSet();
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void deleteBooking() {
		int id=this.getId();
		if (id<=0) {
			throw new IllegalArgumentException("ID not valid");
		}
		
		Map<String, Object> params=new HashMap<String, Object>();

		params.put("id", id);
		
		try {
			ResultSet rs=ResultSetGen.generateResultSet(params, TABLE_NAME);
			if (rs.first()) {
				rs.deleteRow();

				refreshResultSet();
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	/**
	 * Method for getting the total number of rows in the ResultSet
	 * 
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
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the num_adults
	 */
	public int getNum_adults() {
		return num_adults;
	}

	/**
	 * @return the num_children
	 */
	public int getNum_children() {
		return num_children;
	}

	/**
	 * @return the date
	 */
	public Timestamp getDate() {
		return date;
	}

	/**
	 * @return the date_start
	 */
	public Date getDate_start() {
		return date_start;
	}

	/**
	 * @return the date_end
	 */
	public Date getDate_end() {
		return date_end;
	}

	/**
	 * @return the user_id
	 */
	public String getUser_id() {
		return user_id;
	}

	
}
