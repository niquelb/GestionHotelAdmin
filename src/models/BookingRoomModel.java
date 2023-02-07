package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.mariadb.jdbc.Connection;

import main.Main;
import utils.BDConnector;

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
	 * Connection object
	 */
	private static Connection conn=Main.conn;
	
	/**
	 * ResultSet with all of the rooms
	 */
	private static ResultSet rs=BDConnector.execStmt("SELECT * FROM reservas_habitaciones;", conn);

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
	 * Method for getting an ArrayList with all BookingRoomModel objects or with
	 * specified filters
	 * 
	 * @param room_id Room ID for filtering
	 * @param booking_id Booking ID for filtering
	 * @param page_num page number
	 * @return ArrayList with BookingRoomModel objects, null if error
	 */
	public static ArrayList<BookingRoomModel> getBookingRoomList(int room_id, int booking_id, int page_num) {
		ArrayList<BookingRoomModel> al=new ArrayList<>();
		page_num=page_num*ROWS_PER_PAGE+1;
		
		int rows_per_page=(getTotalRows()<ROWS_PER_PAGE)?getTotalRows():ROWS_PER_PAGE;
		
		room_id=(room_id<0)?0:room_id;
		booking_id=(booking_id<0)?0:booking_id;
		
		try {
			rs.absolute(page_num);
			
			if (room_id==0&&booking_id==0) {
				for (int i = 0; i < rows_per_page; i++) {
					al.add(new BookingRoomModel(rs.getInt("habitacion_id"), rs.getInt("reserva_id"), rs.getInt("cantidad"), rs.getDouble("precio")));
					
					rs.next();
				}
				return al;
			} else if (room_id!=0) {
				if (booking_id!=0) {
					for (int i = 0; i < rows_per_page; i++) {
						if (rs.getInt("habitacion_id")==room_id&&rs.getInt("reserva_id")==booking_id) {
							al.add(new BookingRoomModel(rs.getInt("habitacion_id"), rs.getInt("reserva_id"), rs.getInt("cantidad"), rs.getDouble("precio")));
						}
						
						rs.next();
					}
					return al;
				}
				
				for (int i = 0; i < rows_per_page; i++) {
					if (rs.getInt("habitacion_id")==room_id) {
						al.add(new BookingRoomModel(rs.getInt("habitacion_id"), rs.getInt("reserva_id"), rs.getInt("cantidad"), rs.getDouble("precio")));
					}
					
					rs.next();
				}
				return al;
			} else if (booking_id!=0) {
				for (int i = 0; i < rows_per_page; i++) {
					if (rs.getInt("reserva_id")==booking_id) {
						al.add(new BookingRoomModel(rs.getInt("habitacion_id"), rs.getInt("reserva_id"), rs.getInt("cantidad"), rs.getDouble("precio")));
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
	 * Method for getting 1 BookingRoomModel object from the ResultSet based on the given ID,
	 * if it is -1 (or any other negative number), the first booking will be returned
	 * 
	 * @param id Room ID
	 * @return BookingRoomModel object, null if error or none found with the ID
	 */
	public static BookingRoomModel getBookingRoom(int id) {
		try {
			if (id<=0) {
				rs.first();
				return new BookingRoomModel(rs.getInt("habitacion_id"), rs.getInt("reserva_id"), rs.getInt("cantidad"), rs.getDouble("precio"));
			}
			
			rs.beforeFirst();
			while (rs.next()) {
				if (rs.getInt("id")==id) {
					return new BookingRoomModel(rs.getInt("habitacion_id"), rs.getInt("reserva_id"), rs.getInt("cantidad"), rs.getDouble("precio"));
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
	 * Method for inserting a booking into the DB through the
	 * ResultSet using a BookingRoomModel object
	 * 
	 * @param model BookingRoomModel object to be used
	 */
	public static void createBooking(BookingRoomModel model) {
		try {
			rs.moveToInsertRow();
			
			rs.updateInt("habitacion_id", model.getRoom_id());
			rs.updateInt("reserva_id", model.getBooking_id());
			rs.updateInt("cantidad", model.getQuantity());
			rs.updateDouble("precio", model.getPrice());
			
			rs.insertRow();
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
