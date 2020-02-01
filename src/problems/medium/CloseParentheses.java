package problems.medium;

import static org.junit.Assert.assertEquals;

import java.util.ArrayDeque;
import java.util.Deque;

import org.junit.Test;

/**
 * Given a string of parentheses, write a function 
 * to compute the minimum number of parentheses to be removed 
 * to make the string valid (i.e. each open parenthesis is eventually closed).

For example, given the string "()())()", you should return 1. 
Given the string ")(", you should return 2, since we must remove all of them.
 * @author Andrew
 *
 */
public class CloseParentheses {
	
	public static int removeParentheses(String s) {
		int unmatched = 0;
		Deque<Character> stack = new ArrayDeque<>();
		for (int i = 0; i < s.length(); i++) {
			char paren = s.charAt(i);
			if (paren != '(' && paren != ')') {
				throw new IllegalArgumentException("string must be parenetheses");
			}
			if (stack.isEmpty() && paren == ')') {
				unmatched++;
			} else if (paren == '(') {
				stack.push(paren);
			} else if (!stack.isEmpty() && paren == ')') {
				stack.pop();
			}
		}
		return unmatched + stack.size();
	}
	
	@Test
	public void defaultTest() {
		assertEquals(1,removeParentheses("()())()"));
		assertEquals(2,removeParentheses(")("));
	}
	
	@Test
	public void customTest() {
		assertEquals(1,removeParentheses("((())()"));
		assertEquals(2,removeParentheses(")()(()))"));
		assertEquals(0,removeParentheses("(()(()))"));
	}
}
