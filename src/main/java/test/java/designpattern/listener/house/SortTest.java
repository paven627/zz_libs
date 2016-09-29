package test.java.designpattern.listener.house;

public class SortTest {
	public static void main(String[] args) {
		
		int[] arr = new int[]{1,26,43,75,43,22,34,56,11,33,33,567,};
		
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length - 1; j++) {
				if(arr[j] > arr[j+1]){
					int temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
				}
			}
		}
		for (int i : arr) {
			System.out.print(i);
			System.out.print(",");
		}
		System.out.println();
		int i = 9;
		int k = 9;
		i = k-i;
		k=k-i;
		i = k + i;
		System.out.println(i);
		System.out.println(k);
		
	}
}
