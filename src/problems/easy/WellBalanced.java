package problems.easy;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

import org.junit.Test;

/**
 * This problem was asked by Facebook.

Given a string of round, curly, and square open and closing brackets, return whether the brackets are balanced (well-formed).

For example, given the string "([])[]({})", you should return true.

Given the string "([)]" or "((()", you should return false.
 * @author Andrew
 *
 */
public class WellBalanced {
	public static final char OPEN_ROUND   = '(';
	public static final char CLOSED_ROUND = ')';
	public static final char OPEN_CURLY   = '{';
	public static final char CLOSED_CURLY = '}';
	public static final char OPEN_SQUARE  = '[';
	public static final char CLOSED_SQUARE= ']';
	
	public boolean wellFormed(String brackets) {
		Deque<Character> stack = new ArrayDeque<>();
		Set<Character> validChars = new HashSet<>();
		validChars.add(OPEN_ROUND);
		validChars.add(OPEN_CURLY);
		validChars.add(OPEN_SQUARE);
		validChars.add(CLOSED_ROUND);
		validChars.add(CLOSED_CURLY);
		validChars.add(CLOSED_SQUARE);
		char[] arr = brackets.toCharArray();
		try {
			for (char c : arr) {
				if (c == CLOSED_ROUND) {
					char prev = stack.pop();
					if (prev == OPEN_ROUND) continue;
					else return false;
				} else if (c == CLOSED_CURLY) {
					char prev = stack.pop();
					if (prev == OPEN_CURLY) continue;
					else return false;
				} else if (c == CLOSED_SQUARE) {
					char prev = stack.pop();
					if (prev == OPEN_SQUARE) continue;
					else return false;
				}
				else if (validChars.contains(c)) {
					stack.push(c);
				}
			}
		} catch (NoSuchElementException e) { // stack is empty when it shouldn't be
			return false;
		}
		return stack.size() == 0;
	}
	
	@Test
	public void defaultTests() {
		String bracks1 = "([])[]({})";
		String bracks2 = "([)]";
		String bracks3 = "((()";
		assertTrue(wellFormed(bracks1));
		assertFalse(wellFormed(bracks2));
		assertFalse(wellFormed(bracks3));
	}
	
	@Test
	public void customTests() {
		String bracks1 = "((())";
		String bracks2 = "[](){}";
		String bracks3 = "}}(())";
		assertFalse(wellFormed(bracks1));
		assertTrue(wellFormed(bracks2));
		assertFalse(wellFormed(bracks3));
	}
	
	@Test
	public void withSpaces() {
		String bracks1 = "[({ })]";
		String bracks2 = "[] () {}";
		String bracks3 = "}} (())";
		assertTrue(wellFormed(bracks1));
		assertTrue(wellFormed(bracks2));
		assertFalse(wellFormed(bracks3));
	}
}
