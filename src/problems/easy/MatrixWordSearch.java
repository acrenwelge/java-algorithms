package problems.easy;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * This problem was asked by Microsoft.

Given a 2D matrix of characters and a target word, write a function that returns whether 
the word can be found in the matrix by going left-to-right, or up-to-down.

For example, given the following matrix:

[['F', 'A', 'C', 'I'],
 ['O', 'B', 'Q', 'P'],
 ['A', 'N', 'O', 'B'],
 ['M', 'A', 'S', 'S']]
and the target word 'FOAM', you should return true, since it's the leftmost column. Similarly, 
given the target word 'MASS', you should return true, since it's the last row.
 * @author Andrew
 *
 */
public class MatrixWordSearch {
	
	public boolean containsWord(char[][] matrix, String word) {
		for (int i=0; i < matrix.length; i++) {
			StringBuilder across = new StringBuilder();
			for (int j=0; j < matrix[i].length; j++) {
				across.append(matrix[i][j]);
			}
			if (across.toString().equals(word)) return true;
		}
		for (int i=0; i < matrix[0].length; i++) {
			StringBuilder down = new StringBuilder();
			for (int j=0; j < matrix.length; j++) {
				down.append(matrix[j][i]);
			}
			if (down.toString().equals(word)) return true;
		}
		return false;
	}
	
	@Test
	public void defaultTest() {
		char[][] matrix = {
				{'F','A','C','I'},
				{'O', 'B', 'Q', 'P'},
				{'A', 'N', 'O', 'B'},
				{'M', 'A', 'S', 'S'},
				};
		assertTrue(containsWord(matrix,"FOAM"));
		assertTrue(containsWord(matrix,"MASS"));
		assertFalse(containsWord(matrix,"BACK"));
	}
}
