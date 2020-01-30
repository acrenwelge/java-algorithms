package problems.hard;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Ignore;
import org.junit.Test;

/**
 * This problem was asked by Microsoft.

You have an N by N board. Write a function that, given N, returns 
the number of possible arrangements of the board where N queens can be 
placed on the board without threatening each other, i.e. no two queens share the same row, column, or diagonal.
 * @author Andrew
 *
 */
@Ignore
public class QueenArrangement2 {
	
	boolean[][] board;
	Set<int[]> solutions = new HashSet<>();
	int[] currSolution;
	
	/*
	 * Approach:
	 * - place queens from top to bottom rows
	 * - check valid placement
	 * - if invalid then move to next cell, if no more cells then backtrack
	 * - stop when backtracking at first row
	 */
	public int getNumArrangements(int n) {
		this.board = new boolean[n][n];
		currSolution = new int[board.length];
		ROWS: for (int row=0; row < board.length; row++) {
			COLUMNS: for (int col=0; col < board.length; col++) {
				System.out.println("ROW:"+row+";COL:"+col);
				if (!underAttack(row,col)) {
					System.out.println("R"+row+"C"+col+" NOT UNDER ATTACK");
					currSolution[row] = col;
					board[row][col] = true;
					col = 0;
					if (row == board.length-1) {
						solutions.add(currSolution);
						// start next search from previous position
						row--;
						currSolution = new int[board.length]; // reset solution
						board[row][col] = false;
					}
				} 
			}
		}
		for (int[] solution : solutions) {
			for (int i : solution) {
				System.out.println(i);
			}
		}
		return solutions.size();
	}
	
	public boolean underAttack(int currRow, int currCol) {
		int len = board.length;
		// check rows and columns
		for (int i=0; i < len; i++) {
			if (board[i][currCol] || board[currRow][i]) return true;
		}
		
		// check diagonals
		for (int j=currRow, k = currCol; j >= 0 && k >= 0; j--, k--) {
			if (board[j][k]) return true;
		}
		for (int j=currRow, k = currCol; j < len && k < len; j++, k++) {
			if (board[j][k]) return true;
		}
		return false;
	}
	
	@Test
	public void testNIs4() {
		assertEquals(2, new QueenArrangement2().getNumArrangements(4));
	}
	
	@Test
	public void testUnderAttack() {
		QueenArrangement2 qa = new QueenArrangement2();
		qa.board = new boolean[][] {
			{false, false, false, false},
			{false, false, false, false},
			{false, false, false, false},
			{false, false, false, false}
		};
		assertFalse(qa.underAttack(3, 3));
		qa.board = new boolean[][] {
			{true, false, false, false},
			{false, false, false, false},
			{false, false, false, false},
			{false, false, false, false}
		};
		assertTrue(qa.underAttack(3, 3));
		qa.board = new boolean[][] {
			{true, false, false, false},
			{false, false, false, false},
			{false, false, false, false},
			{false, false, false, false}
		};
		assertFalse(qa.underAttack(1, 3));
		qa.board = new boolean[][] {
			{false, false, true, false},
			{true, false, false, false},
			{false, false, false, false},
			{false, true, false, false}
		};
		assertFalse(qa.underAttack(2, 3));
	}
}
