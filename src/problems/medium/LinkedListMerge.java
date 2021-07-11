package problems.medium;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

/**
 * Given k sorted singly linked lists, write a function to merge all the lists into one sorted singly linked list.
 * @author Andrew
 */
public class LinkedListMerge {
	public static final Logger log = LogManager.getLogger(LinkedListMerge.class);
	
	/*
	 * My solution uses a list of iterators for each of the lists passed in and a list of current values of the lists.
	 * We use the iterators to populate the list of current values. The same index applies to each:
	 * Iterator 1 -> Value 1
	 * 
	 * We initially populate the list of values, find the minimum and add it to the result list,
	 * then replace the minimum value we plucked from the list via its iterator. 
	 * 
	 * When an iterator has no more values to give us, we replace the value with null.
	 * We terminate looping once all values in the list of "current" values are null.
	 */
	@SafeVarargs
	public static <E extends Comparable<E>> List<Comparable<E>> merge(LinkedList<E> ...all) {
		int k = all.length;
		List<Iterator<E>> iterators = new ArrayList<>(k);
		List<E> currVals = new ArrayList<>(k);
		for (int i=0; i < k; i++) {
			Iterator<E> it = all[i].iterator();
			iterators.add(it);
			currVals.add(it.next());
		}
		LinkedList<Comparable<E>> result = new LinkedList<>();
		log.debug("STARTING MERGING OF LISTS...");
		while (!usedAllValues(currVals)) {
			log.debug("iterators not finished...");
			E min = findMinAndGetNextVal(iterators, currVals);
			log.debug("MIN VALUE FOR THIS ROUND:"+min);
			result.add(min);
			log.debug("MERGED RESULT SO FAR:");
			result.forEach(log::debug);
			log.debug("ITERATORS:");
			iterators.forEach(it -> log.debug(it.hasNext()));
		}
		return result;
	}
	
	public static <E> boolean usedAllValues(List<E> currVals) {
		return currVals.stream().allMatch(v -> v == null);
	}
	
	private static <E extends Comparable<E>> E findMinAndGetNextVal(List<Iterator<E>> its, List<E> currVals) {
		// Easier with Stream API but no way to find the index from it
//		Optional<Comparable<E>> min = currVals.stream().min((e1, e2) -> {
//			return e1.compareTo((E) e2);
//		});
		E minVal = currVals.get(0);
		int minIdx = 0;
		for (int i = 1; i < currVals.size(); i++) {
			E val = currVals.get(i);
			if (val == null) continue;
			if (minVal == null || val.compareTo(minVal) < 0) {
				minVal = val;
				minIdx = i;
			}
		}
		Iterator<E> it = its.get(minIdx);
		if (it.hasNext()) {
			currVals.set(minIdx, it.next());
		} else {
			currVals.set(minIdx, null); // null marks the end of the list of values
		}
		return minVal;
	}
	
	@Test
	public void testWithStrings() {
		LinkedList<String> list1 = new LinkedList<>();
		list1.add("abc");
		list1.add("bcd");
		list1.add("lmn");
		LinkedList<String> list2 = new LinkedList<>();
		list2.add("ghi");
		list2.add("ijk");
		list2.add("xyz");
		LinkedList<String> list3 = new LinkedList<>();
		list3.add("tuv");
		list3.add("vwx");
		list3.add("wxy");
		assertEquals(
				Arrays.asList("abc","bcd","ghi","ijk","lmn","tuv","vwx","wxy","xyz"),
				merge(list1,list2,list3));
	}
	
	@Test
	public void testWithIntegers() {
		LinkedList<Integer> list1 = new LinkedList<>();
		list1.add(1);
		list1.add(3);
		list1.add(5);
		LinkedList<Integer> list2 = new LinkedList<>();
		list2.add(2);
		list2.add(4);
		list2.add(6);
		LinkedList<Integer> list3 = new LinkedList<>();
		list3.add(3);
		list3.add(7);
		list3.add(9);
		assertEquals(
				Arrays.asList(1,2,3,3,4,5,6,7,9),
				merge(list1,list2,list3));
	}
}
