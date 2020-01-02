package problems.hard;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * This problem was asked by Amazon.

Given a string, find the longest palindromic contiguous substring. 
If there are more than one with the maximum length, return any one.

For example, the longest palindromic substring of "aabcdcb" is "bcdcb". 
The longest palindromic substring of "bananas" is "anana".
 * @author Andrew
 */
public class SubstringPalindrome {
	
	public String find(String str) {
		return null;
	}
	
	@Test
	public void test() {
		assertEquals("bcdcb", find("aabcdcb"));
		assertEquals("anana", find("bananas"));
	}
}
