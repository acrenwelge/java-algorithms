package problems.medium;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * There is an N by M matrix of zeroes. Given N and M, write a function to count the number of ways 
 * of starting at the top-left corner and getting to the bottom-right corner. You can only move right or down.

For example, given a 2 by 2 matrix, you should return 2, since there are two ways to get to the bottom-right:

Right, then down
Down, then right

Given a 5 by 5 matrix, there are 70 ways to get to the bottom-right.
 * @author Andrew
 *
 */
public class MatrixNavigation {
	int ways;
	
	/*
	 * Sample cases:
	 * 2 x 2 -> 2
	 * x|x
	 * x|x
	 * 
	 * 3 x 2 -> 3
	 * x|x|x
	 * x|x|x
	 * 
	 * 3 x 3 -> 6
	 * x|x|x
	 * x|x|x
	 * x|x|x
	 */
	
	/*
	 * Approach:
	 * - start from bottom right corner
	 * - identify # of different ways to get to that cell (by going down/right)
	 * - recurse backwards until the path leads back to top left
	 */
	public int navigate(int m, int n) {
		return recurse(m-1,n) + recurse(m,n-1);
	}
	
	public int recurse(int x, int y) {
		if (x == 1 || y == 1) return 1;
		return recurse(x-1,y) + recurse(x,y-1);
	}
	
	@Test
	public void defaultTest() {
		assertEquals(2, navigate(2,2));
		assertEquals(3, navigate(3,2));
		assertEquals(6, navigate(3,3));
		assertEquals(70, navigate(5,5));
	}
}
