package problems.medium;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;

/**
 * This problem was asked by Facebook.

Given a multiset of integers, return whether it can be partitioned into two subsets whose sums are the same.

For example, given the multiset {15, 5, 20, 10, 35, 15, 10}, it would return true, since we can split it up 
into {15, 5, 10, 15, 10} and {20, 35}, which both add up to 55.

Given the multiset {15, 5, 20, 10, 35}, it would return false, since we can't split it up into two subsets 
that add up to the same sum.
 * @author Andrew
 *
 */
public class SubsetSums {
	
	public boolean hasSubsetsWithSameSum(List<Integer> set) {
		Optional<Integer> sum = set.stream().reduce(Integer::sum);
		if (sum.isPresent()) {
			int val = sum.get();
			if (val % 2 == 0) {
				return helper(set, set.size(), val);
			} else {
				return false;
			}
		}
		return false;
	}
	
	public boolean helper(List<Integer> ints, int n, int sum) {
		if (sum == 0) {
			return true;
		}
		if (n == 0) {
			return false;
		}
		if (ints.get(n-1) > sum) {
			return helper(ints, n-1, sum);
		}
		return helper(ints, n-1, sum) || helper(ints, n-1, sum - ints.get(n-1));
	}
	
	@Test
	public void defaultTest() {
		List<Integer> ints = new ArrayList<>();
		ints.add(15);
		ints.add(5);
		ints.add(20);
		ints.add(10);
		ints.add(35);
		ints.add(15);
		ints.add(10);
		assertTrue(hasSubsetsWithSameSum(ints));
	}
	
	@Test
	public void defaultTest2() {
		List<Integer> ints = new ArrayList<>();
		ints.add(15);
		ints.add(5);
		ints.add(20);
		ints.add(10);
		ints.add(35);
		assertFalse(hasSubsetsWithSameSum(ints));
	}
}
