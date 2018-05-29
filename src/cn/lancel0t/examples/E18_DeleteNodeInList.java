/**
 * 
 * 【剑指Offer】面试题18 ：在O(1)时间删除链表结点
 * 【  题目描述 】给定单向链表的头指针和一个结点指针，定义一个函数在O(1)时间删除该结点
 * 
 * @author lancel0t
 * @date 2018年5月22日
 */
package cn.lancel0t.examples;

import cn.lancel0t.utilities.ListNode;

public class E18_DeleteNodeInList {

	// O(1)时间删除链表结点
	public ListNode deleteNode(ListNode head, ListNode toBeDeleted) {

		if (head == null || toBeDeleted == null)
			return head;

		// 要删除的结点不是尾结点
		if (toBeDeleted.next != null) {

			// 拷贝待删除节点的下个节点的值给删除节点，并删除待删除节点的下个节点
			toBeDeleted.val = toBeDeleted.next.val;
			toBeDeleted.next = toBeDeleted.next.next;
		}
		// 链表只有一个结点，删除头结点（也是尾结点）
		else if (head == toBeDeleted) {
			return null;
		}
		// 链表中有多个结点，删除尾结点
		else {
			// 找到删除节点的前驱
			ListNode currNode = head;
			while (currNode.next != toBeDeleted) {
				currNode = currNode.next;
			}
			// 删除结点
			currNode.next = null;
		}

		return head;
	}

	// ====================测试代码====================
	private void test(String testName, ListNode head, ListNode toBeDeleted, String expect) {
		try {
			System.out.printf("=====%s=====\n", testName);
			ListNode newHead = deleteNode(head, toBeDeleted);
			System.out.print("删除节点后：\nResult:");
			ListNode.print(newHead);
			System.out.printf("Expect:%s", expect);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("\n");
	}

	// 链表中有多个结点，删除中间的结点
	private void test1() {
		ListNode pNode1 = new ListNode(1);
		ListNode pNode2 = new ListNode(2);
		ListNode pNode3 = new ListNode(3);
		ListNode pNode4 = new ListNode(4);
		ListNode pNode5 = new ListNode(5);

		ListNode.connectListNodes(pNode1, pNode2);
		ListNode.connectListNodes(pNode2, pNode3);
		ListNode.connectListNodes(pNode3, pNode4);
		ListNode.connectListNodes(pNode4, pNode5);

		test("test1", pNode1, pNode3, "1 2 4 5");
	}

	// 链表中有多个结点，删除尾结点
	private void test2() {
		ListNode pNode1 = new ListNode(1);
		ListNode pNode2 = new ListNode(2);
		ListNode pNode3 = new ListNode(3);
		ListNode pNode4 = new ListNode(4);
		ListNode pNode5 = new ListNode(5);

		ListNode.connectListNodes(pNode1, pNode2);
		ListNode.connectListNodes(pNode2, pNode3);
		ListNode.connectListNodes(pNode3, pNode4);
		ListNode.connectListNodes(pNode4, pNode5);

		test("test2", pNode1, pNode5, "1 2 3 4");
	}

	// 链表中有多个结点，删除头结点
	private void test3() {
		ListNode pNode1 = new ListNode(1);
		ListNode pNode2 = new ListNode(2);
		ListNode pNode3 = new ListNode(3);
		ListNode pNode4 = new ListNode(4);
		ListNode pNode5 = new ListNode(5);

		ListNode.connectListNodes(pNode1, pNode2);
		ListNode.connectListNodes(pNode2, pNode3);
		ListNode.connectListNodes(pNode3, pNode4);
		ListNode.connectListNodes(pNode4, pNode5);

		test("test3", pNode1, pNode1, "2 3 4 5");
	}

	// 链表中有多个结点，删除头结点
	private void test4() {
		ListNode pNode1 = new ListNode(1);

		test("test4", pNode1, pNode1, "∅");
	}

	// 链表为空
	private void test5() {
		test("test5", null, null, "∅");
	}

	public static void main(String[] args) {

		E18_DeleteNodeInList exam = new E18_DeleteNodeInList();

		exam.test1();
		exam.test2();
		exam.test3();
		exam.test4();
		exam.test5();
	}
}
