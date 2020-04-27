package algorithms.sort;

public interface Sorter {
	
	public int[] sort(int[] arr);
	
	public static void printArr(int[] arr) {
		for (int k = 0; k < arr.length; k++) {
			System.out.println(arr[k]);
		}
	}
}
