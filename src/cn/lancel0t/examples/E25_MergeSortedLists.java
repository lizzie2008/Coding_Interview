/**
 * 
 * 【剑指Offer】面试题25 ：合并两个排序的链表
 * 【  题目描述 】输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
 * 
 * @author lancel0t
 * @date 2018年5月22日
 */
package cn.lancel0t.examples;

import cn.lancel0t.utilities.ListNode;

public class E25_MergeSortedLists {

	/*
	 * 参考排序--递归思想
	 */
	public ListNode MergeI(ListNode list1, ListNode list2) {

		if (list1 == null)
			return list2;
		else if (list2 == null)
			return list1;

		ListNode mergedList = null;

		if (list1.val < list2.val) {
			mergedList = list1;
			mergedList.next = Merge(list1.next, list2);
		} else {
			mergedList = list2;
			mergedList.next = Merge(list1, list2.next);
		}

		return mergedList;
	}

	/*
	 * 【推荐】非递归--参考合并排序，性能更好
	 */
	public ListNode Merge(ListNode list1, ListNode list2) {
		if (list1 == null)
			return list2;
		else if (list2 == null)
			return list1;

		// 记录合并链表
		ListNode tempNode = new ListNode(-1);
		// 指向合并链表的尾结点
		ListNode node = tempNode;

		// 两链表都不为空，进行比较合并
		while (list1 != null && list2 != null) {
			if (list1.val < list2.val) {
				node.next = list1;
				list1 = list1.next;
			} else {
				node.next = list2;
				list2 = list2.next;
			}
			node = node.next; // 将指针移动到合并后链表的结尾，方便下次
		}

		// 如果第一个链表的元素未处理完，将其接到合并链表的最后一个结点之后
		if (list1 != null) {
			node.next = list1;
		}

		// 如果第二个链表的元素未处理完，将其接到合并链表的最后一个结点之后
		if (list2 != null) {
			node.next = list2;
		}
		return tempNode.next;
	}

	// ====================测试代码====================
	private void test(String testName, ListNode node1, ListNode node2) {
		try {
			System.out.printf("=====%s=====\n", testName);
			System.out.print("链表链表1：");
			ListNode.print(node1);
			System.out.print("链表链表2：");
			ListNode.print(node2);
			System.out.print("合并后链表：");
			ListNode.print(Merge(node1, node2));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println();
	}

	// list1: 1->3->5
	// list2: 2->4->6
	private void test1() {
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);
		ListNode node4 = new ListNode(4);
		ListNode node5 = new ListNode(5);
		ListNode node6 = new ListNode(6);

		node1.next = node3;
		node3.next = node5;
		node2.next = node4;
		node4.next = node6;

		test("test1", node1, node2);
	}

	// list1: 1->3->5
	// list2: 1->3->5
	private void test2() {
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(1);
		ListNode node3 = new ListNode(3);
		ListNode node4 = new ListNode(3);
		ListNode node5 = new ListNode(5);
		ListNode node6 = new ListNode(5);

		node1.next = node3;
		node3.next = node5;
		node2.next = node4;
		node4.next = node6;

		test("test2", node1, node2);
	}

	// list1: 1
	// list2: 2
	private void test3() {
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);

		test("test3", node1, node2);
	}

	// list1: 1
	// list2: ∅
	private void test4() {
		ListNode node1 = new ListNode(1);
		ListNode node2 = null;

		test("test4", node1, node2);
	}

	// list1: ∅
	// list2: ∅
	private void test5() {
		ListNode node1 = null;
		ListNode node2 = null;

		test("test5", node1, node2);
	}

	public static void main(String[] args) {

		E25_MergeSortedLists exam = new E25_MergeSortedLists();

		exam.test1();
		exam.test2();
		exam.test3();
		exam.test4();
		exam.test5();
	}
}
