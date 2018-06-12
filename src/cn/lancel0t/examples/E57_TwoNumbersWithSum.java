/**
 * 
 * 【剑指Offer】	面试题57 ：和为s的两个数字
 * 【题目描述】	输入一个递增排序的数组和一个数字S，在数组中查找两个数，是的他们的和正好是S，
 *  如果有多对数字的和等于S，输出两个数的乘积最小的。
 * 
 * @author lancel0t
 * @date 2018年5月22日
 */
package cn.lancel0t.examples;

import java.util.ArrayList;

public class E57_TwoNumbersWithSum {

	/*
	 * 和为s的两个数字
	 * 思路：考虑双指针夹逼，分别用两个指针分别指向左边第一个位置和右边第一个位置， 左右逼近，当左边的位置大于等于右边的位置即可。
	 * 题目要求是先输出乘积小的，每次先得到的都是一个小点的数和一个大点的数，故而和相等的情况下，乘积必定是最小的。
	 */
	public ArrayList<Integer> FindNumbersWithSum(int[] array, int sum) {
		ArrayList<Integer> list = new ArrayList<>();
		if (array == null || array.length < 2) {
			return list;
		}
		int left = 0;
		int right = array.length - 1;
		while (left < right) {
			// 与目标值相等
			if (array[left] + array[right] == sum) {
				list.add(array[left]);
				list.add(array[right]);
				return list;
			}
			// 大于目标值，较大值减少
			else if (array[left] + array[right] > sum) {
				right--;
			}
			// 小于目标值，较小值增大
			else {
				left++;
			}
		}
		return list;
	}

	// ====================测试代码====================
	private void test(String testName, int[] array, int sum, String expect) {
		try {
			System.out.printf("=====%s=====\n", testName);
			ArrayList<Integer> list = FindNumbersWithSum(array, sum);
			if (list.isEmpty())
				System.out.printf("未能找到和为%d的两个数字\n", sum);
			else
				System.out.printf("和为%d的两个数字:\nResult:%d,%d\nExpect:%s\n", sum, list.get(0), list.get(1),
						expect);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println();
	}

	// 存在和为s的两个数字，这两个数字位于数组的中间
	private void test1() {
		int data[] = { 1, 2, 4, 7, 11, 15 };
		test("test1", data, 15, "4,11");
	}

	// 存在和为s的两个数字，这两个数字位于数组的两端
	private void test2() {
		int data[] = { 1, 2, 4, 7, 11, 16 };
		test("test2", data, 17, "1,16");
	}

	// 不存在和为s的两个数字
	private void test3() {
		int data[] = { 1, 2, 4, 7, 11, 16 };
		test("test3", data, 10, "");
	}

	// 鲁棒性测试
	private void test4() {
		int data[] = null;
		test("test4", data, 0, "");
	}

	public static void main(String[] args) {

		E57_TwoNumbersWithSum exam = new E57_TwoNumbersWithSum();

		exam.test1();
		exam.test2();
		exam.test3();
		exam.test4();
	}
}
