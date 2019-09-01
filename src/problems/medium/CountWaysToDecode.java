package problems.medium;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * This problem was asked by Facebook.

Given the mapping a = 1, b = 2, ... z = 26, and an encoded message, count the number of ways it can be decoded.

For example, the message '111' would give 3, since it could be decoded as 'aaa', 'ka', and 'ak'.

You can assume that the messages are decodable. For example, '001' is not allowed.
 * @author Andrew
 *
 */
public class CountWaysToDecode {
	
	/** Approach:
	 * Attempt 1:
	 *   - count single character decoding (length of string) + number of 2-digit decoding
	 *   - realized this won't work because it doesn't count combinations of 1/2-digit decoding
	 *   
	 * Attempt 2:
	 *  - read in characters 2 at a time
	 *  - if > 26 there is only 1 way to decode that: as single digits
	 *  - otherwise there are 2 ways, so the total count is doubled (or set to 2 initially)
	 *  - continue until we hit the end of the string
	 *  --> this is better but it overestimates because it double counts digits that have already been decoded
	 *  --> adjust algorithm to (a) only count valid 2-digit nums, (b) double the count but subtract 1 for reusing the digit
	 *  and (c) return 1 if count is still 0 at the end (there is always at least 1 way of decoding - all single digits)
	 */
	public int countDecodingWays(String msg) {
		int count = 0;
		for (int i = 0; i < msg.length(); i++) {
			int endIndex = i + 2;
			if (endIndex >= msg.length()) {
				endIndex = msg.length(); // for last digit, just read the one digit
			}
			Integer num = Integer.parseInt(msg.substring(i, endIndex));
			if (num <= 26 && num > 9) {
				count = count == 0 ? 2 : count * 2 - 1; // double but subtract 1 for the digit that was reused
				continue;
			}
		}
		if (count == 0)
			return 1;
		else return count;
	}
	
	@Test
	public void testMessage() {
		assertEquals(3, countDecodingWays("111"));
	}
	
	@Test
	public void customTest1() {
		assertEquals(2, countDecodingWays("131"));
	}
	
	@Test
	public void customTest2() {
		assertEquals(1, countDecodingWays("9999"));
	}
	
	@Test
	public void customTest3() {
		assertEquals(5, countDecodingWays("1234521"));
	}
	
	@Test
	public void customTest4() {
		assertEquals(9, countDecodingWays("12345211"));
	}
}
