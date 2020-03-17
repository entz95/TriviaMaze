package mazeTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import triviaMaze.Maze;

class MazeTests {

			private final Maze maze = new Maze(4);
	@Test
	void getXPositionTest() {
		assertEquals(maze.getXPosition(), 1);
	}

	@Test
	void setXPositionTest_ThrowsIllegalArgumentException() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			maze.setXPosition(-1);
		});
		assertEquals("Cannot set x position smaller than 0", exception.getMessage());
	}
	
	@Test
	void getYPositionTest() {
		assertEquals(maze.getYPosition(), 1);
	}
	
	@Test
	void setYPositionTest_ThrowsIllegalArgumentException() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			maze.setYPosition(-1);
		});
		assertEquals("Cannot set y position smaller than 0", exception.getMessage());
	}
	
	@Test
	void navigateMaze_ThrowsIllegalArgumentException() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			maze.navigateMaze(null);
		});
		assertEquals("input is not valid", exception.getMessage());
	}
	
	@Test
	void checkForPaths_ThrowsIllegalArgumentException() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			maze.checkForPaths(null);
		});
		assertEquals("Maze is null", exception.getMessage());
	}
	
	@Test
	void getCurrrentRoomTest() {
		assertEquals(maze.getMazeArray()[1][1], maze.getCurrentRoom());
	}
	
	@Test
	void getEndTest() {
		assertEquals(maze.getMazeArray()[4][4], maze.getEnd());
	}
	
}
