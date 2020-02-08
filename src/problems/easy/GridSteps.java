package problems.easy;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * This problem was asked by Google.

You are in an infinite 2D grid where you can move in any of the 8 directions:

 (x,y) to
    (x+1, y),
    (x - 1, y),
    (x, y+1),
    (x, y-1),
    (x-1, y-1),
    (x+1,y+1),
    (x-1,y+1),
    (x+1,y-1)
You are given a sequence of points and the order in which you need to cover the points. 
Give the minimum number of steps in which you can achieve it. You start from the first point.

Example:

Input: [(0, 0), (1, 1), (1, 2)]
Output: 2
It takes 1 step to move from (0, 0) to (1, 1). It takes one more step to move from (1, 1) to (1, 2).
 * @author Andrew
 *
 */
public class GridSteps {
	public static class Coord {
		int x;
		int y;
		public Coord(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static int minSteps(Coord[] sequence) {
		Coord current = sequence[0];
		Coord prev = current;
		int totDist = 0;
		for (int i=1; i < sequence.length; i++) {
			current = sequence[i];
			int dist = Math.max(Math.abs(current.x - prev.x), Math.abs(current.y - prev.y));
			totDist += dist;
			prev = current;
		}
		return totDist;
	}
	
	@Test
	public void defaultTest() {
		assertEquals(2, minSteps(new Coord[] {new Coord(0,0),new Coord(1,1),new Coord(1,2)}));
	}
	
	@Test
	public void customTest() {
		assertEquals(7, minSteps(new Coord[] {new Coord(5,0),new Coord(10,2),new Coord(12,4)}));
	}
}
