package problems.easy;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * This problem was asked by Microsoft.

Compute the running median of a sequence of numbers. 
That is, given a stream of numbers, print out the median of the list so far on each new element.

Recall that the median of an even-numbered list is the average of the two middle numbers.

For example, given the sequence [2, 1, 5, 7, 2, 0, 5], your algorithm should print out:

2
1.5
2
3.5
2
2
2
 * @author Andrew
 *
 */
public class RunningMedian {
	
	private static Logger log = LogManager.getLogger(RunningMedian.class);
	
	public static double[] getMedians(int[] arr) {
		if (arr.length == 0) return new double[] {};
		double[] meds = new double[arr.length];
		for (int i=1; i <= arr.length; i++) {
			int idx = i - 1;
			int[] sub = Arrays.copyOfRange(arr, 0, i);
			Arrays.sort(sub);
			for (int j : sub) {
				log.debug(j);
			}
			if (sub.length % 2 != 0) {
				int middle = (int) Math.floor((double) sub.length / 2);
				log.debug("MEDIAN:" + sub[middle]);
				meds[idx] = sub[middle];
			} else {
				int a = sub[sub.length / 2];
				int b = sub[sub.length / 2 - 1];
				meds[idx] = (double) (a + b) / 2;
				log.debug("MEDIAN:" + meds[idx]);
			}
			log.debug("=================");
		}
		return meds;
	}
	
	@Test
	public void defaultTest() {
		int[] seq = {2,1,5,7,2,0,5};
		double[] meds = {2,1.5f,2,3.5f,2,2,2};
		assertArrayEquals(meds, getMedians(seq), 0.01);
	}
}
