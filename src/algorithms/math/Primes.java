package algorithms.math;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class Primes {
        static final Logger log = LogManager.getRootLogger();

	public static int[] getFirstNPrimes(int n) {
		int test = 2;
		int count = 0;
		int[] arr = new int[n];
		while(true) {
			boolean isPrime = isPrime(test);
			if (isPrime) {
				arr[count] = test;
				count++;
			}
			test++;
			if (count >= n) return arr;
		}
	}

	public static int getNthPrime(int n) {
		int[] arr = getFirstNPrimes(n);
		return arr[arr.length - 1];
	}

	public static boolean isPrime(int n) {
		for (int i = 2; i < n; i++) {
			if (n % i == 0) return false;
		}
		return true;
	}

	public static int randomPrime(int n) {
		int[] allPrimes = getFirstNPrimes(n);
		int idx = (int) (Math.random() * allPrimes.length);
		return allPrimes[idx];
	}

	@Test
	public void testGetFirstNPrimes() {
		int[] primes = {2, 3, 5, 7, 11, 13};
		int[] primeTest = Primes.getFirstNPrimes(6);
		for (int i : primeTest) {
			log.debug(i);
		}
		assertArrayEquals(primes, primeTest);
	}

	@Test
	public void getNthPrime() {
		int n = 4;
		int prime = 7;
		assertEquals(prime, Primes.getNthPrime(n));
		n = 100;
		prime = 541;
		assertEquals(prime, Primes.getNthPrime(n));
	}

	@Test
	public void isPrime() {
		assertTrue(Primes.isPrime(2));
		assertTrue(Primes.isPrime(3));
		assertFalse(Primes.isPrime(4));
		assertFalse(Primes.isPrime(20));
	}

}
