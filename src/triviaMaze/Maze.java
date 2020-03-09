package triviaMaze;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import application.DBGetter;
import question.Question;

/*
 * Author: Justin Entz
 * 
 * Purpose: This class creates a maze object that is a 2d array of rooms, it moves the player from one room to the next, checks to see if you can still win the game and adjusts player position
 * and room status.
 * 
 * Version: 1.0
 * 
 */

//in the navigate maze class we might want to make a separate method that we call to display and answer the question and change room status from closed to open/sealed to see if they can then move so we have more readable code
//and also implement another method that checks to see if there are any possible paths to the exit

public class Maze implements Serializable {

	private static final long serialVersionUID = -6846687447036983692L;
	private Room[][] mazeArray;
	private int posX;
	private int posY;

	public Maze(int x) {
		generateMaze(x, x);
		this.posX = 1;
		this.posY = 1;
	}

	private void generateMaze(int rows, int columns) {
		
		assert rows > 0 : "rows is zero or less";
		assert columns > 0 : "columns is zero or less";

		this.mazeArray = new Room[rows + 2][columns + 2];

		for (int i = 0; i <= rows; i++) {
			
			for (int j = 0; j <= columns; j++) {
				
				mazeArray[i][j] = Room.nullRoom();
			}
			
		}
		
		ArrayList<Question> questions = DBGetter.getAllQuestions();
		Random rng = new Random();

		for (int i = 1; i <= rows; i++) {
			
			for (int j = 1; j <= columns; j++) {
				
				int index = rng.nextInt(questions.size());
				mazeArray[i][j] = new Room(questions.get(index));
				
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

	public Room[][] getMazeArray() {
		return mazeArray;
	}

	public Room navigateMaze(String input) {
		assert input != null : "input is not valid";

		int currentXPosition = this.posX;
		int currentYPosition = this.posY;
		Room currentRoom = mazeArray[currentXPosition][currentYPosition];
		Room nextRoom;

		try {
			switch (input) {
			
			case ("Up"): // case for moving up/north
				if(checkBounds(currentXPosition, currentYPosition - 1)) {
					
				currentYPosition = currentYPosition - 1;
				
				}else {
					System.out.println("You cannot move " + input);
				}
			
				nextRoom = moveRoom(currentRoom, mazeArray[currentXPosition][currentYPosition]);
				break;
				
			case ("Down"): // case for moving down/south
				if(checkBounds(currentXPosition, currentYPosition + 1)) {
					
				currentYPosition = currentYPosition + 1;

				}else {
					System.out.println("You cannot move " + input);
				}
			
				nextRoom = moveRoom(currentRoom, mazeArray[currentXPosition][currentYPosition]);
				break;
				
			case ("Left"): // case for moving left/west
				if(checkBounds(currentXPosition - 1, currentYPosition)) {
					
				currentXPosition = currentXPosition - 1;

				}else {
					System.out.println("You cannot move " + input);
				}
			
				nextRoom = moveRoom(currentRoom, mazeArray[currentXPosition][currentYPosition]);
				break;
				
			case ("Right"): // case for moving right/east
				if(checkBounds(currentXPosition + 1, currentYPosition)) {
				
					currentXPosition = currentXPosition + 1;

				}else {
					System.out.println("You cannot move " + input);
				}
			
				nextRoom = moveRoom(currentRoom, mazeArray[currentXPosition][currentYPosition]);
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

		Scanner kb = new Scanner(System.in);

		if (next != null) {

			if (next.getStatus() == -1) {// if room is sealed
				System.out.println("Room is sealed");
				return current;
			} else if (next.getStatus() == 0) {// if room is closed
				
				System.out.println("Room is locked. Answer question correctly to progress.");
				System.out.println(next.displayQuestion(next.getQuestion()));
				System.out.println();
				
				String Useranswer = kb.nextLine();
				
				if (Useranswer.equals(next.getQuestion().getAnswer())) {
					System.out.println("you answered correctly and move into the next room");
					next.setStatus(1);
					return next;
				} else {
					System.out.println("Incorrect! The correct answer was: " + next.getQuestion().getAnswer());
					next.setStatus(-1);
					return current;
				}
			} else
				// room will be open if not locked or sealed
				return next;
		} else {
			System.out.println("you cannot move there. Room is null.");
			return current;
		}
	}
	
	private boolean checkBounds(int x, int y) {
		
		if(x < 1) return false;
		if(y < 1) return false;
		if(x > mazeArray.length - 2) return false;
		if(y > mazeArray.length - 2) return false;
		return true;
 	}

	public boolean checkForPaths(Room[][] triviaMaze) {
		
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

	public Room setStart() {
		return mazeArray[1][1];
	}
	
	public Room getCurrentRoom() {
		return mazeArray[posX][posY];
	}

	public Room getEnd() {
		return mazeArray[mazeArray.length - 2][mazeArray.length - 2];
	}

}
