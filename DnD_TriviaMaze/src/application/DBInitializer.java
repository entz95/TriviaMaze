package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
//import java.util.Scanner;

/*
 * Author: Bryan Wilson
 * 
 * The purpose of this class is to allow the database to be reset to a default state, should something ever happen
 * to the database that would make the game unable to be played properly. 
 * 
 * Version: 
 * 
 */

public class DBInitializer {
	
	public static void initializeTrueFalse() {
		Connection c = null;

		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:questions.db");
			
			System.out.println("Connection established...");

			Statement stmt = c.createStatement();
			
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
			//TODO Swap this to a loop using an input file for cleaner code

			sql = "INSERT INTO TRUE_FALSE (ID, QUESTION, ANSWER)"
					+ "VALUES(1, 'Fireball is a 3rd level spell.', 'TRUE')";
			stmt.executeUpdate(sql);

			sql = "INSERT INTO TRUE_FALSE (ID, QUESTION, ANSWER)"
					+ "VALUES(2, 'Warlocks use Charisma to cast spells.', 'TRUE')";
			stmt.executeUpdate(sql);

			sql = "INSERT INTO TRUE_FALSE (ID, QUESTION, ANSWER)"
					+ "VALUES(3, 'If you are not proficient with armor, it does not impact your ability to cast spells.'," + 
					" 'FALSE')";
			stmt.executeUpdate(sql);

			sql = "INSERT INTO TRUE_FALSE (ID, QUESTION, ANSWER)"
					+ "VALUES(4, 'Dwarves are classified as small creatures.', 'FALSE')";
			stmt.executeUpdate(sql);
			
			//TODO end loop content
			
			System.out.println("Added tuples to table...");
			
			stmt.close();
			c.close();
			c = null;
			
			System.out.println("All connections closed...");

		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	public static void initializeMultipleChoice() {
		//TODO Fill in for multiple choice table
		
		Connection c = null;

		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:questions.db");
			
			System.out.println("Connection established...");

			Statement stmt = c.createStatement();
			
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
			//TODO Swap this to a loop using an input file for cleaner code

			sql = "INSERT INTO MULTIPLE_CHOICE (ID, QUESTION, ANSWER, OPTION_A, OPTION_B, OPTION_C, OPTION_D)"
					+" VALUES(1, 'Tieflings are related to what type of creature?', 'Fiends',"
					+" 'Fey', 'Celestials', 'Fiends', 'Undead')";
			stmt.executeUpdate(sql);

			sql = "INSERT INTO MULTIPLE_CHOICE (ID, QUESTION, ANSWER, OPTION_A, OPTION_B, OPTION_C, OPTION_D)"
					+ " VALUES(2, 'Sorcerers use this as their primary attribute:', 'Charisma',"
					+ " 'Strength', 'Charisma', 'Wisdom', 'Dexterity')";
			stmt.executeUpdate(sql);

			sql = "INSERT INTO MULTIPLE_CHOICE (ID, QUESTION, ANSWER, OPTION_A, OPTION_B, OPTION_C, OPTION_D)"
					+ " VALUES(3, 'What class has the highest hit die?', 'Barbarian'," 
					+ " 'Barbarian', 'Wizard', 'Paladin', 'Monk')";
			stmt.executeUpdate(sql);

			sql = "INSERT INTO MULTIPLE_CHOICE (ID, QUESTION, ANSWER, OPTION_A, OPTION_B, OPTION_C, OPTION_D)"
					+ " VALUES(4, 'The shortest race in the PHB is:', 'Halfling', "
					+ " 'Half-orc', 'Human', 'Gnome', 'Halfling')";
			stmt.executeUpdate(sql);
			
			//TODO end loop content
			
			System.out.println("Added tuples to table...");
			
			stmt.close();
			c.close();
			c = null;
			
			System.out.println("All connections closed...");

		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	
	}
	
	//This main is for testing this class. Remove before final testing.
//	public static void main(String[] args) {
//		
//		DBInitializer.initializeTrueFalse();
//		DBInitializer.initializeMultipleChoice();
//		
//	}
}
