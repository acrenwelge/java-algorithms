package problems.hard;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

/**
 * This problem was asked by Amazon.

There exists a staircase with N steps, and you can climb up either 1 or 2 steps at a time. 
Given N, write a function that returns the number of unique ways you can climb the staircase. 
The order of the steps matters.

For example, if N is 4, then there are 5 unique ways:

1, 1, 1, 1
2, 1, 1
1, 2, 1
1, 1, 2
2, 2

What if, instead of being able to climb 1 or 2 steps at a time, you could climb 
any number from a set of positive integers X? For example, if X = {1, 3, 5}, 
you could climb 1, 3, or 5 steps at a time.
 * @author Andrew
 *
 */
public class Staircase {
	
	/*
	 * Approach: look at simple examples
	 * - N=1 -> 1
	 * - N=2 -> 2
	 * - N=3 -> 3
	 * - N=4 -> 5
	 * - N=5 -> 8
	 * 
	 * seems to follow fibonacci sequence, with ways(n) = fib(n+1)
	 */
	public int traverseStaircase(int n) {
		return getFib(n+1);
	}
	
	private int getFib(int n) {
		int first=1;
		int second=1;
		if (n == 1) return first;
		else if (n == 2) return second;
		for (int i=2;i<n;i++) {
			int tmp = second;
			second = first + second;
			first = tmp;
		}
		return second;
	}
	
	/*
	 * Approach to general solution:
	 * ways(n,x) where n is # of stairs, x is set of possible # of steps taken
	 * ways(n,x) = ways(n-x[0],x) + ways(n-x[1],x) + ... ways(n-x[x.length],x)
	 * 
	 * base case: n=1 -> count = 1
	 */
	public int generalSolution(int n, Set<Integer> x) {
		return generalSolutionRecurse(n+1, x);
	}
	
	private int generalSolutionRecurse(int n, Set<Integer> x) {
		if (n <= 1) return 1;
		int count = 0;
		for (Integer i : x) {
			int param = n - i;
			if (param > 0) count += generalSolutionRecurse(param, x);
		}
		return count;
	}
	
	private int fasterGeneralSolution(int n, Set<Integer> x) {
		// TODO: implement iterative approach
		return 0;
	}

	@Test
	public void defaultTest() {
		assertEquals(5, traverseStaircase(4));
	}
	
	@Test
	public void fibTest() {
		assertEquals(1, getFib(1));
		assertEquals(1, getFib(2));
		assertEquals(2, getFib(3));
		assertEquals(3, getFib(4));
		assertEquals(5, getFib(5));
	}
	
	@Test
	public void generalTest() {
		Set<Integer> steps = new HashSet<>();
		steps.add(2);
		steps.add(1);
		assertEquals(5, generalSolution(4,steps));
		assertEquals(8, generalSolution(5,steps));
	}
}
