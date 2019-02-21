package algorithms.search;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Find {

	public static void main(String[] args) {
		int[] smlg = findSmallestAndLargest(new int[] {3, 10, 7, 30, 100, -6, 105, 17});
		System.out.println("Small: " +smlg[0]+ ", Large: " + smlg[1]);
		System.out.println("=======");
		System.out.println(findMissing(new int[]{1, 5, 3, 2, 6, 7, 9, 8}));
		System.out.println("=======");
		int[][] pairs = findPairs(new int[] {20, 10, 15, 30, 8, 5, -3, 33}, 35);
		for (int[] pair : pairs) {
			System.out.println("Pair: " + pair[0] + ", " + pair[1]);
		}
		System.out.println("=======");
		int[] solution = findPairs2(new int[] {1, 3, 7, 1}, new int[] {6, 5, 18, 4}, 24);
		System.out.println("Pairs2: "+solution[0] + ", " + solution[1]);
	}
	
	/**
	 * Finds the missing number in an array of contiguous integers. The array parameter is
	 * guaranteed to contain - when sorted - a sequential order of integers, with a single value missing
	 * @param arr - the array of integers, guaranteed to be at least of length 2
	 * @return the missing integer
	 */
	public static int findMissing(int[] arr) {
		// Approach: sort the array, then loop through until the missing integer is found
		if (arr.length < 2) throw new IllegalArgumentException("Array param must be of size >=2");
		Arrays.sort(arr);
		int a = arr[0];
		for (int i=1; i<arr.length; i++) {
			int b = arr[i];
			if (b != a + 1) {
				return a + 1;
			}
		}
		return 0;
	}
	
	/**
	 * Finds any and all duplicate values in an array of integers. The array parameter is <strong>not</strong>
	 * guaranteed to contain duplicates.
	 * @param arr - the array of integers
	 * @return an array containing the duplicate(s). If there are no duplicates, the array should be empty
	 */
	public static int[] findDuplicates(int[] arr) {
		return new int[] {};
	}
	
	/**
	 * Takes an array of integers and returns the smallest/largest values 
	 * @param arr
	 * @return an array containing two elements: the smallest int, and the largest int
	 */
	public static int[] findSmallestAndLargest(int[] arr) {
		if (arr.length == 0) return new int[] {}; // handle edge case of 0 length array
		int smallest = arr[0]; // init smallest/largest to first element of the array
		int largest = arr[0];
		for (int i : arr) {
			if (i > largest) largest = i;
			if (i < smallest) smallest = i;
		}
		return new int[] {smallest, largest};
	}
	
	/**
	 * Finds the <tt>nth</tt> largest element in an array of integers
	 * @param arr
	 * @return the <tt>nth</tt> largest element
	 */
	public static int findNthLargest(int[] arr, int n) {
		/* One approach would be to sort the array, and then retrieve the nth index - complexity: O(n*log(n)) for QuickSort
		 * The alternative is QuickSelect, which will be O(n) on average, but O(n^2) in the worst case
		 */
		
		return 0;
	}
	
	/**
	 * Finds all unique pairs of an integer array whose sum is equal to the second parameter
	 * @param arr - guaranteed to be of size >= 2
	 * @param sum
	 * @return a 2D array of integer pairs (inner array size is 2), the order of which does not matter
	 */
	public static int[][] findPairs(int[] arr, int sum) {
		int[][] pairs = new int[arr.length][2]; // painfully avoiding using Collections here, a Set would be easier..
		int numPairs = 0;
		// Approach: loop through each element in the array and compare with every other element
		for (int i = 0; i < arr.length; i++) {
			int x = arr[i];
			for (int j = 0; j < arr.length; j++) {
				if (i == j) continue; // avoid comparing the element with itself
				int y = arr[j];
				if (x + y == sum) {
					boolean add = true;
					for (int[] pair : pairs) {
						if (pair[0] == x || pair[1] == x) {add = false; break;} // prevent duplicates 
					}
					if (add) { // add the pair to the array, resizing as needed
						numPairs++;
						pairs = Arrays.copyOf(pairs, numPairs);
						pairs[numPairs-1] = new int[] {x, y};
					}
				}
			}
		}
		return pairs;
	}
	
	/**
	 * From two array parameters, finds a pair of integers - one from each array - whose 
	 * sum is <strong>closest</strong> to the sum passed in as the third parameter
	 * @param arr1
	 * @param arr2
	 * @param sum
	 * @return an array containing the pair - first position will be the int from arr1, second position the int from arr2
	 */
	public static int[] findPairs2(int[] arr1, int[] arr2, int sum) {
		/* Approach: put the first array in a set, then loop through every element in the second array.
		 * For each int, compute the match that would need to exist to make the sum. If it exists in the set, return the match.
		 * Otherwise, continue. If no matches exist for the exact sum, continue checking for matches for sum + 1 and sum - 1 
		 * until a close match is found
		 */
		Set<Integer> s1 = new HashSet<>();// cheating (a little) by using a Set
		for (int i : arr1) s1.add(i);
		int offset = 0;
		while(true) {
			for (int num : arr2) {
				int match1 = sum - num + offset; // for offset = 0, match1==match2
				int match2 = sum - num - offset;
				if (s1.contains(match1) || s1.contains(match2)) {
					return new int[] {match1, num};
				}
			}
			offset++; // increase the distance from the correct sum each time
		}
	}
	
	/**
	 * Same problems as findPairs2, but this implementation is optimized. Imagine a 2D array
	 * consisting of arr1 on the x-axis and arr2 on the y-axis, each sorted ascending. Search is 
	 * conducted from the upper right corner of the matrix, flowing down and to the left until the best 
	 * pair is found. Each pair searched is stored, along with the absolute value of the difference from 
	 * the target sum.
	 * 
	 *   | x | x | x | x | x | x |
	 * y |   |   |   |   |   | 1 | if 1 is less than the target sum, we search down
	 * y |   |   |   |   | 3 | 2 | if 2 is greater than the target sum, we search left
	 * y |   |   | 6 | 5 | 4 |   | continue until 6, which is less than the sum
	 * y |   |   | 7 |   |   |   | continue until the optimum is found, e.g. 7
	 * y |   |   |   |   |   |   |
	 */
	public static int[] findPairs2Optimized(int[] arr1, int[] arr2, int sum) {
		return new int[] {};
	}

}
