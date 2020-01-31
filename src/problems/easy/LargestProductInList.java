package problems.easy;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

/**
 * Given a list of integers, return the largest product that can be made by multiplying any three integers.

For example, if the list is [-10, -10, 5, 2], we should return 500, since that's -10 * -10 * 5.

You can assume the list has at least three integers.
 * @author Andrew
 *
 */
public class LargestProductInList {
	
	/*
	 * Approach:
	 * - possibilities are: all 3 positive or 2 negative * 1 positive
	 * - sort then take the max of those possibilities
	 */
	public static int getLargestProduct(int[] ints) {
		Arrays.sort(ints);
		int maxIdx = ints.length - 1;
		int allPositive = ints[maxIdx] * ints[maxIdx-1] * ints[maxIdx-2];
		if (ints[0] >= 0) return allPositive;
		else {
			int twoNegative = ints[0] * ints[1] * ints[maxIdx];
			return Math.max(allPositive, twoNegative);
		}
	}
	
	@Test
	public void defaultTest() {
		assertEquals(500, getLargestProduct(new int[] {-10,-10,5,2}));
	}
	
	@Test
	public void customTest() {
		assertEquals(60, getLargestProduct(new int[] {1,2,3,4,5}));
		assertEquals(60, getLargestProduct(new int[] {-1,2,3,4,5}));
		assertEquals(60, getLargestProduct(new int[] {-1,-2,3,4,5}));
	}
	
}
