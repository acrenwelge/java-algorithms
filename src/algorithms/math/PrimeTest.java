package algorithms.math;

import static org.junit.Assert.*;

import org.junit.Test;

public class PrimeTest {

	@Test
	public void testGetFirstNPrimes() {
		int[] primes = {2, 3, 5, 7, 11, 13};
		int[] primeTest = Primes.getFirstNPrimes(6);
		for (int i : primeTest) {
			System.out.println(i);
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
