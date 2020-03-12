package application;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Scanner;
//import java.util.Scanner;

/*
 * Author: Bryan Wilson
 * 
 * The purpose of this class is to allow the database to be reset to a default state, should something ever happen
 * to the database that would make the game unable to be played properly. 
 * 
 * Version: 1.0
 * 
 */

public class DBInitializer {
	
	public static boolean initializeTrueFalse() {
		Connection conn = null;

		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:questions.db");

			Statement stmt = conn.createStatement();
			
			String sql = "";
			
			try {
				sql = "CREATE TABLE TRUE_FALSE " + "(ID INT PRIMARY KEY 	NOT NULL,"
						+ "QUESTION		 TEXT	NOT NULL," +  "ANSWER			 TEXT	NOT NULL)";
				stmt.executeUpdate(sql);
			} catch(Exception e) {
				sql = "DROP TABLE TRUE_FALSE";
				stmt.executeUpdate(sql);
				
				sql = "CREATE TABLE TRUE_FALSE " + "(ID INT PRIMARY KEY 	NOT NULL,"
						+ "QUESTION		TEXT	NOT NULL," + "ANSWER	TEXT	NOT NULL)";
				stmt.executeUpdate(sql);
			}
			
			File source = new File("./trueFalse.txt");
			if(source.exists() && source.canRead()) {
				Scanner fin = new Scanner(source);
				int count = 1;
				while(fin.hasNextLine()) {
					String[] result = fin.nextLine().split("\\*");
					PreparedStatement pstmt = conn.prepareStatement("INSERT INTO TRUE_FALSE (ID, QUESTION, ANSWER)" +
					"VALUES(?, ?, ?)");
					pstmt.setInt(1, count);
					pstmt.setString(2, result[0]);
					pstmt.setString(3, result[1]);
					
					pstmt.executeUpdate();
					count++;
				}
				fin.close();
			}
			
			stmt.close();
			conn.close();
			conn = null;
			
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean initializeMultipleChoice() {
		Connection conn = null;

		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:questions.db");

			Statement stmt = conn.createStatement();
			
			String sql = "";
			
			try {
				sql = "CREATE TABLE MULTIPLE_CHOICE (ID INT PRIMARY KEY 	NOT NULL,"
						+ " QUESTION	TEXT	NOT NULL, ANSWER	TEXT	NOT NULL," 
						+ " OPTION_A 	TEXT 	NOT NULL, OPTION_B 	TEXT 	NOT NULL,"
						+ " OPTION_C 	TEXT 	NOT NULL, OPTION_D 	TEXT 	NOT NULL)";
				stmt.executeUpdate(sql);
			} catch(Exception e) {
				sql = "DROP TABLE MULTIPLE_CHOICE";
				stmt.executeUpdate(sql);
				
				sql = "CREATE TABLE MULTIPLE_CHOICE (ID INT PRIMARY KEY 	NOT NULL,"
						+ " QUESTION	TEXT	NOT NULL, ANSWER	TEXT	NOT NULL," 
						+ " OPTION_A 	TEXT 	NOT NULL, OPTION_B 	TEXT 	NOT NULL,"
						+ " OPTION_C 	TEXT 	NOT NULL, OPTION_D 	TEXT 	NOT NULL)";
				stmt.executeUpdate(sql);
			}
			
			File source = new File("./multipleChoice.txt");
			if(source.exists() && source.canRead()) {
				Scanner fin = new Scanner(source);
				int count = 1;
				while(fin.hasNextLine()) {
					String[] result = fin.nextLine().split("\\*");
					PreparedStatement pstmt = conn.prepareStatement("INSERT INTO MULTIPLE_CHOICE " +
					"(ID, QUESTION, ANSWER, OPTION_A, OPTION_B, OPTION_C, OPTION_D)" +
					"VALUES(?, ?, ?, ?, ?, ?, ?)");
					pstmt.setInt(1, count);
					pstmt.setString(2, result[0]);
					pstmt.setString(3, result[1]);
					pstmt.setString(4, result[2]);
					pstmt.setString(5, result[3]);
					pstmt.setString(6, result[4]);
					pstmt.setString(7, result[5]);
					
					pstmt.executeUpdate();
					count++;
				}
				fin.close();
			}
			
			stmt.close();
			conn.close();
			conn = null;
			
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	
	}
	
	public static void main(String[] args) {
		initializeTrueFalse();
		initializeMultipleChoice();
	}
}
