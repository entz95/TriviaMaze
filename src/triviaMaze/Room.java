package triviaMaze;

import java.io.Serializable;

public class Room implements Serializable{

	private static final long serialVersionUID = 4880979877529404810L;
	private String top;
	private String mid;
	private String bot;
	private int status;
		//1 for open
		//0 for locked
		//-1 for sealed
	
	public Room() {
		top = "- - -";
		mid = "|   |";
		bot = "- - -";
		status = 0;
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

	public void setStatus(int status) {
		this.status = status;
	}
}
