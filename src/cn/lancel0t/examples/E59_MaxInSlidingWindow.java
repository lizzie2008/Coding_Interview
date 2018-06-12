/**
 * 
 * 【剑指Offer】	面试题59 ：滑动窗口的最大值
 * 【题目描述】	给定一个数组和滑动窗口的大小，请找出所有滑动窗口里的最大值。
 *  例如，如果输入数组{2, 3, 4, 2, 6, 2, 5, 1}及滑动窗口的大小3，
 *  那么一共存在6个滑动窗口，它们的最大值分别为{4, 4, 6, 6, 6, 5}，
 * 
 * @author lancel0t
 * @date 2018年5月22日
 */
package cn.lancel0t.examples;

import java.util.ArrayList;
import java.util.LinkedList;

public class E59_MaxInSlidingWindow {

	/*
	 * 滑动窗口的最大值
	 * 思路：借助一个辅助队列，从头遍历数组，根据如下规则进行入队列或出队列操作： 
	 * 1. 如果队列为空，则当前数字入队列 
	 * 2. 如果当前数字大于队列尾，则删除队列尾，直到当前数字小于等于队列尾，或者队列空，然后当前数字入队列 
	 * 3. 如果当前数字小于队列尾，则当前数字入队列 
	 * 4. 如果队列头超出滑动窗口范围，则删除队列头 
	 * 这样能始终保证队列头为当前的最大值，我们在队列中存储数组的下标而非数值，
	 * 这样通过判断下标之间的差值是否大于窗口的大小，就可以判断元素是否还在滑动窗口中。
	 */
	public ArrayList<Integer> maxInWindows(int[] num, int size) {
		ArrayList<Integer> result = new ArrayList<>();

		if (num == null || num.length == 0 || size == 0 || size > num.length) {
			return result;
		}

		LinkedList<Integer> queue = new LinkedList<>();

		for (int i = 0; i < num.length; i++) {
			if (!queue.isEmpty()) {
				// 如果队列头元素不在滑动窗口中了，就删除头元素
				if (i >= queue.peek() + size) {
					queue.pop();
				}

				// 如果当前数字大于队列尾，则删除队列尾，直到当前数字小于等于队列尾，或者队列空
				while (!queue.isEmpty() && num[i] >= num[queue.getLast()]) {
					queue.removeLast();
				}
			}
			queue.offer(i); // 入队列

			// 滑动窗口经过size个元素，获取当前的最大值，也就是队列的头元素
			if (i + 1 >= size) {
				result.add(num[queue.peek()]);
			}
		}
		return result;
	}

	// ====================测试代码====================
	private void test(String testName, int[] num, int size) {
		try {
			System.out.printf("=====%s=====\n", testName);
			System.out.printf("滑动窗口的最大值:\n");
			ArrayList<Integer> list = maxInWindows(num, size);
			if (!list.isEmpty()) {
				ArrayList<String> strs = new ArrayList<String>();
				for (Integer n : list) {
					strs.add(n.toString());
				}
				System.out.println(String.join(",", strs));
			} else {
				System.out.println("∅");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println();
	}

	private void test1() {
		int num[] = { 2, 3, 4, 2, 6, 2, 5, 1 };
		test("test1", num, 3);
	}

	private void test2() {
		int num[] = { 1, 3, -1, -3, 5, 3, 6, 7 };
		test("test2", num, 3);
	}

	// 输入数组单调递增
	private void test3() {
		int num[] = { 1, 3, 5, 7, 9, 11, 13, 15 };
		test("test3", num, 4);
	}

	// 输入数组单调递减
	private void test4() {
		int num[] = { 16, 14, 12, 10, 8, 6, 4 };
		test("test4", num, 5);
	}

	// 滑动窗口的大小为1
	private void test5() {
		int num[] = { 10, 14, 12, 11 };
		test("test5", num, 1);
	}

	// 滑动窗口的大小等于数组的长度
	private void test6() {
		int num[] = { 10, 14, 12, 11 };
		test("test6", num, 4);
	}

	// 滑动窗口的大小为0
	private void test7() {
		int num[] = { 10, 14, 12, 11 };
		test("test7", num, 0);
	}

	// 滑动窗口的大小大于输入数组的长度
	private void test8() {
		int num[] = { 10, 14, 12, 11 };
		test("test8", num, 5);
	}

	// 输入数组为空
	private void test9() {
		int num[] = null;
		test("test9", num, 5);
	}

	public static void main(String[] args) {

		E59_MaxInSlidingWindow exam = new E59_MaxInSlidingWindow();

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
