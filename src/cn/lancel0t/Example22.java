/**
 * 
 * 【剑指Offer】面试题22 ：链表中倒数第k个结点
 * 【  题目描述 】输入一个链表，输出该链表中倒数第k个结点。
 * 
 * @author lancel0t
 * @date 2018年5月22日
 */
package cn.lancel0t;

import cn.lancel0t.Example18.ListNode;

public class Example22 {

	// 链表节点
	public class ListNode {
		int val;
		ListNode next = null;

		ListNode(int val) {
			this.val = val;
		}
	}

	/*
	 * 这种题一般考虑双指针：
	 * 第一个指针从链表的头指针开始遍历向前走k-1步，第二个指针保持不动；从第k 步开始，第二个指针也开始从链表的头指针开始遍历。
	 * 当第一个（走在前面的）指针到达链表的尾结点时，第二个指针（走在后面的）指针正好是倒数第k 个结点。
	 */
	public ListNode FindKthToTail(ListNode head, int k) {

		if (head == null || k <= 0)
			return null;

		// pointer先走k-1个位置
		ListNode point1 = head;
		for (int i = 1; i < k; i++) {
			// 说明还有结点
			if (point1.next != null) {
				point1 = point1.next;
			} else {
				// 已经没有节点了
				return null;
			}
		}

		// pointer还没有走到链表的末尾，那么pointer和head一起走
		ListNode point2 = head;
		while (point1.next != null) {
			point1 = point1.next;
			point2 = point2.next;
		}

		return point2;
	}

	public static void main(String[] args) {

		Example22 exam = new Example22();

		ListNode node1 = exam.new ListNode(1);
		ListNode node2 = exam.new ListNode(2);
		ListNode node3 = exam.new ListNode(3);
		ListNode node4 = exam.new ListNode(4);
		ListNode node5 = exam.new ListNode(5);

		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node5;

		System.out.println("=====Test1=====");
		System.out.printf("倒数第2个节点应该是：%s [4]\n\n", exam.FindKthToTail(node1, 2).val);

		System.out.println("=====Test2=====");
		System.out.printf("倒数第5个节点应该是：%s [5]\n\n", exam.FindKthToTail(node1, 1).val);

		System.out.println("=====Test3=====");
		System.out.printf("倒数第1个节点应该是：%s [1]\n\n", exam.FindKthToTail(node1, 5).val);

		System.out.println("=====Test4=====");
		System.out.printf("倒数第100个节点应该是：%s [null]\n\n",
				exam.FindKthToTail(null, 100) == null ? "null" : exam.FindKthToTail(null, 100));

		System.out.println("=====Test5=====");
		System.out.printf("倒数第0个节点应该是：%s [null]\n\n",
				exam.FindKthToTail(node1, 0) == null ? "null" : exam.FindKthToTail(node1, 0));
	}
}
