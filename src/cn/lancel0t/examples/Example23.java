/**
 * 
 * 【剑指Offer】面试题23 ：链表中环的入口结点
 * 【  题目描述 】一个链表中包含环，如何找出环的入口结点？
 * 
 * @author lancel0t
 * @date 2018年5月22日
 */
package cn.lancel0t.examples;

import cn.lancel0t.utilities.ListNode;

public class Example23 {

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

		if (pHead.next == null)
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

	public static void main(String[] args) {

		Example23 exam = new Example23();

		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);
		ListNode node4 = new ListNode(4);
		ListNode node5 = new ListNode(5);

		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node5;

		node5.next = node3;
		System.out.println("=====Test1=====");
		System.out.printf("环的入口结点是：%s [3]\n\n",
				exam.EntryNodeOfLoop(node1) == null ? "null" : exam.EntryNodeOfLoop(node1).val);

		node5.next = node1;
		System.out.println("=====Test2=====");
		System.out.printf("环的入口结点是：%s [1]\n\n",
				exam.EntryNodeOfLoop(node1) == null ? "null" : exam.EntryNodeOfLoop(node1).val);

		node5.next = node5;
		System.out.println("=====Test3=====");
		System.out.printf("环的入口结点是：%s [5]\n\n",
				exam.EntryNodeOfLoop(node1) == null ? "null" : exam.EntryNodeOfLoop(node1).val);

		node5.next = null;
		System.out.println("=====Test4====");
		System.out.printf("环的入口结点是：%s [null]\n\n",
				exam.EntryNodeOfLoop(node1) == null ? "null" : exam.EntryNodeOfLoop(node1).val);

		System.out.println("=====Test5====");
		System.out.printf("环的入口结点是：%s [null]\n\n",
				exam.EntryNodeOfLoop(node1) == null ? "null" : exam.EntryNodeOfLoop(null).val);
	}
}
