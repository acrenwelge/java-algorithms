package problems.easy;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Ignore;
import org.junit.Test;

/**
 * This problem was asked by Google.

The power set of a set is the set of all its subsets. Write a function that, given a set, generates its power set.

For example, given the set {1, 2, 3}, it should return {{}, {1}, {2}, {3}, {1, 2}, {1, 3}, {2, 3}, {1, 2, 3}}.

You may also use a list or array to represent a set.
 * @author Andrew
 *
 */
@Ignore
public class PowerSet {
	
	private Set<List<Integer>> powerset = new HashSet<>();
	
	/*
	 * Approach:
	 * - iterate through the set
	 * - we can choose to include the number or not
	 * - generate the remaining subsets for each case
	 * 
	 * - generate 2^n Arrays of Booleans
	 */
	public Set<List<Integer>> getPowerset(int[] origSet) {
		List<Integer> subset = new ArrayList<>(origSet.length);
		helper(origSet, subset, 0);
		powerset.forEach(l -> l.removeIf(i -> i == null));
		return powerset;
	}
	
	private void helper(int[] origSet, List<Integer> subset, int idx) {
		if (idx == origSet.length) {
			powerset.add(subset);
		} else if (idx < subset.size()) {
			subset.set(idx, null);
			helper(origSet, subset, idx+1);
			subset.set(idx, origSet[idx]);
			helper(origSet, subset, idx+1);
		}
	}
	
	@Test
	public void defaultPowersetTest() {
		Set<List<Integer>> expected = new HashSet<>();
		List<Integer> list = new ArrayList<>();
		expected.add(list);
		list = Arrays.asList(1);
		expected.add(list);
		list = Arrays.asList(2);
		expected.add(list);
		list = Arrays.asList(3);
		expected.add(list);
		list = Arrays.asList(1,2);
		expected.add(list);
		list = Arrays.asList(1,3);
		expected.add(list);
		list = Arrays.asList(2,3);
		expected.add(list);
		list = Arrays.asList(1,2,3);
		expected.add(list);
		
		assertEquals(expected, getPowerset(new int[] {1,2,3}));
	}
	
	@Test
	public void customPowersetTest() {
		Set<List<Integer>> expected = new HashSet<>();
		List<Integer> list = new ArrayList<>();
		expected.add(list);
		list = Arrays.asList(1);
		expected.add(list);
		list = Arrays.asList(2);
		expected.add(list);
		list = Arrays.asList(3);
		expected.add(list);
		list = Arrays.asList(4);
		expected.add(list);
		list = Arrays.asList(1,2);
		expected.add(list);
		list = Arrays.asList(1,2,3);
		expected.add(list);
		list = Arrays.asList(1,2,3,4);
		expected.add(list);
		list = Arrays.asList(2,3,4);
		expected.add(list);
		list = Arrays.asList(1,3);
		expected.add(list);
		list = Arrays.asList(1,4);
		expected.add(list);
		list = Arrays.asList(2,3);
		expected.add(list);
		list = Arrays.asList(2,4);
		expected.add(list);
		list = Arrays.asList(3,4);
		expected.add(list);
		list = Arrays.asList(1,2,3,4);
		expected.add(list);
		list = Arrays.asList(1,3,4);
		expected.add(list);
		list = Arrays.asList(1,2,4);
		expected.add(list);
		
		assertEquals(expected, getPowerset(new int[] {1,2,3,4}));
	}

}
