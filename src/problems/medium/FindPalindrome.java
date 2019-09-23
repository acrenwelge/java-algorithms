package problems.medium;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
Given a string, find the palindrome that can be made by inserting the fewest number of characters 
as possible anywhere in the word. If there is more than one palindrome of minimum length that can be made, 
return the lexicographically earliest one (the first one alphabetically).

For example, given the string "race", you should return "ecarace", 
since we can add three letters to it (which is the smallest amount to make a palindrome). 
There are seven other palindromes that can be made from "race" by adding three letters, but "ecarace" comes first alphabetically.

As another example, given the string "google", you should return "elgoogle".
 * @author Andrew
 *
 */
public class FindPalindrome {
	
	public String findPalindrome(String word) {
		if (isPalindrome(word)) return word;
		return recurse(word);
	}
	
	private String recurse(String word) {
		if (word.length() <= 1) {
			return word;
		} else if (word.length() == 2) {
			char first = word.charAt(0);
			char sec = word.charAt(1);
			if (first == sec) {
				return word;
			} else {
				if (first < sec) return word + first;
				else return sec + word;
			}
		} else {
			int len = word.length() - 1;
			if (word.charAt(0) == word.charAt(len)) {
				return word.charAt(0) + recurse(word.substring(1, len)) + word.charAt(len+1);
			} else {
				String left = recurse(word.substring(0, len-1) + word.charAt(len));
				String right = word.charAt(0) + recurse(word.substring(1));
				if (left.length() < right.length()) {
					return left;
				} else if (right.length() > left.length()) {
					return right;
				} else {
					return left.compareTo(right) < 0 ? left : right;
				}
			}
		}
	}
	
	private boolean isPalindrome(String word) {
		int length = word.length();
		for (int i=0;i<length/2; i++) {
			char c = word.charAt(i);
			if (c != word.charAt(length - i - 1)) {
				return false;
			}
		}
		return true;
	}
	
	@Test
	public void defaultTest() {
		String test = "race";
		assertEquals("ecarace", findPalindrome(test));
	}
	
	@Test
	public void defaultTest2() {
		String test = "google";
		assertEquals("elgoogle", findPalindrome(test));
	}
}
