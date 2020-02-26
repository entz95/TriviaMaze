package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/*
 * Author: Byran Wilson
 * 
 * The purpose of this class is to allow records to be added to the database tables to allow for greater question
 * varity and aid with testing.
 * 
 * Version: 
 */

public class DBUpdater {
	
	public static boolean updateTrueFalse(String question, String answer) {
		Connection c = null;
		
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:questions.db");
			
			Statement stmt = c.createStatement();
			
			String sql = "";
			ResultSet result = stmt.executeQuery("SELECT MAX(ID) FROM TRUE_FALSE");
			
			int newID = result.getInt(1) + 1;
			
			sql += "INSERT INTO TRUE_FALSE (ID, QUESTION, ANSWER) " +
					"VALUES (" + newID + ",'" + question + "', '" + answer +"')";
			
			stmt.executeUpdate(sql);
			
			stmt.close();
			c.close();
			c = null;
			
			return true;
			
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean updateMultipleChoice() {
		//TODO Add update funtionality
		
		return false;
	}
	
	//Main for testing preliminary functionality of DBUpdater
//	public static void main(String[] args) {
//		
//		boolean result = DBUpdater.updateTrueFalse("A longsword can deal 1d10 damage.", "TRUE");
//		System.out.println(result);
//		
//	}
	
}
