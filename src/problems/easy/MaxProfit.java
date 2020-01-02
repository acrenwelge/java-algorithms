package problems.easy;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * Given a array of numbers representing the stock prices of a company in chronological order, 
 * write a function that calculates the maximum profit you could have made from buying and selling 
 * that stock once. You must buy before you can sell it.

For example, given [9, 11, 8, 5, 7, 10], you should return 5, since you could 
buy the stock at 5 dollars and sell it at 10 dollars.
 * @author Andrew
 *
 */
public class MaxProfit {
	
	static final Logger log = LogManager.getRootLogger();
	
	/*
	 * Approach:
	 * - each time we reach local minima, store the value
	 * - once we reach local maxima, calculate and store the difference
	 * - return the max of all these differences
	 */
	public int calcMaxProfit(int[] prices) {
		if (prices.length == 0) return 0;
		int locMin = prices[0];
		List<Integer> profits = new ArrayList<>();
		boolean bull = true;
		for (int i=0; i<prices.length-1; i++) {
			if (bull && prices[i] > prices[i+1]) {
				bull = false;
				int profit = prices[i] - locMin;
				log.info("Adding profit to list: " + profit);
				profits.add(profit);
			} else if (i+1==prices.length-1) {
				profits.add(prices[i+1] - locMin);
			} else if (!bull && prices[i] < prices[i+1]) {
				bull = true;
				locMin = prices[i];
			}
		}
		Collections.sort(profits);
		log.info(profits);
		if (profits.isEmpty()) {
			return 0;
		} else {
			return profits.get(profits.size()-1);
		}
	}
	
	@Test
	public void defaultTest() {
		int[] prices = {9, 11, 8, 5, 7, 10};
		assertEquals(5,calcMaxProfit(prices));
	}

}
