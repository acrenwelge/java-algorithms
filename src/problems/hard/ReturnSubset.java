package problems.hard;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * Given a list of integers S and a target number k, write a function that returns 
 * a subset of S that adds up to k. If such a subset cannot be made, then return null.

Integers can appear more than once in the list. You may assume all numbers in the list are positive.

For example, given S = [12, 1, 61, 5, 9, 2] and k = 24, return [12, 9, 2, 1] since it sums up to 24.
 * @author Andrew
 *
 */
public class ReturnSubset {
	
	public List<Integer> findSubset(List<Integer> list, int k) {
		return null;
	}
	
	@Test
	public void defaultTest() {
		List<Integer> list = new ArrayList<>();
		list.add(12);
		list.add(1);
		list.add(61);
		list.add(5);
		list.add(9);
		list.add(2);
		assertTrue(findSubset(list, 24).containsAll(Arrays.asList(12,9,2,1)));
	}
}
