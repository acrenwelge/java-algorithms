package ocp.concurrent;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.concurrent.RecursiveAction;

public class MyIncrementAction extends RecursiveAction {
	private static final long serialVersionUID = 1L;

		static final Logger log = LogManager.getRootLogger();

	static final int MAX = 100;
	int[] arr;
	int low;
	int high;


	@Override
	protected void compute() {
		// increment elements of array
		if (high - low <= MAX) {
			for (int i=low;i<high;i++) arr[i]++;
		} else {
			int mid = low + (high - low) / 2;
			MyIncrementAction left = new MyIncrementAction(arr, low, mid);
			MyIncrementAction right = new MyIncrementAction(arr, mid, high);
			right.compute();
			left.compute();
		}
	}

	public MyIncrementAction(int[] arr, int low, int high) {
		this.arr = arr;
		this.low = low;
		this.high = high;
	}

	public static void main(String[] args) {
		int[] arr = new int[] {1,5,2,3,-5};
		new MyIncrementAction(arr,0,arr.length).compute();
		for (int i : arr) {
			log.debug(i);
		}
	}

}
