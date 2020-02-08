package ocp.concurrent;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.concurrent.RecursiveTask;

public class MySumTask extends RecursiveTask<Integer> {
	private static final long serialVersionUID = 1L;

	static final Logger log = LogManager.getRootLogger();

	public static final int THRESHOLD = 3;
	public int[] arr;
	public int low;
	public int high;

	@Override
	protected Integer compute() {
		if (high - low <= THRESHOLD) {
			int sum = 0;
			for (int i = low; i < high; i++) {
				sum += arr[i];
			}
			return sum;
		} else {
			int mid = low + (high-low)/2;
			int left = new MySumTask(arr,low,mid).compute();
			int right = new MySumTask(arr,mid,high).compute();
			return left + right;
		}
	}

	MySumTask(int[] arr, int low, int high) {
		this.arr = arr;
		this.low = low;
		this.high = high;
	}

	public static void main(String[] args) {
		int[] arr = new int[] {1,6,2,0,4};
		Integer sum = new MySumTask(arr,0,5).compute();
		log.debug(sum);
	}

}
