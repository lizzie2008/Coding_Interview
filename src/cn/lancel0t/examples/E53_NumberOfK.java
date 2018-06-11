/**
 * 
 * 【剑指Offer】	面试题53 ：数字在排序数组中出现的次数
 * 【题目描述】	统计一个数字在排序数组中出现的次数。
 *  例如输入排序数组{1, 2, 3, 3, 3, 3, 4, 5}和数字3，由于3在这个数组中出现了4次，因此输出4。
 * 
 * @author lancel0t
 * @date 2018年5月22日
 */
package cn.lancel0t.examples;

public class E53_NumberOfK {

	/*
	 * 数字在排序数组中出现的次数
	 * 思路：由于数组是排序的，容易想到找到数字k第一次出现的下标和最后一次出现的下标；
	 * 另外，找数字k在数组中出现的位置，想到用二分查找法：
	 * 1.如果中间位置的元素比k大，则k只可能出现在前半段，同理，中间元素比k小，则k只可能出现在后半段；
	 * 2.如果中间位置的元素跟k相等，判断这个元素前面一个元素是否为k，如果不是，则是第一个元素；否则不是第一个元素，
	 * 	 继续在前半段中查找，直到找出第一次出现的下标。
	 * 3.同理找出最后一次出现的下标。
	 */
	public int GetNumberOfK(int[] array, int k) {

		int number = 0;

		if (array != null && array.length > 0) {
			int first = GetFirstK(array, k, 0, array.length - 1);
			int last = GetLastK(array, k, 0, array.length - 1);

			if (first > -1 && last > -1)
				number = last - first + 1;
		}

		return number;
	}

	// 二分法查找第一个出现的下标
	private int GetFirstK(int[] array, int k, int start, int end) {

		if (start > end)
			return -1;

		int middleIndex = (start + end) / 2;
		int middleData = array[middleIndex];

		if (middleData == k) {
			if ((middleIndex > 0 && array[middleIndex - 1] != k) || middleIndex == 0)
				return middleIndex;
			else
				end = middleIndex - 1;
		} else if (middleData > k)
			end = middleIndex - 1;
		else
			start = middleIndex + 1;

		return GetFirstK(array, k, start, end);
	}

	// 二分法查找最后一个出现的下标
	private int GetLastK(int[] array, int k, int start, int end) {

		if (start > end)
			return -1;

		int middleIndex = (start + end) / 2;
		int middleData = array[middleIndex];

		if (middleData == k) {
			if ((middleIndex < array.length - 1 && array[middleIndex + 1] != k)
					|| middleIndex == array.length - 1)
				return middleIndex;
			else
				start = middleIndex + 1;
		} else if (middleData < k)
			start = middleIndex + 1;
		else
			end = middleIndex - 1;

		return GetLastK(array, k, start, end);
	}

	// ====================测试代码====================
	private void test(String testName, int[] array, int k, int expect) {
		try {
			System.out.printf("=====%s=====\n", testName);
			System.out.printf("数字在排序数组中出现的次数:\nResult:%d\nExpect:%d\n", GetNumberOfK(array, k), expect);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println();
	}

	// 查找的数字出现在数组的中间
	private void test1() {
		int data[] = { 1, 2, 3, 3, 3, 3, 4, 5 };
		test("test1", data, 3, 4);
	}

	// 查找的数组出现在数组的开头
	private void test2() {
		int data[] = { 3, 3, 3, 3, 4, 5 };
		test("test2", data, 3, 4);
	}

	// 查找的数组出现在数组的结尾
	private void test3() {
		int data[] = { 1, 2, 3, 3, 3, 3 };
		test("test3", data, 3, 4);
	}

	// 查找的数字不存在
	private void test4() {
		int data[] = { 1, 3, 3, 3, 3, 4, 5 };
		test("test4", data, 2, 0);
	}

	// 查找的数字比第一个数字还小，不存在
	private void test5() {
		int data[] = { 1, 3, 3, 3, 3, 4, 5 };
		test("test5", data, 0, 0);
	}

	// 查找的数字比最后一个数字还大，不存在
	private void test6() {
		int data[] = { 1, 3, 3, 3, 3, 4, 5 };
		test("test6", data, 6, 0);
	}

	// 数组中的数字从头到尾都是查找的数字
	private void test7() {
		int data[] = { 3, 3, 3, 3 };
		test("test7", data, 3, 4);
	}

	// 数组中的数字从头到尾只有一个重复的数字，不是查找的数字
	private void test8() {
		int data[] = { 3, 3, 3, 3 };
		test("test8", data, 4, 0);
	}

	// 数组中只有一个数字，是查找的数字
	private void test9() {
		int data[] = { 3 };
		test("test9", data, 3, 1);
	}

	// 数组中只有一个数字，不是查找的数字
	private void test10() {
		int data[] = { 3 };
		test("test10", data, 4, 0);
	}

	// 鲁棒性测试，数组空指针
	private void test11() {
		int data[] = null;
		test("test11", data, 0, 0);
	}

	public static void main(String[] args) {

		E53_NumberOfK exam = new E53_NumberOfK();

		exam.test1();
		exam.test2();
		exam.test3();
		exam.test4();
		exam.test5();
		exam.test6();
		exam.test7();
		exam.test8();
		exam.test9();
		exam.test10();
		exam.test11();
	}
}
