package problems.easy;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * This problem was asked by Two Sigma.

Using a function rand5() that returns an integer from 1 to 5 (inclusive) with uniform probability, 
implement a function rand7() that returns an integer from 1 to 7 (inclusive).
 * @author Andrew
 */
public class Rand7 {
	static final Logger log = LogManager.getLogger(Rand7.class);
	
	static final int SIMULATION_SIZE=10000;
	static final double ACCEPT_RANGE=0.15;
	
	static final Random r = new Random(); // reuse the Random (not doing so is code smell)
	
	public static int rand5() {
		return r.nextInt(6-1) + 1;
	}

	/*
	 * First (wrong) attempt
	 */
	public static int badRand7() {
		return Math.round(((float) (rand5() * 7)) / (float) 5);
	}
	
	/*
	 * This solution taken from:
	 * https://stackoverflow.com/questions/137783/expand-a-random-range-from-1-5-to-1-7
	 */
	public static int rand7() {
		int[][] arr = {
				{1,2,3,4,5},
				{6,7,1,2,3},
				{4,5,6,7,1},
				{2,3,4,5,6},
				{7,0,0,0,0}
		};
		int chosen = 0;
		while (chosen == 0) {
			int i = rand5();
			int j = rand5();
			chosen = arr[i-1][j-1];
		}
		return chosen;
	}
	
	@Test
	public void test5() {
		int j = SIMULATION_SIZE;
		List<Integer> nums = new ArrayList<>(j);
		while (j-- > 0) {
			nums.add(rand5());
		}
		Map<Integer,Long> counts = nums.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
		log.debug(counts);
		if (counts.size() != 5) fail("Not generating all 5 integers");
		for(Map.Entry<Integer, Long> entry : counts.entrySet()) {
			if (entry.getKey() < 1 || entry.getKey() > 5) {
				fail(String.format("random number %d outside specified range 1-5", entry.getKey()));
			}
			if (entry.getValue() < (1-ACCEPT_RANGE)*SIMULATION_SIZE/5 || 
				entry.getValue() > (1+ACCEPT_RANGE)*SIMULATION_SIZE/5) {
				fail(String.format("count of random numbers failed to be within %2.0f%% of statistical likelihood",
						ACCEPT_RANGE*100));
			}
		}
	}
	
	@Test
	public void test7() {
		int j = SIMULATION_SIZE;
		List<Integer> nums = new ArrayList<>(j);
		while (j-- > 0) {
			nums.add(rand7());
		}
		Map<Integer,Long> counts = nums.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
		log.debug(counts);
		if (counts.size() != 7) fail("Not generating all 7 integers");
		for(Map.Entry<Integer, Long> entry : counts.entrySet()) {
			if (entry.getKey() < 1 || entry.getKey() > 7) {
				fail(String.format("random number %d outside specified range 1-7", entry.getKey()));
			}
			if (entry.getValue() < (1-ACCEPT_RANGE)*SIMULATION_SIZE/7 || 
				entry.getValue() > (1+ACCEPT_RANGE)*SIMULATION_SIZE/7) {
				fail(String.format("count of random numbers failed to be within %2.0f%% of statistical likelihood",
						ACCEPT_RANGE*100));
			}
		}
	}
}
