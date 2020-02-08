package problems.medium;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

/**
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

For example, given [100, 4, 200, 1, 3, 2], the longest consecutive element sequence is [1, 2, 3, 4]. Return its length: 4.

Your algorithm should run in O(n) complexity.
 * @author Andrew
 *
 */
public class LongestConsecutiveElements {
	
	public static int findLongestConsecutiveSeqSlow(int[] arr) {
		Arrays.sort(arr);
		int prev = arr[0];
		int count = 1;
		int longest = 0;
		for (int i=1; i < arr.length; i++) {
			if (arr[i] == prev + 1 || arr[i] == prev - 1) {
				count++;
			} else {
				if (count > longest) longest = count;
				count = 1;
			}
			prev = arr[i];
		}
		return longest;
	}
	
	// O(n) solution
	public static int findLongestConsecutiveSeqFast(int[] arr) {
		Set<Integer> all = new HashSet<>(arr.length);
		for (int i : arr) {
			all.add(i);
		}
		int longest = 0;
		for (int j : arr) {
			int val = j+1;
			int iter = 1;
			if (!all.contains(j-1)) {
				while (all.contains(val)) {
					iter++;
					val++;
				}
			}
			if (iter > longest) {
				longest = iter;
			}
		}
		return longest;
	}
	
	@Test
	public void defaultTest() {
		int[] arr = new int[] {100,4,200,1,3,2};
		assertEquals(4, findLongestConsecutiveSeqSlow(arr));
		assertEquals(4, findLongestConsecutiveSeqFast(arr));
	}
}
