package triviaMaze;

import java.io.Serializable;
import question.Question;

/*
 * Author: Justin Entz
 * 
 * Purpose: This class contains all data that room needs to hold and know
 * 
 * Version: 1.0
 * 
 */

public class Room implements Serializable {

	private static final long serialVersionUID = 4880979877529404810L;
	private int xPos;
	private int yPos;
	private String top;
	private String mid;
	private String bot;
	private Question question;
	private int status;
		//1 for open
		//0 for locked
		//-1 for sealed
	
	public Room(Question ques, int x, int y) {
		top = "- - -";
		mid = "|   |";
		bot = "- - -";
		status = 0;
		this.question = ques;
		this.xPos = x;
		this.yPos = y;
	}
	
	public Room(Question question) {
		top = "- - -";
		mid = "|   |";
		bot = "- - -";
		status = 0;
		this.question = question;
	}
	
	public static Room nullRoom() {
		Room nullRoom = new Room(null);
		nullRoom.status = -1;
		nullRoom.top = "# # #";
		nullRoom.mid = "# # #";
		nullRoom.bot = "# # #";
		return nullRoom;
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

	public void setStatus(int status) {
		this.status = status;
	}
	
	public int getStatus() {
		return this.status;
	}
	public Question getQuestion() {
		return this.question;
	}
	
	public String displayQuestion(Question question) {
		String toRet = "";
		
		toRet = toRet + question.getAnswer();
		toRet = toRet + question.getOptions();
		
		return toRet;
	}

	public void setMid(String newMid) {
		this.mid = newMid;
		
	}

	public int getXRoomPosition() {
		return xPos;
	}

	public void setXRoomPosition(int xPos) {
		this.xPos = xPos;
	}

	public int getYRoomPosition() {
		return yPos;
	}
	
	public void setYRoomPosition(int yPos) {
		this.yPos = yPos;
	}

}
