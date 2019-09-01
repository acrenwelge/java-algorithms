package ocp;

import java.util.concurrent.RecursiveAction;

public class MyAction extends RecursiveAction {
	
	static final int MAX = 100;
	int[] arr;
	int low;
	int high;
	

	@Override
	protected void compute() {
		// increment elements of array
		if (high - low <= MAX) {
			for (int i=0;i<arr.length;i++) arr[i]++;
		} else {
			int mid = low + (high - low) / 2;
			MyAction left = new MyAction(arr, low, mid);
			MyAction right = new MyAction(arr, mid, high);
			right.compute();
			left.fork().join();
		}
	}
	
	public MyAction(int[] arr, int low, int high) {
		this.arr = arr;
		this.low = low;
		this.high = high;
	}
	
	public static void main(String[] args) {
		int[] arr = new int[] {1,5,2,3,-5};
		new MyAction(arr,0,5).compute();
		for (int i : arr) {
			System.out.println(i);
		}
	}
	
}
