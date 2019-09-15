package problems.medium;

import static org.junit.Assert.assertEquals;

import java.util.Iterator;
import java.util.LinkedList;

import org.junit.Test;

/**
 * This problem was asked by Google.

Given a singly linked list and an integer k, remove the kth last element from the list. 
k is guaranteed to be smaller than the length of the list.

The list is very long, so making more than one pass is prohibitively expensive.

Do this in constant space and in one pass.
 * @author Andrew
 *
 */
public class RemoveKthElementFromList {
	
	/*
	 * Approach: ???
	 *  - iterate through the list
	 *  - keep the kth most recent values in an array
	 *  - when end of list is reached, remove the initial element in the array
	 */
	public void removeKth(LinkedList l, int k) {
		int idx = -1;
		Iterator i = l.iterator();
		while (i.hasNext()) {
			i.next();
			idx++;
		}
		l.remove(idx - k);
	}
	
	@Test
	public void test1() {
		int k = 1;
		LinkedList<Integer> list = new LinkedList<>();
		list.add(1);
		list.add(3);
		list.add(8);
		list.add(4);
		LinkedList<Integer> compare = new LinkedList<>();
		compare.addAll(list);
		compare.remove(list.size() - k - 1);
		removeKth(list, k);
		assertEquals(compare, list);
	}
	
	@Test
	public void test2() {
		int k = 2;
		LinkedList<Integer> list = new LinkedList<>();
		list.add(1);
		list.add(3);
		list.add(8);
		list.add(4);
		LinkedList<Integer> compare = new LinkedList<>();
		compare.addAll(list);
		compare.remove(list.size() - k - 1);
		removeKth(list, k);
		assertEquals(compare, list);
	}
}
