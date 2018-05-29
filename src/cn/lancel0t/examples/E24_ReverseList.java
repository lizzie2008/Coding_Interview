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

	public static void main(String[] args) {

		E24_ReverseList exam = new E24_ReverseList();

		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);
		ListNode node4 = new ListNode(4);
		ListNode node5 = new ListNode(5);

		// 多个节点测试
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node5;
		System.out.println("=====Test1=====");
		System.out.println("原始链表：");
		ListNode.print(node1);
		System.out.println("逆转链表：");
		ListNode.print(exam.ReverseList(node1));
		System.out.println();

		// 空节点测试
		System.out.println("=====Test2=====");
		System.out.printf("逆转链表：%s\n", exam.ReverseList(null) == null ? "null" : exam.ReverseList(node1));
		System.out.println();
		
		// 单节点测试
		node1.next=null;
		System.out.println("=====Test3=====");
		System.out.println("原始链表：");
		ListNode.print(node1);
		System.out.println("逆转链表：");
		ListNode.print(exam.ReverseList(node1));
		System.out.println();
	}
}
