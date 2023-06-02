package problems.medium;

import static org.junit.Assert.assertArrayEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

/**
 * Given a set of closed intervals, find the smallest set of numbers that covers all the intervals. 
 * If there are multiple smallest sets, return any of them.

For example, given the intervals [0, 3], [2, 6], [3, 4], [6, 9], one set of numbers that covers 
all these intervals is {3, 6}.
 * @author Andrew
 *
 */
public class CoverAllIntervals {
	
	public Integer[] findSmallestThatCovers(Set<Integer[]> intervals) {
		Integer[] lowestInterval = new Integer[] {Integer.MAX_VALUE,Integer.MAX_VALUE};
		Integer[] highestInterval = new Integer[] {Integer.MIN_VALUE,Integer.MIN_VALUE};
		for (Integer[] interval : intervals) {
			if (interval[0] < lowestInterval[0]) {
				lowestInterval = interval;
			} else if (interval[1] > highestInterval[1]) {
				highestInterval = interval;
			}
		}
		return new Integer[] {lowestInterval[1], highestInterval[0]};
	}
	
	@Test
	public void defaultTest() {
		Set<Integer[]> ans = new HashSet<>();
		ans.add(new Integer[] {0,3});
		ans.add(new Integer[] {2,6});
		ans.add(new Integer[] {3,4});
		ans.add(new Integer[] {6,9});
		assertArrayEquals(new Integer[] {3,6}, findSmallestThatCovers(ans));
	}
}
