package algorithms.sort;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;

import org.junit.Test;

public class MergeSorter implements Sorter {

	@Override
	public int[] sort(int[] arr) {
		int[] sorted = Arrays.copyOf(arr, arr.length);
		return null;
	}
	
	@Test
	public void testInsertionSorter() {
		int[] arr = {9, 5, 8, 0, 11, 4, 7};
		Sorter sorter = new MergeSorter();
		assertArrayEquals(new int[] {0,4,5,7,8,9,11}, sorter.sort(arr));
	}

}
