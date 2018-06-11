/**
 * 
 * 【剑指Offer】	面试题51 ：数组中的逆序对
 * 【题目描述】	在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。
 * 输入一个数组,求出这个数组中的逆序对的总数P。并将P对1000000007取模的结果输出。 即输出P%1000000007
 * 
 * @author lancel0t
 * @date 2018年5月22日
 */
package cn.lancel0t.examples;

public class E51_InversePairs {

	/*
	 * 数组中的逆序对
	 * 思路：利用归并排序的思想，先求前面一半数组的逆序数，再求后面一半数组的逆序数，
	 * 然后求前面一半数组比后面一半数组中大的数的个数（也就是逆序数），这三个过程加起来就是整体的逆序数目了。
	 * 第二个方法在归并时，需要array的左右子数组是已排好序的数组，归并的结果是得到排好序的数组copy。
	 * 因此在递归调用iPairs时，方法的前2个参数是颠倒的，这样得到的array才是排好序的。
	 */
	public int InversePairs(int[] array) {
		if (array == null)
			return 0;
		// 创建辅助数组
		int length = array.length;
		int[] copy = new int[length];
		System.arraycopy(array, 0, copy, 0, length);
		int numberOfInversePairs = iPairs(array, copy, 0, length - 1);
		return numberOfInversePairs;
	}

	public int iPairs(int[] array, int[] copy, int begin, int end) {
		if (begin == end)
			return 0;
		int mid = (begin + end) / 2;
		// 递归调用
		int left = iPairs(copy, array, begin, mid);
		int right = iPairs(copy, array, mid + 1, end);
		// 归并
		int i = mid, j = end, pos = end;
		int count = 0; // 记录相邻子数组间逆序数

		while (i >= begin && j >= mid + 1) {
			if (array[i] > array[j]) {
				copy[pos--] = array[i--];
				count = (count + j - mid) % 1000000007;
			} else
				copy[pos--] = array[j--];
		}

		while (i >= begin)
			copy[pos--] = array[i--];
		while (j >= mid + 1)
			copy[pos--] = array[j--];

		return (left + right + count) % 1000000007;
	}

	// ====================测试代码====================
	private void test(String testName, int[] array, int expect) {
		try {
			System.out.printf("=====%s=====\n", testName);
			System.out.printf("数组中的逆序对:\nResult:%d\nExpect:%d\n", InversePairs(array), expect);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println();
	}

	private void test1() {
		int data[] = { 1, 2, 3, 4, 7, 6, 5 };
		test("test1", data, 3);
	}

	private void test2() {
		int data[] = { 6, 5, 4, 3, 2, 1 };
		test("test2", data, 15);
	}

	private void test3() {
		int data[] = { 1, 2, 3, 4, 5, 6 };
		test("test3", data, 0);
	}

	private void test4() {
		int data[] = { 1 };
		test("test4", data, 0);
	}

	private void test5() {
		int data[] = { 1, 2 };
		test("test5", data, 0);
	}

	private void test6() {
		int data[] = { 2, 1 };
		test("test6", data, 1);
	}

	private void test7() {
		int data[] = { 1, 2, 1, 2, 1 };
		test("test7", data, 3);
	}

	private void test8() {
		int data[] = { 364, 637, 341, 406, 747, 995, 234, 971, 571, 219, 993, 407, 416, 366, 315, 301,
				601, 650, 418, 355, 460, 505, 360, 965, 516, 648, 727, 667, 465, 849, 455, 181, 486, 149,
				588, 233, 144, 174, 557, 67, 746, 550, 474, 162, 268, 142, 463, 221, 882, 576, 604, 739,
				288, 569, 256, 936, 275, 401, 497, 82, 935, 983, 583, 523, 697, 478, 147, 795, 380, 973,
				958, 115, 773, 870, 259, 655, 446, 863, 735, 784, 3, 671, 433, 630, 425, 930, 64, 266,
				235, 187, 284, 665, 874, 80, 45, 848, 38, 811, 267, 575 };
		test("test8", data, 2519);
	}

	private void test9() {
		int data[] = null;
		test("test9", data, 0);
	}

	public static void main(String[] args) {

		E51_InversePairs exam = new E51_InversePairs();

		exam.test1();
		exam.test2();
		exam.test3();
		exam.test4();
		exam.test5();
		exam.test6();
		exam.test7();
		exam.test8();
		exam.test9();
	}
}
