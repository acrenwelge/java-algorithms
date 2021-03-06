package problems.medium;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * A sorted array of integers was rotated an unknown number of times.

Given such an array, find the index of the element in the array in faster than linear time. 
If the element doesn't exist in the array, return null.

For example, given the array [13, 18, 25, 2, 8, 10] and the element 8, return 4 (the index of 8 in the array).

You can assume all the integers in the array are unique.
 * @author Andrew
 *
 */
public class IntegerArrayRotated {
	
	public Integer findIndex(int[] arr, int el) {
		for (int i=0; i < arr.length; i++) {
			if (arr[i] == el)
				return i;
		}
		return null;
	}
	
	@Test
	public void defaultTest() { 
		int[] arr = {13, 18, 25, 2, 8, 10};
		assertEquals(Integer.valueOf(4), findIndex(arr, 8));
	}

}
