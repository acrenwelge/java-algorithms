package problems.easy;

import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

/**
 * Given a mapping of digits to letters (as in a phone number), and a digit string, 
 * return all possible letters the number could represent. 
 * You can assume each valid number in the mapping is a single digit.

For example if {2: [“a”, “b”, “c”], 3: [“d”, “e”, “f”], …} then “23” should 
return [“ad”, “ae”, “af”, “bd”, “be”, “bf”, “cd”, “ce”, “cf"].
 * @author Andrew
 *
 */
public class DigitLetterMapping {
	public static final Logger log = LogManager.getLogger(DigitLetterMapping.class);
	
	public static String[] getAllPossibleLetters(Map<Integer, char[]> mapping, String digits) {
		List<StringBuilder> solutions = new ArrayList<>();
		solutions.add(new StringBuilder()); // initial value to start the loop, otherwise would never even start
		for (int i=0; i < digits.length(); i++) {
			// identify next mapping of int to characters
			log.debug(String.format("looking at %s of string %s",digits.substring(i, i+1),digits));
			Integer findMe = Integer.parseInt(digits.substring(i, i+1));
			char[] mappedTo = mapping.get(findMe);
			// iterate through existing list of permutations
			ListIterator<StringBuilder> it = solutions.listIterator();
			while(it.hasNext()) {
				StringBuilder oldPermutation = it.next();
				for (char c : mappedTo) {
					// create new permutations
					StringBuilder permutation = new StringBuilder(oldPermutation);
					permutation.append(c);
					log.debug("adding: "+permutation.toString());
					it.add(permutation);
				}
				log.debug("solutions:"+solutions);
			}
		}
		solutions.removeIf(s -> s.length() < digits.length());
		String[] finalSolution = new String[solutions.size()];
		for (int i=0; i < solutions.size(); i++) {
			finalSolution[i] = solutions.get(i).toString();
		}
		Arrays.sort(finalSolution);
		return finalSolution;
	}
	
	@Test
	public void defaultTest() {
		Map<Integer, char[]> map = new HashMap<>();
		map.put(2, new char[] {'a','b','c'});
		map.put(3, new char[] {'d','e','f'});
		assertArrayEquals(new String[] {"ad","ae","af","bd","be","bf", "cd", "ce", "cf"}, 
				getAllPossibleLetters(map, "23"));
	}
	
	@Test
	public void customTest() {
		Map<Integer, char[]> map = new HashMap<>();
		map.put(2, new char[] {'q','r'});
		map.put(3, new char[] {'s','t','v'});
		map.put(5, new char[] {'p','a'});
		assertArrayEquals(new String[] {"aqs","aqt","aqv","ars","art","arv","pqs","pqt","pqv","prs","prt","prv"}, 
				getAllPossibleLetters(map, "523"));
	}
}
