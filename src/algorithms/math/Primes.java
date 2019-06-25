package algorithms.math;

public class Primes {
	
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
	
	public static int randomPrime() {
		return 0;
	}

}
