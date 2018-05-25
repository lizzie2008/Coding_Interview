/**
 * 
 * 【剑指Offer】面试题9：用两个栈实现队列
 * 【  题目描述 】用两个栈实现一个队列。队列的声明如下，请实现它的两个函数appendTail 和deleteHead，
 * 分别完成在队列尾部插入结点和在队列头部删除结点的功能。
 * 
 * @author lancel0t
 * @date 2018年5月22日
 */
package cn.lancel0t;

import java.util.Stack;

public class Example09 {

	Stack<Integer> stack1 = new Stack<Integer>();
	Stack<Integer> stack2 = new Stack<Integer>();

	// 入队列
	public void push(int node) {
		// 直接压入stack1
		stack1.push(node);
	}

	// 出队列
	public int pop() {

		// 如果第2个栈没有数据，依次将第1个栈元素弹出，并压入第2个栈
		if (stack2.size() <= 0) {
			while (stack1.size() > 0) {
				int ele = stack1.pop();
				stack2.push(ele);
			}
		}

		// 如果栈1和栈2都没有元素，此时栈2为空
		if (stack2.size() == 0)
			throw new RuntimeException("队列为空！");

		// 弹出第2个栈顶元素即为最早入队列的元素
		return stack2.pop();
	}

	public static void main(String[] args) {

		Example09 exam = new Example09();

		exam.push(1);
		exam.push(2);
		exam.push(3);
		System.out.println("== 出队列：" + exam.pop() + "[1]");
		exam.push(4);
		exam.push(5);
		System.out.println("== 出队列：" + exam.pop() + "[2]");
		System.out.println("== 出队列：" + exam.pop() + "[3]");
		System.out.println("== 出队列：" + exam.pop() + "[4]");
		System.out.println("== 出队列：" + exam.pop() + "[5]");

	}
}
