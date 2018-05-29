/**
 * 
 * 【剑指Offer】面试题24 ：反转链表
 * 【  题目描述 】输入一个链表，反转链表后，输出链表的所有元素。
 * 
 * @author lancel0t
 * @date 2018年5月22日
 */
package cn.lancel0t.examples;

import cn.lancel0t.utilities.ListNode;

public class E24_ReverseList {

	/*
	 * 头插法反转链表
	 */
	public ListNode ReverseList(ListNode head) {

		ListNode reversed = null;
		ListNode currNode = head;

		// 头插法
		while (currNode != null) {
			ListNode nextNode = currNode.next;

			currNode.next = reversed;
			reversed = currNode;
			currNode = nextNode;
		}
		return reversed;

	}

	// ====================测试代码====================
	private void test(String testName, ListNode head) {
		try {
			System.out.printf("=====%s=====\n", testName);
			System.out.print("反转前链表：");
			ListNode.print(head);
			ListNode newHead = ReverseList(head);
			System.out.print("反转后链表：");
			ListNode.print(newHead);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println();
	}

	// 输入的链表有多个结点
	private void test1() {
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);
		ListNode node4 = new ListNode(4);
		ListNode node5 = new ListNode(5);

		ListNode.connectListNodes(node1, node2);
		ListNode.connectListNodes(node2, node3);
		ListNode.connectListNodes(node3, node4);
		ListNode.connectListNodes(node4, node5);

		test("test1", node1);
	}

	// 输入的链表只有一个结点
	private void test2() {
		ListNode node1 = new ListNode(1);

		test("test2", node1);
	}

	// 输入空链表
	private void test3() {
		test("test3", null);
	}

	public static void main(String[] args) {

		E24_ReverseList exam = new E24_ReverseList();

		exam.test1();
		exam.test2();
		exam.test3();
	}
}
