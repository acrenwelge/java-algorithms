package problems.hard;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

/**
 * This problem was asked by Google.

Given an array of strictly the characters 'R', 'G', and 'B', segregate the values of the array 
so that all the Rs come first, the Gs come second, and the Bs come last. You can only swap elements of the array.

Do this in linear time and in-place.

For example, given the array ['G', 'B', 'R', 'R', 'B', 'R', 'G'], it should become ['R', 'R', 'R', 'G', 'G', 'B', 'B'].
 * @author Andrew
 *
 */
public class GroupArray {
	
	/*
	 * Approach:
	 *  - loop over array to count Rs, Gs, and Bs
	 *  - loop again to swap elements depending on index of array
	 */
	public static char[] segregate(char[] random) {
		int rs = 0; // compute total Rs and Gs - Bs not needed 
		int gs = 0;
		for (int i=0; i < random.length; i++) {
			if (random[i] == 'R') rs++;
			if (random[i] == 'G') gs++;
		}
		for (int i=0; i < random.length; i++) {
			char c = random[i];
			if (i < rs) {
				if (c != 'R') { // these should all be Rs
					int swapWith = indexOf(random, 'R', i);
					swap(random,i,swapWith);
				}
			} else if (i < rs+gs) { // these should all be Gs
				if (c != 'G') {
					int swapWith = indexOf(random, 'G', i);
					swap(random,i,swapWith);
				}
			} else { // these should all be Bs
				if (c != 'B') {
					int swapWith = indexOf(random, 'B', i);
					swap(random,i,swapWith);
				}
			}
		}
		return random;
	}
	
	// method for finding index of the next char in the array
	private static int indexOf(char[] arr, char c, int start) {
		for (int j=start;j<arr.length;j++) {
			if (arr[j] == c) return j;
		}
		// start at beginning again
		for (int j=0;j<start;j++) {
			if (arr[j] == c) return j;
		}
		return -1;
	}
	
	// method for swapping two chars in the array
	private static char[] swap(char[] arr,int first, int sec) {
		char tmp = arr[first];
		arr[first] = arr[sec];
		arr[sec] = tmp;
		return arr;
	}
	
	@Test
	public void defaultTest() {
		char[] arr = {'G','B','R','R','B','R','G'};
		assertArrayEquals(new char[] {'R','R','R','G','G','B','B'}, segregate(arr));
	}
	
	@Test
	public void myTest() {
		char[] arr = {'B','B','B','G','G','G','R'};
		assertArrayEquals(new char[] {'R','G','G','G','B','B','B'}, segregate(arr));
	}
	
	@Test
	public void myTest2() {
		char[] arr = {'R','G','B'};
		assertArrayEquals(new char[] {'R','G','B'}, segregate(arr));
	}
	
	@Test
	public void myTest3() {
		char[] arr = {'G','R','B'};
		assertArrayEquals(new char[] {'R','G','B'}, segregate(arr));
	}

}
