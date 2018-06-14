/**
 * 
 * 【剑指Offer】	面试题32 ：从上往下打印二叉树
 * 【题目描述】	从上往下打印出二叉树的每个节点，同层节点从左至右打印。
 * 
 * @author lancel0t
 * @date 2018年5月22日
 */
package cn.lancel0t.examples;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import cn.lancel0t.utilities.BinaryTreeNode;

public class E32_PrintTreeFromTopToBottom {

	/*
	 * 从上往下打印二叉树
	 * 分析打印同级元素某节点，打印该节点同时，将该节点的左右子节点保存到队列；
	 * 打印同级元素后，依次打印队列中的保存的下一级子节点即可。
	 */
	public ArrayList<Integer> PrintFromTopToBottom(BinaryTreeNode root) {

		ArrayList<Integer> list = new ArrayList<Integer>();
		if (root == null)
			return list;

		// 辅助队列，保存下一层子节点
		Queue<BinaryTreeNode> queue = new LinkedList<BinaryTreeNode>();
		queue.offer(root);

		while (!queue.isEmpty()) {
			// 弹出需要打印节点
			BinaryTreeNode node = queue.poll();
			list.add(node.val);

			// 将该节点的下一层节点添加到打印队列
			if (node.left != null)
				queue.offer(node.left);
			if (node.right != null)
				queue.offer(node.right);
		}

		return list;
	}

	// ====================测试代码====================
	private void test(String testName, BinaryTreeNode root) {
		try {
			System.out.printf("=====%s=====\n", testName);
			ArrayList<Integer> list = PrintFromTopToBottom(root);
			if (!list.isEmpty()) {
				System.out.print("从上往下打印二叉树:");

				for (int i : list) {
					System.out.print(i + " ");
				}
			} else {
				System.out.print("无可打印的二叉树。");
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("\n");
	}

	/*
	 *       10
	 *    /      \
	 *   6        14
	 *  /\        /\
	 * 4  8     12  16
	 */
	private void test1() {
		BinaryTreeNode pNode10 = new BinaryTreeNode(10);
		BinaryTreeNode pNode6 = new BinaryTreeNode(6);
		BinaryTreeNode pNode14 = new BinaryTreeNode(14);
		BinaryTreeNode pNode4 = new BinaryTreeNode(4);
		BinaryTreeNode pNode8 = new BinaryTreeNode(8);
		BinaryTreeNode pNode12 = new BinaryTreeNode(12);
		BinaryTreeNode pNode16 = new BinaryTreeNode(16);

		BinaryTreeNode.connectTreeNodes(pNode10, pNode6, pNode14);
		BinaryTreeNode.connectTreeNodes(pNode6, pNode4, pNode8);
		BinaryTreeNode.connectTreeNodes(pNode14, pNode12, pNode16);

		test("test1", pNode10);
	}

	/*
	 *          5
	 *         /
	 *        4
	 *       /
	 *      3
	 *     /
	 *    2
	 *   /
	 *  1
	 */
	private void test2() {
		BinaryTreeNode pNode5 = new BinaryTreeNode(5);
		BinaryTreeNode pNode4 = new BinaryTreeNode(4);
		BinaryTreeNode pNode3 = new BinaryTreeNode(3);
		BinaryTreeNode pNode2 = new BinaryTreeNode(2);
		BinaryTreeNode pNode1 = new BinaryTreeNode(1);

		BinaryTreeNode.connectTreeNodes(pNode5, pNode4, null);
		BinaryTreeNode.connectTreeNodes(pNode4, pNode3, null);
		BinaryTreeNode.connectTreeNodes(pNode3, pNode2, null);
		BinaryTreeNode.connectTreeNodes(pNode2, pNode1, null);

		test("test2", pNode5);
	}

	/*
	 * 1
	 *  \
	 *   2
	 *    \
	 *     3
	 *      \
	 *       4
	 *        \
	 *         5
	 */
	private void test3() {
		BinaryTreeNode pNode1 = new BinaryTreeNode(1);
		BinaryTreeNode pNode2 = new BinaryTreeNode(2);
		BinaryTreeNode pNode3 = new BinaryTreeNode(3);
		BinaryTreeNode pNode4 = new BinaryTreeNode(4);
		BinaryTreeNode pNode5 = new BinaryTreeNode(5);

		BinaryTreeNode.connectTreeNodes(pNode1, pNode2, null);
		BinaryTreeNode.connectTreeNodes(pNode2, pNode3, null);
		BinaryTreeNode.connectTreeNodes(pNode3, pNode4, null);
		BinaryTreeNode.connectTreeNodes(pNode4, pNode5, null);

		test("test3", pNode1);
	}

	// 树中只有1个结点
	private void test4() {
		BinaryTreeNode pNode1 = new BinaryTreeNode(1);
		test("test4", pNode1);
	}

	// 树中没有结点
	private void test5() {
		test("test5", null);
	}

	public static void main(String[] args) {

		E32_PrintTreeFromTopToBottom exam = new E32_PrintTreeFromTopToBottom();

		exam.test1();
		exam.test2();
		exam.test3();
		exam.test4();
		exam.test5();
	}
}
