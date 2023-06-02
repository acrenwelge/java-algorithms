package problems.medium;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assume.assumeThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

/**
 * Given a dictionary of words and a string made up of those words (no spaces), 
 * return the original sentence in a list. If there is more than one possible reconstruction, 
 * return any of them. If there is no possible reconstruction, then return null.

For example, given the set of words 'quick', 'brown', 'the', 'fox', and the string "thequickbrownfox", 
you should return ['the', 'quick', 'brown', 'fox'].

Given the set of words 'bed', 'bath', 'bedbath', 'and', 'beyond', and the string "bedbathandbeyond", 
return either ['bed', 'bath', 'and', 'beyond'] or ['bedbath', 'and', 'beyond'].
 * @author Andrew
 *
 */
public class ConstructSentence {
	
	/*
	 * Approach
	 *  - parse and search for first occurance of any word
	 */
	public List<String> constructSentence(Set<String> dictionary, String parseMe) {
		List<String> sentence = new ArrayList<>();
		int wordStart = 0;
		for (int i=0; i <= parseMe.length(); i++) {
			String sub = parseMe.substring(wordStart, i);
			for (String word : dictionary) {
				if (sub.equals(word)) {
					sentence.add(word);
					wordStart = i;
					break;
				}
			}
		}
		return sentence;
	}
	
	@Test
	public void defaultTest() {
		Set<String> dict = new HashSet<>();
		List<String> orig = Arrays.asList("the","quick","brown","fox"); 
		dict.addAll(orig);
		assertEquals(orig, constructSentence(dict, "thequickbrownfox"));
	}
	
	@Test
	public void defaultTest2() {
		Set<String> dict = new HashSet<>();
		List<String> orig = Arrays.asList("bed","bath","bedbath","and","beyond"); 
		dict.addAll(orig);
		assertEquals(Arrays.asList("bed","bath","and","beyond"), constructSentence(dict, "bedbathandbeyond"));
	}
}
