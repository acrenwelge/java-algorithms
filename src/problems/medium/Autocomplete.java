package problems.medium;

import static org.junit.Assert.assertArrayEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

/**
Implement an autocomplete system. That is, given a query string s and a set of all possible query strings, 
return all strings in the set that have s as a prefix.

For example, given the query string de and the set of strings [dog, deer, deal], return [deer, deal].
Hint: Try preprocessing the dictionary into a more efficient data structure to speed up queries.
*/
public class Autocomplete {

  public String[] autocomplete(String query, Set<String> possibilities) {
    Set<String> solutionSet = new HashSet<>();
    for (String s : possibilities) {
      if (s.startsWith(query)) solutionSet.add(s);
    }
    System.out.println(solutionSet);
    return solutionSet.toArray(new String[solutionSet.size()]);
  }

  @Test
  public void defaultTest() {
    String query = "";
    Set<String> set = new HashSet<>();
    set.add("");
    set.add("");
    set.add("");
    assertArrayEquals(new String[] {"",""}, autocomplete(query, set));
  }

}
