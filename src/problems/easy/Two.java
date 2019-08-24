package problems.easy;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

/**
 * This problem was asked by Uber.

Given an array of integers, return a new array such that each element at index i of the new array is the product of all the numbers in the original array except the one at i.

For example, if our input was [1, 2, 3, 4, 5], the expected output would be [120, 60, 40, 30, 24]. If our input was [3, 2, 1], the expected output would be [2, 3, 6].

Follow-up: what if you can't use division?
 * @author Andrew
 *
 */
public class Two {
	int[] input;
	int[] output;
	
	@Test
	public void exampleTest() {
		input = new int[] {1, 2, 3, 4, 5};
		output = naive(input);
		assertArrayEquals(output, new int[] {120, 60, 40, 30, 24});
	}
	
	@Test
	public void exampleTestTwo() {
		input = new int[] {3, 2, 1};
		output = naive(input);
		assertArrayEquals(output, new int[] {2, 3, 6});
	}
	
	public int[] naive(int[] input) {
		int [] arr = new int[input.length];
		for (int i=0; i < input.length; i++) {
			int product = 1;
			for (int j=0; j < input.length; j++) {
				if (j != i) {
					product *= input[j];
				}
			}
			arr[i] = product;
		}
		return arr;
	}
}
