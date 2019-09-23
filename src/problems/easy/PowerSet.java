package problems.easy;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

/**
 * This problem was asked by Google.

The power set of a set is the set of all its subsets. Write a function that, given a set, generates its power set.

For example, given the set {1, 2, 3}, it should return {{}, {1}, {2}, {3}, {1, 2}, {1, 3}, {2, 3}, {1, 2, 3}}.

You may also use a list or array to represent a set.
 * @author Andrew
 *
 */
public class PowerSet {
	
	public Set<List<Integer>> getPowerset(Set<Integer> set) {
		Set<List<Integer>> powerset = new HashSet<>();
		powerset.add(new ArrayList<>());
		List<Integer> all = new ArrayList<>(); // subset that will contain all elements in set 
		for (Integer i : set) {
			all.add(i);
			powerset.add(Arrays.asList(i)); // add the subset which is the single element
			List<Integer> subset = new ArrayList<>(); // subset which will contain all elements except the current
			for (Integer j : set) {
				if (!i.equals(j)) {
					subset.add(j);
				}
			}
			powerset.add(subset);
		}
		powerset.add(all);
		List<Integer> list = new ArrayList<>();
		list.addAll(set);
		powerset.add(list);
		return powerset;
	}
	
	@Test
	public void defaultTest() {
		Set<Integer> set = new HashSet<>();
		set.add(1);
		set.add(2);
		set.add(3);
		Set<List<Integer>> powerset = new HashSet<>();
		List<Integer> list = new ArrayList<>();
		powerset.add(list);
		list = Arrays.asList(1);
		powerset.add(list);
		list = Arrays.asList(2);
		powerset.add(list);
		list = Arrays.asList(3);
		powerset.add(list);
		list = Arrays.asList(1,2);
		powerset.add(list);
		list = Arrays.asList(1,3);
		powerset.add(list);
		list = Arrays.asList(2,3);
		powerset.add(list);
		list = Arrays.asList(1,2,3);
		powerset.add(list);
		
		assertEquals(powerset, getPowerset(set));
	}
	
	@Test
	public void customTest() {
		Set<Integer> set = new HashSet<>();
		set.add(1);
		set.add(2);
		set.add(3);
		set.add(4);
		Set<List<Integer>> powerset = new HashSet<>();
		List<Integer> list = new ArrayList<>();
		powerset.add(list);
		list = Arrays.asList(1);
		powerset.add(list);
		list = Arrays.asList(2);
		powerset.add(list);
		list = Arrays.asList(3);
		powerset.add(list);
		list = Arrays.asList(4);
		powerset.add(list);
		list = Arrays.asList(1,2);
		powerset.add(list);
		list = Arrays.asList(1,2,3);
		powerset.add(list);
		list = Arrays.asList(1,2,3,4);
		powerset.add(list);
		list = Arrays.asList(2,3,4);
		powerset.add(list);
		list = Arrays.asList(1,3);
		powerset.add(list);
		list = Arrays.asList(2,3);
		powerset.add(list);
		list = Arrays.asList(1,2,3,4);
		powerset.add(list);
		list = Arrays.asList(1,3,4);
		powerset.add(list);
		list = Arrays.asList(1,2,4);
		powerset.add(list);
		
		assertEquals(powerset, getPowerset(set));
	}

}
