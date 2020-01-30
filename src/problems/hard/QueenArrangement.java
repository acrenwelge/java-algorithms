package problems.hard;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
public class QueenArrangement {
	
	boolean[] board; // true = queen; false = no queen. indexes start from top left to bottom right of chess board
	Set<List<Integer>> solutions = new HashSet<>();
	List<Integer> currSolution = new ArrayList<>();
	int currIdx;
	
	/*
	 * Approach:
	 * - Place each of the N queens on the next square
	 * - Check if each placement is possible
	 *   - If not, go back
	 * - Once all queens are placed, record the positions
	 * - Continue with placing queens on next first spaces until existing configuration found
	 */
	public int getQueenPlacements(int n) {
		this.board = new boolean[(int) Math.pow(n,2)];
		while(!atTheEnd()) {
			System.out.println("STARTING WITH BOARD SIZE " + n);
			// start with increment position of last queen
			if (!currSolution.isEmpty()) {
				currIdx = currSolution.get(currSolution.size()-1);
			}
			for (int currIdx=0; currIdx < this.board.length; currIdx++) {
				System.out.println(currIdx);
				if (isPossible(currIdx)) {
					this.board[currIdx] = true;
					currSolution.add(currIdx);
					if (currSolution.size() >= 8) {
						if (!solutions.contains(currSolution)) {
							System.out.println("Added another solution:");
							System.out.println(currSolution);
							solutions.add(currSolution);
						}
						this.board = new boolean[this.board.length];
						break;
					}
				}
				else if (currIdx == this.board.length - 1){
					// placing a queen on next spot is not possible and we've reached the end
					// so try placing the last queen elsewhere
					backtrackAndReplaceLastQueen();
					currIdx = currSolution.get(currSolution.size()-1);
				}
			}
			// start next search from existing state
			backtrackAndReplaceLastQueen();
		}
		return solutions.size();
	}
	
	private void backtrackAndReplaceLastQueen() {
		int lastIdx = currSolution.remove(currSolution.size()-1);
		this.board[lastIdx] = false;
	}
	
	private boolean atTheEnd() {
		// when first queen in the current solution is on the same square
		// as a 2nd queen in existing solution we know we've reached the end
		for (List<Integer> solution : solutions) {
			if(currSolution.get(0).equals(solution.get(1))) {
				return true;
			}
		}
		return false;
	}
	
	private boolean isPossible(int queenCoord) {
		/* Not possible if another queen exists in:
		 *  - same row
		 *  - same column
		 *  - same diagonal
		 * Iterate through each and check the board
		 */
		int N = (int) Math.sqrt(this.board.length);
		int qCol = queenCoord % N;
		int qRow = Math.floorDiv(queenCoord, N);
		// check row
		int minInRow = queenCoord - qCol, maxInRow = N + minInRow;
		for (int i=minInRow; i < maxInRow; i++) {
			if (this.board[i]) return false;
		}
		
		// check columns
		int minInCol = queenCoord - (N*qRow), maxInCol = this.board.length - (N-qCol);
		for (int i = minInCol; i < maxInCol; i += N) {
			if (this.board[i]) return false;
		}
		
		// check diags
		int startDiagRow = qRow - Math.min(qRow, qCol);
		int startDiagCol = qCol - Math.min(qRow, qCol);
		int currRow = startDiagRow;
		int currCol = startDiagCol;
		int check = startDiagRow * N + startDiagCol;
		// check negative slope
		while (check > 0 && check < this.board.length) {
			if (this.board[check]) return false;
			currRow = startDiagRow + 1;
			currCol = startDiagCol + 1;
			check = currRow * N + currCol;
		}
		
		// check positive slope
		check = startDiagRow * N + startDiagCol;
		while (check > 0 && check < this.board.length) {
			if (this.board[check]) return false;
			currRow = startDiagRow - 1;
			currCol = startDiagCol + 1;
			check = currRow * N + currCol;
		}
		// positive slope - other direction
		check = startDiagRow * N + startDiagCol;
		while (check > 0 && check < this.board.length) {
			if (this.board[check]) return false;
			currRow = startDiagRow + 1;
			currCol = startDiagCol - 1;
			check = currRow * N + currCol;
		}
		return true;
	}
	
	@Test
	public void testQueenAlgorithm() {
		
		assertEquals(new QueenArrangement().getQueenPlacements(1), 1);
		assertEquals(new QueenArrangement().getQueenPlacements(2), 0);
	}
	
	@Test
	public void testQueenPlacementIsPossible() {
		
		QueenArrangement qa = new QueenArrangement();
		qa.board = new boolean[] {
			true, false, false,
			false, false, false,
			false, false, false
		};
		assertFalse(qa.isPossible(2));
		assertTrue(qa.isPossible(5));
	}
	
	@Test
	public void testEndCondition() {
		fail("Not completed");
	}
	
	@Test
	public void testBacktrackWorks() {
		fail("Not completed");
	}
}
