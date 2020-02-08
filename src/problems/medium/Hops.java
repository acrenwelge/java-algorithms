package problems.medium;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * Given an integer list where each number represents the number of hops you can make, 
 * determine whether you can reach to the last index starting at index 0.

For example, [2, 0, 1, 0] returns True while [1, 1, 0, 1] returns False.
 * @author Andrew
 */
public class Hops {
	
	/*
	 * Approach:
	 * - start from end and trace backwards
	 */
	public static boolean canReachEnd(int[] arr) {
		return canReachIdx(arr, arr.length-1);
	}
	
	public static boolean canReachIdx(int[] arr, int idx) {
		if (idx == 0) return true;
		int neededHops = 1;
		// building a list of indexes from which we can reach arr[idx]
		List<Integer> indexesCanReach = new ArrayList<>();
		for (int i=idx-1; i >= 0; i--) {
			if (arr[i] == neededHops) { // we can reach arr[idx] from arr[i]
				indexesCanReach.add(i);
			}
			neededHops++;
		}
		// iterate through list of indexes to see if we can reach THEM
		for (Integer index : indexesCanReach) {
			if (canReachIdx(arr, index)) {
				return true; // if any of them can be reached, then idx can be reached
			}
		}
		return false; // no valid paths to reach idx
	}
	
	/*
	 * Approach:
	 * - start from beginning and follow the hops
	 */
	public static boolean canReachEndFromStart(int[] arr) {
		int idx = 0;
		int makeHops = arr[idx];
		while(idx < arr.length-1) {
			if (makeHops == 0) return false;
			idx += makeHops;
			if (idx <= arr.length - 1) { // prevents IndexOutOfBounds
				// alternatively, we could catch it and return false from the catch block but thought this is cleaner 
				makeHops = arr[idx];
			} else {
				return false;
			}
		}
		return idx == arr.length - 1;
	}
	
	@Test
	public void defaultTests() {
		assertTrue(canReachEnd(new int[] {2,0,1,0}));
		assertFalse(canReachEnd(new int[] {1,1,0,1}));
	}
	
	@Test
	public void customPositiveTests() {
		assertTrue(canReachEnd(new int[] {1,1,1,1,1}));
		assertTrue(canReachEnd(new int[] {2,0,3,0,0,0}));
		assertTrue(canReachEnd(new int[] {4,0,0,0,0}));
		assertTrue(canReachEnd(new int[] {3,5,1,2,0,1,4}));
		assertTrue(canReachEnd(new int[] {1,5,1,2,0,1,4}));
		assertTrue(canReachEnd(new int[] {5,1,1,2,0,2,0,4}));
	}
	
	@Test
	public void customNegativeTests() {
		assertFalse(canReachEnd(new int[] {0,0,0,0,0,0,0,0}));
		assertFalse(canReachEnd(new int[] {0,1,1,1,1}));
		assertFalse(canReachEnd(new int[] {2,1,6,1,1}));
		assertFalse(canReachEnd(new int[] {2,1,1,0,1}));
	}
	
	@Test
	public void defaultTestsFromStart() {
		assertTrue(canReachEndFromStart(new int[] {2,0,1,0}));
		assertFalse(canReachEndFromStart(new int[] {1,1,0,1}));
	}
	
	@Test
	public void customPositiveTestsFromStart() {
		assertTrue(canReachEndFromStart(new int[] {1,1,1,1,1}));
		assertTrue(canReachEndFromStart(new int[] {2,0,3,0,0,0}));
		assertTrue(canReachEndFromStart(new int[] {4,0,0,0,0}));
		assertTrue(canReachEndFromStart(new int[] {3,5,1,2,0,1,4}));
		assertTrue(canReachEndFromStart(new int[] {1,5,1,2,0,1,4}));
		assertTrue(canReachEndFromStart(new int[] {5,1,1,2,0,2,0,4}));
	}
	
	@Test
	public void customNegativeTestsFromStart() {
		assertFalse(canReachEndFromStart(new int[] {0,0,0,0,0,0,0,0}));
		assertFalse(canReachEndFromStart(new int[] {0,1,1,1,1}));
		assertFalse(canReachEndFromStart(new int[] {2,1,6,1,1}));
		assertFalse(canReachEndFromStart(new int[] {2,1,1,0,1}));
	}
}
