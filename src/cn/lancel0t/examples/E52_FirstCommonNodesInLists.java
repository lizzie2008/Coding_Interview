/**
 * 
 * 【剑指Offer】	面试题52 ：两个链表的第一个公共结点
 * 【题目描述】	输入两个链表，找出它们的第一个公共结点。
 * 
 * @author lancel0t
 * @date 2018年5月22日
 */
package cn.lancel0t.examples;

import cn.lancel0t.utilities.ListNode;

public class E52_FirstCommonNodesInLists {

	/*
	 * 两个链表的第一个公共结点
	 * 思路：可以先遍历获得两个链表的长度，比如链表1的长度比链表2长度多s，定义两个指针分别指向两个链表，链表1的指针先走s步，
	 * 然后链表1和链表2的指针同时走，找到第一个相同的节点就是我们想要的结果。
	 */
	public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {

		int length1 = getListLength(pHead1);
		int length2 = getListLength(pHead2);

		ListNode pLong = null;
		ListNode pShort = null;
		int diff = 0;

		// 比较两个链表长度
		if (length1 >= length2) {
			pLong = pHead1;
			pShort = pHead2;
			diff = length1 - length2;
		} else {
			pLong = pHead2;
			pShort = pHead1;
			diff = length2 - length1;
		}

		// 长链表上先走
		for (int i = 0; i < diff; i++) {
			pLong = pLong.next;
		}

		// 开始同时走，直到两个链表指向同个元素
		while (pLong != null && pShort != null && pLong != pShort) {
			pLong = pLong.next;
			pShort = pShort.next;
		}

		return pLong;
	}

	// 获得链表长度
	private int getListLength(ListNode pHead) {
		int length = 0;
		ListNode currNode = pHead;
		while (currNode != null) {
			length++;
			currNode = currNode.next;
		}
		return length;
	}

	// ====================测试代码====================
	private void test(String testName, ListNode pHead1, ListNode pHead2, ListNode expect) {
		try {
			System.out.printf("=====%s=====\n", testName);
			ListNode result = FindFirstCommonNode(pHead1, pHead2);
			System.out.printf("两个链表的第一个公共结点:\nResult:%d\nExpect:%d\n",
					result == null ? null : result.val, result == null ? null : expect.val);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println();
	}

	/*
	 * 第一个公共结点在链表中间
	 *  1 - 2 - 3 \
	 *             6 - 7
	 *      4 - 5 /           
	 */
	private void test1() {
		ListNode pNode1 = new ListNode(1);
		ListNode pNode2 = new ListNode(2);
		ListNode pNode3 = new ListNode(3);
		ListNode pNode4 = new ListNode(4);
		ListNode pNode5 = new ListNode(5);
		ListNode pNode6 = new ListNode(6);
		ListNode pNode7 = new ListNode(7);

		ListNode.connectListNodes(pNode1, pNode2);
		ListNode.connectListNodes(pNode2, pNode3);
		ListNode.connectListNodes(pNode3, pNode6);
		ListNode.connectListNodes(pNode4, pNode5);
		ListNode.connectListNodes(pNode5, pNode6);
		ListNode.connectListNodes(pNode6, pNode7);

		test("Test1", pNode1, pNode4, pNode6);
	}

	/*
	 * 没有公共结点
	 * 1 - 2 - 3 - 4
	 * 
	 * 5 - 6 - 7           
	 */
	private void test2() {
		ListNode pNode1 = new ListNode(1);
		ListNode pNode2 = new ListNode(2);
		ListNode pNode3 = new ListNode(3);
		ListNode pNode4 = new ListNode(4);
		ListNode pNode5 = new ListNode(5);
		ListNode pNode6 = new ListNode(6);
		ListNode pNode7 = new ListNode(7);

		ListNode.connectListNodes(pNode1, pNode2);
		ListNode.connectListNodes(pNode2, pNode3);
		ListNode.connectListNodes(pNode3, pNode4);
		ListNode.connectListNodes(pNode5, pNode6);
		ListNode.connectListNodes(pNode6, pNode7);

		test("test2", pNode1, pNode5, null);
	}

	/*
	 * 公共结点是最后一个结点
	 * 1 - 2 - 3 - 4 \
	 *                7
	 *         5 - 6 /
	 */
	private void test3() {
		ListNode pNode1 = new ListNode(1);
		ListNode pNode2 = new ListNode(2);
		ListNode pNode3 = new ListNode(3);
		ListNode pNode4 = new ListNode(4);
		ListNode pNode5 = new ListNode(5);
		ListNode pNode6 = new ListNode(6);
		ListNode pNode7 = new ListNode(7);

		ListNode.connectListNodes(pNode1, pNode2);
		ListNode.connectListNodes(pNode2, pNode3);
		ListNode.connectListNodes(pNode3, pNode4);
		ListNode.connectListNodes(pNode4, pNode7);
		ListNode.connectListNodes(pNode5, pNode6);
		ListNode.connectListNodes(pNode6, pNode7);

		test("test3", pNode1, pNode5, pNode7);
	}

	/*
	 * 公共结点是第一个结点
	 * 1 - 2 - 3 - 4 - 5
	 * 两个链表完全重合   
	 */
	private void test4() {
		ListNode pNode1 = new ListNode(1);
		ListNode pNode2 = new ListNode(2);
		ListNode pNode3 = new ListNode(3);
		ListNode pNode4 = new ListNode(4);
		ListNode pNode5 = new ListNode(5);

		ListNode.connectListNodes(pNode1, pNode2);
		ListNode.connectListNodes(pNode2, pNode3);
		ListNode.connectListNodes(pNode3, pNode4);
		ListNode.connectListNodes(pNode4, pNode5);

		test("test4", pNode1, pNode1, pNode1);
	}

	/*
	 * 输入的两个链表有一个空链表
	 */
	private void test5() {
		ListNode pNode1 = new ListNode(1);
		ListNode pNode2 = new ListNode(2);
		ListNode pNode3 = new ListNode(3);
		ListNode pNode4 = new ListNode(4);
		ListNode pNode5 = new ListNode(5);

		ListNode.connectListNodes(pNode1, pNode2);
		ListNode.connectListNodes(pNode2, pNode3);
		ListNode.connectListNodes(pNode3, pNode4);
		ListNode.connectListNodes(pNode4, pNode5);

		test("test5", null, pNode1, null);
	}

	/*
	 * 输入的两个链表都是空链表
	 */
	private void test6() {
		test("test6", null, null, null);
	}

	public static void main(String[] args) {

		E52_FirstCommonNodesInLists exam = new E52_FirstCommonNodesInLists();

		exam.test1();
		exam.test2();
		exam.test3();
		exam.test4();
		exam.test5();
		exam.test6();
	}
}
