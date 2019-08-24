package problems.hard;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

/**
 * This problem was asked by Stripe.

Given an array of integers, find the first missing positive integer in linear time and constant space. 
In other words, find the lowest positive integer that does not exist in the array. 
The array can contain duplicates and negative numbers as well.

For example, the input [3, 4, -1, 1] should give 2. The input [1, 2, 0] should give 3.

You can modify the input array in-place.
 * @author Andrew
 *
 */
public class Four {
	
	// does not conform to linear time / constant space
	public int naiveSolution(int[] arr) {
		int max = arr.length + 1; // maximum possible missing integer
		int min = 1;
		Set<Integer> unique = new HashSet<>();
		for (int i = 0; i < arr.length; i++) {
			int val = arr[i];
			// if the number is < 0, duplicated, or > max, it reduces the possible max int
			if (val < min || val > max || unique.contains(val)) {
				max--;
			}
			unique.add(val);
		}
		for (int j = min; j <= max; j++) {
			if (!unique.contains(j)) {
				return j;
			}
		}
		return max;
	}
	
	/** Approach:
	 *  - iterate through the array, stop at values where value doesn't match index (e.g. arr[0] should be 1, arr[1]==2, etc)
	 *  - if out of place, place the value at its index (val - 1) in the array
	 *    - values swap places in the array
	 *  - negative numbers, 0, or > max are placed from end of the array
	 *    - for this operation, elements are shifted left and the value is placed at the end
	 *  - then just loop through array till sequence (1,2,3...) is broken
	 */
	public int linearTime(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			int val = arr[i];
			if (val <= 0 || val > arr.length) {
				// perform shift
				int[] toShift = Arrays.copyOfRange(arr, i+1, arr.length);
				System.arraycopy(toShift, 0, arr, i, toShift.length);
				arr[arr.length-1] = val; // put the value at the end of array
			} else if (val != i + 1) {
				// do a swap
				int toSwap = arr[val-1];
				arr[val-1] = val;
				arr[i] = toSwap;
				i--; // look at same index again
			}
		}
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != i+1) {
				return i + 1;
			}
		}
		return arr.length + 1;
	}

	@Test
	public void defaultTests() {
		int[] arr1 = {3, 4, -1, 1};
		int[] arr2 = {1, 2, 0};
		assertEquals(2, naiveSolution(arr1));
		assertEquals(3, naiveSolution(arr2));
	}
	
	@Test
	public void customTests() {
		int[] arr1 = {10, 4, -8, 0, 1};
		int[] arr2 = {-5, -2, 100, 7, 9};
		assertEquals(2, naiveSolution(arr1));
		assertEquals(1, naiveSolution(arr2));
	}
	
	@Test
	public void testsLinearTime() {
		int[] arr1 = {3, 4, -1, 1};
		int[] arr2 = {1, 2, 0};
		assertEquals(2, linearTime(arr1));
		assertEquals(3, linearTime(arr2));
		arr1 = new int[] {10, 4, -8, 0, 1};
		arr2 = new int[] {-5, -2, 100, 7, 9};
		assertEquals(2, linearTime(arr1));
		assertEquals(1, linearTime(arr2));
	}
}
