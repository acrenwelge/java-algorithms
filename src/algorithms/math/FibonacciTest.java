package algorithms.math;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

class FibonacciTest {

	/**
	 * The Fibonacci sequence:
	 * 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89
	 */
	@Test
	void testLoop() {
		assertEquals(1, Fibonacci.getNthFib(1));
		assertEquals(1, Fibonacci.getNthFib(2));
		assertEquals(2, Fibonacci.getNthFib(3));
		assertEquals(3, Fibonacci.getNthFib(4));
		assertEquals(13, Fibonacci.getNthFib(7));
		assertEquals(55, Fibonacci.getNthFib(10));
		assertEquals(832040, Fibonacci.getNthFib(30));
	}
	
	@Test
	void testBigDecimal() {
		assertEquals(BigDecimal.ONE, Fibonacci.getNthFibBigDec(1));
		assertEquals(BigDecimal.ONE, Fibonacci.getNthFibBigDec(2));
		assertEquals(new BigDecimal(2), Fibonacci.getNthFibBigDec(3));
		assertEquals(new BigDecimal(3), Fibonacci.getNthFibBigDec(4));
		assertEquals(new BigDecimal(13), Fibonacci.getNthFibBigDec(7));
		assertEquals(new BigDecimal(55), Fibonacci.getNthFibBigDec(10));
		assertEquals(new BigDecimal(832040), Fibonacci.getNthFibBigDec(30));
	}
	
	@Test
	void testRecursion() {
		assertEquals(1, Fibonacci.getNthFibRecurse(1));
		assertEquals(1, Fibonacci.getNthFibRecurse(2));
		assertEquals(2, Fibonacci.getNthFibRecurse(3));
		assertEquals(3, Fibonacci.getNthFibRecurse(4));
		assertEquals(13, Fibonacci.getNthFibRecurse(7));
		assertEquals(55, Fibonacci.getNthFibRecurse(10));
		assertEquals(832040, Fibonacci.getNthFibRecurse(30));
	}
	
}
