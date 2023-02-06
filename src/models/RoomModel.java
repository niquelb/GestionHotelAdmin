package models;

import java.sql.ResultSet;
import java.util.ArrayList;

import org.mariadb.jdbc.Connection;

import main.Main;
import utils.BDConnector;

public class RoomModel {
	private int id,
	quantity,
	max_guests,
	num_beds;
	private double price;
	private String name,
	description;
	
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
	public RoomModel(int id, int quantity, int max_guests, int num_beds, double price, String name,
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
	public RoomModel(int quantity, int max_guests, int num_beds, double price, String name, String description) {
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
	 * @param price Room price
	 * @param max_guests Maximum number of guests allowed
	 * @param page_num page_number ! MUST START FROM 0 ! (First page is number 0, second number 1 etc.)
	 * @return ArrayList with RoomModel objects, null if error
	 */
	public static ArrayList<RoomModel> getRoomList(String name, double price, int max_guests, int page_num) {
		ArrayList<RoomModel> al=new ArrayList<>();
		page_num=page_num*10+1;
		
		name=(name==null)?"":name;
		price=(price==0.0)?999:price;
		max_guests=(max_guests==0)?10:max_guests;
		
		try {
			rs.absolute(page_num);
			if (name==""&&price>=999&&max_guests>=10) {
				for (int i = 0; i < 10; i++) {
					al.add(new RoomModel(rs.getInt("cantidad"), rs.getInt("numero_maximo_personas"),
							rs.getInt("numero_camas"), rs.getDouble("precio"),
							rs.getString("nombre"), rs.getString("descripcion")));
					
					rs.next();
				}
				
				return al;
			} else if (!name.equals("")) {
				for (int i = 0; i < 10; i++) {
					if (rs.getString("nombre")!=null && rs.getString("nombre").toUpperCase().contains(name.toUpperCase()))
						al.add(new RoomModel(rs.getInt("cantidad"), rs.getInt("numero_maximo_personas"),
								rs.getInt("numero_camas"), rs.getDouble("precio"),
								rs.getString("nombre"), rs.getString("descripcion")));
					rs.next();
				}
				return al;
			} else if (price<999&&max_guests<10) {
				for (int i = 0; i < 10; i++) {
					if (
							(rs.getInt("precio")<=price) &&
							(rs.getInt("numero_maximo_personas")>=max_guests)
							)
						al.add(new RoomModel(rs.getInt("cantidad"), rs.getInt("numero_maximo_personas"),
								rs.getInt("numero_camas"), rs.getDouble("precio"),
								rs.getString("nombre"), rs.getString("descripcion")));
					rs.next();
				}
				return al;
			} else if (price<999) {
				for (int i = 0; i < 10; i++) {
					if (rs.getInt("precio")<=price)
						al.add(new RoomModel(rs.getInt("cantidad"), rs.getInt("numero_maximo_personas"),
								rs.getInt("numero_camas"), rs.getDouble("precio"),
								rs.getString("nombre"), rs.getString("descripcion")));
					rs.next();
				}
				return al;
			} else {
				for (int i = 0; i < 10; i++) {
					if (rs.getInt("numero_maximo_personas")>=max_guests)	
						al.add(new RoomModel(rs.getInt("cantidad"), rs.getInt("numero_maximo_personas"),
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
	
	
	
}
