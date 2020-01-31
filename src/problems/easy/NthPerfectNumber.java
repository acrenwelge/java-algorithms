package problems.easy;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * A number is considered perfect if its digits sum up to exactly 10.

Given a positive integer n, return the n-th perfect number.

For example, given 1, you should return 19. Given 2, you should return 28.
 * @author Andrew
 *
 */
public class NthPerfectNumber {
	
	public static int getNthPerfectNumber(int n) {
		int countPerfNums = 0;
		int i = 0;
		while (countPerfNums < n) {
			i++;
			if(isPerfect(i)) countPerfNums++;
		}
		return i;
	}
	
	private static boolean isPerfect(int i) {
		char[] arr = Integer.toString(i).toCharArray();
		int sum = 0;
		for (char c : arr) {
			sum += Character.getNumericValue(c);
		}
		return sum == 10;
	}
	
	@Test
	public void defaultTests() {
		assertEquals(19, getNthPerfectNumber(1));
		assertEquals(28, getNthPerfectNumber(2));
		assertEquals(37, getNthPerfectNumber(3));
		assertEquals(109, getNthPerfectNumber(10));
		assertEquals(118, getNthPerfectNumber(11));
	}
}
