package utils;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import org.mariadb.jdbc.Connection;

import main.Main;

public class ResultSetGen {


	/**
	 * Connection object
	 */
	private final static Connection conn=Main.conn;
	
	public static ResultSet generateResultSet(Map<String, Object> params, String tableName) {
		String query="SELECT * FROM "+tableName+" WHERE TRUE";
		
		params = params==null
				? new HashMap<String, Object>()
				: params;
		
		if (!params.isEmpty()) {
			for (Map.Entry<String, Object> entry : params.entrySet()) {
		        if (entry.getValue() instanceof Integer)
				        query=query.concat(" AND "+entry.getKey()+" = "+entry.getValue());
		        else
			        query=query.concat(" AND "+entry.getKey()+" LIKE '%"+entry.getValue()+"%'");
		    }
		}
		
		ResultSet rs=BDConnector.execStmt(query, conn);
		
		return rs;
	}
	
}
