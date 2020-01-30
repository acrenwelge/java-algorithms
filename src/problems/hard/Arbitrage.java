package problems.hard;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * This problem was asked by Jane Street.

Suppose you are given a table of currency exchange rates, represented as a 2D array. 
Determine whether there is a possible arbitrage: that is, whether there is some 
sequence of trades you can make, starting with some amount A of any currency, so that 
you can end up with some amount greater than A of that currency.

There are no transaction costs and you can trade fractional quantities.
 * @author Andrew
 *
 */
public class Arbitrage {
	
	private static boolean determineArbitrage(double[][] exchangeRates) {
		int size = exchangeRates.length;
		for (int i=0; i < size; i++) {
			for (int j=0; j < size; j++) {
				if (exchangeRates[i][j] != exchangeRates[j][i]) return true;
			}
		}
		return false;
	}
	
	@Test
	public void checkNotSymmetric() {
		/*      USD  |  CAN  
		 * USD   1   |  1.2 
		 * CAN  0.9  |   1  
		 * 
		 * If the table is not symmetric then arbitrage is possible
		 */
		double[][] table = new double[][] {
			{1,1.2},
			{0.9,1}
		};
		assertTrue(determineArbitrage(table));
	}
	
	@Test
	public void checkIsSymmetric() {
		/*      USD  |  CAN  
		 * USD   1   |  1.2 
		 * CAN  1.2  |   1  
		 * 
		 * If the table is not symmetric then arbitrage is possible
		 */
		double[][] table = new double[][] {
			{1,1.2},
			{1.2,1}
		};
		assertFalse(determineArbitrage(table));
	}
}
