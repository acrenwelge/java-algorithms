package algorithms.search;

import static algorithms.search.Find.findMissing;
import static algorithms.search.Find.findPairs;
import static algorithms.search.Find.findPairs2;
import static algorithms.search.Find.findSmallestAndLargest;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

class FindTest {

	@Test
	void testFindMissing() {
		assertEquals(4, findMissing(new int[]{1, 5, 3, 2, 6, 7, 9, 8}));
	}

	@Test
	void testFindDuplicates() {
		fail("Not yet implemented");
	}

	@Test
	void testFindSmallestAndLargest() {
		assertArrayEquals(new int[] {-6,105}, findSmallestAndLargest(new int[] {3, 10, 7, 30, 100, -6, 105, 17}));
	}

	@Test
	void testFindNthLargest() {
		fail("Not yet implemented");
	}

	@Test
	void testFindPairs() {
		assertArrayEquals(new int[][] {{20, 15},{30, 5}},findPairs(new int[] {20, 10, 15, 30, 8, 5, -3, 33}, 35));
	}

	@Test
	void testFindPairs2() {
		assertArrayEquals(new int[] {7, 18}, findPairs2(new int[] {1, 3, 7, 1}, new int[] {6, 5, 18, 4}, 24));
	}

}
