package algorithms.sort;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;

import org.junit.Test;

public class MergeSorter implements Sorter {

	@Override
	public int[] sort(int[] arr) {
		// sort the above array using merge sort
		if (arr.length <= 1) {
			return arr;
		} else if (arr.length == 2) {
			if (arr[0] > arr[1]) {
				int temp = arr[0];
				arr[0] = arr[1];
				arr[1] = temp;
			}
			return arr;
		} else {
			// 1. divide the array into two halves
			int[] left = Arrays.copyOfRange(arr, 0, arr.length/2);
			int[] right = Arrays.copyOfRange(arr, arr.length/2, arr.length);
			// 2. sort each half
			left = sort(left);
			right = sort(right);
			// 3. merge the two halves and return the result
			return merge(left, right);
		}
	}

	private int[] merge(int[] left, int[] right) {
		int[] merged = new int[left.length + right.length];
		int i = 0, j = 0, k = 0;
		while (i < left.length && j < right.length) {
			// compare the two elements and add the smaller one to the merged array
			if (left[i] < right[j]) {
				merged[k] = left[i];
				i++;
			} else {
				merged[k] = right[j];
				j++;
			}
			k++;
		}
		while (i < left.length) {
			// add the remaining elements from left to the merged array
			merged[k] = left[i];
			i++;
			k++;
		}
		while (j < right.length) {
			// add the remaining elements from right to the merged array
			merged[k] = right[j];
			j++;
			k++;
		}
		return merged;
	}
	
	@Test
	public void testMergeSorter1() {
		int[] arr = {9, 5, 8, 0, 11, 4, 7};
		Sorter sorter = new MergeSorter();
		assertArrayEquals(new int[] {0,4,5,7,8,9,11}, sorter.sort(arr));
	}

	@Test
	public void testMergeSorter2() {
		int[] left = {1, 3, 5, 7, 9};
		int[] right = {2, 4, 6, 8, 10};
		int[] merged = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		assertArrayEquals(merged, merge(left, right));
	}

}
