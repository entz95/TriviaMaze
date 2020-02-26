package application;

import java.sql.*;

/*
 * Author: Bryan Wilson
 * 
 * This class is only for testing purposes ONLY. This file should NEVER arrive on the master branch.
 */

public class SQLite_Test {

	public static void main(String[] args) {
		Connection c = null;

		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
			
			System.out.println("Connection established!");

			Statement stmt = c.createStatement();
			
			String sql = "";
			
			try {
				sql = "CREATE TABLE TRUE_FALSE " + "(ID INT PRIMARY KEY 	NOT NULL,"
						+ "QUESTION		 TEXT	NOT NULL," + "OPTION_A		 TEXT	NOT NULL,"
						+ "OPTION_B 		 TEXT	NOT NULL," + "ANSWER			 TEXT	NOT NULL)";
				stmt.executeUpdate(sql);
			} catch(Exception e) {
				sql = "DROP TABLE TRUE_FALSE";
				stmt.executeUpdate(sql);
				
				sql = "CREATE TABLE TRUE_FALSE " + "(ID INT PRIMARY KEY 	NOT NULL,"
						+ "QUESTION		 TEXT	NOT NULL," + "OPTION_A		 TEXT	NOT NULL,"
						+ "OPTION_B 		 TEXT	NOT NULL," + "ANSWER			 TEXT	NOT NULL)";
				stmt.executeUpdate(sql);
			}
			
			System.out.println("Table created...");

			sql = "INSERT INTO TRUE_FALSE (ID, QUESTION, OPTION_A, OPTION_B, ANSWER)"
					+ "VALUES(1, 'Fireball is a 3rd level spell.', 'TRUE', 'FALSE', 'TRUE')";
			stmt.executeUpdate(sql);

			sql = "INSERT INTO TRUE_FALSE (ID, QUESTION, OPTION_A, OPTION_B, ANSWER)"
					+ "VALUES(2, 'Warlocks use Charisma to cast spells.', 'TRUE', 'FALSE', 'TRUE')";
			stmt.executeUpdate(sql);

			sql = "INSERT INTO TRUE_FALSE (ID, QUESTION, OPTION_A, OPTION_B, ANSWER)"
					+ "VALUES(3, 'If you are not proficient with armor, it does not impact your ability to cast spells.'," + 
					" 'TRUE', 'FALSE', 'FALSE')";
			stmt.executeUpdate(sql);

			sql = "INSERT INTO TRUE_FALSE (ID, QUESTION, OPTION_A, OPTION_B, ANSWER)"
					+ "VALUES(4, 'Dwarves are classified as small creatures.', 'TRUE', 'FALSE', 'FALSE')";
			stmt.executeUpdate(sql);
			
			System.out.println("Added tuples to table...");
			
			stmt.close();
			c.close();
			c = null;
			
			System.out.println("All connections closed...");

		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}

//		System.out.println("Connection established!");

	}

}
