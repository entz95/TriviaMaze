package question;

import java.util.ArrayList;

/*
 * Author: Bryan Wilson
 * 
 * Purpose: This abstract class will form the basis of the interactions with both TrueFalse questions and 
 * 			MultipleChoice questions, and provide necessary functionality and data containment.
 * 
 * Version: 1.0
 * 
 */

public abstract class Question {
	private String question;
	private String answer;
	
	public Question(String ans, String ques) {
		this.question = ques;
		this.answer = ans;
	}
	
	public String getQuestion() {
		return this.question;
	}
	
	public String getAnswer() {
		return this.answer;
	}
	
	public boolean checkAnswer(String givenAnswer) {
		if(answer.toLowerCase().equals(givenAnswer.toLowerCase())) {
			return true;
		} else {
			return false;
		}
	}
	
	public abstract ArrayList<String> getOptions();
}
