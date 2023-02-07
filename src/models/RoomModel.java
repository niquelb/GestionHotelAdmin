package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.eclipse.jdt.annotation.NonNull;
import org.mariadb.jdbc.Connection;

import main.Main;
import utils.BDConnector;

/**
 * Model class for the rooms table
 * 
 * @author Nico
 *
 */
public class RoomModel {
	private int id,
	quantity,
	max_guests,
	num_beds;
	private double price;
	@NonNull
	private String name,
	description;
	
	/**
	 * Number of rows for each page
	 */
	public final static int ROWS_PER_PAGE=10;
	
	/**
	 * Maximum price allowed per room
	 */
	public final static double MAX_PRICE=999;
	
	/**
	 * Maximum number of guests allowed per room
	 */
	public final static int MAX_GUESTS=10;
	
	/**
	 * Connection object
	 */
	private static Connection conn=Main.conn;
	
	/**
	 * ResultSet with all of the rooms
	 */
	private static ResultSet rs=BDConnector.execStmt("SELECT * FROM habitaciones;", conn);

	/**
	 * Default constructor
	 * @param id Room ID
	 * @param quantity Number of rooms
	 * @param max_guests Maximum number of guests allowed
	 * @param num_beds Number of beds
	 * @param price Room price
	 * @param name Room name
	 * @param description Room description
	 */
	public RoomModel(int id, int quantity, int max_guests, int num_beds, double price, @NonNull String name,
			String description) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.max_guests = max_guests;
		this.num_beds = num_beds;
		this.price = price;
		this.name = name;
		this.description = description;
	}

	/**
	 * Constructor without id param, it's auto-incremented
	 * in the DB
	 * @param quantity Number of rooms
	 * @param max_guests Maximum number of guests allowed
	 * @param num_beds Number of beds
	 * @param price Room price
	 * @param name Room name
	 * @param description Room description
	 */
	public RoomModel(int quantity, int max_guests, int num_beds, double price, @NonNull String name, String description) {
		super();
		this.quantity = quantity;
		this.max_guests = max_guests;
		this.num_beds = num_beds;
		this.price = price;
		this.name = name;
		this.description = description;
	}
	
	/**
	 * Method for getting an ArrayList with all rooms or with
	 * specified filters
	 * 
	 * @param name Room name
	 * @param price Maximum room price allowed
	 * @param num_guests Minimum number of guests allowed
	 * @param page_num page_number ! MUST START FROM 0 ! (First page is number 0, second number 1 etc.)
	 * @return ArrayList with RoomModel objects, null if error
	 */
	public static ArrayList<RoomModel> getRoomList(String name, double price, int num_guests, int page_num) {
		ArrayList<RoomModel> al=new ArrayList<>();
		page_num=page_num*ROWS_PER_PAGE+1;
		
		int rows_per_page=(getTotalRows()<ROWS_PER_PAGE)?getTotalRows():ROWS_PER_PAGE;
		
		name=(name==null)?"":name;
		price=(price==0.0||price>MAX_PRICE)?MAX_PRICE:price;
		num_guests=(num_guests==0||num_guests>MAX_GUESTS)?MAX_GUESTS:num_guests;
		
		try {
			rs.absolute(page_num);
			if (name.equals("")&&price>=MAX_PRICE&&num_guests>=MAX_GUESTS) {
				for (int i = 0; i < rows_per_page; i++) {
					al.add(new RoomModel(rs.getInt("id"), rs.getInt("cantidad"), rs.getInt("numero_maximo_personas"),
							rs.getInt("numero_camas"), rs.getDouble("precio"),
							rs.getString("nombre"), rs.getString("descripcion")));
					
					rs.next();
				}
				
				return al;
			} else if (!name.equals("")) {
				for (int i = 0; i < rows_per_page; i++) {
					if (rs.getString("nombre")!=null && rs.getString("nombre").toUpperCase().contains(name.toUpperCase()))
						al.add(new RoomModel(rs.getInt("id"), rs.getInt("cantidad"), rs.getInt("numero_maximo_personas"),
								rs.getInt("numero_camas"), rs.getDouble("precio"),
								rs.getString("nombre"), rs.getString("descripcion")));
					rs.next();
				}
				return al;
			} else if (price<MAX_PRICE&&num_guests<MAX_GUESTS) {
				for (int i = 0; i < rows_per_page; i++) {
					if (
							(rs.getInt("precio")<=price) &&
							(rs.getInt("numero_maximo_personas")>=num_guests)
							)
						al.add(new RoomModel(rs.getInt("id"), rs.getInt("cantidad"), rs.getInt("numero_maximo_personas"),
								rs.getInt("numero_camas"), rs.getDouble("precio"),
								rs.getString("nombre"), rs.getString("descripcion")));
					rs.next();
				}
				return al;
			} else if (price<MAX_PRICE) {
				for (int i = 0; i < rows_per_page; i++) {
					if (rs.getInt("precio")<=price)
						al.add(new RoomModel(rs.getInt("id"), rs.getInt("cantidad"), rs.getInt("numero_maximo_personas"),
								rs.getInt("numero_camas"), rs.getDouble("precio"),
								rs.getString("nombre"), rs.getString("descripcion")));
					rs.next();
				}
				return al;
			} else {
				for (int i = 0; i < rows_per_page; i++) {
					if (rs.getInt("numero_maximo_personas")>=num_guests)	
						al.add(new RoomModel(rs.getInt("id"), rs.getInt("cantidad"), rs.getInt("numero_maximo_personas"),
								rs.getInt("numero_camas"), rs.getDouble("precio"),
								rs.getString("nombre"), rs.getString("descripcion")));
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
	 * Method for getting 1 RoomModel object from the ResultSet
	 * based on the room name given (NOT the "id" field from the DB),
	 * if it is null or empty, the first room will be returned
	 * 
	 * @param room_id Room name for filtering (NOT the "id" field from the DB)
	 * @return RoomModel object, null if error
	 */
	public static RoomModel getRoom(String room_id) {
		try  {
			if (room_id==null || room_id.equals("")) {
				rs.first();
				return new RoomModel(rs.getInt("id"), rs.getInt("cantidad"), rs.getInt("numero_maximo_personas"),
						rs.getInt("numero_camas"), rs.getDouble("precio"),
						rs.getString("nombre"), rs.getString("descripcion"));
			}
			
			rs.beforeFirst();
			while (rs.next()) {
				if (rs.getString("nombre").toUpperCase().equals(room_id.toUpperCase())) {
					return new RoomModel(rs.getInt("id"), rs.getInt("cantidad"), rs.getInt("numero_maximo_personas"),
							rs.getInt("numero_camas"), rs.getDouble("precio"),
							rs.getString("nombre"), rs.getString("descripcion"));
				}
			}
			
			return null;
		
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}

	}
	
	/**
	 * Method for getting 1 RoomModel object through the
	 * ResultSet with a given ID, if none is given it returns
	 * the first entry
	 * @param room_id  Room ID
	 * @return RoomModel object
	 */
	public static RoomModel getRoom(int room_id) {
		room_id=(room_id<0)?0:room_id;
		
		try  {
			if (room_id==0) {
				System.out.println("first");
				rs.first();
				return new RoomModel(rs.getInt("id"), rs.getInt("cantidad"), rs.getInt("numero_maximo_personas"),
						rs.getInt("numero_camas"), rs.getDouble("precio"),
						rs.getString("nombre"), rs.getString("descripcion"));
			}
			
			rs.beforeFirst();
			while (rs.next()) {
				if (rs.getInt("id")==room_id) {
					return new RoomModel(rs.getInt("id"), rs.getInt("cantidad"), rs.getInt("numero_maximo_personas"),
							rs.getInt("numero_camas"), rs.getDouble("precio"),
							rs.getString("nombre"), rs.getString("descripcion"));
				}
			}
			
			return null;
		
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}

	}
	
	/**
	 * Method for creating a room through the
	 * ResultSet using a RoomModel object
	 * !! OBJECT CANNOT HAVE NULL NAME !!
	 * @param new_room RoomModel object
	 */
	public static void createRoom(RoomModel new_room) {
		String name=(new_room.getName().equals(""))?null:new_room.getName();
		if (name==null) {
			throw new IllegalArgumentException("Name cannot be null");
		}
		
		String description=new_room.getDescription();
		int quantity=new_room.getQuantity();
		double price=new_room.getPrice();
		int max_guests=new_room.getMax_guests();
		int num_beds=new_room.getNum_beds();
		
		try {
			rs.moveToInsertRow();
			
			rs.updateString("nombre", name);
			rs.updateString("descripcion", description);
			rs.updateInt("cantidad", quantity);
			rs.updateDouble("precio", price);
			rs.updateInt("numero_maximo_personas", max_guests);
			rs.updateInt("numero_camas", num_beds);
			
			rs.insertRow();
//			rs=BDConnector.execStmt("SELECT * FROM habitaciones;", conn);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	/**
	 * Method for updating existing rooms through the ResultSet
	 * using a RoomModel object
	 * !! OBJECT CANNOT HAVE NULL NAME !!
	 * @param new_room RoomModel object
	 */
	public static void updateRoom(RoomModel new_room) {
		String name=(new_room.getName().equals(""))?null:new_room.getName();
		if (name==null) {
			throw new IllegalArgumentException("Name cannot be null");
		}
		
		String description=new_room.getDescription();
		int quantity=new_room.getQuantity();
		double price=new_room.getPrice();
		int max_guests=new_room.getMax_guests();
		int num_beds=new_room.getNum_beds();
		
		try {
			rs.beforeFirst();
			while (rs.next()) {
				if (rs.getString("nombre").equals(name)) {
					rs.updateString("nombre", name);
					rs.updateString("descripcion", description);
					rs.updateInt("cantidad", quantity);
					rs.updateDouble("precio", price);
					rs.updateInt("numero_maximo_personas", max_guests);
					rs.updateInt("numero_camas", num_beds);
					
					rs.updateRow();
					
					System.out.println("Updated successfully");
					
					break;
				}
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
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
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @return the max_guests
	 */
	public int getMax_guests() {
		return max_guests;
	}

	/**
	 * @return the num_beds
	 */
	public int getNum_beds() {
		return num_beds;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * @param max_guests the max_guests to set
	 */
	public void setMax_guests(int max_guests) {
		this.max_guests = max_guests;
	}

	/**
	 * @param num_beds the num_beds to set
	 */
	public void setNum_beds(int num_beds) {
		this.num_beds = num_beds;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
