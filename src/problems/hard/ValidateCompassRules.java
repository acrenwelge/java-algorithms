package problems.hard;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * This problem was asked by Uber.

A rule looks like this:

A NE B

This means this means point A is located northeast of point B.

A SW C

means that point A is southwest of C.

Given a list of rules, check if the sum of the rules validate. For example:

A N B
B NE C
C N A
does not validate, since A cannot be both north and south of C.

A NW B
A N B
is considered valid.
 * @author Andrew
 *
 */
public class ValidateCompassRules {
	
	public static boolean rulesAreValid(List<String> rules) {
		return false;
	}
	
	@Test
	public void defaultTest() {
		List<String> list = Arrays.asList("A N B", "B NE C"," C N A");
		assertFalse(rulesAreValid(list));
		list = Arrays.asList("A NW B","A N B");
		assertTrue(rulesAreValid(list));
	}
}
