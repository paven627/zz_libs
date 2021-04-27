

public class Main {

	public static void main(String[] args) throws Exception {
        int[] arr = new int[10];

		for (int i = 0; i < arr.length  ; i++) {
		    arr[i] = i + 10;
		}

        int i = binerySearch(arr, 12);
        System.out.println(i);
    }

    public static int binerySearch(int[] arr, int searchNum) {
        // 初始化左侧索引
        int leftIndex = 0;
        // 初始化右侧索引
        int rightIndex = arr.length - 1;
        while (leftIndex <= rightIndex) {
            // 计算中间索引
            int mid = (leftIndex + rightIndex) >>> 1;//主要防止溢出，就是除以2的意思
            // 如果查询的数等于中间索引对应的数组里的数，则返回mid索引，并退出循环
            if (searchNum == arr[mid]) {
                return mid;
            }
            // 判断并计算右侧索引
            if (searchNum < arr[mid]) {
                rightIndex = mid - 1;
            }
            // 判断并计算左侧索引
            if (searchNum > arr[mid]) {
                leftIndex = mid + 1;
            }
        }
        return -1;
    }
}
