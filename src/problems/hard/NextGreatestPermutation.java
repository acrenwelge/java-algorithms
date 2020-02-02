package problems.hard;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * This problem was asked by Palantir.

Given a number represented by a list of digits, find the next greater permutation 
of a number, in terms of lexicographic ordering. If there is not greater permutation 
possible, return the permutation with the lowest value/ordering.

For example, the list [1,2,3] should return [1,3,2]. The list [1,3,2] should return [2,1,3]. 
The list [3,2,1] should return [1,2,3].

Can you perform the operation without allocating extra memory (disregarding the input memory)?

 * @author Andrew
 */
public class NextGreatestPermutation {
	public static final Logger log = LogManager.getLogger(NextGreatestPermutation.class);
	
	public static int[] findNextGreatestPermutation(int[] arr) {
		// from right to left of array, find where first decrease occurs
		int i;
		for (i=arr.length-1; i > 0; i--) {
			if (arr[i] > arr[i-1]) {
				log.debug("decreasing at i="+i);
				break;
			}
		}
		if (i==0) {
			Arrays.sort(arr);
			return arr;
		} else {
			// from left to right, starting at index identified, find next greatest value
			int x = arr[i-1];
			int min = i;
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[j] > x && arr[j] < arr[min]) {
					min = j;
				}
			}
			log.debug("min is "+arr[min]);
			// swap the two digits identified
			int tmp = arr[i-1];
			arr[i-1] = arr[min];
			arr[min] = tmp;
			// sort, starting from i-1
			Arrays.sort(arr,i,arr.length);
			return arr;
		}
	}
	
	@Test
	public void defaultTest() {
		int[] arr = new int[] {5,3,4,9,7,6};
		assertArrayEquals(new int[] {5,3,6,4,7,9}, findNextGreatestPermutation(arr));
		arr = new int[] {1,2,3};
		assertArrayEquals(new int[] {1,3,2}, findNextGreatestPermutation(arr));
		arr = new int[] {1,3,2};
		assertArrayEquals(new int[] {2,1,3}, findNextGreatestPermutation(arr));
		arr = new int[] {3,2,1};
		assertArrayEquals(new int[] {1,2,3}, findNextGreatestPermutation(arr));
	}
	
}
