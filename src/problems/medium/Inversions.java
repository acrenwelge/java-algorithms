package problems.medium;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

/**
 * We can determine how "out of order" an array A is by counting the number of inversions it has. 
 * Two elements A[i] and A[j] form an inversion if A[i] > A[j] but i < j. 
 * That is, a smaller element appears after a larger element.

Given an array, count the number of inversions it has. Do this faster than O(N^2) time.

You may assume each element in the array is distinct.

For example, a sorted list has zero inversions. The array [2, 4, 1, 3, 5] has three inversions: 
(2, 1), (4, 1), and (4, 3). The array [5, 4, 3, 2, 1] has ten inversions: every distinct 
pair forms an inversion.

 * @author Andrew
 */
public class Inversions {
	
	static final Logger log = LogManager.getRootLogger(); 
	
	// simple O(n^2) solution
	public int countInversionsN2(int[] arr) {
		log.info("Starting O(n^2) solution to count inversions");
		int inverts = 0;
		for (int i=0; i < arr.length; i++) {
			int compare = arr[i];
			for (int j=i+1;j < arr.length; j++) {
				if (compare > arr[j]) inverts++;
			}
		}
		return inverts;
	}
	
	/*
	 * Approach:
	 *  - we only need to check for other inversions once we find a single inversion
	 *  - traverse forward/back once we find inversion
	 * Problem: ran into problem with test 2, was double counting previous inversions
	 * Solution: use a set to store inversion pairs
	 * - we can assume elements are distinct, so this solution should work 
	 * (numbers won't appear twice and fail to be added to the set)
	 */
	public int countInversions(int[] arr) {
		class Invert {
			int x;
			int y;
			public Invert(int x,int y) {
				this.x=x; this.y=y;
			}
			
			@Override
			public String toString() {
				return this.x + "," + this.y;
			}

			/* It's important that x and y are interchangeable in the inversion pairs.
			 * So hashCode and equals methods must be overridden so that the set detects e.g. (1,2) and (2,1) as the same
			 * (because elements are distinct, this means we've already detected this inversion)
			 */
			@Override
			public int hashCode() {
				return x + y;
			}

			@Override
			public boolean equals(Object obj) {
				Invert o = (Invert) obj;
				return ((this.x == o.x) && (this.y == o.y)) || ((this.y == o.x) && (this.x == o.y));
			}
			
		}
		Set<Invert> inverts = new HashSet<>();
		log.info("Counting inversions... efficient solution");
		for (int i=0; i < arr.length-1; i++) {
			log.debug("AT " + i);
			int current = arr[i];
			int next = arr[i+1];
			if (current > next) {
				inverts.add(new Invert(current,next));
				log.debug("INVERT++  ... TOTAL = " + inverts.size());
				for (int j=i-1; j>=0; j--) {
					if (arr[j] > next) {
						log.debug("BACK INVERT");
						inverts.add(new Invert(arr[j],next));
					}
				}
				for (int j=i+2; j<arr.length; j++) {
					if (current > arr[j]) {
						log.debug("FORWARD INVERT");
						inverts.add(new Invert(arr[j],next));
					}
				}
			}
		}
		inverts.stream().forEach(log::debug);
		return inverts.size();
	}
	
	@Test
	public void sortedTest() {
		int[] arr = {1,2,3,4,5};
		assertEquals(0, countInversionsN2(arr));
		assertEquals(0, countInversions(arr));
	}
	
	@Test
	public void defaultTest() {
		int[] arr = {2,4,1,3,5};
		assertEquals(3, countInversionsN2(arr));
		assertEquals(3, countInversions(arr));
	}
	
	@Test
	public void defaultTest2() {
		int[] arr = {5,4,3,2,1};
		assertEquals(10, countInversionsN2(arr));
		assertEquals(10, countInversions(arr));
	}
}
