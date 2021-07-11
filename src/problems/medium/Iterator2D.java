package problems.medium;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Implement a 2D iterator class. It will be initialized with an array of arrays, and should implement the following methods:

next(): returns the next element in the array of arrays. If there are no more elements, raise an exception.
has_next(): returns whether or not the iterator still has elements left.
For example, given the input [[1, 2], [3], [], [4, 5, 6]], calling next() repeatedly should output 1, 2, 3, 4, 5, 6.

Do not use flatten or otherwise clone the arrays. Some of the arrays can be empty.
*/
public class Iterator2D {
  static final Logger log = LogManager.getRootLogger();

  private int[][] arr;
  private int arrCount;
  private int idxCount = -1;

  public Iterator2D() { }

  public void init(int[][] arr) {
    this.arr = arr;
  }

  public int next() {
    if (!hasNext()) {
      throw new RuntimeException("Out of elements");
    } else {
      if (idxCount + 1 > arr[arrCount].length-1) {
        idxCount = 0;
        arrCount++;
        while (arr[arrCount].length == 0) {
          arrCount++;
        }
        return arr[arrCount][0];
      } else {
        return arr[arrCount][++idxCount];
      }
    }
  }

  public boolean hasNext() {
    if (idxCount + 1 > arr[arrCount].length - 1) {
      if (arrCount + 1 > arr.length - 1) {
        return false;
      } else {
        return true;
      }
    } else {
      return true;
    }
  }

  @Test
  public void defaultTest() {
    Iterator2D it = new Iterator2D();
    it.init(new int[][]{{1,2},{3},{},{4,5,6}});
    assertTrue(it.hasNext());
    assertEquals(1,it.next());
    assertEquals(2,it.next());
    assertEquals(3,it.next());
    assertEquals(4,it.next());
    assertEquals(5,it.next());
    assertEquals(6,it.next());
    assertFalse(it.hasNext());
  }

}
