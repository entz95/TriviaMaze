package triviaMaze;

import java.io.Serializable;

public class Room implements Serializable{

	private static final long serialVersionUID = 4880979877529404810L;
	private String top;
	private String mid;
	private String bot;
	
	public Room() {
		top = "- - -";
		mid = "|   |";
		bot = "- - -";
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
}
