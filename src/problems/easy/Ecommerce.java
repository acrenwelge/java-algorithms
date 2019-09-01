package problems.easy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * This problem was asked by Twitter.

You run an e-commerce website and want to record the last N order ids in a log. 
Implement a data structure to accomplish this, with the following API:

record(order_id): adds the order_id to the log
get_last(i): gets the ith last element from the log. i is guaranteed to be smaller than or equal to N.
You should be as efficient with time and space as possible.
 * @author Andrew
 *
 */
public class Ecommerce {
	static final int N = 100; // max to record in the log
	int[] log = new int[N];
	int cur_idx = 0;
	boolean isFull = false;
	
	public void record(int order_id) {
		log[cur_idx] = order_id;
		cur_idx++;
		if (cur_idx > N-1) {
			cur_idx = 0;
			isFull = true;
		}
	}
	
	public int get_last(int i) {
		int idx = cur_idx - i - 1;
		if (idx < 0) {
			idx = N - 1 - Math.abs(idx); // circle back and count from end of the array
		}
		return log[idx];
	}
	
	@Test
	public void testRecord() {
		Ecommerce myAwesomeApp = new Ecommerce();
		myAwesomeApp.record(5380);
		myAwesomeApp.record(7151);
		myAwesomeApp.record(5719);
		myAwesomeApp.record(3612);
		assertEquals(5380, myAwesomeApp.log[0]);
		assertEquals(3612, myAwesomeApp.log[3]);
	}
	
	@Test
	public void testGet() {
		Ecommerce myAwesomeApp = new Ecommerce();
		myAwesomeApp.record(5380);
		myAwesomeApp.record(7151);
		myAwesomeApp.record(5719);
		myAwesomeApp.record(3612);
		assertEquals(4, myAwesomeApp.cur_idx);
		
		assertEquals(3612, myAwesomeApp.get_last(0));
		assertEquals(5380, myAwesomeApp.get_last(3));
	}
	
	@Test
	public void testFull() {
		Ecommerce myAwesomeApp = new Ecommerce();
		for (int i = 0; i < Ecommerce.N * 1.5; i++) {
			myAwesomeApp.record((int) (Math.random()*100));
		}
		assertTrue(myAwesomeApp.isFull);
		assertNotEquals(0, myAwesomeApp.get_last(0));
		assertNotEquals(0, myAwesomeApp.get_last(100));
	}
}
