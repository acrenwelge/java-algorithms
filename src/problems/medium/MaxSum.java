package problems.medium;

import static org.junit.Assert.assertEquals;

import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.Test;

/**
 * Given an array of numbers, find the maximum sum of any contiguous subarray of the array.

For example, given the array [34, -50, 42, 14, -5, 86], the maximum sum would be 137, since we would take elements 42, 14, -5, and 86.

Given the array [-5, -1, -8, -9], the maximum sum would be 0, since we would not take any elements.

Do this in O(N) time.
 * @author Andrew
 *
 */
public class MaxSum {
	
	/*
	 * 1st Approach:
	 * - sum contiguous elements until negative number is encountered
	 * - if no positive elements, return 0
	 * - otherwise return the highest of the set of sums
	 * - this fails (test case does not pass)
	 * 
	 * 2nd Approach (brute force):
	 * - compute all sums and take the highest
	 * 
	 * 3rd Approach:
	 * - incremental search
	 * 
	 * 4th Approach:
	 * - looked up Kadame's algorithm (runs in O(n))
	 */
	public int findMaxSumBrute(int[] arr) {
		SortedSet<Integer> sums = new TreeSet<>();
		for (int i=0; i < arr.length; i++) {
			for (int j=i; j<arr.length; j++) {
				sums.add(getSum(arr, i, j));
			}
		}
		int a = sums.last();
		return a > 0 ? a : 0;
	}
	
	private int getSum(int[] arr, int start, int stop) {
		int sum = 0;
		for (int i=start; i <= stop; i++) {
			sum += arr[i];
		}
		return sum;
	}
	
	public int kadame(int[] arr) {
		if (arr.length == 0) return 0;
		int[] maxSubs = new int[arr.length];
		maxSubs[0] = arr[0];
		int globalMax = arr[0];
		for (int i=1; i < arr.length; i++) {
			maxSubs[i] = Math.max(arr[i], maxSubs[i-1] + arr[i]);
			if (maxSubs[i] > globalMax) {
				globalMax = maxSubs[i];
			}
		}
		return globalMax > 0 ? globalMax : 0;
	}
	
	@Test
	public void defaultTest() {
		int[] arr = {34,-50, 42,14,-5,86};
		assertEquals(137, findMaxSumBrute(arr));
		assertEquals(137, kadame(arr));
		arr = new int[]{-5, -1, -8, -9};
		assertEquals(0, findMaxSumBrute(arr));
		assertEquals(0, kadame(arr));
	}
}
