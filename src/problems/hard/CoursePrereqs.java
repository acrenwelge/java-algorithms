package problems.hard;

import static org.junit.Assert.assertArrayEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * We're given a hashmap associating each courseId key with a 
 * list of courseIds values, which represents that the prerequisites 
 * of courseId are courseIds. Return a sorted ordering of courses 
 * such that we can finish all courses.

Return null if there is no such ordering.

For example, given {'CSC300': ['CSC100', 'CSC200'], 
'CSC200': ['CSC100'], 'CSC100': []}, should return ['CSC100', 'CSC200', 'CSCS300'].
 * @author Andrew
 */
public class CoursePrereqs {
	
	public static String[] orderAllPrereqs(Map<String,String[]> courseMappings) {
		
		return null;
	}
	
	@Test
	public void defaultTest() {
		Map<String,String[]> courses = new HashMap<>();
		courses.put("CSC300", new String[] {"CSC100","CSC200"});
		courses.put("CSC200", new String[] {"CSC200"});
		courses.put("CSC100", new String[] {});
		assertArrayEquals(new String[] {"CSC100","CSC200","CSC300"}, orderAllPrereqs(courses));
	}
}
