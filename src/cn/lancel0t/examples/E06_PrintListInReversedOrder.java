/**
 * 
 * 【剑指Offer】面试题6 ： 从尾到头打印链表
 * 【  题目描述 】输入个链表的头结点，从尾到头反过来打印出每个结点的值。
 * 
 * @author lancel0t
 * @date 2018年5月22日
 */
package cn.lancel0t.examples;

import java.util.ArrayList;

import cn.lancel0t.utilities.ListNode;

public class E06_PrintListInReversedOrder {

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

	// ====================测试代码====================
	private void test(String testName, ListNode node, String expect) {
		System.out.printf("%s\n从尾到头打印链表：Result:", testName);
		ArrayList<Integer> list = printListFromTailToHead(node);
		if (list.isEmpty()) {
			System.out.print("∅");
		} else {
			for (Integer val : list) {
				System.out.print(val + " ");
			}
		}
		System.out.printf(" \tExpect:%s", expect);
		System.out.println("\n");
	}

	// 1->2->3->4->5
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

		test("=====Test1=====", pNode1, "5 4 3 2 1");
	}

	// 只有一个结点的链表: 1
	private void test2() {
		ListNode pNode1 = new ListNode(1);
		test("=====Test2=====", pNode1, "1");
	}

	// 空链表
	private void test3() {
		test("=====Test3=====", null, "∅");
	}

	public static void main(String[] args) {
		E06_PrintListInReversedOrder exam = new E06_PrintListInReversedOrder();

		exam.test1();
		exam.test2();
		exam.test3();
	}
}
