package problems.hard;

import static org.junit.Assert.assertEquals;

import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;

/**
 * This problem was asked by Google.

Given an array of integers where every integer occurs three times except for one integer, 
which only occurs once, find and return the non-duplicated integer.

For example, given [6, 1, 3, 3, 3, 6, 6], return 1. Given [13, 19, 13, 13], return 19.

Do this in O(N) time and O(1) space.
 * @author Andrew
 *
 */
public class NonDuplicateInt {
	
	/*
	 * Approach:
	 * - use a Map to relate numbers to their counts
	 * - increment the count each time the number is encountered
	 * - loop through the map to find the integer w/ count of 1
	 * - this is O(N) time but NOT O(1) space
	 * - optimized solution deals with bitwise operations (looked this up)
	 */
	public int find(int[] arr) {
		Map<Integer, Integer> counts = new TreeMap<>();
		for (int i : arr) {
			counts.merge(i,1, Integer::sum);
		}
		for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
			if (entry.getValue() == 1) {
				return entry.getKey();
			}
		}
		return -1;
	}
	
	@Test
	public void defaultTest() {
		int[] arr = {6,1,3,3,3,6,6};
		assertEquals(1, find(arr));
		arr = new int[] {13,19,13,13};
		assertEquals(19, find(arr));
	}
	
}
