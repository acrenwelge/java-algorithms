package problems.easy;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

/**
 * Good morning! Here's your coding interview problem for today.

This problem was recently asked by Google.

Given a list of numbers and a number k, return whether any two numbers from the list add up to k.

For example, given [10, 15, 3, 7] and k of 17, return true since 10 + 7 is 17.

Bonus: Can you do this in one pass?
 * @author Andrew
 */
public class DoTheyAddUp {
	
	int[] arr;
	int sum;

	@Test
	public void testTrue() {
		arr = new int[] {10, 15, 3, 7};
		sum = 17;
		assertTrue(containsSum(arr,sum));
	}
	
	@Test
	public void testFalse() {
		arr = new int[] {3, 10, 2, 9};
		sum = 14;
		assertFalse(containsSum(arr,sum));
	}
	
	@Test
	public void testTrueOnePass() {
		arr = new int[] {10, 15, 3, 7};
		sum = 17;
		assertTrue(onePass(arr,sum));
	}
	
	@Test
	public void testFalseOnePass() {
		arr = new int[] {3, 10, 2, 9};
		sum = 14;
		assertFalse(onePass(arr,sum));
	}
	
	public static boolean containsSum(int[] nums, int sum) {
		for (int i=0; i<nums.length; i++) {
			if (i != nums.length) {
				for (int j=i+1; j<nums.length; j++) {
					if (nums[i] + nums[j] == sum) return true;
				}
			}
		}
		return false;
	}
	
	public static boolean onePass(int[] nums, int sum) {
		Set<Integer> matchMe = new HashSet<>();
		matchMe.add(sum - nums[0]);
		for (int i = 1; i < nums.length; i++) {
			if (matchMe.contains(nums[i])) {
				return true;
			} else {
				matchMe.add(sum - nums[i]);
			}
		}
		return false;
	}

}
