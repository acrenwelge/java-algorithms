package problems.medium;

import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * This problem was asked by Square.

Assume you have access to a function toss_biased() which returns 0 or 1 with a 
probability that's not 50-50 (but also not 0-100 or 100-0). You do not know the bias of the coin.

Write a function to simulate an unbiased coin toss.
 * @author Andrew
 *
 */
public class TossBiased {
	public static final int PRECISION = 1000; // the higher this is, the more accurate the simulated unbiased coin toss 
	public static final int SAMPLE_SIZE = 1000; // similarly, the larger this sample, the more accurate the method becomes (Law of Large Numbers)
	public static final double RANDOM_BIAS = Math.random(); // different random bias every time the class is loaded
	
	// how far (percentage-wise) the method can be outside the 50-50 range and still pass the test
	public static final double TEST_RANGE = 0.15;
	public static final int TEST_SAMPLE_SIZE = 100;
	
	/*
	 * Approach:
	 * - call tossBiased a large number of times
	 * - average the results to approximate the bias
	 * - call tossBiased some number of times again
	 */
	public int tossUnbiased() {
		int[] results = new int[PRECISION];
		float sum = 0;
		for (int i=0; i < PRECISION; i++) {
			results[i] = tossBiased();
			sum += results[i];
		}
		float avg = sum / (float) PRECISION;
		int[] compare = new int[SAMPLE_SIZE];
		float numberOf1s = 0;
		for (int i=0; i < SAMPLE_SIZE; i++) {
			compare[i] = tossBiased();
			if(compare[i] == 1) numberOf1s++;  
		}
		return numberOf1s > avg * SAMPLE_SIZE ? 1 : 0;
	}
	
	private int tossBiased() {
		if (Math.random() < RANDOM_BIAS) return 1; // returns 1 a random % of the time
		else return 0;
	}
	
	@Test
	public void testCoinToss() {
		int numOf1s = 0;
		for (int i=0; i < TEST_SAMPLE_SIZE; i++) {
			int val = tossUnbiased();
			if (val == 1) {
				numOf1s++;
			}
		}
		if (numOf1s < (1 - TEST_RANGE) * TEST_SAMPLE_SIZE/2 
				|| numOf1s > (1 + TEST_RANGE) * TEST_SAMPLE_SIZE/2) {
			fail(String.format("Coin toss not within test sample range of %2.0f%% of 50-50 probability", TEST_RANGE*100));
		}
 	}
}
