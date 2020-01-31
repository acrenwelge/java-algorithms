package problems.easy;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

/**
 * Given a list of possibly overlapping intervals, return a new list of intervals 
 * where all overlapping intervals have been merged.

The input list is not necessarily ordered in any way.

For example, given [(1, 3), (5, 8), (4, 10), (20, 25)], you should return [(1, 3), (4, 10), (20, 25)].

 * @author Andrew
 *
 */
public class OverlappingIntervals {
	
	static class Interval {
		int min;
		int max;
		public Interval() {}
		public Interval(int min, int max) {
			this.min = min;
			this.max = max;
		}
		@Override
		public String toString() {
			return "MIN:"+min+", MAX:"+max;
		}
		@Override
		public boolean equals(Object o) {
			if (!(o instanceof Interval)) return false;
			Interval i = (Interval) o;
			return i.min == this.min && i.max == this.max;
		}
		
	}
	
	public static Interval[] merge(Interval[] arr) {
		List<Interval> merged = new ArrayList<>();
		for (int i=0; i < arr.length; i++) {
			Interval ival = arr[i];
			boolean foundOverlap = false;
			for (int j=0; j < arr.length; j++) {
				Interval compare = arr[j];
				if (i != j && compare != null && hasOverlap(ival, compare)) {
					foundOverlap = true;
					Interval newInterval = new Interval();
					newInterval.min = Math.min(ival.min, compare.min);
					newInterval.max = Math.max(ival.max, compare.max);
					merged.add(newInterval);
					arr[j] = null; // remove unnecessary interval
					break;
				}
			}
			if (!foundOverlap && ival != null) {
				merged.add(ival);
			}
		}
		return merged.toArray(new Interval[0]);
	}
	
	public static boolean hasOverlap(Interval i1, Interval i2) {
		if (i1 == null || i2 == null) return false;
		return (i1.max > i2.min && i1.min < i2.max)
		 || (i2.max > i1.min && i2.min < i1.max);
	}
	
	@Test
	public void defaultTest() {
		Interval i1 = new Interval(1,3);
		Interval i2 = new Interval(5,8);
		Interval i3 = new Interval(4,10);
		Interval i4 = new Interval(20,25);
		Interval[] input = new Interval[] {i1,i2,i3,i4};
		Interval[] expected = new Interval[] {i1,i3,i4};
		assertArrayEquals(expected, merge(input));
	}
	
	@Test
	@Ignore
	public void testOverlap() {
		Interval i1 = new Interval(1,3);
		Interval i2 = new Interval(5,8);
		Interval i3 = new Interval(4,10);
		Interval i4 = new Interval(20,25);
		assertFalse(hasOverlap(i1,i2));
		assertTrue(hasOverlap(i2,i3));
		assertFalse(hasOverlap(i1,i2));
		assertFalse(hasOverlap(i3,i4));
	}
}
