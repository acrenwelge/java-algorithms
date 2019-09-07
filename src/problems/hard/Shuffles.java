package problems.hard;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Problem description: see https://open.kattis.com/problems/shuffles
 * @author Andrew
 *
 */
public class Shuffles {
	
	/*
	 * Suppose there are n unique cards, and that they start out perfectly ordered: 1,2,3,...,𝑛. 
	 * Given an ordering of the deck, what is the smallest number of shuffles that could possibly put the deck in that order?
	 */
	
	/*
	 * Observations:
	 * - each split of deck is ordered
	 * - splitting and interleaving can be done anywhere (we can optimize this to find the minimum)
	 */
	public int smallestShuffles(int n, int[] deck) {
		// TODO: solve
		
		return 0;
	}
	
	@Test
	public void test1() {
		assertEquals(1, smallestShuffles(10, new int[] {1,2,7,3,8,9,4,5,10,6}));
	}
}
