package problems.medium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * Given a matrix of 1s and 0s, return the number of "islands" in the matrix. 
 * A 1 represents land and 0 represents water, so an island is a group of 1s 
 * that are neighboring whose perimeter is surrounded by water.

For example, this matrix has 4 islands.

1 0 0 0 0
0 0 1 1 0
0 1 1 0 0
0 0 0 0 0
1 1 0 0 1
1 1 0 0 1

 * @author Andrew
 */
public class Islands {
	public static final Logger log = LogManager.getLogger(Islands.class);
	
	public static class Coord {
		int row;
		int col;
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (!(obj instanceof Coord))
				return false;
			Coord other = (Coord) obj;
			if (col != other.col)
				return false;
			if (row != other.row)
				return false;
			return true;
		}
		
		@Override
		public String toString() {
			return String.format("[%d,%d]", row,col);
		}
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + col;
			result = prime * result + row;
			return result;
		}
		
		public Coord(int r, int c) {
			this.row = r;
			this.col = c;
		}
	}
	
	/*
	 * For this approach we iterate through the matrix looking for islands which we store 
	 * as a set of integer coordinates.
	 * If we encounter a 1, we check if its coordinates have already been found in an island.
	 * If not we create a new island and add it.
	 * Whenever a new island is found, we explore in all directions until the entire island has been found. 
	 */
	public static int determineNumberOfIslands(int[][] matrix) {
		List<Set<Coord>> islands = new ArrayList<>();
		for (int row = 0; row < matrix.length; row++) {
			log.debug(String.format("in row %d", row));
			for (int col = 0; col < matrix[row].length; col++) {
				log.debug(String.format("in col %d", col));
				int val = matrix[row][col];
				if (val == 1) {
					log.debug("it's a 1!");
					if (!alreadyInIsland(islands, new Coord(row, col))) {
						Set<Coord> newIsland = new HashSet<>();
						Coord coord = new Coord(row, col);
						newIsland.add(coord);
						islands.add(newIsland);
						log.debug("created new island");
						exploreNewIsland(matrix, newIsland, coord);
					} // ignore if we already found it
				} else if (val != 0) {
					throw new IllegalArgumentException("matrix must be 1s and 0s");
				}
			}
		}
		return islands.size();
	}
	
	public static boolean alreadyInIsland(List<Set<Coord>> islands, Coord coord) {
		for (Set<Coord> island : islands) {
			if (island.contains(coord)) return true;
		}
		return false;
	}
	
	// since we have already explored every previous island, new discoveries 
	// are guaranteed to be unique (unconnected to other islands) 
	public static void exploreNewIsland(int[][] matrix, Set<Coord> island, Coord coord) {
		log.debug("exploring new island at "+coord.row+","+coord.col);
		log.debug("island contains: "+island);
		// recursively build a list of possible paths and explore them
		Coord[] toExplore = new Coord[] {
				new Coord(coord.row-1,coord.col),
				new Coord(coord.row+1,coord.col),
				new Coord(coord.row,coord.col-1),
				new Coord(coord.row,coord.col+1)};
		for (Coord newCoord : toExplore) {
			log.debug("possibility: "+newCoord.row+","+newCoord.col);
			if (newCoord.row >= 0 && newCoord.row < matrix.length 
					&& newCoord.col >= 0 && newCoord.col < matrix[0].length 
					&& matrix[newCoord.row][newCoord.col] == 1
					&& !island.contains(newCoord)) {
				log.debug("possibility confirmed");
				island.add(newCoord);
				exploreNewIsland(matrix,island,newCoord);
			}
		}
	}
	
	@Test
	public void proofWhyICantUseArrays() {
		Set<Integer[]> set = new HashSet<>();
		set.add(new Integer[] {1,2,3});
		assertFalse(set.contains(new Integer[] {1,2,3}));
	}
	
	@Test
	public void defaultTest() {
		assertEquals(4, determineNumberOfIslands(new int[][] {
			{1, 0, 0, 0, 0},
			{0, 0, 1, 1, 0},
			{0, 1, 1, 0, 0},
			{0, 0, 0, 0, 0},
			{1, 1, 0, 0, 1},
			{1, 1, 0, 0, 1},
		}));
	}
	
	@Test
	public void customTest1() {
		assertEquals(1, determineNumberOfIslands(new int[][] {
			{1, 1, 0, 0, 0},
			{0, 1, 1, 1, 0},
			{0, 1, 1, 0, 0},
			{0, 1, 0, 0, 0},
			{1, 1, 0, 0, 1},
			{1, 1, 1, 1, 1},
		}));
	}
	
	@Test
	public void customTest2() {
		assertEquals(2, determineNumberOfIslands(new int[][] {
			{1, 1, 0, 0, 0},
			{1, 1, 0, 1, 1},
		}));
	}
	
	@Test
	public void customTest3() {
		assertEquals(0, determineNumberOfIslands(new int[][] {
			{0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0},
		}));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void customTest4() {
		assertEquals(0, determineNumberOfIslands(new int[][] {
			{0, 0, 0, 2, 0},
			{0, 5, 0, 0, 0},
			{0, 5, 0, 0, 3},
		}));
	}
}
