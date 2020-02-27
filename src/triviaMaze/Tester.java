package triviaMaze;

import java.util.Scanner;

/*
 * Author: Justin Entz
 * 
 * Purpose: This class is current acting as the driver/testing file that will display options to the player and call maze methods
 * 
 * Version: 0.1
 * 
 */

public class Tester {

	public static void main(String[] args) {

		Scanner kb = new Scanner(System.in);
		boolean keepPlaying = false;

		do {
			Maze triviaMaze = new Maze(4, 4);
			Room currentRoom = triviaMaze.setStart();
			do {
				System.out.println(triviaMaze.toString());

				int choice = chooseDirection(kb);
				currentRoom = movePlayer(choice, triviaMaze, currentRoom);
			} while (triviaMaze.checkForPaths(triviaMaze.getMazeArray()) && currentRoom != triviaMaze.getMazeArray()[4][4]);
			keepPlaying = playAgain(kb);
		} while (keepPlaying);
	}

	private static int chooseDirection(Scanner kb) {
		int choice = 0;
		do {
			System.out.println("What do you want to do?\n" 
					+ "1. Move Up\n" 
					+ "2. Move Down\n" 
					+ "3. Move Left\n"
					+ "4. Move Right\n");
			try {
				choice = kb.nextInt();
			} catch (Exception e) {
				System.out.println("Please choose a valid number(1-4)");
			}
		} while (choice < 0 || choice > 4);

		return choice;
	}

	private static Room movePlayer(int choice, Maze maze, Room currentRoom) {
		switch(choice) {
		case 1:
			return currentRoom = maze.navigateMaze("Up");
		case 2:
			return currentRoom = maze.navigateMaze("Down");
		case 3:
			return currentRoom = maze.navigateMaze("Left");
		case 4:
			return currentRoom = maze.navigateMaze("Right");
		default:
			throw new IllegalArgumentException("Invalid Action");
		}
	}
	
	

	private static boolean playAgain(Scanner kb) {
		System.out.println("Do you want to play again? (y/n)");
		String choice = kb.nextLine();
		choice.toLowerCase();
		return choice.equals("y");
	}

	public String getAnswer(Scanner kb) {
		System.out.println("Choose an answer");
		String userAnswer = kb.nextLine();
		return userAnswer;
	}
}
