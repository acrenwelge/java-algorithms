package problems.medium;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Given a string of words delimited by spaces, reverse the words in string. 
 * For example, given "hello world here", return "here world hello"

Follow-up: given a mutable string representation, can you perform this operation in-place?
 * @author Andrew
 */
public class ReverseWords {

	public String reverseWords(String words) {
		StringBuilder reversed = new StringBuilder();
		// iterate through from the end of the string and look for spaces
		int endIdx = words.length();
		int startIdx = endIdx-1;
		for (;startIdx >= 0; startIdx--) {
			if (words.charAt(startIdx) == ' ') {
				String sub = words.substring(startIdx+1, endIdx);
				reversed.append(sub + " ");
				endIdx = startIdx;
			} else if (startIdx == 0) {
				String sub = words.substring(startIdx, endIdx);
				reversed.append(sub);
			}
		}
		return reversed.toString();
	}
	
	public String reverseInPlace(StringBuilder words) {
		int initLength = words.length();
		int start = 0;
		for (int i = 1; i <= initLength; i++) {
			if (words.charAt(i-1) == ' ' || i == initLength) {
				String word = words.substring(start, i);
				if (i == initLength) {
					words.insert(initLength, word + " ");
				} else {
					words.insert(initLength, word);
				}
				start = i;
			}
		}
		return words.substring(initLength, words.length()-1);
	}
	
	public String reverseAgain(String words) {
		String[] split = words.split(" ");
		StringBuilder reversed = new StringBuilder();
		for (int i=split.length-1; i >= 0; i--) {
			if (i == 0) {
				reversed.append(split[i]);
			} else {
				reversed.append(split[i] + " ");
			}
		}
		return reversed.toString();
	}
	
	@Test
	public void testReversed() {
		assertEquals("here world hello", reverseWords("hello world here"));
		assertEquals("the little brown fox", reverseWords("fox brown little the"));
	}
	
	@Test
	public void testReversedAgain() {
		assertEquals("here world hello", reverseAgain("hello world here"));
		assertEquals("the little brown fox", reverseAgain("fox brown little the"));
	}
	
	@Test
	public void testReversedInPlace() {
		assertEquals("here world hello", reverseInPlace(new StringBuilder("hello world here")));
		assertEquals("the little brown fox", reverseInPlace(new StringBuilder("fox brown little the")));
	}
	
}
