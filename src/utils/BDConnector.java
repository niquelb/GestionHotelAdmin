package utils;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.mariadb.jdbc.Connection;
import org.mariadb.jdbc.Statement;

/**
 * Class responsible for establishing the connection
 * with the database and executing queries using JDBC
 * 
 * @author Nico
 * 
 */
public class BDConnector {
	private static final String BD="gestionhotel";
	private static final String URL="jdbc:mariadb://localhost:3306/";
	private static final String BD_USER="root";
	private static final String BD_PASSWORD="";
	private static ResultSet rs=null;
	
	/**
	 * Method responsible for establishing a connection to the DB
	 * 
	 * @return Connection object, null if connection failed
	 */
	public static Connection connectToBD() {
		Connection conn=null;
		try {			
			Class.forName("org.mariadb.jdbc.Driver");
			conn=(Connection) DriverManager.getConnection(URL+BD,BD_USER,BD_PASSWORD);
			if (conn!=null) {
				System.out.println("Succesful connection: "+BD);
			} else {
				System.err.println("Failed connection (is null): "+BD);
			}
		} catch (ClassNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} 
		
		return conn;
	}
	
	/**
	 * Method responsible for closing a given Connection
	 * 
	 * @param conn Connection object to close
	 */
	public static void closeConnection(Connection conn) {
		if (conn!=null) {
			try {
				System.out.println("Closing connection: "+BD);
				conn.close();
			} catch (SQLException e) {
				System.err.println(e.getMessage());
			}
		} else {
			System.err.println("Connection is null");
		}
	}
	
	/**
	 * Method for executing statements with a given connection to a BD
	 * @param stmt_str Statement to be executed
	 * @param conn open Connection object to be used
	 * @return ResultSet with the DB's response, null if there's an error
	 */
	public static ResultSet execStmt(String stmt_str, Connection conn) {
		if (conn!=null) {
			try {
				System.out.println("Executing statement:\n"+stmt_str+"\nin BD "+BD);
				Statement stmt=conn.createStatement();
				rs=stmt.executeQuery(stmt_str);
			} catch (SQLException e) {
				System.err.println(e.getMessage());
			}
		} else {
			System.err.println("Connection is null");
		}
		
		return rs;
	}
}
