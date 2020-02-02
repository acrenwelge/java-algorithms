package problems.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * This problem was asked by Microsoft.
 * 
 * Given a number in the form of a list of digits, return all possible
 * permutations.
 * 
 * For example, given [1,2,3], return
 * [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]].
 * 
 * @author Andrew
 */
public class ArrayPermutations {

	/*
	 * Question: does [1,2,2,3] count as the same permutation as [1,2,2,3] (swap the 2s)?
	 */
	public static List<List<Integer>> getPermutations(List<Integer> list) {
		List<List<Integer>> result = new ArrayList<>();
		if (list.size() == 1) {
			result.add(list);
		} else {
			Integer last = list.get(list.size() - 1);
			List<Integer> remain = list.subList(0, list.size() - 1);
			result = merge(getPermutations(remain), last);
		}
		Collections.sort(result, (l1, l2) -> {
			for (int i = 0; i < Math.min(l1.size(), l2.size()); i++) {
				int c = l1.get(i).compareTo(l2.get(i));
				if (c != 0) {
					return c;
				}
			}
			return Integer.compare(l1.size(), l2.size());
		});
		return result;
	}

	private static List<List<Integer>> merge(List<List<Integer>> list, Integer toAdd) {
		List<List<Integer>> merged = new ArrayList<>();
		for (List<Integer> inner : list) {
			// insert new integer at every position in every list
			for (int i = 0; i <= inner.size(); i++) {
				List<Integer> newList = new ArrayList<>(inner.size());
				for (Integer num : inner) {
					newList.add(num);
				}
				newList.add(i, toAdd);
				merged.add(newList);
			}
		}
		return merged;
	}

	@Test
	public void defaultTest() {
		List<Integer> arr = Arrays.asList(1, 2, 3);
		List<Integer> arr2 = Arrays.asList(1, 3, 2);
		List<Integer> arr3 = Arrays.asList(2, 1, 3);
		List<Integer> arr4 = Arrays.asList(2, 3, 1);
		List<Integer> arr5 = Arrays.asList(3, 1, 2);
		List<Integer> arr6 = Arrays.asList(3, 2, 1);
		assertEquals(Arrays.asList(arr, arr2, arr3, arr4, arr5, arr6), getPermutations(arr));
	}
}
