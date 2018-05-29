/**
 * 
 * 【剑指Offer】面试题22 ：链表中倒数第k个结点
 * 【  题目描述 】输入一个链表，输出该链表中倒数第k个结点。
 * 
 * @author lancel0t
 * @date 2018年5月22日
 */
package cn.lancel0t.examples;

import cn.lancel0t.utilities.ListNode;

public class E22_KthNodeFromEnd {

	/*
	 * 这种题一般考虑双指针：
	 * 第一个指针从链表的头指针开始遍历向前走k-1步，第二个指针保持不动；从第k 步开始，第二个指针也开始从链表的头指针开始遍历。
	 * 当第一个（走在前面的）指针到达链表的尾结点时，第二个指针（走在后面的）指针正好是倒数第k 个结点。
	 */
	public ListNode FindKthToTail(ListNode head, int k) {

		if (head == null || k <= 0)
			return null;

		// point1先走k-1个位置
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

		// point1还没有走到链表的末尾，那么point1和point2一起走
		ListNode point2 = head;
		while (point1.next != null) {
			point1 = point1.next;
			point2 = point2.next;
		}

		return point2;
	}

	// ====================测试代码====================
	private void test(String testName, ListNode head, int k, String expect) {
		try {
			System.out.printf("=====%s=====\n", testName);
			ListNode node = FindKthToTail(head, k);
			System.out.printf("倒数第%d个结点：Result:%s \t Expect:%s\n\n", k, node == null ? "∅" : node.val, expect);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// 测试要找的结点在链表中间
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

		test("test1", node1, 2, "4");
	}

	// 测试要找的结点是链表的尾结点
	private void test2() {
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);
		ListNode node4 = new ListNode(4);
		ListNode node5 = new ListNode(5);

		ListNode.connectListNodes(node1, node2);
		ListNode.connectListNodes(node2, node3);
		ListNode.connectListNodes(node3, node4);
		ListNode.connectListNodes(node4, node5);

		test("test2", node1, 1, "5");
	}

	// 测试要找的结点是链表的头结点
	private void test3() {
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);
		ListNode node4 = new ListNode(4);
		ListNode node5 = new ListNode(5);

		ListNode.connectListNodes(node1, node2);
		ListNode.connectListNodes(node2, node3);
		ListNode.connectListNodes(node3, node4);
		ListNode.connectListNodes(node4, node5);

		test("test3", node1, 5, "1");
	}

	// 测试空链表
	private void test4() {
		test("test4", null, 100, "∅");
	}

	// 测试输入的第二个参数大于链表的结点总数
	private void test5() {
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);
		ListNode node4 = new ListNode(4);
		ListNode node5 = new ListNode(5);

		ListNode.connectListNodes(node1, node2);
		ListNode.connectListNodes(node2, node3);
		ListNode.connectListNodes(node3, node4);
		ListNode.connectListNodes(node4, node5);

		test("test5", node1, 6, "∅");
	}

	// 测试输入的第二个参数为0
	private void test6() {
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);
		ListNode node4 = new ListNode(4);
		ListNode node5 = new ListNode(5);

		ListNode.connectListNodes(node1, node2);
		ListNode.connectListNodes(node2, node3);
		ListNode.connectListNodes(node3, node4);
		ListNode.connectListNodes(node4, node5);

		test("test6", node1, 0, "∅");
	}

	public static void main(String[] args) {

		E22_KthNodeFromEnd exam = new E22_KthNodeFromEnd();

		exam.test1();
		exam.test2();
		exam.test3();
		exam.test4();
		exam.test5();
		exam.test6();
	}
}
