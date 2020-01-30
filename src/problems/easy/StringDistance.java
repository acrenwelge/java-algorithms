package problems.easy;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * This problem was asked by Google.

The edit distance between two strings refers to the minimum number of 
character insertions, deletions, and substitutions required to change one string to the other. 
For example, the edit distance between “kitten” and “sitting” is three: 
substitute the “k” for “s”, substitute the “e” for “i”, and append a “g”.

Given two strings, compute the edit distance between them.
 * @author Andrew
 */
public class StringDistance {
	
	/*
	 * Approach: 
	 * - looked up Levenshtein distance (https://en.wikipedia.org/wiki/Levenshtein_distance)
	 * - recursively implement
	 */
	public int getDistance(String first, String second) {
		int d1 = first.length();
		int d2 = second.length();
		if (d1 == 0 || d2 == 0) {
			return Math.max(d1, d2);
		} else {
			int deletionTerm = getDistance(first.substring(0, first.length()-1),second) + 1;
			int insertionTerm = getDistance(first,second.substring(0, second.length()-1)) + 1;
			int indicatorFunc = first.substring(first.length()-1, first.length()).equals(
					second.substring(second.length()-1, second.length())) 
					 ? 0 : 1;
			int matchTerm = getDistance(first.substring(0, first.length()-1),second.substring(0, second.length()-1)) + indicatorFunc;
			return Math.min(deletionTerm, Math.min(matchTerm, insertionTerm));
		}
	}
	
	@Test
	public void defaultStringDistTest() {
		assertEquals(3, getDistance("kitten", "sitting"));
	}
	
	@Test
	public void customStringDistTest() {
		assertEquals(2, getDistance("flaw", "lawn"));
	}
}
