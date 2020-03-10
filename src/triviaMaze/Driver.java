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

public class Driver {

	public static void main(String[] args) {

		Scanner kb = new Scanner(System.in);
		boolean keepPlaying = false;

		do {
			
			int difficulty = chooseDifficulty(kb);
			Maze triviaMaze = new Maze(setDifficulty(difficulty));
			Room currentRoom = triviaMaze.getMazeArray()[1][1];
			
			do {
				
				Maze.mazeSetup(triviaMaze);
				System.out.println(triviaMaze.toString());
				int directionChoice = chooseDirection(kb);
				currentRoom = movePlayer(directionChoice, triviaMaze, currentRoom);
				
			} while (triviaMaze.checkForPaths(triviaMaze.getMazeArray()) && currentRoom != triviaMaze.getEnd());
			
			if (triviaMaze.getCurrentRoom() == triviaMaze.getEnd()) {
				
				System.out.println("You Win!");
				
			} else if (!triviaMaze.checkForPaths(triviaMaze.getMazeArray())) {
				
				System.out.println("You Lose!");
				
			}
			keepPlaying = playAgain(kb);
		} while (keepPlaying);
	}

	private static int chooseDifficulty(Scanner kb) {
		int choice = 0;
		do {
			System.out.println("What difficulty do you want?\n" + "1. Easy\n" + "2. Medium\n" + "3. Hard\n");
			try {
				choice = kb.nextInt();
				kb.nextLine();
			} catch (Exception e) {
				System.out.println("Please choose a valid number(1, 2, or 3)");
			}
		} while (choice < 1 || choice > 3);

		return choice;
	}

	private static int setDifficulty(int difficulty) {
		switch (difficulty) {
		case 1:
			return 2;
		case 2:
			return 4;
		case 3:
			return 6;
		default:
			throw new IllegalArgumentException("Invalid Difficulty");
		}
	}

	private static int chooseDirection(Scanner kb) {
		int choice = 0;
		do {
			System.out.println("What do you want to do?\n" + "1. Move Up\n" + "2. Move Down\n" + "3. Move Left\n"
					+ "4. Move Right\n");
			try {
				choice = kb.nextInt();
				kb.nextLine();
			} catch (Exception e) {
				System.out.println("Please choose a valid number(1-4)");
			}
		} while (choice < 0 || choice > 4);

		return choice;
	}

	private static Room movePlayer(int choice, Maze maze, Room currentRoom) {
		switch (choice) {
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
		String again;

		System.out.println("Play again (y/n)?");
		again = kb.nextLine();

		return (again.equals("Y") || again.equals("y"));
	}

}
