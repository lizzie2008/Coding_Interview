/**
 * 
 * 【剑指Offer】	面试题55 ：二叉树的深度
 * 【题目描述】	输入一棵二叉树的根结点，求该树的深度。
 *  从根结点到叶结点依次经过的结点（含根、叶结点）形成树的一条路径，最长路径的长度为树的深度。
 * 
 * @author lancel0t
 * @date 2018年5月22日
 */
package cn.lancel0t.examples;

import cn.lancel0t.utilities.TreeNode;

public class E55_TreeDepth {

	/*
	 * 二叉树的深度
	 * 思路：如果树只有一个节点，则深度为1，如果只有左子树，深度为左子树加1，同理只有右子树，深度为右子树加1；
	 * 如果既有左子树又有右子树，则取子树相对大的值加1，递归方法很容易实现。
	 */
	public int TreeDepth(TreeNode root) {

		if (root == null)
			return 0;

		int deptLeft = TreeDepth(root.left);
		int deptRight = TreeDepth(root.right);

		return deptLeft > deptRight ? (deptLeft + 1) : (deptRight + 1);
	}

	// ====================测试代码====================
	private void test(String testName, TreeNode root, int expect) {
		try {
			System.out.printf("=====%s=====\n", testName);
			System.out.printf("二叉树的深度:\nResult:%d\nExpect:%d\n", TreeDepth(root), expect);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println();
	}

	/*
	 *        1
	 *     /      \
	 *    2        3
	 *   /\         \
	 *  4  5         6
	 *    /
	 *   7
	 */
	private void test1() {
		TreeNode pNode1 = new TreeNode(1);
		TreeNode pNode2 = new TreeNode(2);
		TreeNode pNode3 = new TreeNode(3);
		TreeNode pNode4 = new TreeNode(4);
		TreeNode pNode5 = new TreeNode(5);
		TreeNode pNode6 = new TreeNode(6);
		TreeNode pNode7 = new TreeNode(7);

		TreeNode.connectTreeNodes(pNode1, pNode2, pNode3);
		TreeNode.connectTreeNodes(pNode2, pNode4, pNode5);
		TreeNode.connectTreeNodes(pNode3, null, pNode6);
		TreeNode.connectTreeNodes(pNode5, pNode7, null);

		test("test1", pNode1, 4);
	}

	/*
	 *         1
	 *        /
	 *       2
	 *      /
	 *     3
	 *    /
	 *   4
	 *  /
	 * 5
	 */
	private void test2() {
		TreeNode pNode1 = new TreeNode(1);
		TreeNode pNode2 = new TreeNode(2);
		TreeNode pNode3 = new TreeNode(3);
		TreeNode pNode4 = new TreeNode(4);
		TreeNode pNode5 = new TreeNode(5);

		TreeNode.connectTreeNodes(pNode1, pNode2, null);
		TreeNode.connectTreeNodes(pNode2, pNode3, null);
		TreeNode.connectTreeNodes(pNode3, pNode4, null);
		TreeNode.connectTreeNodes(pNode4, pNode5, null);

		test("test2", pNode1, 5);
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
		TreeNode pNode1 = new TreeNode(1);
		TreeNode pNode2 = new TreeNode(2);
		TreeNode pNode3 = new TreeNode(3);
		TreeNode pNode4 = new TreeNode(4);
		TreeNode pNode5 = new TreeNode(5);

		TreeNode.connectTreeNodes(pNode1, null, pNode2);
		TreeNode.connectTreeNodes(pNode2, null, pNode3);
		TreeNode.connectTreeNodes(pNode3, null, pNode4);
		TreeNode.connectTreeNodes(pNode4, null, pNode5);

		test("test3", pNode1, 5);
	}

	// 只有1个节点
	private void test4() {
		TreeNode pNode1 = new TreeNode(1);

		test("test4", pNode1, 1);
	}

	// 空树
	private void test5() {
		test("test5", null, 0);
	}

	public static void main(String[] args) {

		E55_TreeDepth exam = new E55_TreeDepth();

		exam.test1();
		exam.test2();
		exam.test3();
		exam.test4();
		exam.test5();
	}
}
