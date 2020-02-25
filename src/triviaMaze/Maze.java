package triviaMaze;

import java.io.Serializable;
import java.util.Arrays;

//in the navigate maze class we might want to make a separate method that we call to display and answer the question and change room status from closed to open/sealed to see if they can then move so we have more readable code
//and also implement another method that checks to see if there are any possible paths to the exit

public class Maze implements Serializable {

	private static final long serialVersionUID = -6846687447036983692L;
	private Room[][] mazeArray;
	private int posX;
	private int posY;

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

	public int getXPosition() {
		return posX;
	}

	public void setXPosition(int x) {
		this.posX = x;
	}

	public int getYPosition() {
		return posY;
	}

	public void setYPosition(int y) {
		this.posY = y;
	}

	public Room navigateMaze(String input) {
		assert input != null : "input is not valid";

		int currentXPosition = this.posX;
		int currentYPosition = this.posY;
		Room currentRoom = mazeArray[currentYPosition][currentXPosition];
		Room nextRoom = new Room();

		try {
			switch (input) {
			case ("Up"): // case for moving up/north
				currentYPosition = currentYPosition - 1;
				nextRoom = moveRoom(currentRoom, mazeArray[currentYPosition][currentXPosition]);
				break;
			case ("Down"): // case for moving down/south
				currentYPosition = currentYPosition + 1;
				nextRoom = moveRoom(currentRoom, mazeArray[currentYPosition][currentXPosition]);
				break;
			case ("Left"): // case for moving left/west
				currentXPosition = currentXPosition - 1;
				nextRoom = moveRoom(currentRoom, mazeArray[currentYPosition][currentXPosition]);
				break;
			case ("Right"): // case for moving right/east
				currentXPosition = currentXPosition + 1;
				nextRoom = moveRoom(currentRoom, mazeArray[currentYPosition][currentXPosition]);
				break;
			default:
				nextRoom = currentRoom;
			}
			setXPosition(currentXPosition);
			setYPosition(currentYPosition);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("You cannot move " + input);
			return currentRoom;
		}
		return nextRoom;

	}

	private Room moveRoom(Room current, Room next) {
		if (next.getStatus() == -1) {// if room is sealed
			System.out.println("Room is sealed");
			return current;
		} else if (next.getStatus() == 1) {// if room is open
			System.out.println("room is open");
			return next;
		} else
			// implement code for the question here
			return next;
	}

	private boolean checkForPaths(Room[][] triviaMaze) {
		assert triviaMaze != null : "Maze is null";

		int[][] pathTester = roomArrayToIntArray(triviaMaze);
		int length = pathTester.length;

		pathTester[0][0] = 1;

		for (int i = 1; i < length; i++) {
			if (pathTester[0][i] != -1) {
				pathTester[0][i] = pathTester[0][i - 1];
			}
		}
		for (int j = 1; j < length; j++) {
			if (pathTester[j][0] != -1) {
				pathTester[j][0] = pathTester[j - 1][0];
			}
		}

		for (int i = 1; i < length; i++) {
			for (int j = 1; j < length; j++) {
				if (pathTester[i][j] != -1) {
					pathTester[i][j] = Math.max(pathTester[i][j - 1], pathTester[i - 1][j]);
				}
			}
		}
		return (pathTester[length - 1][length - 1] == 1);
	}

	private int[][] roomArrayToIntArray(Room[][] triviaMaze) {
		int length = triviaMaze.length - 2;
		int[][] intArray = new int[length][];

		for (int i = 0; i < length; i++) {
			intArray[i] = new int[length];
			for (int j = 0; j < length; j++) {
				intArray[i][j] = triviaMaze[i + 1][j + 1].getStatus();
			}
		}
		return intArray;
	}

	public String toString() {
		String line = "";
		for (int i = 1; i < mazeArray.length - 1; i++) {
			for (int k = 0; k < 3; k++) {
				for (int j = 1; j < mazeArray[0].length - 1; j++) {
					if (k == 0)
						line += mazeArray[i][j].getTop();
					else if (k == 1)
						line += mazeArray[i][j].getMid();
					else if (k == 2)
						line += mazeArray[i][j].getBot();
				}
				line += "\n";
			}
		}
		return line;
	}
}
