package algorithms.sort;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;

public class CountSorter implements Sorter {
	static final Logger log = LogManager.getLogger(CountSorter.class);
	static final int MAX_VAL = 10_000; // array elements must not exceed this

	/**
	 * Count sort requires that each element of the array is less than MAX_VAL.
	 * It counts the occurrences of each value and then iterates through the range
	 * of indexes to sort the array.
	 * <br>
	 * <strong>Complexity:</strong> O(n + MAX_VAL)
	 * @param arr - the array of <tt>int</tt>
	 */
	@Override
	public int[] sort(int[] arr) {
		Map<Integer, Integer> counts = new HashMap<>();
		for (int i=0; i < arr.length; i++) {
			int val = arr[i];
			if (val > MAX_VAL) throw new IllegalArgumentException("Illegal array value: cannot be >"+MAX_VAL);
			if (counts.containsKey(val)) {
				log.debug(String.format("Inserting num %s with count %s into hash table", val, counts.get(val)+1));
				counts.put(val, counts.get(val)+1);
			} else {
				log.debug(String.format("Inserting num %s with count 1 into hash table", val));
				counts.put(val, 1);
			}
		}
		for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
			log.debug(entry.getKey() + ":" + entry.getValue());
		}
		int[] sorted = new int[arr.length];
		int idx = 0;
		for (int j=0; j < MAX_VAL; j++) {
			if (counts.containsKey(j)) {
				int amt = counts.get(j);
				Arrays.fill(sorted, idx, idx+amt, j);
				idx += amt;
			}
			if (idx == arr.length) break; // no need to waste more time, we're done!
		}
		return sorted;
	}
	
	@Test
	public void testCountSorter() {
		int[] arr = {9, 5, 8, 0, 11, 4, 7};
		Sorter sorter = new CountSorter();
		assertArrayEquals(new int[] {0,4,5,7,8,9,11}, sorter.sort(arr));
	}

}
