package models;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.mariadb.jdbc.Connection;

import main.Main;
import utils.BDConnector;

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
	 * Connection object
	 */
	private static Connection conn=Main.conn;
	
	/**
	 * ResultSet with all of the rooms
	 */
	private static ResultSet rs=BDConnector.execStmt("SELECT * FROM reservas;", conn);
	
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
	 * Method for getting an ArrayList with all bookings or with
	 * specified filters
	 * 
	 * @param user_id User ID
	 * @param start_date Earliest date to be filtered by
	 * @param end_date Latest date to be filtered by, if combined with start_date creates a date interval
	 * @param page_num page_number ! MUST START FROM 0 ! (First page is number 0, second number 1 etc.)
	 * @return ArrayList with BookingModel objects, null if error
	 * @throws Exception if end_date ISN'T after start_date
	 */
	public static ArrayList<BookingModel> getBookingList(String user_id, Date start_date, Date end_date, int page_num) throws Exception {
		ArrayList<BookingModel> al=new ArrayList<>();
		page_num=page_num*ROWS_PER_PAGE+1;
		
		int rows_per_page=(getTotalRows()<ROWS_PER_PAGE)?getTotalRows():ROWS_PER_PAGE;
		
		user_id=(user_id==null)?"":user_id;
		
		try {
			rs.absolute(page_num);
			if (user_id.equals("") && start_date==null && end_date==null) {
				for (int i = 0; i < rows_per_page; i++) {
					al.add(new BookingModel(rs.getInt("id"), rs.getInt("numero_adultos"), rs.getInt("numero_ninyos"), rs.getTimestamp("fecha"),
							rs.getDate("fecha_entrada"), rs.getDate("fecha_salida"),
							rs.getString("user_id")));
					
					rs.next();
				}
				return al;
			} if (!user_id.equals("")) {
				for (int i = 0; i < rows_per_page; i++) {
					if (rs.getString("user_id").equals(user_id)) {
						al.add(new BookingModel(rs.getInt("id"), rs.getInt("numero_adultos"), rs.getInt("numero_ninyos"), rs.getTimestamp("fecha"),
								rs.getDate("fecha_entrada"), rs.getDate("fecha_salida"),
								rs.getString("user_id")));
					}
					
					rs.next();
				}
				return al;
			} if (start_date!=null) {
				if (end_date!=null) {
					if (!end_date.after(start_date)) {
						throw new Exception("End date is not after start date.");
					}
					for (int i = 0; i < rows_per_page; i++) {
						if ((rs.getDate("fecha_entrada").after(start_date) || rs.getDate("fecha_entrada").equals(start_date)) &&
								(rs.getDate("fecha_salida").before(end_date) || rs.getDate("fecha_salida").equals(end_date))) {
							al.add(new BookingModel(rs.getInt("id"), rs.getInt("numero_adultos"), rs.getInt("numero_ninyos"), rs.getTimestamp("fecha"),
									rs.getDate("fecha_entrada"), rs.getDate("fecha_salida"),
									rs.getString("user_id")));
						}
						
						rs.next();
					}
					return al;
				}
				
				for (int i = 0; i < rows_per_page; i++) {
					if (rs.getDate("fecha_entrada").after(start_date) || rs.getDate("fecha_entrada").equals(start_date)) {
						al.add(new BookingModel(rs.getInt("id"), rs.getInt("numero_adultos"), rs.getInt("numero_ninyos"), rs.getTimestamp("fecha"),
								rs.getDate("fecha_entrada"), rs.getDate("fecha_salida"),
								rs.getString("user_id")));
					}
					
					rs.next();
				}
				return al;
			} if (end_date!=null) {
				for (int i = 0; i < rows_per_page; i++) {
					if (rs.getDate("fecha_salida").before(end_date) || rs.getDate("fecha_salida").equals(end_date)) {
						al.add(new BookingModel(rs.getInt("id"), rs.getInt("numero_adultos"), rs.getInt("numero_ninyos"), rs.getTimestamp("fecha"),
								rs.getDate("fecha_entrada"), rs.getDate("fecha_salida"),
								rs.getString("user_id")));
					}
					
					rs.next();
				}
				return al;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return null;
		
	}
	
	/**
	 * Method for getting 1 BookingModel object from the
	 * ResultSet based on the given ID, if it is -1 (or any
	 * other negative number), the first booking will be
	 * returned
	 * 
	 * @param booking_id Booking ID for filtering
	 * @return BookingModel object, null if error
	 */
	public static BookingModel getBooking(int booking_id) {
		try {
			if (booking_id<=0) {
				rs.first();
				return new BookingModel(rs.getInt("id"), rs.getInt("numero_adultos"), rs.getInt("numero_ninyos"), rs.getTimestamp("fecha"),
						rs.getDate("fecha_entrada"), rs.getDate("fecha_salida"),
						rs.getString("user_id"));
			}
			
			rs.beforeFirst();
			while (rs.next()) {
				if (rs.getInt("id")==booking_id) {
					return new BookingModel(rs.getInt("id"), rs.getInt("numero_adultos"), rs.getInt("numero_ninyos"), rs.getTimestamp("fecha"),
							rs.getDate("fecha_entrada"), rs.getDate("fecha_salida"),
							rs.getString("user_id"));
				}
			}
			
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Method for getting 1 BookingModel object from the
	 * ResultSet based on the given User ID and start_date
	 * 
	 * @param user_id User ID for filtering
	 * @param start_date Start date for filtering
	 * @return BookingModel object, null if error or none found with filters
	 */
	public static BookingModel getBooking(String user_id, Date start_date) {
		
		user_id=(user_id=="")?null:user_id;
		
		try {
			if (user_id==null) {
				rs.first();
				return new BookingModel(rs.getInt("id"), rs.getInt("numero_adultos"), rs.getInt("numero_ninyos"), rs.getTimestamp("fecha"),
						rs.getDate("fecha_entrada"), rs.getDate("fecha_salida"),
						rs.getString("user_id"));
			}
			
			if (start_date==null) {
				rs.beforeFirst();
				while (rs.next()) {
					if (rs.getString("user_id").equals(user_id)) {
						return new BookingModel(rs.getInt("id"), rs.getInt("numero_adultos"), rs.getInt("numero_ninyos"), rs.getTimestamp("fecha"),
								rs.getDate("fecha_entrada"), rs.getDate("fecha_salida"),
								rs.getString("user_id"));
					}
					break;
				}
			}
			
			rs.beforeFirst();
			while (rs.next()) {
				if (rs.getString("user_id").equals(user_id) && rs.getDate("fecha_entrada").equals(start_date)) {
					return new BookingModel(rs.getInt("id"), rs.getInt("numero_adultos"), rs.getInt("numero_ninyos"), rs.getTimestamp("fecha"),
							rs.getDate("fecha_entrada"), rs.getDate("fecha_salida"),
							rs.getString("user_id"));
				}
			}
			
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Method for creating bookings through the
	 * ResultSet using a given BookingModel object
	 * 
	 * @param new_booking BookingModel object to be used
	 * @throws Exception if end_date ISN'T after start_date
	 */
	public static void createBooking(BookingModel new_booking) throws Exception {
		Date start_date=new_booking.getDate_start();
		Date end_date=new_booking.getDate_end();
		int num_adults=(new_booking.getNum_adults()<=0)?1:new_booking.getNum_adults();
		int num_children=new_booking.getNum_children();
		String user_id=new_booking.getUser_id();
		
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
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Method for updating a booking through the
	 * ResultSet using a given BookingModel object
	 * 
	 * @param new_booking BookingModel object to be used
	 * @throws Exception if end_date ISN'T after start_date or if ID isn't valid (less than 0)
	 */
	public static void updateBooking(BookingModel new_booking) throws Exception {
		int id=new_booking.getId();
		if (id<=0) {
			throw new Exception("ID not valid");
		}
		
		Date start_date=new_booking.getDate_start();
		Date end_date=new_booking.getDate_end();
		int num_adults=(new_booking.getNum_adults()<=0)?1:new_booking.getNum_adults();
		int num_children=new_booking.getNum_children();
		String user_id=new_booking.getUser_id();
		
		if (!end_date.after(start_date)) {
			throw new Exception("End date is not after start date.");
		}
		
		try {
			while(rs.next()) {
				if (rs.getInt("id")==id) {
					rs.updateDate("fecha_entrada", start_date);
					rs.updateDate("fecha_salida", end_date);
					rs.updateInt("numero_adults", num_adults);
					rs.updateInt("numero_ninyos", num_children);
					rs.updateString("user_id", user_id);
					
					rs.updateRow();
				}
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

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @param num_adults the num_adults to set
	 */
	public void setNum_adults(int num_adults) {
		this.num_adults = num_adults;
	}

	/**
	 * @param num_children the num_children to set
	 */
	public void setNum_children(int num_children) {
		this.num_children = num_children;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Timestamp date) {
		this.date = date;
	}

	/**
	 * @param date_start the date_start to set
	 */
	public void setDate_start(Date date_start) {
		this.date_start = date_start;
	}

	/**
	 * @param date_end the date_end to set
	 */
	public void setDate_end(Date date_end) {
		this.date_end = date_end;
	}

	/**
	 * @param user_id the user_id to set
	 */
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	
}
