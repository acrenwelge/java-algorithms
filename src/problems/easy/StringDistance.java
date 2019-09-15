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
	
	public int getDistance(String first, String second) {
		return 0;
	}
	
	@Test
	public void defaultTest() {
		assertEquals(3, getDistance("kitten", "sitting"));
	}
}
