package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.mariadb.jdbc.Connection;

import main.Main;
import utils.BDConnector;
import utils.ResultSetGen;

/**
 * Model class for the table "bookings_rooms"
 * @author Nico
 *
 */
public class BookingRoomModel {
	private int id,
		room_id,
		booking_id,
		quantity;
	private double price;
	
	/**
	 * Number of rows for each page
	 */
	public final static int ROWS_PER_PAGE=10;
	
	/**
	 * DB Table name
	 */
	public final static String TABLE_NAME="reservas_habitaciones";

	/**
	 * ResultSet with all of the users
	 */
	private static ResultSet rs=ResultSetGen.generateResultSet(null, TABLE_NAME);

	/**
	 * Default constructor
	 * @param id ID
	 * @param room_id Room ID
	 * @param booking_id Booking ID
	 * @param quantity Quantity of rooms related to a booking
	 * @param price Booking price
	 */
	public BookingRoomModel(int id, int room_id, int booking_id, int quantity, double price) {
		super();
		this.id = id;
		this.room_id = room_id;
		this.booking_id = booking_id;
		this.quantity = quantity;
		this.price = price;
	}

	/**
	 * Constructor without ID field (auto-generated)
	 * @param room_id Room ID
	 * @param booking_id Booking ID
	 * @param quantity Quantity of rooms related to a booking
	 * @param price Booking price
	 */
	public BookingRoomModel(int room_id, int booking_id, int quantity, double price) {
		super();
		this.room_id = room_id;
		this.booking_id = booking_id;
		this.quantity = quantity;
		this.price = price;
	}
	
	/**
	 * Method for getting an array list with all bookings_rooms or using specified parameters
	 * 
	 * @param params Map object with the column-name and the value for filtering
	 * @param page_num Page number
	 * @return ArrayList with BookingRoomModel object, null if error
	 */
	public static ArrayList<BookingRoomModel> getBookingRoomList(Map<String, Object> params, int page_num) {
		ArrayList<BookingRoomModel> al=new ArrayList<>();
		page_num=page_num*ROWS_PER_PAGE;
		
		params = params==null
				? new HashMap<String, Object>()
				: params;
		
		try {
			if (params.isEmpty()) {
				rs.absolute(page_num);

				int i = 0;

				while (rs.next() && i < ROWS_PER_PAGE) {
					al.add(new BookingRoomModel(rs.getInt("habitacion_id"),
							rs.getInt("reserva_id"),
							rs.getInt("cantidad"),
							rs.getDouble("precio")));
					
					i++;
				}

				return al;
			} 
			
			ResultSet rs=ResultSetGen.generateResultSet(params,TABLE_NAME); 
			
			//TODO implement date ranges and check end date

			int i=0;

			while (rs.next() && i < ROWS_PER_PAGE) {
				al.add(new BookingRoomModel(rs.getInt("habitacion_id"),
						rs.getInt("reserva_id"),
						rs.getInt("cantidad"),
						rs.getDouble("precio")));
				i++;
			}

			return al;


			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * Method for getting 1 BookingRoomModel object with specified parameters, if none are given, the first result will be given
	 * 
	 * @param params Map object with the column-name and the value for filtering
	 * @return BookingRoomModel object, null if error
	 */
	public static BookingRoomModel getBookingRoom(Map<String, Object> params) {
		
		params = params==null
				? new HashMap<String, Object>()
				: params;
		
		try {
			
			if (params.isEmpty()) {
				rs.first();
				return new BookingRoomModel(rs.getInt("habitacion_id"),
						rs.getInt("reserva_id"),
						rs.getInt("cantidad"),
						rs.getDouble("precio"));
			
			}

			ResultSet rs=ResultSetGen.generateResultSet(params,TABLE_NAME);

			if (rs.first()) {
				return new BookingRoomModel(rs.getInt("habitacion_id"),
						rs.getInt("reserva_id"),
						rs.getInt("cantidad"),
						rs.getDouble("precio"));
			
			}

			return null;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
				
	}
	
	/**
	 * Method for inserting a booking into the DB through the ResultSet
	 */
	public void createBooking() {
		int room_id=this.getRoom_id();
		int booking_id=this.getBooking_id();
		int quantity=this.getQuantity()<0
				? 1
				: this.getQuantity();
		double price=this.getPrice()<0
				? 1
				: this.getPrice();
		
		//TODO check FKs
		
		
		try {
			rs.moveToInsertRow();
			
			rs.updateInt("habitacion_id", room_id);
			rs.updateInt("reserva_id", booking_id);
			rs.updateInt("cantidad", quantity);
			rs.updateDouble("precio", price);
			
			rs.insertRow();
			
			refreshResultSet();
		} catch (SQLException e) {
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
	 * @return the room_id
	 */
	public int getRoom_id() {
		return room_id;
	}

	/**
	 * @return the booking_id
	 */
	public int getBooking_id() {
		return booking_id;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @param room_id the room_id to set
	 */
	public void setRoom_id(int room_id) {
		this.room_id = room_id;
	}

	/**
	 * @param booking_id the booking_id to set
	 */
	public void setBooking_id(int booking_id) {
		this.booking_id = booking_id;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	
	

}
