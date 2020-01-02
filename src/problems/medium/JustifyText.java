package problems.medium;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * This problem was asked by Palantir.

Write an algorithm to justify text. Given a sequence of words and an integer line length k,
return a list of strings which represents each line, fully justified.

More specifically, you should have as many words as possible in each line.
There should be at least one space between each word. Pad extra spaces when necessary
so that each line has exactly length k. Spaces should be distributed as equally as possible,
with the extra spaces, if any, distributed starting from the left.

If you can only fit one word on a line, then you should pad the right-hand side with spaces.

Each word is guaranteed not to be longer than k.

For example, given the list of words ["the", "quick", "brown", "fox", "jumps", "over", "the", "lazy", "dog"]
and k = 16, you should return the following:

["the  quick brown", # 1 extra space on the left
"fox  jumps  over", # 2 extra spaces distributed evenly
"the   lazy   dog"] # 4 extra spaces distributed evenly
 * @author Andrew
 *
 */
public class JustifyText {
        static final Logger log = LogManager.getRootLogger();

	static final String SPACE = " ";

	/*
	 * Approach:
	 *  - join words until line limit is reached
	 *  - add single extra space after each word until full length reached
	 */
	public static List<String> justifyText(String[] words, int lineLength) {
		List<String> source = Arrays.asList(words);
		List<String> lines = new ArrayList<>();
		StringBuilder line = new StringBuilder();
		for (int i = 0; i < source.size(); i++) {
			String word = source.get(i);
			if (line.length() + word.length() + 1 <= lineLength) {
				if (line.length() == 0) { // first word in line - no space before
					line.append(word);
				} else {
					line.append(' ' + word);
				}
				if (i + 1 == source.size()) {
					lines.add(padLine(line, lineLength));
				}
			} else { // cannot fit more words on this line
				// pad the line
				String str = padLine(line, lineLength);
				// line is now justified - add it to the list
				lines.add(str);
				// use the current word on new line
				i--;
				line = new StringBuilder();
			}
		}
		return lines;
	}

	private static String padLine(StringBuilder line, int lineLength) {
		int idx = 0;
		int iter = 1;
		while(line.length() < lineLength) {
			idx = line.indexOf(SPACE, idx);
			if (idx == -1) {
				idx = 0;
				iter++;
				continue;
			}
			line.insert(idx, SPACE);
			idx += iter+1;
		}
		log.debug(line);
		return line.toString();
	}

	@Test
	public void defaultTest() {
		List<String> answer = new ArrayList<>();
		answer.add("the  quick brown");
		answer.add("fox  jumps  over");
		answer.add("the   lazy   dog");
		assertEquals(answer, justifyText(new String[]
				{"the", "quick", "brown", "fox", "jumps", "over", "the", "lazy", "dog"},
				16));
	}

	@Test
	public void customTest() {
		List<String> answer = new ArrayList<>();
		answer.add("A long time ago");
		answer.add("in a galaxy far");
		answer.add("far        away");
		assertEquals(answer, justifyText(new String[]
				{"A", "long", "time", "ago", "in", "a", "galaxy", "far", "far", "away"},
				15));
	}
}
