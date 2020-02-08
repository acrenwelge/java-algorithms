package problems.hard;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Ignore;
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
	
	private List<Integer> subset;
	private int[] inputInts;
	private int k;
	private int runningSumOfSubset;
	private int delta;
	
	public List<Integer> findSubset() {
		/*
		 * Approach:
		 * - sort the array
		 * - iterate through, adding numbers to a temporary list and stop once the sum (s) is >= k
		 * - recursively take the difference (delta) between k and s as the target to hit
		 *   - identify any differences between combinations of elements in the list (to remove) and those not in the list (to add)
		 *   that would equate to this delta
		 *   - can start by taking out elements until s <= k
		 *   - then look for combos of elements to remove/add (this is more complex)
		 */
		List<Integer> subset = new ArrayList<>();
		if (inputInts.length <= 1) {
			throw new IllegalArgumentException("The list of ints must contain more than 1");
		} else {
			sortAndPopulateSubset();
			if (checkForSubsetMatch()) {
				return subset;
			}
			takeOutElements();
			checkForSubsetMatch();
			int numOfCombos = 2;
			while (numOfCombos < inputInts.length) {
				boolean found = lookForCombos(numOfCombos);
				if (found) return subset;
				numOfCombos++;
			}
		}
		return subset;
	}
	
	private boolean lookForCombos(int numOfCombos) {
		return true;
	}
	
	private void sortAndPopulateSubset() {
		Arrays.sort(inputInts);
		for (int i=0; i<inputInts.length; i++) {
			int j = inputInts[i];
			subset.add(j);
			runningSumOfSubset += j;
			setDelta();
			if (delta >= 0) {
				break;
			}
		}
	}
	
	private void takeOutElements() {
		for (int i=0; i<subset.size(); i++) {
			int j = subset.remove(i);
			runningSumOfSubset -= j;
			if (runningSumOfSubset <= k) {
				break;
			}
		}
	}
	
	private boolean checkForSubsetMatch() {
		if (delta == 0) {
			return true;
		}
		return false;
	}
	
	private void setDelta() {
		delta = runningSumOfSubset - k;
	}
	
	public static boolean hasSubsetSum(int[] arr, int k) {
		/*
		 * Approach:
		 * - 2 cases: include or exclude each element
		 * - recur from the last element i=arr.length until base case i=0 
		 */
		if (arr.length == 0 && k == 0) {
			return true;
		} else if (arr.length == 1 && k == arr[0]) {
			return true;
		} else if (arr.length == 0 && k != 0) {
			return false;
		}
		else {
			int last = arr[arr.length-1];
			int[] newArr = Arrays.copyOf(arr, arr.length-1);
			boolean withoutLastEl = hasSubsetSum(newArr, k-last);
			boolean withLastEl = hasSubsetSum(newArr, k);
			return withoutLastEl || withLastEl;
		}
	}
	
	public static List<Integer> findSubset2(int[] arr, int k) {
		boolean hasAny = hasSubsetSum(arr,k);
		List<Integer> subset = new ArrayList<>();
		if (!hasAny) {
			return null;
		} else {
			return subset;
		}
	}
	
	@Test
	@Ignore
	public void defaultTest() {
		this.inputInts = new int[] {12, 1, 61, 5, 9, 2 };
		assertTrue(new ReturnSubset().findSubset().containsAll(Arrays.asList(12,9,2,1)));
	}
	
	@Test
	public void subsetBooleanTest() {
		int[] arr = new int[] {12, 1, 61, 5, 9, 2 };
		assertTrue(ReturnSubset.hasSubsetSum(arr, 24));
	}
	
	@Test
	public void baseSubsetBooleanTest() {
		int[] arr = new int[] {3};
		assertTrue(ReturnSubset.hasSubsetSum(arr, 3));
		arr = new int[] {};
		assertTrue(ReturnSubset.hasSubsetSum(arr, 0));
	}
	
}
