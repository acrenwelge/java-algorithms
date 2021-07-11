package algorithms.sort;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

public class InsertionSorter implements Sorter {
	
	static final Logger log = LogManager.getLogger(InsertionSorter.class);
	
	@Test
	public void testInsertionSorter() {
		int[] arr = {9, 5, 8, 0, 11, 4, 7};
		Sorter sorter = new InsertionSorter();
		assertArrayEquals(new int[] {0,4,5,7,8,9,11}, sorter.sort(arr));
	}

	/**
	 * Insertion sort checks each element from index 1 and determines where to insert
	 * it in the (sorted) sub-array to the left of the element
	 * <strong>Complexity:</strong> O(n<sup>2</sup>)
	 * @param arr - the array of <tt>int</tt>
	 */
	@Override
	public int[] sort(int[] arr) {
		int[] sorted = Arrays.copyOf(arr, arr.length);
		for (int i = 1; i < sorted.length; i++) {
			int insertMe = sorted[i];
			for (int j = 0; j < i; j++) {
				int check = sorted[j];
				if (insertMe < check) { // insert at j
					log.debug("inserting " +insertMe + " at " + j);
					shiftRight(sorted, j, i);
					sorted[j] = insertMe;
					break;
				}
			}
		}
		return sorted;
	}
	
	public static void shiftRight(int[] arr, int start, int finish) {
		for (int i=finish; i > start; i--) {
			arr[i] = arr[i-1];
		}
	}
	
}
