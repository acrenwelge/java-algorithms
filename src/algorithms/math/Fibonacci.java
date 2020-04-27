package algorithms.math;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import util.Profiler;

public class Fibonacci {
	private static Map<Integer, Integer> memoMap = new HashMap<>();
	static {
		memoMap.put(1, 1);
		memoMap.put(2, 1);
	}
	
	/**
	 * Returns the nth Fibonacci number in the Fibonacci sequence iteratively.
	 * For example, getNthFib(6) returns 8. 
	 */
	public static int getNthFib(int n) {
		if (n <= 2) return 1;
		int first = 1;
		int second = 2;
		for (int i = 2; i < n; i++) {
			first += second;
			// swap first, second b/c first is now bigger
			int temp = first;
			first = second;
			second = temp;
		}
		return first;
	}
	
	/**
	 * BigDecimal implementation
	 */
	public static BigDecimal getNthFibBigDec(int n) {
		if (n <= 2) return BigDecimal.ONE;
		BigDecimal first = BigDecimal.ONE;
		BigDecimal second = new BigDecimal(2);
		for (int i = 2; i < n; i++) {
			first = first.add(second);
			// swap first, second b/c first is now bigger
			BigDecimal temp = first;
			first = second;
			second = temp;
		}
		return first;
	}
	
	/**
	 * Returns the nth Fibonacci number, using recursion
	 */
	public static int getNthFibRecurse(int n) {
		if (n <= 2) return 1;
		return getNthFibRecurse(n-1) + getNthFibRecurse(n-2);
	}
	
	/**
	 * Uses memoization to improve efficiency
	 */
	public static int getNthFibMemoized(int n) {
		if (memoMap.containsKey(n)) {
			return memoMap.get(n);
		} else {
			int ans = getNthFibMemoized(n-1) + getNthFibMemoized(n-2);
			memoMap.put(n, ans);
			return ans;
		}
	}
	
	/**
	 * Returns the index of the given Fibonacci number
	 */
	public static int getFibIndex(BigDecimal fibNum) {
		if (fibNum.equals(BigDecimal.ONE)) {
			return 1;
		}
		int i = 3;
		BigDecimal first = BigDecimal.ONE;
		BigDecimal second = BigDecimal.ONE;
		while(true) {
			BigDecimal tmp = second;
			second = first.add(second);
			first = tmp;
			if (second.equals(fibNum)) {
				return i;
			} else if (second.compareTo(fibNum) > 0) {
				throw new IllegalArgumentException("The parameter " + fibNum + " is not a fibonacci number");
			}
			i++;
		}
	}
	
	/**
	 * The Fibonacci sequence:
	 * 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89
	 */
	@Test
	public void testLoop() {
		assertEquals(1, Fibonacci.getNthFib(1));
		assertEquals(1, Fibonacci.getNthFib(2));
		assertEquals(2, Fibonacci.getNthFib(3));
		assertEquals(3, Fibonacci.getNthFib(4));
		assertEquals(13, Fibonacci.getNthFib(7));
		assertEquals(55, Fibonacci.getNthFib(10));
		assertEquals(832040, Fibonacci.getNthFib(30));
	}
	
	@Test
	public void testBigDecimal() {
		assertEquals(BigDecimal.ONE, Fibonacci.getNthFibBigDec(1));
		assertEquals(BigDecimal.ONE, Fibonacci.getNthFibBigDec(2));
		assertEquals(new BigDecimal(2), Fibonacci.getNthFibBigDec(3));
		assertEquals(new BigDecimal(3), Fibonacci.getNthFibBigDec(4));
		assertEquals(new BigDecimal(13), Fibonacci.getNthFibBigDec(7));
		assertEquals(new BigDecimal(55), Fibonacci.getNthFibBigDec(10));
		assertEquals(new BigDecimal(832040), Fibonacci.getNthFibBigDec(30));
	}
	
	@Test
	public void testRecursion() {
		assertEquals(1, Fibonacci.getNthFibRecurse(1));
		assertEquals(1, Fibonacci.getNthFibRecurse(2));
		assertEquals(2, Fibonacci.getNthFibRecurse(3));
		assertEquals(3, Fibonacci.getNthFibRecurse(4));
		assertEquals(13, Fibonacci.getNthFibRecurse(7));
		assertEquals(55, Fibonacci.getNthFibRecurse(10));
		assertEquals(832040, Fibonacci.getNthFibRecurse(30));
	}
	
	@Test
	public void testRecursionWithMemoization() {
		assertEquals(1, Fibonacci.getNthFibMemoized(1));
		assertEquals(1, Fibonacci.getNthFibMemoized(2));
		assertEquals(2, Fibonacci.getNthFibMemoized(3));
		assertEquals(3, Fibonacci.getNthFibMemoized(4));
		assertEquals(13, Fibonacci.getNthFibMemoized(7));
		assertEquals(55, Fibonacci.getNthFibMemoized(10));
		assertEquals(832040, Fibonacci.getNthFibMemoized(30));
	}
	
	@Test
	public void testFibIndex() {
		BigDecimal fib7 = new BigDecimal(13);
		assertEquals(7, getFibIndex(fib7));
		BigDecimal fib30 = new BigDecimal(832040);
		assertEquals(30, getFibIndex(fib30));
	}
	
	@Test
	public void speedTest() {
		Profiler.profile("regular alg",() -> {Fibonacci.getNthFib(30);});
		Profiler.profile("recurse alg",() -> {Fibonacci.getNthFibRecurse(30);});
		Profiler.profile("memoized alg",() -> {Fibonacci.getNthFibMemoized(30);});
		Profiler.profile("big decimal alg",() -> {Fibonacci.getNthFibBigDec(30);});
	}
}
