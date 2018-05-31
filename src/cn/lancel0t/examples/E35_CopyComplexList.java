/**
 * 
 * 【剑指Offer】	面试题35 ：复杂链表的复制
 * 【题目描述】	输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，另一个特殊指针指向任意一个节点），
 * 返回结果为复制后复杂链表的head。（注意，输出结果中请不要返回参数中的节点引用，否则判题程序会直接返回空）
 * 
 * @author lancel0t
 * @date 2018年5月22日
 */
package cn.lancel0t.examples;

import cn.lancel0t.utilities.RandomListNode;

public class E35_CopyComplexList {

	/*
	 * 复杂链表的复制
	 * 思路：在不用辅助空间的情况下实现：
	 * 1.根据原始链表的每个节点N，创建一个复制的节点N'，并将N'链接在N之后，形成新的链表；
	 * 2.遍历新链表的奇数元素，如果random指针不为空，将该节点之后的复制节点的random指针指向random所指元素之后的元素；
	 * 3.拆分新链表的奇数元素和偶数元素，形成原链表和复制后的链表。
	 */
	public RandomListNode Clone(RandomListNode pHead) {

		if (pHead == null)
			return null;

		// 实现步骤1
		RandomListNode currNode = pHead;
		while (currNode != null) {
			RandomListNode clonedNode = new RandomListNode(currNode.label);
			clonedNode.next = currNode.next;
			currNode.next = clonedNode;
			currNode = clonedNode.next;
		}

		// 实现步骤2
		currNode = pHead;
		while (currNode != null) {
			if (currNode.random != null)
				currNode.next.random = currNode.random.next;
			currNode = currNode.next.next;
		}

		// 实现步骤3
		RandomListNode clonedHead = new RandomListNode(-1);
		RandomListNode currCloneNode = clonedHead;
		currNode = pHead;
		while (currNode != null) {
			currCloneNode.next = currNode.next;
			currCloneNode = currCloneNode.next;
			currNode.next = currCloneNode.next;
			currNode = currNode.next;
		}

		return clonedHead.next;
	}

	// ====================测试代码====================
	private void test(String testName, RandomListNode head) {
		try {
			System.out.printf("=====%s=====\n", testName);
			RandomListNode cloneHead = Clone(head);
			if (cloneHead != null) {
				System.out.println("原链表：");
				RandomListNode.PrintList(head);
				System.out.println("==========");
				System.out.println("克隆链表：");
				RandomListNode.PrintList(cloneHead);
			} else {
				System.out.println("链表为空！");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println();
	}

	/*
	 *          -----------------
	 *         \|/              |
	 *  1-------2-------3-------4-------5
	 *  |       |      /|\             /|\
	 *  --------+--------               |
	 *          -------------------------   
	 */
	private void test1() {
		RandomListNode pNode1 = new RandomListNode(1);
		RandomListNode pNode2 = new RandomListNode(2);
		RandomListNode pNode3 = new RandomListNode(3);
		RandomListNode pNode4 = new RandomListNode(4);
		RandomListNode pNode5 = new RandomListNode(5);

		RandomListNode.connectListNodes(pNode1, pNode2, pNode3);
		RandomListNode.connectListNodes(pNode2, pNode3, pNode5);
		RandomListNode.connectListNodes(pNode3, pNode4, null);
		RandomListNode.connectListNodes(pNode4, pNode5, pNode2);

		test("test1", pNode1);
	}

	/*
	 * random指向结点自身
	 *          -----------------
	 *         \|/              |
	 *  1-------2-------3-------4-------5
	 *         |       | /|\           /|\
	 *         |       | --             |
	 *         |------------------------| 
	 */
	private void test2() {
		RandomListNode pNode1 = new RandomListNode(1);
		RandomListNode pNode2 = new RandomListNode(2);
		RandomListNode pNode3 = new RandomListNode(3);
		RandomListNode pNode4 = new RandomListNode(4);
		RandomListNode pNode5 = new RandomListNode(5);

		RandomListNode.connectListNodes(pNode1, pNode2, null);
		RandomListNode.connectListNodes(pNode2, pNode3, pNode5);
		RandomListNode.connectListNodes(pNode3, pNode4, pNode3);
		RandomListNode.connectListNodes(pNode4, pNode5, pNode2);

		test("test2", pNode1);
	}

	/*
	 * random形成环
	 *          -----------------
	 *         \|/              |
	 *  1-------2-------3-------4-------5
	 *          |              /|\
	 *          |               |
	 *          |---------------|
	 */
	private void test3() {
		RandomListNode pNode1 = new RandomListNode(1);
		RandomListNode pNode2 = new RandomListNode(2);
		RandomListNode pNode3 = new RandomListNode(3);
		RandomListNode pNode4 = new RandomListNode(4);
		RandomListNode pNode5 = new RandomListNode(5);

		RandomListNode.connectListNodes(pNode1, pNode2, null);
		RandomListNode.connectListNodes(pNode2, pNode3, pNode4);
		RandomListNode.connectListNodes(pNode3, pNode4, null);
		RandomListNode.connectListNodes(pNode4, pNode5, pNode2);

		test("test3", pNode1);
	}

	// 只有一个结点
	private void test4() {
		RandomListNode pNode1 = new RandomListNode(1);
		RandomListNode.connectListNodes(pNode1, null, pNode1);

		test("test4", pNode1);
	}

	// 鲁棒性测试
	private void test5() {
		test("test5", null);
	}

	public static void main(String[] args) {

		E35_CopyComplexList exam = new E35_CopyComplexList();

		exam.test1();
		exam.test2();
		exam.test3();
		exam.test4();
		exam.test5();
	}
}
