package algorithms.sort;

import static org.junit.Assert.assertArrayEquals;

import org.junit.jupiter.api.Test;

class BubbleSortTest {

	@Test
	void testBubbleSortArray() {
		int[] arr = {9, 5, 8, 0, 11, 4, 7};
		assertArrayEquals(new int[] {0,4,5,7,8,9,11}, BubbleSort.bubbleSortArray(arr));
	}

}
