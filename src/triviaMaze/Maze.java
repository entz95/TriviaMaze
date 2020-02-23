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
				nextRoom = mazeArray[currentYPosition][currentXPosition];
				if (nextRoom.getStatus() == -1) {
					nextRoom = currentRoom;
					System.out.println("Room is sealed");
					break;
				} else if (nextRoom.getStatus() == 0) {
					System.out.println("answer question");// implement code for the question here
				}
				break;
			case ("Down"): // case for moving down/south
				currentYPosition = currentYPosition + 1;
				nextRoom = mazeArray[currentYPosition][currentXPosition];
				if (nextRoom.getStatus() == -1) {
					nextRoom = currentRoom;
					System.out.println("Room is sealed");
					break;
				} else if (nextRoom.getStatus() == 0) {
					System.out.println("answer question");// implement code for the question here
				}
				break;
			case ("Left"): // case for moving left/west
				currentXPosition = currentXPosition - 1;
				nextRoom = mazeArray[currentYPosition][currentXPosition];
				if (nextRoom.getStatus() == -1) {
					nextRoom = currentRoom;
					System.out.println("Room is sealed");
					break;
				} else if (nextRoom.getStatus() == 0) {
					System.out.println("answer question");// implement code for the question here
				}
				break;
			case ("Right"): // case for moving right/east
				currentXPosition = currentXPosition + 1;
				nextRoom = mazeArray[currentYPosition][currentXPosition];
				if (nextRoom.getStatus() == -1) {
					nextRoom = currentRoom;
					System.out.println("Room is sealed");
					break;
				} else if (nextRoom.getStatus() == 0) {
					System.out.println("answer question");// implement code for the question here
				}
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

	private boolean checkForPaths(Room[][] triviaMaze) {
		assert triviaMaze != null : "Maze is null";

		triviaMaze[1][1].setStatus(1);

		for (int i = 2; i < triviaMaze[1].length; i++) {
			if (triviaMaze[1][i].getStatus() != -1) {
				triviaMaze[1][i] = triviaMaze[1][i - 1];
			}
		}
		for (int j = 2; j < triviaMaze[1].length; j++) {
			if (triviaMaze[j][1].getStatus() != -1) {
				triviaMaze[j][1] = triviaMaze[j - 1][1];
			}
		}

		for (int i = 1; i < 5; i++) {
			for (int j = 1; j < 5; j++) {
				if (triviaMaze[i][j].getStatus() != -1) {
					triviaMaze[i][j]
							.setStatus(Math.max(triviaMaze[i][j - 1].getStatus(), triviaMaze[i - 1][j].getStatus()));
				}
			}
		}
		return false;
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
