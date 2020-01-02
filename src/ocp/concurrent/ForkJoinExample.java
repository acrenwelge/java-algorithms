package ocp.concurrent;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;

public class ForkJoinExample {
        static final Logger log = LogManager.getRootLogger();

	public static void main(String[] args) {
		ForkJoinPool pool = new ForkJoinPool();
		int[] arr = new int[1000];
		Arrays.fill(arr, 5);

		MyIncrementAction action = new MyIncrementAction(arr, 0, 1000);
		MySumTask task = new MySumTask(arr, 0, 1000);
		pool.execute(action);
		Integer sum = pool.invoke(task);
		for (Integer i : arr) System.out.print(i + " ");
		log.debug("\n"+sum);
	}

}
