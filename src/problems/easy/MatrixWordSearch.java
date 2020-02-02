package problems.easy;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

/**
 * This problem was asked by Microsoft.
 * 
 * Given a 2D matrix of characters and a target word, write a function that
 * returns whether the word can be found in the matrix by going left-to-right,
 * or up-to-down.
 * 
 * For example, given the following matrix:
 * 
 * [['F', 'A', 'C', 'I'],
 *  ['O', 'B', 'Q', 'P'],
 *  ['A', 'N', 'O', 'B'],
 *  ['M', 'A', 'S', 'S']] 
 *  
 * and the target word 'FOAM', you should return true, since it's the
 * leftmost column. Similarly, given the target word 'MASS', you should return
 * true, since it's the last row.
 * 
 * @author Andrew
 *
 */
public class MatrixWordSearch {

	public boolean containsWord(char[][] matrix, String word) {
		for (int i = 0; i < matrix.length; i++) {
			StringBuilder across = new StringBuilder();
			for (int j = 0; j < matrix[i].length; j++) {
				across.append(matrix[i][j]);
			}
			if (across.toString().contains(word))
				return true;
		}
		for (int i = 0; i < matrix[0].length; i++) {
			StringBuilder down = new StringBuilder();
			for (int j = 0; j < matrix.length; j++) {
				down.append(matrix[j][i]);
			}
			if (down.toString().contains(word))
				return true;
		}
		return false;
	}

	@Test
	public void defaultTest() {
		char[][] matrix = { { 'F', 'A', 'C', 'I' }, { 'O', 'B', 'Q', 'P' }, { 'A', 'N', 'O', 'B' },
				{ 'M', 'A', 'S', 'S' }, };
		assertTrue(containsWord(matrix, "FOAM"));
		assertTrue(containsWord(matrix, "MASS"));
		assertFalse(containsWord(matrix, "BACK"));
		assertTrue(containsWord(matrix, "NO"));
	}

	/**
	 * v2 - This problem was asked by Coursera.
	 * 
	 * Given a 2D board of characters and a word, find if the word exists in the
	 * grid.
	 * 
	 * The word can be constructed from letters of sequentially adjacent cell, where
	 * "adjacent" cells are those horizontally or vertically neighboring. The same
	 * letter cell may not be used more than once.
	 * 
	 * For example, given the following board:
	 * 
	 * [ ['A','B','C','E'],
	 *   ['S','F','C','S'],
	 *   ['A','D','E','E'] ]
	 * 
	 * exists(board, "ABCCED") returns true, exists(board, "SEE") returns true, 
	 * exists(board, "ABCB") returns false.
	 */
	public boolean containsWordMultiDirection(char[][] board, String word) {
		for (int row=0; row < board.length; row++) {
			for (int col=0; col < board[row].length; col++) {
				char c = board[row][col];
				Set<Coord> visited = new HashSet<>();
				visited.add(new Coord(row,col));
				if (c == word.charAt(0) && exploreWord(board,row,col,word,0,visited)) return true;
			}
		}
		return false;
	}
	
	static class Coord {
		int row;
		int col;
		public Coord(int r, int c) {
			this.row = r;
			this.col = c;
		}
		@Override
		public boolean equals(Object o) {
			if (!(o instanceof Coord)) return false;
			Coord c = (Coord) o;
			return c.row == row && c.col == col;
		}
		@Override
		public int hashCode() {
			int prime = 31;
			return prime * (row+1) * (col+1);
		}
	}
	
	private boolean exploreWord(char[][] board, int row, int col, String word, int charIdx, Set<Coord> visited) {
		charIdx++;
		if (charIdx == word.length()) return true;
		char searchFor = word.charAt(charIdx);
		if (row-1 > 0 && board[row-1][col] == searchFor && !visited.contains(new Coord(row-1,col))) {
			visited.add(new Coord(row-1,col));
			return exploreWord(board,row-1,col,word,charIdx,visited);
		}
		if (row+1 < board.length && board[row+1][col] == searchFor && !visited.contains(new Coord(row+1,col))) {
			visited.add(new Coord(row+1,col));
			return exploreWord(board,row+1,col,word,charIdx,visited);
		}
		if (col-1 > 0 && board[row][col-1] == searchFor && !visited.contains(new Coord(row,col-1))) {
			visited.add(new Coord(row,col-1));
			return exploreWord(board,row,col-1,word,charIdx,visited);
		}
		if (col+1 < board.length && board[row][col+1] == searchFor && !visited.contains(new Coord(row,col+1))) {
			visited.add(new Coord(row,col+1));
			return exploreWord(board,row,col+1,word,charIdx,visited);
		}
		return false;
	}
	
	@Test
	public void defaultTestv2() {
		char[][] board = new char[][] {
			{'A','B','C','E'},
			{'S','F','C','S'},
			{'A','D','E','E'},
		};
		assertTrue(containsWordMultiDirection(board, "ABCCED"));
		assertTrue(containsWordMultiDirection(board, "SEE"));
		assertFalse(containsWordMultiDirection(board, "ABCB"));
	}
}
