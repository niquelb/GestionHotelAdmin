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
import utils.ResultSetGen;

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
	 * DB Table name
	 */
	public final static String TABLE_NAME="habitaciones";

	/**
	 * ResultSet with all of the rooms
	 */
	private static ResultSet rs=ResultSetGen.generateResultSet(null, TABLE_NAME);

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
	public static ArrayList<RoomModel> getRoomList(Map<String, Object> params, int page_num) {
		ArrayList<RoomModel> al=new ArrayList<>();
		page_num=page_num*ROWS_PER_PAGE;

		params = params==null
				? new HashMap<String, Object>()
						: params;

		try {
			if (params.isEmpty()) {
				rs.absolute(page_num);

				int i = 0;

				while (rs.next() && i < ROWS_PER_PAGE) {
					al.add(new RoomModel(rs.getInt("id"), rs.getInt("cantidad"), rs.getInt("numero_maximo_personas"),
							rs.getInt("numero_camas"), rs.getDouble("precio"),
							rs.getString("nombre"), rs.getString("descripcion")));
					i++;
				}

				return al;
			}

			ResultSet rs=ResultSetGen.generateResultSet(params,TABLE_NAME); //TODO make price and max-guests ranges instead of fixed values

			int i=0;

			while (rs.next() && i < ROWS_PER_PAGE) {
				al.add(new RoomModel(rs.getInt("id"), rs.getInt("cantidad"), rs.getInt("numero_maximo_personas"),
						rs.getInt("numero_camas"), rs.getDouble("precio"),
						rs.getString("nombre"), rs.getString("descripcion")));
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
	 * Method for getting 1 RoomModel object from the ResultSet
	 * based on the room name given (NOT the "id" field from the DB),
	 * if it is null or empty, the first room will be returned
	 * 
	 * @param room_id Room name for filtering (NOT the "id" field from the DB)
	 * @return RoomModel object, null if error
	 */
	public static RoomModel getRoom(Map<String, Object> params) { //TODO update JavaDoc
		try  {
			
			params = params==null
					? new HashMap<String, Object>()
							: params;

			if (params.isEmpty()) {
				rs.first();
				return new RoomModel(rs.getInt("id"), rs.getInt("cantidad"), rs.getInt("numero_maximo_personas"),
						rs.getInt("numero_camas"), rs.getDouble("precio"),
						rs.getString("nombre"), rs.getString("descripcion"));
			}
			
			ResultSet rs=ResultSetGen.generateResultSet(params,TABLE_NAME);

			if (rs.first()) {
				return new RoomModel(rs.getInt("id"), rs.getInt("cantidad"), rs.getInt("numero_maximo_personas"),
						rs.getInt("numero_camas"), rs.getDouble("precio"),
						rs.getString("nombre"), rs.getString("descripcion"));
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
	public void createRoom() {
		String name=(this.getName().equals(""))
				? null
				: this.getName();
		if (name==null) {
			throw new IllegalArgumentException("Name cannot be null");
		}

		String description=this.getDescription();
		int quantity=this.getQuantity();
		double price=this.getPrice();
		int max_guests=this.getMax_guests();
		int num_beds=this.getNum_beds();
		

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
	public void updateRoom() {
		String name=(this.getName().equals(""))
				? null
				: this.getName();
		if (name==null) {
			throw new IllegalArgumentException("Name cannot be null");
		}

		String description=this.getDescription();
		int quantity=this.getQuantity();
		double price=this.getPrice();
		int max_guests=this.getMax_guests();
		int num_beds=this.getNum_beds();
		
		Map<String, Object> update_fields=new HashMap<String, Object>();
		
		update_fields.put("descripcion", description);
		update_fields.put("cantidad", quantity);
		update_fields.put("precio", price);
		update_fields.put("numero_maximo_personas", max_guests);
		update_fields.put("numero_camas", num_beds);
		
		Map<String, Object> params=new HashMap<String, Object>();

		params.put("nombre", name);

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
					if (entry.getValue() instanceof Double) {
						rs.updateDouble(entry.getKey(), (Double) entry.getValue());
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
