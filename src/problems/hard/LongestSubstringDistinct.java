package problems.hard;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

/**
 * This problem was asked by Amazon.

Given an integer k and a string s, find the length of the longest substring that contains at most k distinct characters.

For example, given s = "abcba" and k = 2, the longest substring with k distinct characters is "bcb".
 * @author Andrew
 */
public class LongestSubstringDistinct {
	
	/*
	 * Approach:
	 *  - brute force: search all substrings
	 *  - check each for distinct characters
	 *  - keep only the longest substring
	 */
	public int getLongestSubstring(int k, String s) {
		String match = "";
		for (String current : getAllSubstrings(s)) {
			int count = countDistinctChars(current);
			if (count > k) continue;
			else if (current.length() > match.length()) match = current;
		}
		return match.length();
	}
	
	private String[] getAllSubstrings(String s) {
		Set<String> set = new HashSet<>();
		for (int i = 0; i <= s.length(); i++) {
			for (int j = 0; j <= s.length(); j++) {
				if (i > j) {
					set.add(s.substring(j,i));
				} else {
					set.add(s.substring(i, j));
				}
			}
		}
		return set.toArray(new String[set.size()]);
	}
	
	private int countDistinctChars(String s) {
		Set<Character> set = new HashSet<>();
		for (Character c : s.toCharArray()) {
			set.add(c);
		}
		return set.size();
	}
	
	@Test
	public void defaultTest() {
		String s = "abcba";
		int k = 2;
		assertEquals(3, getLongestSubstring(k,s));
	}
	
	@Test
	public void customTest() {
		String s = "aaaabcab"; // longest substring w/ 2 distinct chars is "aaaab"
		int k = 2;
		assertEquals(5, getLongestSubstring(k,s));
	}
	
	@Test
	public void substringTest() {
		assertEquals(7,getAllSubstrings("abc").length);
	}

}
