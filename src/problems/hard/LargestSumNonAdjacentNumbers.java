package problems.hard;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * This problem was asked by Airbnb.

Given a list of integers, write a function that returns the largest sum of non-adjacent numbers. Numbers can be 0 or negative.

For example, [2, 4, 6, 2, 5] should return 13, since we pick 2, 6, and 5. [5, 1, 1, 5] should return 10, since we pick 5 and 5.

Follow-up: Can you do this in O(N) time and constant space?
 * @author Andrew
 *
 */
public class LargestSumNonAdjacentNumbers {
	
	/**
	 * Approach:
	 * - calculate largest possible sum at each point in the array
	 */
	public int solution(int[] arr) {
		int[] sums = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			if (i == 0) sums[0] = arr[0];
			else if (i == 1) sums[1] = Math.max(arr[0], arr[1]);
			else sums[i] = Math.max(sums[i-2] + arr[i], sums[i-1]);
		}
		return sums[arr.length-1];
	}
	
	@Test
	public void defaultTest() {
		assertEquals(13, solution(new int[] {2, 4, 6, 2, 5}));
		assertEquals(10, solution(new int[] {5, 1, 1, 5}));
	}
	
	@Test
	public void customTest() {
		assertEquals(33, solution(new int[] {4, 1, 9, 3, 9, 20, 2}));
	}
	
}
