package problems.medium;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Implement integer exponentiation. That is, implement the pow(x, y) function, where x and y are integers and returns x^y.

Do this faster than the naive method of repeated multiplication.

For example, pow(2, 10) should return 1024.
 * @author Andrew
 *
 */
public class Exponentiation {
	
	public static int naivePower(int base, int power) {
		int result = 1;
		for (int i=0; i < power; i++) {
			result *= base;
		}
		return result;
	}
	
	public static int pow(int base, int power) {
		if (power == 0) return 1;
		if (power % 2 != 0) {
			return base * pow(base, power - 1);
		} else {
			int p = pow(base, power / 2);
			return p * p;
		}
	}
	
	@Test
	public void testPower() {
		assertEquals(naivePower(2, 10), 1024);
		assertEquals(naivePower(2, 15), Math.pow(2, 15), 0.1);
	}
	
	@Test
	public void testFastPower() {
		assertEquals(pow(2, 10), 1024);
		assertEquals(pow(2, 15), Math.pow(2, 15), 0.1);
	}
}
