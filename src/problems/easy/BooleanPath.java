package problems.easy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;

/**
 * This problem was asked by Google.

You are given an M by N matrix consisting of booleans that represents a board.
Each True boolean represents a wall. Each False boolean represents a tile you can walk on.

Given this matrix, a start coordinate, and an end coordinate, return the minimum number of steps
required to reach the end coordinate from the start. If there is no possible path, then return null.
You can move up, left, down, and right. You cannot move through walls. You cannot wrap around the edges of the board.

For example, given the following board:

[[f, f, f, f],
[t, t, f, t],
[f, f, f, f],
[f, f, f, f]]
and start = (3, 0) (bottom left) and end = (0, 0) (top left), the minimum number of steps
required to reach the end is 7, since we would need to go through (1, 2) because there is
a wall everywhere else on the second row.
 * @author Andrew
 *
 */
@Ignore
public class BooleanPath {
    static final Logger log = LogManager.getLogger(BooleanPath.class);

	class Coord {
		int m;
		int n;
		boolean val = true; // default = true in case we're at the edge

		public Coord(int m, int n, boolean b) {
			this.m = m;
			this.n = n;
			this.val = b;
		}

		@Override
		public boolean equals(Object obj) {
			Coord c = (Coord) obj;
			return m == c.m && n == c.n && val == c.val;
		}

		@Override
		public String toString() {
			return String.format("[%d,%d] - %s", m, n, val);
		}
	}

	/*
	 * Approach:
	 *  - start at the start coordinate
	 *  - look at tile in most direct direction of the end coordinate
	 *    - if on a diagonal, choose at random (or order doesn't matter)
	 *  - if possible, make a step in that direction
	 *    - !!check that we haven't already gone to this coordinate!! (realized later)
	 *  - otherwise, choose a different direction
	 *  - if no directions are possible, go back
	 *    - change current tile to true so that we know that path is not viable
	 *  - if at initial tile and going back, return null
	 *
	 *  NOTE: currently incomplete - this is a naive solution that does not pass test case 2
	 *  problem is that when backtracking, it may stumble upon a coordinate that
	 *  can be reached a shorter way earlier on in the history
	 *  TODO: detect such shorter paths and take it. note that may not work for test case 3 however
	 */
	public Integer findMinSteps(boolean[][] board, int[] startCoord, int[] endCoord) {
		final Coord start = new Coord(startCoord[0], startCoord[1], false);
		final Coord end = new Coord(endCoord[0], endCoord[1], false);

		Coord current = start;
		Deque<Coord> history = new LinkedList<>();
		OUTER: while (!current.equals(end)) {
			printBoard(board, current, end);
			Coord up = null;
			Coord down = null;
			Coord left = null;
			Coord right = null;
			// look at surrounding tiles, checking for edges
			if (current.m - 1 >= 0) {
				up = new Coord(current.m-1, current.n, board[current.m-1][current.n]);
			}
			if (current.m + 1 < board.length) {
				down = new Coord(current.m+1, current.n, board[current.m+1][current.n]);
			}
			if (current.n - 1 >= 0) {
				left = new Coord(current.m, current.n-1, board[current.m][current.n-1]);
			}
			if (current.n + 1 < board[current.m].length) {
				right = new Coord(current.m, current.n+1, board[current.m][current.n+1]);
			}
			List<Coord> order = new ArrayList<>();
			if (up != null) order.add(up);
			if (down != null) order.add(down);
			if (left != null) order.add(left);
			if (right != null) order.add(right);
			Collections.sort(order, (c1, c2) -> {
				double d1 = getDist(c1,end);
				double d2 = getDist(c2,end);
				if (d1 > d2) return 1;
				else return -1;
			});
			for (Coord c : order) {
				if (!c.val && !history.contains(c)) {
					history.push(current);
					current = c;
					continue OUTER;
				}
			}
			// are we at the beginning and going backwards? then we're done (no paths)
			if (current.equals(start)) return null;
			// no directions possible, so go back and set tile to "true"
			board[current.m][current.n] = true;
			current = history.pop();
		}
		return history.size();
	}

	private double getDist(Coord c1, Coord c2) {
		return Math.sqrt(Math.pow(c1.m - c2.m, 2) + Math.pow(c1.n-c2.n, 2)); // distance formula
	}

	private void printBoard(boolean[][] board, Coord current, Coord end) {
		log.debug("==================");
		for (int i=0; i < board.length; i++) {
			StringBuilder line = new StringBuilder();
			for (int j = 0; j < board[i].length; j++) {
				if (current.m == i && current.n == j) line.append("   x   ");
				else if (end.m == i && end.n == j) line.append("  end  ");
				else line.append(String.format("%7s", board[i][j]));
			}
			log.debug(line.toString());
			log.debug(String.format("%n"));
		}
		log.debug("==================");
	}

	public int findMinSteps(boolean[][] board, Coord start, Coord end) {
		boolean[][] visited = new boolean[board.length][board[0].length];
		return 0;
	}
	
	@Test
	public void newTest() {
		boolean[][] testBoard = {
				{false, false, false, false},
				{true,  true,  false, true},
				{false, false, false, false},
				{false, false, false, false}
				};
		assertEquals(7, findMinSteps(testBoard, new Coord(3,0,false), new Coord(0,0,false)));
	}
	
	@Test
	public void defaultTest() {
		boolean[][] testBoard = {
				{false, false, false, false},
				{true,  true,  false, true},
				{false, false, false, false},
				{false, false, false, false}
				};
		int[] start = {3,0};
		int[] end = {0,0};
		assertEquals(new Integer(7), findMinSteps(testBoard, start, end));
	}

	@Test
	public void customTest() {
		boolean[][] testBoard = {
				{false, false, false, false},
				{true,  true,  true, true},
				{false, false, false, false},
				{false, false, false, false}
				};
		int[] start = {3,0};
		int[] end = {0,0};
		assertNull(findMinSteps(testBoard, start, end));
	}

	@Test
	public void customTest2() {
		boolean[][] testBoard = {
				{false, false, false, false},
				{false,  true,  true, true},
				{false, false, false, false},
				{false, false, false, false}
				};
		int[] start = {3,0};
		int[] end = {0,3};
		assertEquals(new Integer(6), findMinSteps(testBoard, start, end));
	}

	@Test
	public void customTest3() {
		boolean[][] testBoard = {
				{false, false, false, false, false, false, false},
				{false,  true,  true, true,  true, true, false},
				{false, true, false,  false, false, true, false},
				{false, true, false,  true,  false, true, false},
				{false, false, false, true,  false, false, false},
				};
		int[] start = {4,0};
		int[] end = {0,4};
		assertEquals(new Integer(10), findMinSteps(testBoard, start, end));
	}
}
