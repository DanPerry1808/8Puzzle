package dan.puzzle.common;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class Grid {
	
	private static final int SQUARE_SIZE = 100;
	private static final int GRID_SIZE = 3;
	
	private int[][] grid;
	private static final int[][] WIN_STATE = new int[][] {{1, 2, 3},{4, 5, 6},{7, 8, 0}};
	private int[] blankPos;
	
	public Grid() {
		grid = new int[GRID_SIZE][GRID_SIZE];
		// Fallback value for blankPos
		blankPos = new int[] {-1, -1};
		generateRandomGrid();
	}
	
	public void moveBlank(Direction d) {
		if(isValidMove(d)) {
			System.out.println("Valid");
			switch(d) {
			case DOWN:
				// Swaps the zero and the tile it
				grid[blankPos[0]][blankPos[1]] = grid[blankPos[0] + 1][blankPos[1]];
				blankPos[0]++;
				grid[blankPos[0]][blankPos[1]] = 0;
				break;
			case LEFT:
				// Swaps the zero and the tile to the left of it
				grid[blankPos[0]][blankPos[1]] = grid[blankPos[0]][blankPos[1] - 1];
				blankPos[1]--;
				grid[blankPos[0]][blankPos[1]] = 0;
				break;
			case UP:
				// Swaps the zero and the tile above it
				grid[blankPos[0]][blankPos[1]] = grid[blankPos[0] - 1][blankPos[1]];
				blankPos[0]--;
				grid[blankPos[0]][blankPos[1]] = 0;
				break;
			case RIGHT:
				// Swaps the zero and the tile to the right of it
				grid[blankPos[0]][blankPos[1]] = grid[blankPos[0]][blankPos[1] + 1];
				blankPos[1]++;
				grid[blankPos[0]][blankPos[1]] = 0;
				break;
			}
		
			System.out.println("New blankPos: " + blankPos[0] + ", " + blankPos[1]);
		}
	}
	
	private void generateRandomGrid() {
		ArrayList<Integer> sorted = new ArrayList<Integer>();
		int highestNum = GRID_SIZE * GRID_SIZE;
		for(int i = 0; i < highestNum; i++) {
			sorted.add(i);
		}
		
		Random r = new Random();
		for(int i = 0; i < highestNum; i++) {
			int randNum = r.nextInt(sorted.size());
			int row = i / GRID_SIZE;
			int col = i % GRID_SIZE;
			grid[row][col] = sorted.get(randNum);
			if(sorted.get(randNum) == 0) {
				blankPos = new int[] {row, col};
			}
			sorted.remove(randNum);
		}
		
	}
	
	private boolean isValidMove(Direction d) {
		switch(d){
		case UP:
			if(blankPos[0] == 0) {
				return false;
			}else {
				return true;
			}
		case RIGHT:
			if(blankPos[1] == GRID_SIZE - 1){
				return false;
			}else {
				return true;
			}
		case DOWN:
			if(blankPos[0] == GRID_SIZE - 1) {
				return false;
			}else {
				return true;
			}
		case LEFT:
			if(blankPos[1] == 0) {
				return false;
			}else {
				return true;
			}
		}
		return false;
	}
	
	public void draw(Graphics g) {
		for(int i = 0; i < GRID_SIZE; i++) {
			for(int j = 0; j < GRID_SIZE; j++) {
				if(grid[i][j] != 0) {
					g.drawString(Integer.toString(grid[i][j]), j * 50 + 50, i * 50 + 50);
				}
			}
		}
	}

}
