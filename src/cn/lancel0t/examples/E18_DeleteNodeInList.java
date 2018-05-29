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

		// 如果删除的是最后一个元素
		if (toBeDeleted.next == null) {
			// 找到删除节点的前驱
			ListNode currNode = head;
			while (currNode.next != toBeDeleted) {
				currNode = currNode.next;
			}
			// 删除结点
			currNode.next = null;
		} else {
			// 拷贝待删除节点的下个节点的值给删除节点，并删除待删除节点的下个节点
			toBeDeleted.val = toBeDeleted.next.val;
			toBeDeleted.next = toBeDeleted.next.next;
		}

		return head;
	}

	public static void main(String[] args) {

		E18_DeleteNodeInList exam = new E18_DeleteNodeInList();

		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);
		ListNode node4 = new ListNode(4);
		ListNode node5 = new ListNode(5);
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node5;

		ListNode head = exam.deleteNode(node1, node3);
		System.out.print("Result:");
		ListNode.print(head);
		System.out.println("Expect:" + "1 2 4 5");
	}
}
