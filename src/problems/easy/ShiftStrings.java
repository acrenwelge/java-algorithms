package problems.easy;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Given two strings A and B, return whether or not A can be shifted some number of times to get B.

For example, if A is abcde and B is cdeab, return true. If A is abc and B is acb, return false.
 * @author Andrew
 */
public class ShiftStrings {
	
	public static boolean canShift(String a, String b) {
		int strSize = a.length();
		if (strSize != b.length()) return false;
		String shifted = a;
		for (int i = 0; i < strSize; i++) {
			if (shifted.equals(b)) {
				return true;
			}
			shifted = shifted.substring(1,strSize).concat(shifted.substring(0, 1));
		}
		return false;
	}
	
	@Test
	public void defaultTests() {
		assertTrue(canShift("abcde","cdeab"));
		assertFalse(canShift("abc","acb"));
	}
	
	@Test
	public void customTests() {
		assertTrue(canShift("andrew","wandre"));
		assertFalse(canShift("andrew","werdna"));
	}
}
