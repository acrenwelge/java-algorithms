package problems.medium;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Given an array of integers, write a function to determine whether the array could 
 * become non-decreasing by modifying at most 1 element.

For example, given the array [10, 5, 7], you should return true, since we can modify 
the 10 into a 1 to make the array non-decreasing.

Given the array [10, 5, 1], you should return false, since we can't modify 
any one element to get a non-decreasing array.
 * @author Andrew
 *
 */
public class NonDecreasingArray {
	
	public static boolean solve(int[] arr) {
		int numToChange = 0;
		int prev = arr[0];
		for (int i=1; i < arr.length; i++) {
			if (arr[i] < prev) {
				// deal with cases at edge of array differently
				if (i==1 || i==arr.length-1) {
					numToChange++;
				} else {
					if (arr[i] >= arr[i-2]) { // can the prev value be modified to be between the others?
						numToChange++; // if so only that value needs to change
					} else {
						return false; // no point in continuing on here since there are already >1 changes needed
					}
				}
			}
			prev = arr[i];
		}
		return numToChange <= 1;
	}
	
	@Test
	public void defaultTest() {
		assertTrue(solve(new int[] {10,5,7}));
		assertFalse(solve(new int[] {10,5,1}));
	}
	
	@Test
	public void customTest() {
		assertTrue(solve(new int[] {1,2,55,3,4,5}));
		assertTrue(solve(new int[] {1,4,9,5,6,7}));
		assertFalse(solve(new int[] {1,4,9,3,4,5}));
	}
}
