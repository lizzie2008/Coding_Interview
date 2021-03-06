/**
 * 
 * 【剑指Offer】	面试题54 ：二叉搜索树的第k个结点
 * 【题目描述】	给定一棵二叉搜索树，请找出其中的第k大的结点。
 * 
 * @author lancel0t
 * @date 2018年5月22日
 */
package cn.lancel0t.examples;

import java.util.Stack;
import cn.lancel0t.utilities.BinaryTreeNode;

public class E54_KthNodeInBST {

	/*
	 * 二叉搜索树的第k个结点
	 * 思路：二叉搜索树的一个重要性质就是它的中序遍历是排序的，所以我们通过非递归遍历二叉树，使用辅助栈，
	 * 每次访问根节点时，计数加1，直到计数等于k时，当前结点就是要求的结点。
	 */
	public BinaryTreeNode KthNode(BinaryTreeNode pRoot, int k) {

		if (pRoot == null || k <= 0)
			return null;

		int index = 0;
		BinaryTreeNode kthNode = null;
		BinaryTreeNode node = pRoot;
		Stack<BinaryTreeNode> stack = new Stack<BinaryTreeNode>();
		while (node != null || !stack.isEmpty()) {
			if (node != null) {
				stack.push(node);
				node = node.left;
			} else {
				node = stack.pop();
				index++;
				// 当遍历到第k个节点时，跳出循环
				if (index == k) {
					kthNode = node;
					break;
				}
				node = node.right;
			}
		}
		return kthNode;
	}

	// ====================测试代码====================
	private void test(String testName, BinaryTreeNode pRoot, int k, int expect) {
		try {
			System.out.printf("=====%s=====\n", testName);
			BinaryTreeNode node = KthNode(pRoot, k);
			System.out.printf("二叉搜索树的第%d个结点:\nResult:%s\nExpect:%d\n", k, node == null ? "-1" : node.val,
					expect);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println();
	}

	/*
	 *      8
	 *  6      10
	 * 5 7    9  11
	 */
	private void testA() {
		BinaryTreeNode pNode8 = new BinaryTreeNode(8);
		BinaryTreeNode pNode6 = new BinaryTreeNode(6);
		BinaryTreeNode pNode10 = new BinaryTreeNode(10);
		BinaryTreeNode pNode5 = new BinaryTreeNode(5);
		BinaryTreeNode pNode7 = new BinaryTreeNode(7);
		BinaryTreeNode pNode9 = new BinaryTreeNode(9);
		BinaryTreeNode pNode11 = new BinaryTreeNode(11);

		BinaryTreeNode.connectTreeNodes(pNode8, pNode6, pNode10);
		BinaryTreeNode.connectTreeNodes(pNode6, pNode5, pNode7);
		BinaryTreeNode.connectTreeNodes(pNode10, pNode9, pNode11);

		test("testA0", pNode8, 0, -1);
		test("testA1", pNode8, 1, 5);
		test("testA2", pNode8, 2, 6);
		test("testA3", pNode8, 3, 7);
		test("testA4", pNode8, 4, 8);
		test("testA5", pNode8, 5, 9);
		test("testA6", pNode8, 6, 10);
		test("testA7", pNode8, 7, 11);
		test("testA8", pNode8, 8, -1);

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
	private void testB() {
		BinaryTreeNode pNode5 = new BinaryTreeNode(5);
		BinaryTreeNode pNode4 = new BinaryTreeNode(4);
		BinaryTreeNode pNode3 = new BinaryTreeNode(3);
		BinaryTreeNode pNode2 = new BinaryTreeNode(2);
		BinaryTreeNode pNode1 = new BinaryTreeNode(1);

		BinaryTreeNode.connectTreeNodes(pNode5, pNode4, null);
		BinaryTreeNode.connectTreeNodes(pNode4, pNode3, null);
		BinaryTreeNode.connectTreeNodes(pNode3, pNode2, null);
		BinaryTreeNode.connectTreeNodes(pNode2, pNode1, null);

		test("testB0", pNode5, 0, -1);
		test("testB1", pNode5, 1, 1);
		test("testB2", pNode5, 2, 2);
		test("testB3", pNode5, 3, 3);
		test("testB4", pNode5, 4, 4);
		test("testB5", pNode5, 5, 5);
		test("testB6", pNode5, 6, -1);
	}

	/*
	// 1
	//  \
	//   2
	//    \
	//     3
	//      \
	//       4
	//        \
	//         5
	 */
	private void testC() {
		BinaryTreeNode pNode1 = new BinaryTreeNode(1);
		BinaryTreeNode pNode2 = new BinaryTreeNode(2);
		BinaryTreeNode pNode3 = new BinaryTreeNode(3);
		BinaryTreeNode pNode4 = new BinaryTreeNode(4);
		BinaryTreeNode pNode5 = new BinaryTreeNode(5);

		BinaryTreeNode.connectTreeNodes(pNode1, null, pNode2);
		BinaryTreeNode.connectTreeNodes(pNode2, null, pNode3);
		BinaryTreeNode.connectTreeNodes(pNode3, null, pNode4);
		BinaryTreeNode.connectTreeNodes(pNode4, null, pNode5);

		test("testC0", pNode1, 0, -1);
		test("testC1", pNode1, 1, 1);
		test("testC2", pNode1, 2, 2);
		test("testC3", pNode1, 3, 3);
		test("testC4", pNode1, 4, 4);
		test("testC5", pNode1, 5, 5);
		test("testC6", pNode1, 6, -1);
	}

	// 只有1个节点
	private void testD() {
		BinaryTreeNode pNode1 = new BinaryTreeNode(1);

		test("testD0", pNode1, 0, -1);
		test("testD1", pNode1, 1, 1);
		test("testD2", pNode1, 2, -1);
	}

	// 空树
	private void testE() {
		test("testE0", null, 0, -1);
		test("testE1", null, 1, -1);
	}

	public static void main(String[] args) {

		E54_KthNodeInBST exam = new E54_KthNodeInBST();

		exam.testA();
		exam.testB();
		exam.testC();
		exam.testD();
		exam.testE();
	}
}
