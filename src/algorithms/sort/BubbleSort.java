package algorithms.sort;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class BubbleSort {

	public static void main(String[] args) {
		int[] sorted = bubbleSortArray(new int[]{1, 9, 5, 3, 7, 11, 0});
		for (int i : sorted) {
			System.out.println(i);
		}
	}
	
	/**
	 * Sorts an array using the <strong>bubble sort</strong> algorithm <br>
	 * <strong>Complexity:</strong> O(n<sup>2</sup>)
	 * @param arr - the array of <tt>int</tt>
	 */
	public static int[] bubbleSortArray(int[] arr) {
		boolean sorted = false;
		while (!sorted) {
			sorted = true; // each time before looping through, assume the array is sorted
			for (int i = 0; i < arr.length-1; i++) {
				if (arr[i] > arr[i+1]) { // if the first number is bigger, swap the values
					int tmp = arr[i];
					arr[i] = arr[i+1];
					arr[i+1] = tmp;
					sorted = false; // if we have to swap, it's not sorted yet
				}
			}
		}
		return arr;
	}
	
	@Test
	public void testBubbleSortArray() {
		int[] arr = {9, 5, 8, 0, 11, 4, 7};
		assertArrayEquals(new int[] {0,4,5,7,8,9,11}, BubbleSort.bubbleSortArray(arr));
	}

}
