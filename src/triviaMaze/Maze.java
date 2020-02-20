package triviaMaze;

import java.io.Serializable;
import java.util.Arrays;

public class Maze implements Serializable {

	private Room[][] mazeArray;

	public Maze(int x, int y) {
		generateMaze(x, y);
	}

	private void generateMaze(int rows, int columns) {
		assert rows > 0 : "rows is zero or less";
		assert columns > 0 : "columns is zero or less";

		this.mazeArray = new Room[rows + 2][columns + 2];

		for (Room[] row : mazeArray) {
			Arrays.fill(row, null);
		}

		for (int i = 1; i <= rows; i++) {
			for (int j = 1; j <= columns; j++) {
				mazeArray[i][j] = new Room();
			}
		}
	}

	public String toString() {
		String line = "";
		for (int i = 1; i < mazeArray.length - 1; i++) {
			for (int k = 0; k < 3; k++) {
				for (int j = 1; j < mazeArray[0].length - 1; j++) {
					if(k == 0)
					line += mazeArray[i][j].getTop();
					else if(k == 1)
					line += mazeArray[i][j].getMid();
					else if(k ==2)
					line += mazeArray[i][j].getBot();
				}
				line += "\n";
			}
		}
		return line;
	}
}
