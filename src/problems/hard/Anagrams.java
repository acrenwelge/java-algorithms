package problems.hard;

import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * Given a word W and a string S, find all starting indices in S which are anagrams of W.

For example, given that W is "ab", and S is "abxaba", return 0, 3, and 4.
 * @author Andrew
 *
 */
public class Anagrams {
	private static final Logger log = LogManager.getLogger(Anagrams.class);
	
	public static int[] firstAttempt(String word, String searchMe) {
		List<Integer> indexes = new ArrayList<>();
		List<Integer> tmp = new ArrayList<>();
		String newWord = word;
		boolean buildingAnagram = false;
		for (int j = 0; j < searchMe.length(); j++) {
			char consider = searchMe.charAt(j);
			int wdIdx = newWord.indexOf(consider);
			log.debug(String.format("looking at %s in word %s",consider,searchMe));
			if (wdIdx == -1) {
				log.debug(String.format("character %s doesn't exist in %s",consider,newWord));
				buildingAnagram = false;
			} else {
				if (!buildingAnagram) {
					log.debug("adding index " + j);
					tmp.add(j);
				}
				buildingAnagram = true;
				log.debug(String.format("character %s exists, adding it to list",consider));
				log.debug("new word: " + newWord);
				String first = newWord.substring(0, wdIdx);
				String second = newWord.substring(wdIdx+1, newWord.length());
				log.debug("Joining " +first+" and "+second);
				newWord = String.join(first, second);
				if (newWord.isEmpty()) {
					indexes.addAll(tmp);
					tmp.clear();
					newWord = word;
					buildingAnagram = false;
				}
				log.debug("new word: " + newWord);
				
			}
		}
		int[] idxs = new int[indexes.size()];
		for (int i=0; i < indexes.size(); i++) {
			idxs[i] = indexes.get(i);
		}
		for (int i : idxs) {System.out.println(i);}
		return idxs;
	}

	/* This method takes the approach of generating all anagrams of the word and then
	 * searching the string for indexes that start a substring that match an anagram
	 */
	public static int[] findAnagrams(String word, String searchMe) {
		List<Integer> idxs = new ArrayList<>();
		int wordLen = word.length();
		Set<String> anagrams = generateAnagrams(word);
		// search the strings for substrings that match an anagram
		for (int i = 0; i + wordLen <= searchMe.length(); i++) {
			String sub = searchMe.substring(i, i + wordLen);
			if (anagrams.contains(sub)) {
				idxs.add(i);
			}
		}
		int[] result = new int[idxs.size()];
		for (int i=0; i < idxs.size(); i++) {
			result[i] = idxs.get(i);
		}
		return result;
	}
	
	private static Set<String> generateAnagrams(String str) {
		Set<String> set = new HashSet<>();
		set.add(str);
		anagramHelper(set,str,0,str.length()-1);
		return set;
    }
	
	private static void anagramHelper(Set<String> all, String str, int start, int end) {
		if (start == end)
            all.add(str);
        else {
            for (int i = start; i <= end; i++) { 
                str = swap(str, start, i); 
                anagramHelper(all, str, start + 1, end); 
                str = swap(str, start, i); 
            }
        }
	}
	
    private static String swap(String a, int i, int j) {
        char temp;
        char[] charArray = a.toCharArray(); 
        temp = charArray[i];
        charArray[i] = charArray[j]; 
        charArray[j] = temp;
        return String.valueOf(charArray); 
    }
	
	@Test
	public void defaultTest() {
		assertArrayEquals(new int[] {0,3,4}, findAnagrams("ab","abxaba"));
	}
	
	@Test
	public void customTest() {
		assertArrayEquals(new int[] {4,6,16,17}, findAnagrams("and","darndnandersonaaadna"));
	}
}
