package p38;

/**
 * 查找和排序算法实现+八大排序算法
 * @author Guozhu Zhu
 * @date 2018/9/13
 * @version 1.0
 *
 */
public class Test12 {
	
	/* ========== Test ========== */
	public static void main(String[] args) {
		int[] arr = {1, 4, 3, 2, 6};
		Sort08(arr);
		for (int i : arr) {
			System.out.println(i);
		}
	}
	
	//1. 冒泡排序算法实现， O(n)=n^2, 稳定的排序算法实现，空间O(n)=1;
	public static void Sort01(int[] arr) {
		for (int i = 0; i < arr.length-1; i++) {
			for (int j = 0; j < arr.length-1-i; j++) {
				if (arr[j+1] < arr[j]) {
					int temp = arr[j+1];
					arr[j+1] = arr[j];
					arr[j] = temp;
				}
			}
		}
	}
	
	//ArrToStr
	public static String ArrToStr(int[] arr) {
		if (arr == null) {
			return null;
		}
		if (arr.length == 0) {
			return "[]";
		}
		StringBuilder b = new StringBuilder();
		b.append('[');
		for (int i = 0; i < arr.length; i++) {
			b.append(arr[i]);
			if (i == arr.length-1) {
				break;
			}
			b.append(',');
		}
		b.append(']');
		return b.toString();
	}
	
	//2. 直接选择排序算法实现， O(n)=n^2, 稳定的排序算法实现，空间O(n)=1;
	public static void Sort02(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = i+1; j < arr.length; j++) {
				if (arr[i] > arr[j]) {
					int temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
			}
		} 
	}
	
	//3. 直接插入排序算法实现， O(n)=n^2, 稳定的排序算法实现， 空间O(n)=1;
	public static void Sort03(int[] arr) {
		for (int i = 1; i < arr.length; i++) {
			int temp = arr[i];
			int j = i-1;
			while (j >= 0 && arr[j] > temp) {
				arr[j+1] = arr[j];
				j--;
			}
			arr[j+1] = temp;
		}
	}
	
	//4. 二分插入排序算法实现， O(n)=n^2, 稳定的排序算法实现， 空间O(n)=1;
	public static void Sort04(int[] arr) {
		for (int i = 1; i < arr.length; i++) {
			int temp = arr[i];
			int low = 0;
			int high = i-1;
			int mid = 0;
			while (low <= high) {
				mid = (low+high) / 2;
				if (arr[mid] > temp) {
					high = mid-1;
				} else {
					low = mid+1;
				}
			}
			for (int j = i-1; j >= low; j--) {
				arr[j+1] = arr[j];
			}
			arr[low] = temp;
		}
	}
	
	//5. 希尔排序算法实现， O(n)=nlogn ~ n^2, 最优O(n)=n^1.3;
	public static void Sort05(int[] arr) {
		int gap = arr.length-1 / 2;
		while (gap >= 1) {
			for (int i = gap; i < arr.length; i++) {
				int temp = arr[i];
				int j = i-gap;
				while (j >= 0 && arr[j] > temp) {
					arr[j+gap] = arr[j];
					j = j-gap;
				}
				arr[j+gap] = temp;
			}
			gap = gap / 2;
		}
	}
	
	//6. 快速排序算法实现， O(n)=nlogn, O(n)=nlogn;
	public static void Sort06(int[] arr, int left, int right) {
		if (left > right) {
			return;
		}
		int i = left;
		int j = right;
		int key = arr[i];
		while (i < j) {
			while (i < j && arr[j] > key) {
				j--;
			}
			arr[i] = arr[j];
			while (i < j && arr[i] < key) {
				i++;
			}
			arr[j] = arr[i];
		}
		arr[i] = key;
		Sort06(arr, left, i-1);
		Sort06(arr, i+1, right);
	}
	
	//7. 归并排序算法实现， O(n)=nlogn, 稳定的排序算法实现；
	public static void Sort07(int[] arr) {
		int[] temp = new int[arr.length];
		MergeSort(arr, 0, arr.length-1, temp);
	}
	
	public static void MergeSort(int[] arr, int first, int last, int[] temp) {
		int mid = (first+last) / 2;
		if (first < last) {
			MergeSort(arr, first, mid, temp);
			MergeSort(arr, mid+1, last, temp);
			Merge(arr, first, mid, last, temp);
		}
	}
	
	public static void Merge(int[] arr, int first, int mid, int last, int[] temp) {
		int i = first;
		int j = mid+1;
		int m = mid;
		int n = last;
		int k = 0;
		while (i <= m && j <= n) {
			if (arr[i] < arr[j]) {
				temp[k++] = arr[i++];
			} else {
				temp[k++] = arr[j++];
			}
		}
		while (i <= m) {
			temp[k++] = arr[i++];
		}
		while (j <= n) {
			temp[k++] = arr[j++];
		}
		for (int a = 0; a < k; a++) {
			arr[a+first] = temp[a];
		}
	}
	
	//8. 堆排序算法实现， O(n)=nlogn;
	public static void Sort08(int[] arr) {
		int len = arr.length;
		for (int i = (int) Math.floor(len/2); i >= 0; i--) {
			downAdjust(arr, i, arr.length-1);
		}
		for (int i = arr.length-1; i > 0; i--) {
			swap(arr, 0, i);
			downAdjust(arr, 0, i-1);
		}
	}
	
	public static void downAdjust(int[] arr, int parentIndex, int length) {
		int temp = arr[parentIndex];
		int childIndex = parentIndex*2+1;
		while (childIndex <= length) {
			if (childIndex+1 <= length && arr[childIndex+1] > arr[childIndex]) {
				childIndex++;
			}
			if (temp > arr[childIndex]) {
				break;
			}
			arr[parentIndex] = arr[childIndex];
			parentIndex = childIndex;
			childIndex = childIndex*2;
		}
		arr[parentIndex] = temp;
	}
	
	public static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

}
