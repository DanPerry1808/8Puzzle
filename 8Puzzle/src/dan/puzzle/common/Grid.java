package dan.puzzle.common;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class Grid {
	
	private static final int SQUARE_SIZE = 100;
	private static final int GRID_SIZE = 3;
	
	private int[][] grid;
	private static final int[][] WIN_STATE = new int[][] {{1, 2, 3},{4, 5, 6},{7, 8, 0}};
	
	public Grid() {
		grid = new int[GRID_SIZE][GRID_SIZE];
		generateRandomGrid();
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
			sorted.remove(randNum);
		}
		
	}
	
	public void draw(Graphics g) {
		for(int i = 0; i < GRID_SIZE; i++) {
			for(int j = 0; j < GRID_SIZE; j++) {
				g.drawString(Integer.toString(grid[i][j]), j * 50 + 50, i * 50 + 50);
			}
		}
	}

}
