package problems.medium;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/*
 * This problem was asked by Facebook.

A builder is looking to build a row of N houses that can be of K different colors. 
He has a goal of minimizing cost while ensuring that no two neighboring houses are of the same color.

Given an N by K matrix where the nth row and kth column represents the cost 
to build the nth house with kth color, return the minimum cost which achieves this goal.
 */
public class HouseCostMinimization {
	/*
	 * Approach: look at a sample matrix
	 * ------k (color) ------
	 * | 4   5   3*
	 * n 3   2*  1
	 * | 1*  3   6
	 *  solution here is 6 (3+2+1) - houses are m[1][2], m[2][1], m[0][0]
	 *  
	 * Observations:
	 * No 2 neighboring houses can have same color, so k index of m[n] != m[n-1] OR m[n+1]
	 * Each house has a dependency on the house before and after it
	 * Pick the minimum cost for each house given that the next and previous 
	 * houses of minimum cost don't have the same color (k index)
	 * 
	 * Try looping through rows, pick the minimum cost in that row
	 * - skip the picked index in the next row
	 * *This is naive and incorrect, will not guarantee global minimum*
	 * Also, runtime is O(nk)
	 */
	public static int findMinCost(int[][] matrix) {
		System.out.println("===================");
		int forbiddenIdx = -1;
		int totCost = 0;
		for (int n=0; n < matrix.length; n++) {
			int minCost = 0;
			for (int j = 0; j < matrix[n].length; j++) {
				if (j != forbiddenIdx) {
					minCost = matrix[n][j];
					break;
				}
			}
			int newForbid = forbiddenIdx; // use a temporary variable to store the new forbidden index
			for (int k=0; k < matrix[n].length; k++) {
				if (k != forbiddenIdx && matrix[n][k] < minCost) {
					minCost = matrix[n][k];
					newForbid = k; // exclude this index in the next loop
				}
			}
			forbiddenIdx = newForbid; // don't set the actual forbidden index until after we're done looping through the row
			System.out.format("At n=%d,k=%d, picked %d %n",n,forbiddenIdx,minCost);
			totCost += minCost;
		}
		return totCost;
	}
	
	@Test
	public void myTest() {
		// solution here is 6 (3+2+1) - houses are m[1][2], m[2][1], m[0][0]
		assertEquals(6, findMinCost(new int[][] {
			{4, 5, 3},
			{3, 2, 1},
			{1, 3, 6}
		}));
	}
	
	@Test
	public void chooseGlobalMin() {
		// solution here is 11 (9+1+1) - houses are m[1][2], m[2][1], m[0][0]
		assertEquals(11, findMinCost(new int[][] {
			{11, 5, 9}, // should pick 9
			{8, 1, 8},  // should pick 1
			{1, 9, 9}   // should pick 1
		}));
	}
	
	@Test
	public void noAdjacentColors() {
		assertEquals(8, findMinCost(new int[][] {
			{5, 1, 9},  // should pick 1
			{5, 1, 9},  // should pick 5
			{5, 2, 9}   // should pick 1
		}));
		// solution here is 8 (1+5+2) - houses are m[1][1], m[2][0], m[0][1]
	}
}
