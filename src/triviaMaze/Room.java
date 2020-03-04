package triviaMaze;

import java.io.Serializable;
import java.util.ArrayList;

import question.Question;

public class Room implements Serializable{

	private static final long serialVersionUID = 4880979877529404810L;
	private String top;
	private String mid;
	private String bot;
	private int status;
	private Question question;
		//1 for open
		//0 for locked
		//-1 for sealed
	
	public Room(Question ques) {
		top = "- - -";
		mid = "|   |";
		bot = "- - -";
		status = 0;
		question = ques;
	}
	
	public int getStatus() {
		return this.status;
	}

	public String getTop() {
		return this.top;
	}

	public String getMid() {
		return this.mid;
	}

	public String getBot() {
		return this.bot;
	}

	private void setStatus(int status) {
		this.status = status;
	}
	
	public String displayQuestion() {
		return question.getQuestion();
	}
	
	public String displayOptions() {
		
		String toRet = " | ";
		ArrayList<String> options = question.getOptions();
		for(String string: options) {
			toRet += string + " | ";
		}
		
		return toRet;
	}
	
	public boolean answerQuestion(String answer) {
		
		boolean correct = question.checkAnswer(answer);
		
		if(correct) {
			setStatus(1);
		} else {
			setStatus(-1);
		}
		
		return correct;
	}
}
