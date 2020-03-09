package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import question.MultipleChoice;
import question.Question;
import question.TrueFalse;

public class DBGetter {
	
	public static ArrayList<Question> getTrueFalse() {
		Connection conn = null;
		ArrayList<Question> toRet = new ArrayList<Question>();
		
		try {
			
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:questions.db");

			Statement stmt = conn.createStatement();
			
			String sql = "SELECT * FROM TRUE_FALSE";
			
			ResultSet result = stmt.executeQuery(sql);
			
			while(result.next()) {
				TrueFalse question = new TrueFalse(result.getString("QUESTION"), result.getString("ANSWER"));
				toRet.add(question);
			}
			
			stmt.close();
			conn.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			toRet = new ArrayList<Question>();
		}
		
		
		return toRet;
	}
	
	
	public static ArrayList<Question> getMultipleChoice() {
		Connection conn = null;
		ArrayList<Question> toRet = new ArrayList<Question>();
		
		try {
			
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:questions.db");

			Statement stmt = conn.createStatement();
			
			String sql = "SELECT * FROM MULTIPLE_CHOICE";
			
			ResultSet result = stmt.executeQuery(sql);
			
			while(result.next()) {
				MultipleChoice question = new MultipleChoice(result.getString("QUESTION"), result.getString("ANSWER"), 
										  result.getString("OPTION_A"), result.getString("OPTION_B"),
										  result.getString("OPTION_C"), result.getString("OPTION_D"));
				toRet.add(question);
			}
			
			stmt.close();
			conn.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			toRet = new ArrayList<Question>();
		}
		
		return toRet;
	}
	
	public static ArrayList<Question> getAllQuestions() {
		Connection conn = null;
		ArrayList<Question> toRet = new ArrayList<Question>();
		
		try {
			
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:questions.db");

			Statement stmt = conn.createStatement();
			
			String sql = "SELECT * FROM MULTIPLE_CHOICE";
			
			ResultSet result = stmt.executeQuery(sql);
			
			while(result.next()) {
				MultipleChoice question = new MultipleChoice(result.getString("QUESTION"), result.getString("ANSWER"), 
										  result.getString("OPTION_A"), result.getString("OPTION_B"),
										  result.getString("OPTION_C"), result.getString("OPTION_D"));
				toRet.add(question);
			}
			
			sql = "SELECT * FROM TRUE_FALSE";
			
			result = stmt.executeQuery(sql);
			
			while(result.next()) {
				TrueFalse question = new TrueFalse(result.getString("QUESTION"), result.getString("ANSWER"));
				toRet.add(question);
			}
			
			stmt.close();
			conn.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			toRet = new ArrayList<Question>();
		}
		
		return toRet;
	}
}
