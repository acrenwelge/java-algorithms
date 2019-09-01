package problems.hard;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * This problem was asked by Google.

Given an array of integers and a number k, where 1 <= k <= length of the array, 
compute the maximum values of each subarray of length k.

For example, given array = [10, 5, 2, 7, 8, 7] and k = 3, we should get: [10, 7, 8, 8], since:

10 = max(10, 5, 2)
7 = max(5, 2, 7)
8 = max(2, 7, 8)
8 = max(7, 8, 7)

Do this in O(n) time and O(k) space. You can modify the input array in-place 
and you do not need to store the results. You can simply print them out as you compute them.
 * @author Andrew
 *
 */
public class SubarrayMaxValues {
	
	/*
	 * Approach:
	 *  - iterate through each subarray
	 *  - find max
	 *  - return list of max
	 */
	public static List<Integer> computeMaxValSubarr(int[] arr, int k) {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i <= arr.length - k; i++) {
			int max = arr[i]; // assume the first element of subarray is the max
			for (int j = i; j < i + k; j++) {
				if (arr[j] > max) {
					max = arr[j];
				}
			}
			list.add(max);
		}
		return list;
	}
	
	@Test
	public void defaultTest() {
		List<Integer> result = computeMaxValSubarr(new int[] {10,5,2,7,8,7}, 3);
		assertEquals(result, Arrays.asList(10,7,8,8));
	}
	
	@Test
	public void customTest() {
		List<Integer> result = computeMaxValSubarr(new int[] {3, -1, 9, 4}, 2);
		assertEquals(result, Arrays.asList(3, 9, 9));
	}
}
