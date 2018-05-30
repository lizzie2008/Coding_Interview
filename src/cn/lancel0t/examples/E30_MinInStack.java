/**
 * 
 * 【剑指Offer】	面试题30 ：包含min函数的栈
 * 【题目描述】	定义栈的数据结构，请在该类型中实现一个能够得到栈最小元素的min函数。
 * 
 * @author lancel0t
 * @date 2018年5月22日
 */
package cn.lancel0t.examples;

import java.util.Stack;

public class E30_MinInStack {

	// 数据栈，用于存放插入的数据
	private Stack<Integer> dataStack = new Stack<Integer>();;
	// 最小数位置栈，存放数据栈中最小的数的位置
	private Stack<Integer> minStack = new Stack<Integer>();;

	// 压栈
	public void push(int node) {
		// 把新元素添加到辅助栈
		dataStack.push(node);

		// 当新元素比之前的最小元素小时，把新元素插入辅助栈里；
		// 否则把之前的最小元素重复插入辅助栈里
		if (minStack.isEmpty() || node < minStack.peek())
			minStack.push(node);
		else
			minStack.push(minStack.peek());
	}

	// 出栈
	public void pop() {
		if (dataStack.isEmpty() || minStack.isEmpty())
			throw new RuntimeException("栈已经为空！");

		// 如果有数据，最小数位置栈和数据栈必定是有相同的元素个数，
		// 两个栈同时出栈
		minStack.pop();
		dataStack.pop();
	}

	// 返回栈顶元素
	public int top() {
		return dataStack.peek();
	}

	// 返回最小元素
	public int min() {
		return minStack.peek();
	}

	// ====================测试代码====================
	private void test(int expect) {
		try {
			System.out.print("当前栈元素：");
			for (int val : dataStack) {
				System.out.print(val + " ");
			}
			System.out.println();
			System.out.printf("当前最小值：Result:%d \t Expect:%d", min(), expect);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("\n");
	}

	public static void main(String[] args) {

		E30_MinInStack exam = new E30_MinInStack();

		exam.push(3); // 压入3
		exam.test(3); // 当前最小3

		exam.push(4); // 压入4
		exam.test(3); // 当前最小3

		exam.push(2); // 压入2
		exam.test(2); // 当前最小2

		exam.push(3); // 压入3
		exam.test(2); // 当前最小2

		exam.pop(); // 弹出3
		exam.test(2); // 当前最小2

		exam.pop(); // 弹出2
		exam.test(3); // 当前最小3

		exam.pop(); // 弹出4
		exam.test(3); // 当前最小3

		exam.push(0); // 压入0
		exam.test(0); // 当前最小0
	}
}
