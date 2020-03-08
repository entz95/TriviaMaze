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
 * Version: 0.7
 * 
 */

public class DBInitializer {
	
	public static void initializeTrueFalse() {
		Connection conn = null;

		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:questions.db");
			
			System.out.println("Connection established...");

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
			
			System.out.println("Table created...");
			
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
			
			System.out.println("Added tuples to table...");
			
			stmt.close();
			conn.close();
			conn = null;
			
			System.out.println("All connections closed...");

		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	public static void initializeMultipleChoice() {
		//TODO Fill in for multiple choice table
		
		Connection conn = null;

		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:questions.db");
			
			System.out.println("Connection established...");

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
			
			System.out.println("Table created...");
			
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
			
			System.out.println("Added tuples to table...");
			
			stmt.close();
			conn.close();
			conn = null;
			
			System.out.println("All connections closed...");

		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	
	}
	
//	This main is for testing this class. Remove before final testing.
//	public static void main(String[] args) {
//		
//		DBInitializer.initializeTrueFalse();
//		DBInitializer.initializeMultipleChoice();
//		
//		//System.out.println("Hello world");
//		
//	}
}
