package algorithms.sort;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;

import org.junit.Test;

public class BubbleSorter implements Sorter{
    static final Logger log = LogManager.getRootLogger();

	/**
	 * Sorts an array using the <strong>bubble sort</strong> algorithm <br>
	 * <strong>Complexity:</strong> O(n<sup>2</sup>)
	 * @param arr - the array of <tt>int</tt>
	 */
	@Override
	public int[] sort(int[] arr) {
		int[] sortedArr = Arrays.copyOf(arr, arr.length);
		boolean sorted = false;
		while (!sorted) {
			sorted = true; // each time before looping through, assume the array is sorted
			for (int i = 0; i < sortedArr.length-1; i++) {
				if (sortedArr[i] > sortedArr[i+1]) { // if the first number is bigger, swap the values
					int tmp = sortedArr[i];
					sortedArr[i] = sortedArr[i+1];
					sortedArr[i+1] = tmp;
					sorted = false; // if we have to swap, it's not sorted yet
				}
			}
		}
		return sortedArr;
	}
	
	@Test
	public void testBubbleSorter() {
		int[] arr = {9, 5, 8, 0, 11, 4, 7};
		Sorter sorter = new BubbleSorter();
		assertArrayEquals(new int[] {0,4,5,7,8,9,11}, sorter.sort(arr));
	}

}
