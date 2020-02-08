package problems.medium;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.Test;

/**
 * This problem was asked by Square.

Given a string and a set of characters, return the shortest substring containing all the characters in the set.

For example, given the string "figehaeci" and the set of characters {a, e, i}, you should return "aeci".

If there is no substring containing all the characters in the set, return null.
 * @author Andrew
 *
 */
public class ShortestSubstring {
	
	public static String getShortestSubstringWithChars(String word, Set<Character> includes) {
		List<String> subs = new ArrayList<>();
		for (int i=0; i < word.length(); i++) {
			for (int j=i; j <= word.length(); j++) {
				String sub = word.substring(i, j);
				boolean valid = true;
				for (Character c : includes) {
					if (!sub.contains(c.toString())) {
						valid = false;
						break;
					}
				}
				if (valid) {
					subs.add(sub);
				}
			}
		}
		Optional<String> min = subs.stream().min((s1, s2) -> s1.length() - s2.length());
		if (min.isPresent()) {
			return min.get();
		} else {
			return null;
		}
	}
	
	@Test
	public void defaultTest() {
		Set<Character> set = new HashSet<>();
		set.add('a');
		set.add('e');
		set.add('i');
		set.add('c');
		assertEquals("aeci",getShortestSubstringWithChars("figehaeci", set));
	}
	
	@Test
	public void customTest() {
		Set<Character> set = new HashSet<>();
		set.add('a');
		set.add('j');
		set.add('c');
		assertEquals("jamesc",getShortestSubstringWithChars("andrewjamescrenwelge", set));
	}
}
