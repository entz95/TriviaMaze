package triviaMaze;

public class Room {

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
