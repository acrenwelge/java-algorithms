package problems.medium;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * This problem was asked by Google.

On our special chessboard, two bishops attack each other if they share the same diagonal. 
This includes bishops that have another bishop located between them, i.e. bishops can attack through pieces.

You are given N bishops, represented as (row, column) tuples on a M by M chessboard. 
Write a function to count the number of pairs of bishops that attack each other. 
The ordering of the pair doesn't matter: (1, 2) is considered the same as (2, 1).

For example, given M = 5 and the list of bishops:

(0, 0)
(1, 2)
(2, 2)
(4, 0)
The board would look like this:

[b 0 0 0 0]
[0 0 b 0 0]
[0 0 b 0 0]
[0 0 0 0 0]
[b 0 0 0 0]
You should return 2, since bishops 1 and 3 attack each other, as well as bishops 3 and 4.
 * @author Andrew
 *
 */
public class Bishops {
	public int M;
	
	public static Bishops get(int boardSize) {
		Bishops b = new Bishops();
		b.M = boardSize;
		return b;
	}
	
	static class Coord {
		int row;
		int col;
		
		public Coord(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	/*
	 * Approach:
	 * - loop through bishops
	 *   - check if the bishop attacks any others that haven't been checked yet
	 *   - if so, increment the number
	 */
	public int countPairsOfAttackingBishops(List<Coord> bishops) {
		int totalAttackPairs = 0;
		for (int i=0; i < bishops.size(); i++) {
			Coord bishop = bishops.get(i);
			for (int j=i+1; j < bishops.size(); j++) {
				Coord compareBishop = bishops.get(j);
				if (attackEachOther(bishop, compareBishop)) {
					totalAttackPairs++;
				}
			}
		}
		return totalAttackPairs;
	}
	
	private boolean attackEachOther(Coord b1, Coord b2) {
		int min = Math.min(b1.col, b1.row);
		// check negative slope
		int col = b1.col - min;
		int row = b1.row - min;
		while (col < M - 1 && row < M - 1) {
			if (b2.row == row && b2.col == col) return true;
			row++;
			col++;
		}
		// check positive slope (left to right)
		col = 0;
		row = b1.row + b1.col;
		while (col < M - 1) {
			if (b2.row == row && b2.col == col) return true;
			col++;
			row--;
		}
		return false;
	}
	
	@Test
	public void defaultTest() {
		Coord b1 = new Coord(0,0);
		Coord b2 = new Coord(1,2);
		Coord b3 = new Coord(2,2);
		Coord b4 = new Coord(4,0);
		List<Coord> bishops = Arrays.asList(b1,b2,b3,b4);
		assertEquals(2, Bishops.get(5).countPairsOfAttackingBishops(bishops));
	}
}
