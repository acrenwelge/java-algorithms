package problems.easy;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

/**
 * Given a sorted list of integers, square the elements and give the output in sorted order.

For example, given [-9, -2, 0, 2, 3], return [0, 4, 4, 9, 81].
 * @author Andrew
 */
public class SquareAndSort {
	
	public List<Integer> implement(List<Integer> ints) {
		List<Integer> out = new ArrayList<>(ints.size());
		for (Integer num : ints) {
			out.add(num * num);
		}
		Collections.sort(out);
		return out;
	}
	
	@Test
	public void defaultTest() {
		assertEquals(implement(Arrays.asList(-9,-2,0,2,3)), Arrays.asList(0,4,4,9,81));
	}
}
