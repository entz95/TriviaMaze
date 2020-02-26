package application;

import java.sql.*;
import java.util.ArrayList;

public class RS_Test {

	public static void main(String[] args) {
		Connection conn = null;
		
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:test.db");
			//System.out.println("Connection established.");
			
			String sql = "SELECT * FROM TRUE_FALSE";
			Statement stmt = conn.createStatement();
			
			ResultSet result = stmt.executeQuery(sql);
			
			ArrayList<String> list = new ArrayList<String>(); 
			
			while(result.next()) {
//				System.out.println(result.getString("ID") + " " + result.getString("QUESTION") + " " +
//								   result.getString("ANSWER"));
				list.add(result.getString("QUESTION"));
			}
			
			for(String s : list) {
				System.out.println(s);
			}
			
			
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}

	}

}
