package problems.easy;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * Given an even number (greater than 2), return two prime numbers whose sum will be equal to the given number.
 * @author Andrew
 */
public class PrimeSum {
	
	public int[] findPrimes(int even) {
		int[] primes = getPrimesLessThan(even);
		while(true) {
			for (int j=0;j < primes.length; j++) {
				int primeTwo = primes[j];
				for (int i=0;i < primes.length; i++) {
					int primeOne = primes[i];
					if (primeOne + primeTwo == even) {
						return new int[] {primeOne, primeTwo};
					}
				}
			}
		}
	}
	
	private int[] getPrimesLessThan(int number) {
		List<Integer> primes = new ArrayList<>();
		for (int i=2; i < number; i++) {
			if (isPrime(i)) {
				primes.add(i);			
			}
		}
		Integer[] tmp = primes.toArray(new Integer[0]);
		int[] arr =  new int[tmp.length];
		for (int i = 0; i < tmp.length; i++) {
			arr[i] = tmp[i];
		}
		return arr;
	}
	
	private boolean isPrime(int number) {
		for (int i=2; i < number; i++) {
			if (number % i == 0) return false;
		}
		return true;
	}
	
	@Test
	public void defaultTest() {
		int[] primes = findPrimes(4);
		Assert.assertEquals(4, primes[0] + primes[1]);
	}
	
	@Test
	public void secondTest() {
		int[] primes = findPrimes(100);
		Assert.assertEquals(100, primes[0] + primes[1]);
	}
	
	@Test
	public void testIsPrime() {
		int prime = 5;
		int nonprime = 4;
		Assert.assertTrue(isPrime(prime));
		Assert.assertFalse(isPrime(nonprime));
	}

}
