/**
 * 
 * 【剑指Offer】	面试题27 ：二叉树的镜像
 * 【题目描述】	操作给定的二叉树，将其变换为源二叉树的镜像。
 *    	8           	    8
 * 	   /  \         	   /  \
 * 	  6   10     ==>   	  10   6
 * 	 / \  / \       	 / \  / \
 * 	5  7 9  11      	11 9 7   5
 * 
 * @author lancel0t
 * @date 2018年5月22日
 */
package cn.lancel0t.examples;

import java.util.Stack;

import cn.lancel0t.utilities.BinaryTreeNode;

public class E27_MirrorOfBinaryTree {

	/*
	 * 二叉树的镜像
	 */
	public void Mirror(BinaryTreeNode root) {
		MirrorIteratively(root);
	}

	/*
	 * 递归方式
	 */
	public void MirrorRecursively(BinaryTreeNode root) {

		if (root == null || (root.left == null && root.right == null))
			return;

		// 镜像根节点
		BinaryTreeNode tmp = root.left;
		root.left = root.right;
		root.right = tmp;

		// 镜像左子树
		if (root.left != null)
			MirrorRecursively(root.left);
		// 镜像右子树
		if (root.right != null)
			MirrorRecursively(root.right);
	}

	/*
	 * 迭代方式
	 */
	public void MirrorIteratively(BinaryTreeNode root) {
		if (root == null)
			return;

		Stack<BinaryTreeNode> stackTreeNode = new Stack<BinaryTreeNode>();
		stackTreeNode.push(root);

		// 将栈中的节点依次弹出并处理
		while (stackTreeNode.size() > 0) {
			BinaryTreeNode node = stackTreeNode.pop();

			// 处理当前节点
			BinaryTreeNode tmp = node.left;
			node.left = node.right;
			node.right = tmp;

			// 将左孩子压栈，作为要处理的元素
			if (node.left != null)
				stackTreeNode.push(node.left);
			// 将右孩子压栈，作为要处理的元素
			if (node.right != null)
				stackTreeNode.push(node.right);
		}
	}

	// ====================测试代码====================
	private void test(String testName, BinaryTreeNode root) {
		try {
			System.out.printf("=====%s=====\n", testName);
			System.out.println("镜像前：");
			BinaryTreeNode.printTree(root);
			System.out.println("镜像后：");
			Mirror(root);
			BinaryTreeNode.printTree(root);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println();
	}

	/*
	 * 测试完全二叉树：除了叶子节点，其他节点都有两个子节点
	 *            8
	 *        6      10
	 *       5 7    9  11
	 */
	private void test1() {
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

		test("test1", pNode8);
	}

	/*
	 * 测试二叉树：出叶子结点之外，左右的结点都有且只有一个左子结点
	 *         8
	 *       7   
	 *     6 
	 *   5
	 * 4
	 */
	private void test2() {
		BinaryTreeNode pNode8 = new BinaryTreeNode(8);
		BinaryTreeNode pNode7 = new BinaryTreeNode(7);
		BinaryTreeNode pNode6 = new BinaryTreeNode(6);
		BinaryTreeNode pNode5 = new BinaryTreeNode(5);
		BinaryTreeNode pNode4 = new BinaryTreeNode(4);

		BinaryTreeNode.connectTreeNodes(pNode8, pNode7, null);
		BinaryTreeNode.connectTreeNodes(pNode7, pNode6, null);
		BinaryTreeNode.connectTreeNodes(pNode6, pNode5, null);
		BinaryTreeNode.connectTreeNodes(pNode5, pNode4, null);

		test("test2", pNode8);
	}

	/*
	 * 测试二叉树：出叶子结点之外，左右的结点都有且只有一个右子结点
	 *  8
	 *   7   
	 *    6 
	 *     5
	 *      4
	 */
	private void test3() {
		BinaryTreeNode pNode8 = new BinaryTreeNode(8);
		BinaryTreeNode pNode7 = new BinaryTreeNode(7);
		BinaryTreeNode pNode6 = new BinaryTreeNode(6);
		BinaryTreeNode pNode5 = new BinaryTreeNode(5);
		BinaryTreeNode pNode4 = new BinaryTreeNode(4);

		BinaryTreeNode.connectTreeNodes(pNode8, null, pNode7);
		BinaryTreeNode.connectTreeNodes(pNode7, null, pNode6);
		BinaryTreeNode.connectTreeNodes(pNode6, null, pNode5);
		BinaryTreeNode.connectTreeNodes(pNode5, null, pNode4);

		test("test3", pNode8);
	}

	/*
	 * 测试空二叉树：根结点为空指针
	 */
	private void test4() {
		BinaryTreeNode pNode = null;

		test("test4", pNode);
	}

	/*
	 * 测试只有一个结点的二叉树
	 */
	private void test5() {
		BinaryTreeNode pNode8 = new BinaryTreeNode(8);

		test("test5", pNode8);
	}

	public static void main(String[] args) {

		E27_MirrorOfBinaryTree exam = new E27_MirrorOfBinaryTree();
		exam.test1();
		exam.test2();
		exam.test3();
		exam.test4();
		exam.test5();
	}
}
