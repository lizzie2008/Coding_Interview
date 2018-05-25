/**
 * 
 * 【剑指Offer】面试题11 : 旋转数组的最小数字
 * 【  题目描述 】把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。 
 * 输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素。 
 * 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。 
 * NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
 * 
 * @author lancel0t
 * @date 2018年5月22日
 */
package cn.lancel0t;

public class Example11 {

	/*
	 * 一般解法时间复杂度O(n)
	 * 这里考虑用二分查找法O(logn)
	 */
	public int minNumberInRotateArray(int[] array) {

		if (array == null || array.length <= 0)
			return 0;

		// 定义高低2个指针lo、hi，二分位置mid
		int lo = 0;
		int hi = array.length - 1;
		int mid = lo;

		while (array[lo] >= array[hi]) {
			if (hi - lo == 1) {
				mid = hi;
				break;
			}
			mid = (lo + hi) / 2;

			// 如果lo、hi、mid三处相等，只能顺序查找
			if (array[lo] == array[mid] && array[mid] == array[hi]) {
				int result = array[lo];
				for (int i = lo + 1; i <= hi; i++) {
					if (array[i] < result)
						result = array[i];
				}
				return result;
			}

			// 二分查找
			if (array[mid] >= array[lo])
				lo = mid;
			else if (array[mid] <= array[hi])
				hi = mid;
		}

		return array[mid];
	}

	public static void main(String[] args) {

		Example11 exam = new Example11();

		// 典型输入，单调升序的数组的一个旋转
		int[] array1 = { 3, 4, 5, 1, 2 };
		System.out.println(exam.minNumberInRotateArray(array1) + "[1]");

		// 有重复数字，并且重复的数字刚好的最小的数字
		int[] array2 = { 3, 4, 5, 1, 1, 2 };
		System.out.println(exam.minNumberInRotateArray(array2) + "[1]");

		// 有重复数字，但重复的数字不是第一个数字和最后一个数字
		int[] array3 = { 3, 4, 5, 1, 2, 2 };
		System.out.println(exam.minNumberInRotateArray(array3) + "[1]");

		// 有重复的数字，并且重复的数字刚好是第一个数字和最后一个数字
		int[] array4 = { 1, 0, 1, 1, 1 };
		System.out.println(exam.minNumberInRotateArray(array4) + "[0]");

		// 单调升序数组，旋转0个元素，也就是单调升序数组本身
		int[] array5 = { 1, 2, 3, 4, 5 };
		System.out.println(exam.minNumberInRotateArray(array5) + "[1]");

		// 数组中只有一个数字
		int[] array6 = { 2 };
		System.out.println(exam.minNumberInRotateArray(array6) + "[2]");

		// 数组中数字都相同
		int[] array7 = { 1, 1, 1, 1, 1, 1, 1 };
		System.out.println(exam.minNumberInRotateArray(array7) + "[1]");
		System.out.println(exam.minNumberInRotateArray(array6) + "[2]");

		// 输入NULL
		System.out.println(exam.minNumberInRotateArray(null) + "[0]");
	}
}
