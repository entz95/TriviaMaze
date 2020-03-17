package triviaMaze;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import application.DBInitializer;
import application.DBUpdater;

/*
 * Author: Justin Entz & Bryan Wilson
 * Purpose: This class is current acting as the driver/testing file that will display options to the player and call maze methods
 * Date Modified 3/16/2020
 * Version: 1.0
 * 
 * Cheat Codes:
 * --On select difficulty: 1938 will produce an Easy maze that is 2x2.
 * 
 */

public class Driver {

	public static void main(String[] args) {
		
		Scanner kb = new Scanner(System.in);
		System.out.println("Welcome to the D&D Trivia Maze! Please select an option below!");
		int option = 0;
		
		do {
			System.out.println("1. Play the Game \n2. Add questions \n3. Reinitialize database " +
								"\n4. Help \n0. Exit");
			try {
				option = kb.nextInt();
				kb.nextLine();
			} catch (InputMismatchException e) {
				option = -1;
				kb.nextLine();
			}
			
			switch(option) {
			case 1:
				playGame(kb);
				break;
			case 2:
				admin(kb);
				break;
			case 3:
				initDB(kb);
				break;
			case 4:
				showHelp();
				break;
			default:
				System.out.println("Please enter a valid number...");
				break;
			}
		} while(option != 0);
	}
	
	private static void admin(Scanner kb) {
		String cont = "n";
		do {
			System.out.print("What type of question do you want to add: TF or MC?  ");
			String select = kb.nextLine();
			switch(select) {
			case "TF":
				addTF(kb);
				break;
			case "MC":
				addMC(kb);
				break;
			default:
				System.out.println("Invalid selection.");
				break;
			}
			
			System.out.println("Continue? (y/n): ");
			cont = kb.nextLine();
		} while (cont.equals("y") || cont.equals("Y"));
		System.out.println("Please restart program to reflect database changes...");
	}
	
	private static void addTF(Scanner kb) {
		String ques, ans;
		
		System.out.print("Enter a question: ");
		ques = kb.nextLine();
		System.out.print("Enter the answer, TRUE or FALSE: ");
		ans = kb.nextLine();
		
		if(ans.toLowerCase().equals("true") || ans.toLowerCase().equals("false")) {
			ans = ans.toUpperCase();
			boolean success = DBUpdater.updateTrueFalse(ques, ans);
			
			if(!success) {
				System.out.println("There was an error adding the question...");
			}
			
		} else {
			System.out.println("Answer did not match TRUE or FALSE. Question not added.");
		}
		
	}
	
	private static void addMC(Scanner kb) {
		String ques, ans, optA, optB, optC, optD;
		
		System.out.print("Enter a question: ");
		ques = kb.nextLine();
		System.out.print("Enter the answer: ");
		ans = kb.nextLine();
		System.out.print("Enter option A: ");
		optA = kb.nextLine();
		System.out.print("Enter option B: ");
		optB = kb.nextLine();
		System.out.print("Enter option C: ");
		optC = kb.nextLine();
		System.out.print("Enter option D: ");
		optD = kb.nextLine();
		
		if(ans.toLowerCase().equals(optA.toLowerCase()) ||
				ans.toLowerCase().equals(optB.toLowerCase()) ||
				ans.toLowerCase().equals(optC.toLowerCase()) || 
				ans.toLowerCase().equals(optD.toLowerCase())) {
			
			boolean success = DBUpdater.updateMultipleChoice(ques, ans, optA, optB, optC, optD);
			if(!success) {
				System.out.println("There was an error adding the question...");
			}
		} else {
			System.out.println("Answer did not match any given options. Question not added.");
		}
		
	}
	
	private static void initDB(Scanner kb) {
		System.out.print("Caution! This option will initialize the databases to a clean state. Enter 'y' to continue: ");
		String choice = kb.nextLine();
		if(choice.toLowerCase().equals("y")) {
			System.out.print("This choice is permanent. Any questions manually added will be lost. Enter 'y' to continue: ");
			choice = kb.nextLine();
			if(choice.toLowerCase().equals("y")) {
				System.out.print("WARNING! You are about to reinitialize the database. Enter 'confirm' to proceed with initilization: ");
				choice = kb.nextLine();
				if(choice.toLowerCase().equals("confirm")) {
					System.out.println("Reinitiaizing database...");
					DBInitializer.initializeMultipleChoice();
					DBInitializer.initializeTrueFalse();
					System.out.println("Database reinitialized.");
				}
			}
		}
	}
	
	private static void playGame(Scanner kb) {
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
	
	private static void showHelp() {
		System.out.println("Welcome to the Dungeons and Dragons Trivia Maze, created by Justin Entz and Bryan Wilson!"+
				"\nThis game makes use of the Dungeons and Dragons 5th Edition ruleset published and owned by" +
				"\nWizards of the Coast. This work is purely for educational purposes and also serves as a project" +
				"\nby two dedicated fans of D&D.");
		System.out.println();
		System.out.println("This help option is meant to answer any minor questions you may have. Below are some"+
				"\ncommon questions that you may have:"+
				"\n--As mentioned above, this game uses the 5th edition ruleset of Dungeons and Dragons, so all the"+
				"\nquestions and answers are drawn from that." +
				"\n--This application is entirely text based and can be run with nothing but your keyboard! In"+
				"\naddition, the input to any given question can be formatted with or without capitalization. Just"+
				"make sure all the letters are right!"+
				"\n--You can add your own questions to add to the fun! Select 'Add questions' at the main menu" +
				"\nto add in questions at your leisure."+
				"\n--If somehow the question database gets deleted or otherwise becomes unusable, fret not! You can"+
				"\nreinitialize the database from the main menu. Be warned though, this gives the database a clean"+
				"\nslate, so anything that has been added to it will be removed! Use with caution!"+
				"\n--When you select 'Play game', you can choose between Normal, Hard, and Harder difficulties."+
				"\nThey may also be an Easy mode for those who know the year Gygax was born. ;)");
		System.out.println();
		System.out.println("That's it! Follow the onscreen instructions and have fun! Just beware, the"+
				"\nexit of the maze is locked just like the rest...with the same penalty for failure.");
		System.out.println();
				
	}

}
