package triviaMaze;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import application.DBInitializer;

/*
 * Author: Justin Entz
 * Purpose: This class is current acting as the driver/testing file that will display options to the player and call maze methods
 * Date Modified 3/16/2020
 * Version: 0.9
 * 
 */

public class Driver {

	public static void main(String[] args) {
		
		DBInitializer.initializeMultipleChoice();
		DBInitializer.initializeTrueFalse();
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
				
				System.out.println("No more paths to the exit, You Lose!");
				
			}
			keepPlaying = playAgain(kb);
		} while (keepPlaying);
	}
	

	private static int chooseDifficulty(Scanner kb) {
		int choice = 0;
		do {
			System.out.println("What difficulty do you want?\n" + "1. Normal\n" + "2. Hard\n" + "3. Harder\n");
			try {
				choice = kb.nextInt();
				kb.nextLine();
			} catch (Exception e) {
				System.out.println("Please choose a valid number(1, 2, or 3)");
			}
		} while (choice < 1 || choice > 3 && choice != 1938);

		return choice;
	}

	private static int setDifficulty(int difficulty) {
		switch (difficulty) {
		case 1:
			return 4;
		case 2:
			return 5;
		case 3:
			return 6;
		case 1938:
			return 2;
		default:
			throw new IllegalArgumentException("Invalid Difficulty");
		}
	}

	private static int chooseDirection(Scanner kb) {
		int choice = 0;
		do {
			System.out.println("What do you want to do?\n" + "1. Move Up\n" + "2. Move Down\n" + "3. Move Left\n"
					+ "4. Move Right\n" + "5. Save Game\n" + "6. Load Game\n");
			try {
				choice = kb.nextInt();
				kb.nextLine();
			} catch (Exception e) {
				System.out.println("Please choose a valid number(1-6)");
			}
		} while (choice < 0 || choice > 6);

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
		case 5:
			try {
				saveState(maze, currentRoom);
			} catch(Exception e){
				System.out.println("Could not save trivia maze state...");
				e.printStackTrace();
			}
			break;
		case 6:
			try {
				ArrayList<Object> saves = loadFile();
				maze.setMaze((Maze)saves.get(0));
				currentRoom.setRoom((Room)saves.get(1));
			} catch(Exception e) {
				System.out.println("Could not load trivia maze");
				e.printStackTrace();
			}
			break;
		default:
			throw new IllegalArgumentException("Invalid Action");
		}
		return currentRoom;
	}
	
	private static final void saveState(Maze maze, Room currentRoom) throws IOException {
		ArrayList<Object> toSave = new ArrayList<Object>();
		toSave.add(maze);
		toSave.add(currentRoom);
		try {
			File SaveFile = new File("./SaveGame");
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(SaveFile));
			out.writeObject(toSave);
			out.close();
			System.out.println("...Trivia Maze Saved...");
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	private static final ArrayList<Object> loadFile() throws IOException, ClassNotFoundException {
		ArrayList<Object> toLoad = new ArrayList<Object>();
		try {
			File saveFile = new File("./SaveGame");
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(saveFile));
			toLoad = (ArrayList<Object>) in.readObject();
			in.close();
			System.out.println("Maze loaded!");
		} catch(IOException e) {
			e.printStackTrace();
		}
		return toLoad;
	}

	private static boolean playAgain(Scanner kb) {
		String again;

		System.out.println("Play again (y/n)?");
		again = kb.nextLine();

		return (again.equals("Y") || again.equals("y"));
	}

}
