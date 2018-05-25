/**
 * 
 * 【剑指Offer】面试题6 ： 从尾到头打印链表
 * 【  题目描述 】输入个链表的头结点，从尾到头反过来打印出每个结点的值。
 * 
 * @author lancel0t
 * @date 2018年5月22日
 */
package cn.lancel0t;

import java.util.ArrayList;

public class Example06 {

	public class ListNode {
		int val;
		ListNode next = null;

		ListNode(int val) {
			this.val = val;
		}
	}

	public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		addPrintList(listNode, list);
		return list;
	}

	// 递归
	private void addPrintList(ListNode listNode, ArrayList<Integer> list) {
		if (listNode != null) {
			if (listNode.next != null) {
				addPrintList(listNode.next, list);
			}
			list.add(listNode.val);
		}
	}

	public static void main(String[] args) {
		Example06 exam = new Example06();
		ListNode node1 = exam.new ListNode(1);
		ListNode node2 = exam.new ListNode(2);
		ListNode node3 = exam.new ListNode(3);
		ListNode node4 = exam.new ListNode(4);
		ListNode node5 = exam.new ListNode(5);
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node5;
		node1.next = node2;
		for (Integer val : exam.printListFromTailToHead(node1)) {
			System.out.print(val + " ");
		}
		System.out.print("[5 4 3 2 1]");
	}
}
