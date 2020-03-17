package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/*
 * Author: Bryan Wilson
 * 
 * Purpose: This class is to allow records to be added to the database tables to allow for greater question
 * 			variety and aid with testing.
 * 
 * Version: 1.0
 */

public class DBUpdater {
	
	public static boolean updateTrueFalse(String question, String answer) {
		Connection conn = null;
		
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:questions.db");
			
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO TRUE_FALSE (ID, QUESTION, ANSWER)" +
										"VALUES(?, ?, ?)");
			
			Statement getID = conn.createStatement();
			ResultSet result = getID.executeQuery("SELECT MAX(ID) FROM TRUE_FALSE");
			
			int newID = result.getInt(1) + 1;
			
			stmt.setInt(1,  newID);
			stmt.setString(2, question);
			stmt.setString(3, answer);
			
			
			stmt.executeUpdate();
			
			stmt.close();
			conn.close();
			conn = null;
			
			return true;
			
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean updateMultipleChoice(String question, String answer, String optA,
												String optB, String optC, String optD) {
		Connection conn = null;

		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:questions.db");

			PreparedStatement stmt = conn.prepareStatement("INSERT INTO MULTIPLE_CHOICE" + 
			" (ID, QUESTION, ANSWER, OPTION_A, OPTION_B, OPTION_C, OPTION_D) VALUES(?, ?, ?, ?, ?, ?, ?)");
			
			Statement getID = conn.createStatement();
			// String sql = "";
			ResultSet result = getID.executeQuery("SELECT MAX(ID) FROM MULTIPLE_CHOICE");

			int newID = result.getInt(1) + 1;

			stmt.setInt(1, newID);
			stmt.setString(2, question);
			stmt.setString(3, answer);
			stmt.setString(4, optA);
			stmt.setString(5, optB);
			stmt.setString(6, optC);
			stmt.setString(7, optD);

			stmt.executeUpdate();

			stmt.close();
			conn.close();
			conn = null;

			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
