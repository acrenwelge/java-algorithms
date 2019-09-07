package ocp.concurrent;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;

public class ForkJoinExample {

	public static void main(String[] args) {
		ForkJoinPool pool = new ForkJoinPool();
		int[] arr = new int[1000];
		Arrays.fill(arr, 5);
		
		MyIncrementAction action = new MyIncrementAction(arr, 0, 1000);
		MySumTask task = new MySumTask(arr, 0, 1000);
		pool.execute(action);
		Integer sum = pool.invoke(task);
		for (Integer i : arr) System.out.print(i + " ");
		System.out.println("\n"+sum);
	}

}
