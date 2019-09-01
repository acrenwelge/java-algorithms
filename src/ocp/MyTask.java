package ocp;

import java.util.concurrent.RecursiveTask;

public class MyTask extends RecursiveTask<Integer> {
	
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
			int left = new MyTask(arr,low,mid).compute();
			int right = new MyTask(arr,mid,high).compute();
			return left + right;
		}
	}
	
	MyTask(int[] arr, int low, int high) {
		this.arr = arr;
		this.low = low;
		this.high = high;
	}
	
	public static void main(String[] args) {
		int[] arr = new int[] {1,6,2,0,4};
		Integer sum = new MyTask(arr,0,5).compute();
		System.out.println(sum);
	}

}
