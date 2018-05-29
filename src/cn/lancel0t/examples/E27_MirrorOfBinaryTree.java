/**
 * 
 * 【剑指Offer】面试题27 ：二叉树的镜像
 * 【  题目描述 】操作给定的二叉树，将其变换为源二叉树的镜像。
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

import cn.lancel0t.utilities.TreeNode;

public class E27_MirrorOfBinaryTree {

	/*
	 * 二叉树的镜像
	 */
	public void Mirror(TreeNode root) {
		MirrorIteratively(root);
	}

	/*
	 * 递归方式
	 */
	public void MirrorRecursively(TreeNode root) {

		if (root == null || (root.left == null && root.right == null))
			return;

		// 镜像根节点
		TreeNode tmp = root.left;
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
	public void MirrorIteratively(TreeNode root) {
		if (root == null)
			return;

		Stack<TreeNode> stackTreeNode = new Stack<TreeNode>();
		stackTreeNode.push(root);

		// 将栈中的节点依次弹出并处理
		while (stackTreeNode.size() > 0) {
			TreeNode node = stackTreeNode.pop();

			// 处理当前节点
			TreeNode tmp = node.left;
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
	private void test(String testName, TreeNode root) {
		try {
			System.out.printf("=====%s=====\n", testName);
			System.out.println("镜像前：");
			TreeNode.printTree(root);
			System.out.println("镜像后：");
			Mirror(root);
			TreeNode.printTree(root);
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
		TreeNode pNode8 = new TreeNode(8);
		TreeNode pNode6 = new TreeNode(6);
		TreeNode pNode10 = new TreeNode(10);
		TreeNode pNode5 = new TreeNode(5);
		TreeNode pNode7 = new TreeNode(7);
		TreeNode pNode9 = new TreeNode(9);
		TreeNode pNode11 = new TreeNode(11);

		TreeNode.connectTreeNodes(pNode8, pNode6, pNode10);
		TreeNode.connectTreeNodes(pNode6, pNode5, pNode7);
		TreeNode.connectTreeNodes(pNode10, pNode9, pNode11);

		test("test1",pNode8);
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
		TreeNode pNode8 = new TreeNode(8);
		TreeNode pNode7 = new TreeNode(7);
		TreeNode pNode6 = new TreeNode(6);
		TreeNode pNode5 = new TreeNode(5);
		TreeNode pNode4 = new TreeNode(4);

		TreeNode.connectTreeNodes(pNode8, pNode7, null);
		TreeNode.connectTreeNodes(pNode7, pNode6, null);
		TreeNode.connectTreeNodes(pNode6, pNode5, null);
		TreeNode.connectTreeNodes(pNode5, pNode4, null);

		test("test2",pNode8);
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
		TreeNode pNode8 = new TreeNode(8);
		TreeNode pNode7 = new TreeNode(7);
		TreeNode pNode6 = new TreeNode(6);
		TreeNode pNode5 = new TreeNode(5);
		TreeNode pNode4 = new TreeNode(4);

		TreeNode.connectTreeNodes(pNode8, null, pNode7);
		TreeNode.connectTreeNodes(pNode7, null, pNode6);
		TreeNode.connectTreeNodes(pNode6, null, pNode5);
		TreeNode.connectTreeNodes(pNode5, null, pNode4);

		test("test3",pNode8);
	}

	/*
	 * 测试空二叉树：根结点为空指针
	 */
	private void test4() {
		TreeNode pNode = null;

		test("test4",pNode);
	}

	/*
	 * 测试只有一个结点的二叉树
	 */
	private void test5() {
		TreeNode pNode8 = new TreeNode(8);

		test("test5",pNode8);
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
