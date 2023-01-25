package utils;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.mariadb.jdbc.Connection;
import org.mariadb.jdbc.Statement;

public class BDConnector {
	private static final String BD="gestionhotel";
	private static Connection CONNECTION=null;
	
	public static void connectToBD() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			CONNECTION=(Connection) DriverManager.getConnection("jdbc:mariadb://localhost:3306/"+BD,"root","");
			if (CONNECTION!=null) {
				System.out.println("Succesful connection: "+BD);
				
				// v Example v
				Statement stmt=CONNECTION.createStatement();
				ResultSet rs=stmt.executeQuery("SELECT * FROM users");
				while (rs.next()) {
					System.out.println(rs.getString("email")+' '+rs.getString("nombre"));					
				}
				
			} else {
				System.err.println("Failed connection (is null): "+BD);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (CONNECTION!=null) {
				try {
					CONNECTION.close();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		}
	}
}
