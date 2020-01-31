package problems.hard;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * Given an array of numbers, find the length of the longest increasing subsequence in the array. 
 * The subsequence does not necessarily have to be contiguous.

For example, given the array [0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15], the longest 
increasing subsequence has length 6: it is 0, 2, 6, 9, 11, 15.
 * @author Andrew
 *
 */
public class LongestIncreasingSubsequence {

	public static int findLongestContiguousSubsequence(int[] arr) {
		List<Integer> sequenceLengths = new ArrayList<>();
		int lastNum = arr[0];
		int currSeq = 0;
		for (int i=1; i < arr.length; i++) {
			if (arr[i] > lastNum) currSeq++;
			else {
				currSeq++;
				sequenceLengths.add(currSeq);
				currSeq = 0;
			}
			lastNum = arr[i];
		}
		int max = 0;
		for (Integer seq : sequenceLengths) {
			if (seq > max) max = seq;
		}
		return max;
	}
	
	public static int findLongestSubsequence(int[] arr) {
		return 0;
	}
	
	@Test
	public void defaultTest() {
		int[] arr = {0,8,4,12,2,10,6,14,1,9,5,13,3,11,7,15};
		assertEquals(2, findLongestContiguousSubsequence(arr));
		assertEquals(6, findLongestSubsequence(arr));
	}
}
