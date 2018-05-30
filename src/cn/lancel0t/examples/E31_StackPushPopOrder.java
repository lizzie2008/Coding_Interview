/**
 * 
 * 【剑指Offer】	面试题31 ：栈的压入、弹出序列
 * 【题目描述】	输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。
 * 假设压入栈的所有数字均不相等。
 * 例如序列1、2、3、4、5是某栈的压栈序列，
 * 序列4、5、3、2、1是该压栈序列对应的一个弹出序列，
 * 但4、3、5、1、2就不可能是该压栈序列的弹出序列。
 * 
 * @author lancel0t
 * @date 2018年5月22日
 */
package cn.lancel0t.examples;

import java.util.Stack;

public class E31_StackPushPopOrder {

	/*
	 * 判断第二个序列是否可以为第一个序列栈弹出的序列
	 * 总结规律：弹出序列的下一个弹出元素正好是栈顶元素，那么直接弹出；
	 * 如果下一个弹出元素不在栈顶，需要把压栈序列中还没有入栈的元素压入辅助栈，直到把下一个弹出元素压栈为止；
	 * 如果所有元素都已经压栈了，仍然没有找到下一个弹出元素，证明该序列不可以作为压栈序列的一个弹出的序列。
	 */
	public boolean IsPopOrder(int[] pushA, int[] popA) {

		if (pushA == null || popA == null || pushA.length != popA.length || pushA.length < 1)
			return false;

		// 辅助栈
		Stack<Integer> stack = new Stack<Integer>();
		int p1 = 0, p2 = 0;

		while (p2 < popA.length) {

			// 当辅助栈的栈顶元素不是要弹出的元素
			// 先压入一些数字入栈
			while (p1 < pushA.length && (stack.isEmpty() || stack.peek() != popA[p2])) {
				stack.push(pushA[p1]);
				p1++;
			}

			// 所有元素都已经压栈了，仍然没有找到下一个弹出元素
			if (stack.peek() != popA[p2])
				return false;

			stack.pop();
			p2++;
		}

		// 完全匹配
		if (stack.isEmpty())
			return true;

		return false;
	}

	// ====================测试代码====================
	private void test(String testName, int[] pushA, int[] popA, boolean expect) {
		try {
			System.out.printf("=====%s=====\n", testName);
			System.out.printf("是否可以为弹出的序列：Result:%b \t Expect:%b", IsPopOrder(pushA, popA), expect);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("\n");
	}

	private void test1() {
		int push[] = { 1, 2, 3, 4, 5 };
		int pop[] = { 4, 5, 3, 2, 1 };
		test("test1", push, pop, true);
	}

	private void test2() {
		int push[] = { 1, 2, 3, 4, 5 };
		int pop[] = { 3, 5, 4, 2, 1 };
		test("test2", push, pop, true);
	}

	private void test3() {
		int push[] = { 1, 2, 3, 4, 5 };
		int pop[] = { 4, 3, 5, 1, 2 };
		test("test3", push, pop, false);
	}

	private void test4() {
		int push[] = { 1, 2, 3, 4, 5 };
		int pop[] = { 3, 5, 4, 1, 2 };
		test("test4", push, pop, false);
	}

	private void test5() {
		int push[] = { 1 };
		int pop[] = { 2 };
		test("test5", push, pop, false);
	}

	private void test6() {
		int push[] = { 1 };
		int pop[] = { 1 };
		test("test6", push, pop, true);
	}

	private void test7() {
		int push[] = null;
		int pop[] = null;
		test("test7", push, pop, false);
	}

	public static void main(String[] args) {

		E31_StackPushPopOrder exam = new E31_StackPushPopOrder();

		exam.test1();
		exam.test2();
		exam.test3();
		exam.test4();
		exam.test5();
		exam.test6();
		exam.test7();
	}
}
