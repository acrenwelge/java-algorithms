package problems.medium;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * This problem was asked by Facebook.

You are given an array of non-negative integers that represents a two-dimensional elevation map 
where each element is unit-width wall and the integer is the height. Suppose it will rain and 
all spots between two walls get filled up.

Compute how many units of water remain trapped on the map in O(N) time and O(1) space.

For example, given the input [2, 1, 2], we can hold 1 unit of water in the middle.

Given the input [3, 0, 1, 3, 0, 5], we can hold 3 units in the first index, 2 in the second, 
and 3 in the fourth index (we cannot hold 5 since it would run off to the left), so we can trap 8 units of water.
 * @author Andrew
 *
 */
public class TrapWater {
	
	/*
	 * Approach 1:
	 *  - find the max 2 wall heights
	 *  - if these 2 are the same then compute the water stored between them
	 *  - if they are different heights...
	 *    - find any other walls of the same height as second tallest
	 *    - use the second tallest height to compute water between walls
	 *  this doesn't work.. because you could have [2,0,2,3,0,3] and it would only look at the second "well"
	 *  
	 *  Approach 2:
	 *   - keep track of rolling max height
	 *   - look at 3 walls at a time (<3 walls means you can't hold any water)
	 *   - if the two outer walls are both higher than the inner (e.g. [3,1,3]), you can hold at least as much as (2nd highest - inner) water
	 *   - if not, check whether ascending (e.g. [1,2,3]) or descending (e.g. [3,2,1])
	 *     - ascending: check max against each of the 3 walls
	 *       - if max < wall[0]...
	 *     - descending: keep going until...
	 *     
	 *   Approach 3:
	 *   - start from beginning of walls
	 *   - as long as walls increase (or stay same) we can't store any water
	 *   - once the walls start to descend, store the local maximum (also store the following elements)
	 *     - when walls ascend again, add the water and "fill in the gap"
	 *       - if next wall is lower than local maximum, compute gap betw each wall since the max and the next wall
	 *         - continue
	 *       - if next wall is taller than local maximum, compute gap betw local max and each wall since
	 *         - reset local max to be the next wall
	 *   - continue until all walls are done
	 */
	public int getUnitsHeld(int[] walls) {
		if (walls.length < 3) return 0; // quick sanity check
		int totWater = 0;
		int localMax = walls[0];
		int prevWall = walls[0];
		int currWall = walls[0];
		List<Integer> tmpStorage = new ArrayList<>();
		boolean pastFirstMax = false;
		for (int i=1; i < walls.length; i++) {
			prevWall = currWall;
			currWall = walls[i];
			if (!pastFirstMax && currWall >= prevWall) { // still ascending
				continue;
			} else if (!pastFirstMax) { // first descent
				pastFirstMax = true;
				localMax = prevWall;
				tmpStorage.add(currWall);
			} else if (currWall <= prevWall) { // still descending
				tmpStorage.add(currWall);
			} else { // fill in the gap!
				if (currWall < localMax) {
					for (int j : tmpStorage) {
						if (j < currWall) {
							totWater += currWall - j;
						}
					}
					final int finalWall = currWall; // variable for lambda must be final
					// water has been counted so fill in the wall to prevent double counting
					tmpStorage.replaceAll(num -> num < finalWall ? finalWall : num);
					tmpStorage.add(currWall);
				} else {
					for (int j : tmpStorage) {
						totWater += localMax - j;
					}
					localMax = currWall;
					tmpStorage.clear();
				}
			}
		}
		return totWater;
	}
	
	public int getUnitsHeld1(int[] walls) {
		int max = walls[0];
		int sec_max = max;
		for (int i=0; i<walls.length; i++) {
			if (walls[i] >= max) {
				int tmp = max;
				max = walls[i];
				sec_max = tmp;
			}
		}
		return sec_max;
	}
	
	@Test
	public void defaultTest1() {
		int[] walls = {2, 1, 2};
		assertEquals(1, getUnitsHeld(walls));
	}
	
	@Test
	public void defaultTest2() {
		int[] walls = {3,0,1,3,0,5};
		assertEquals(8, getUnitsHeld(walls));
	}
	
	@Test
	public void customTest1() {
		int[] walls = {1,2,3,4,3,2,1,2,0};
		assertEquals(1, getUnitsHeld(walls));
	}
	
	@Test
	public void customTest2() {
		int[] walls = {5,4,3,2,3,4,5,1,0};
		assertEquals(9, getUnitsHeld(walls));
	}
	
	@Test
	public void customTest3() {
		int[] walls = {3,0,3,5,0,5};
		assertEquals(8, getUnitsHeld(walls));
	}
	
	@Test
	public void customTest4() {
		int[] walls = {5,4,3,2,1,2,3,2,1,5};
		assertEquals(22, getUnitsHeld(walls));
	}
}
