package algorithms.sort;

import java.util.Random;

import util.Profiler;

public class SortComparison {
	
	static final int ARR_SIZE = 10_000;

	public static void main(String[] args) {
		final Sorter[] sorters = new Sorter[] {
				new BubbleSorter(),
				new InsertionSorter(),
				new CountSorter(),
				new MergeSorter()};
		final int[] arr = generateRandomArray();
		for (Sorter s : sorters) {
			Profiler.profile(s.getClass().getSimpleName(), () -> s.sort(arr));
		}
	}
	
	public static int[] generateRandomArray() {
		Random r = new Random();
		int[] ints = new int[ARR_SIZE];
		for (int i=0; i < ARR_SIZE; i++) {
			ints[i] = r.nextInt(ARR_SIZE);
		}
		return ints;
	}

}
