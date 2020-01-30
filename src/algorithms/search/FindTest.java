package algorithms.search;

import static algorithms.search.Find.findDuplicates;
import static algorithms.search.Find.findMissing;
import static algorithms.search.Find.findNthLargest;
import static algorithms.search.Find.findPairs;
import static algorithms.search.Find.findPairs2;
import static algorithms.search.Find.findSmallestAndLargest;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FindTest {

	@Test
	public void testFindMissing() {
		assertEquals(4, findMissing(new int[]{1, 5, 3, 2, 6, 7, 9, 8}));
		assertEquals(6, findMissing(new int[]{8, 7, 5, 4, 3}));
	}

	@Test
	public void testFindDuplicates() {
		int[] noDups = new int[] {3, 6, 1, -8, 4};
		assertArrayEquals(new int[] {}, findDuplicates(noDups));
		int[] dups = new int[] {4, 6, 1, -8, 4, 6, 6, 2};
		assertArrayEquals(new int[] {4,6,6}, findDuplicates(dups));
	}

	@Test
	public void testFindSmallestAndLargest() {
		assertArrayEquals(new int[] {-6,105}, findSmallestAndLargest(new int[] {3, 10, 7, 30, 100, -6, 105, 17}));
	}

	@Test
	public void testFindNthLargest() {
		assertEquals(11, findNthLargest(new int[] {5, 7, 3, 9, 38, 11}, 2));
		assertEquals(1, findNthLargest(new int[] {43, 56, 99, 1, 0, 3}, 5));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testFindNthLargestException() {
		findNthLargest(new int[] {5, 7, 3, 9}, 5);
	}

	@Test
	public void testFindPairs() {
		assertArrayEquals(new int[][] {{20, 15},{30, 5}},findPairs(new int[] {20, 10, 15, 30, 8, 5, -3, 33}, 35));
	}

	@Test
	public void testFindPairs2() {
		assertArrayEquals(new int[] {7, 18}, findPairs2(new int[] {1, 3, 7, 1}, new int[] {6, 5, 18, 4}, 24));
	}

}
