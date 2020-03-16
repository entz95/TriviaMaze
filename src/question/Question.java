package question;

import java.io.Serializable;
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

public abstract class Question implements Serializable{

	private static final long serialVersionUID = 2740654142557678915L;
	private String question;
	private String answer;
	
	public Question(String ques, String ans) {
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
