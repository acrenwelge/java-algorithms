package algorithms.math;

import java.math.BigDecimal;

public class Fibonacci {
	
	public static void main(String[] args) {
		System.out.println(getNthFib(100));
		System.out.println(getNthFibRecurse(30));
	}
	
	/**
	 * Returns the nth Fibonacci number in the Fibonacci sequence.
	 * Uses a simple for loop.
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
	 * Returns the nth Fibonacci number in the Fibonacci sequence.
	 * Uses a simple for loop.
	 * For example, getNthFib(6) returns 8. 
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
	 * @param n
	 * @return
	 */
	public static int getNthFibRecurse(int n) {
		if (n <= 2) return 1;
		return getNthFibRecurse(n-1) + getNthFibRecurse(n-2);
	}
	
	/**
	 * Returns the index of the given Fibonacci number
	 */
	public static int getFibIndex(BigDecimal fibNum) {
		return 0;
		// TODO: implement
	}
}
