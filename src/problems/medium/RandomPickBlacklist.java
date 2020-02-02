package problems.medium;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Given an integer n and a list of integers l, write a function that randomly
 * generates a number from 0 to n-1 that isn't in l (uniform).
 * 
 * @author Andrew
 *
 */
public class RandomPickBlacklist {

	public static int pick(int N, int[] blacklist) {
		Set<Integer> blackSet = new HashSet<>();
		for (int i : blacklist) {
			blackSet.add(i);
		}
		if (blacklist.length > N / 3) {
			List<Integer> allowed = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				if (!blackSet.contains(i)) {
					allowed.add(i);
				}
			}
			int idx = new Random().nextInt(allowed.size());
			return allowed.get(idx);
		} else {
			while (true) {
				int test = new Random().nextInt(N);
				if (!blackSet.contains(test)) {
					return test;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			System.out.println(pick(9,new int[] {1,4,6,7}));
		}
		for (int i = 0; i < 10; i++) {
			System.out.println(pick(21,new int[] {9,10,11}));
		}
	}
}
