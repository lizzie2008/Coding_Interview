/**
 * 
 * 【剑指Offer】	面试题23 ：链表中环的入口结点
 * 【题目描述】	一个链表中包含环，如何找出环的入口结点？
 * 
 * @author lancel0t
 * @date 2018年5月22日
 */
package cn.lancel0t.examples;

import cn.lancel0t.utilities.ListNode;

public class E23_EntryNodeInListLoop {

	/*
	 * 使用快慢指针的方法，如果快指针和慢指针相遇，则说明有环。
	 * 假设经过 k 步两个指针相遇，入环的第一个节点的位置是 s ，两个指针相遇的位置在 m 处，环的长度为 r 。
	 * 可以推出：2k = (s + m + n1n1r) = 2(s + m + n2n2r) ==> s + m = nr，也就是说，在相遇的位置 m
	 * 处，再走 s 步会达到环的起始点。
	 * 1.设置两个指针，一个快一个慢，快的指针每次走两步，慢的指针每次走一步，如果快指针和慢指针相遇，则说明有环；
	 * 2.指针相遇后，再设置一个慢指针从起点出发，之前的慢指针从相遇点继续出发；
	 * 3.由上述数据证明可以推导，这两个慢指针会在环的起始点相遇，相遇时返回节点即可；
	 */
	public ListNode EntryNodeOfLoop(ListNode pHead) {

		if (pHead == null)
			return null;

		ListNode pSlow = pHead;
		ListNode pFast = pHead;
		boolean isCycle = false;

		// 判断快慢指针是否相遇，如果相遇则说明有环
		while (pSlow != null && pFast != null) {
			pSlow = pSlow.next;
			if (pFast.next == null)
				return null;
			pFast = pFast.next.next;
			if (pSlow == pFast) {
				isCycle = true;
				break;
			}
		}

		if (!isCycle)
			return null;
		// 再设置慢指针从起点出发，原慢指针继续从相遇点出发
		pSlow = pHead;
		while (pSlow != pFast) {
			pSlow = pSlow.next;
			pFast = pFast.next;
		}

		return pSlow;
	}

	// ====================测试代码====================
	private void test(String testName, ListNode head, String expect) {
		try {
			System.out.printf("=====%s=====\n", testName);
			ListNode node = EntryNodeOfLoop(head);
			System.out.printf("链表中环的入口结点：Result:%s \t Expect:%s\n\n", node == null ? "∅" : node.val, expect);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// 单个节点，没有环
	private void test1() {
		ListNode node1 = new ListNode(1);

		test("test1", node1, "∅");
	}

	// 单个节点，有环
	private void test2() {
		ListNode node1 = new ListNode(1);
		ListNode.connectListNodes(node1, node1);

		test("test2", node1, "1");
	}

	// 多个节点，有环
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
		ListNode.connectListNodes(node5, node3);

		test("test3", node1, "3");
	}

	// 多个节点，有环
	private void test4() {
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);
		ListNode node4 = new ListNode(4);
		ListNode node5 = new ListNode(5);

		ListNode.connectListNodes(node1, node2);
		ListNode.connectListNodes(node2, node3);
		ListNode.connectListNodes(node3, node4);
		ListNode.connectListNodes(node4, node5);
		ListNode.connectListNodes(node5, node1);

		test("test4", node1, "1");
	}

	// 多个节点，有环
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
		ListNode.connectListNodes(node5, node5);

		test("test5", node1, "5");
	}

	// 多个节点，没有环
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

		test("test6", node1, "∅");
	}

	// 空链表
	private void test7() {
		test("test7", null, "∅");
	}

	public static void main(String[] args) {

		E23_EntryNodeInListLoop exam = new E23_EntryNodeInListLoop();

		exam.test1();
		exam.test2();
		exam.test3();
		exam.test4();
		exam.test5();
		exam.test6();
		exam.test7();
	}
}
