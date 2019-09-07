package problems.easy;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This problem was asked by Snapchat.

Given an array of time intervals (start, end) for classroom lectures (possibly overlapping), 
find the minimum number of rooms required.

For example, given [(30, 75), (0, 50), (60, 150)], you should return 2.
 * @author Andrew
 *
 */
public class MinimumRooms {
	
	/*
	 * Approach:
	 *  - min rooms is max # of overlapping intervals
	 *  - intervals [a,b], [c,d] overlap if c or d is between a and b
	 *  - for each interval, check against all others (O(n^2))
	 *  - take the max # 
	 */
	public int findMinimumRooms(int[][] times) {
		int maxOverlap = 0;
		for (int i=0; i<times.length; i++) {
			int[] arr = times[i];
			int start = arr[0];
			int end = arr[1];
			int overlap = 0;
			for (int j=0; j<times.length; j++) {
				if (i!=j) {
					int[] compare = times[j];
					int start2 = compare[0];
					int end2 = compare[1];
					if ((start2 > start && start2 < end) || (end2 > start && end2 < end)) {
						overlap++;
					}
				}
			}
			if (overlap > maxOverlap) {
				maxOverlap = overlap;
			}
		}
		return maxOverlap;
	}
	
	@Test
	public void defaultTest() {
		assertEquals(2, findMinimumRooms(new int[][]{{30, 75}, {0, 50}, {60, 150}}));
	}
	
	@Test
	public void myTest() {
		assertEquals(0, findMinimumRooms(new int[][]{{0, 15}, {20, 30}, {35, 40}}));
	}
	
	@Test
	public void anotherTest() {
		assertEquals(1, findMinimumRooms(new int[][]{{0, 15}, {10, 30}, {35, 40}}));
	}
}
