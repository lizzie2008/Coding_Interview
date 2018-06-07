/**
 * 
 * 【剑指Offer】	面试题28 ：对称的二叉树
 * 【题目描述】	请实现一个函数，用来判断一颗二叉树是不是对称的。注意，如果一个二叉树同此二叉树的镜像是同样的，定义其为对称的。
 * 
 * @author lancel0t
 * @date 2018年5月22日
 */
package cn.lancel0t.examples;

import cn.lancel0t.utilities.TreeNode;

public class E28_SymmetricalBinaryTree {

	/*
	 * 判断一颗二叉树是不是对称
	 */
	public boolean isSymmetrical(TreeNode pRoot) {
		if (pRoot == null)
			return true;

		return isSymmetrical(pRoot.left, pRoot.right);
	}

	private boolean isSymmetrical(TreeNode root1, TreeNode root2) {

		// A为空，B为空
		if (root1 == null && root2 == null)
			return true;
		// A,B其中只要有个为空，且不都为空
		if (root1 == null || root2 == null)
			return false;

		// A,B都不为空

		// A,B节点值不相等
		if (root1.val != root2.val)
			return false;

		// A,B节点值相等，再去判断子树是否对称
		return isSymmetrical(root1.left, root2.right) && isSymmetrical(root1.right, root2.left);
	}

	// ====================测试代码====================
	private void test(String testName, TreeNode root, boolean expect) {
		try {
			System.out.printf("=====%s=====\n", testName);
			System.out.printf("是否对称的二叉树：Result:%b \t Expect:%b\n", isSymmetrical(root), expect);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println();
	}

	/*
	 *         8
	 *     6      6
	 *    5 7    7 5
	 */
	private void test1() {

		TreeNode pNode8 = new TreeNode(8);
		TreeNode pNode61 = new TreeNode(6);
		TreeNode pNode62 = new TreeNode(6);
		TreeNode pNode51 = new TreeNode(5);
		TreeNode pNode71 = new TreeNode(7);
		TreeNode pNode72 = new TreeNode(7);
		TreeNode pNode52 = new TreeNode(5);

		TreeNode.connectTreeNodes(pNode8, pNode61, pNode62);
		TreeNode.connectTreeNodes(pNode61, pNode51, pNode71);
		TreeNode.connectTreeNodes(pNode62, pNode72, pNode52);

		test("test1", pNode8, true);
	}

	/*
	 *         8
	 *     6      9
	 *    5 7    7 5
	 */
	private void test2() {

		TreeNode pNode8 = new TreeNode(8);
		TreeNode pNode61 = new TreeNode(6);
		TreeNode pNode9 = new TreeNode(9);
		TreeNode pNode51 = new TreeNode(5);
		TreeNode pNode71 = new TreeNode(7);
		TreeNode pNode72 = new TreeNode(7);
		TreeNode pNode52 = new TreeNode(5);

		TreeNode.connectTreeNodes(pNode8, pNode61, pNode9);
		TreeNode.connectTreeNodes(pNode61, pNode51, pNode71);
		TreeNode.connectTreeNodes(pNode9, pNode72, pNode52);

		test("test2", pNode8, false);
	}

	/*
	 *         8
	 *     6      6
	 *    5 7    7 
	 */
	private void test3() {

		TreeNode pNode8 = new TreeNode(8);
		TreeNode pNode61 = new TreeNode(6);
		TreeNode pNode62 = new TreeNode(6);
		TreeNode pNode51 = new TreeNode(5);
		TreeNode pNode71 = new TreeNode(7);
		TreeNode pNode72 = new TreeNode(7);

		TreeNode.connectTreeNodes(pNode8, pNode61, pNode62);
		TreeNode.connectTreeNodes(pNode61, pNode51, pNode71);
		TreeNode.connectTreeNodes(pNode62, pNode72, null);

		test("test3", pNode8, false);
	}

	/*
	 *          5
	 *         / \
	 *        3   3
	 *       /     \
	 *      4       4
	 *     /         \
	 *    2           2
	 *   /             \
	 *  1               1
	 */
	private void test4() {

		TreeNode pNode5 = new TreeNode(5);
		TreeNode pNode31 = new TreeNode(3);
		TreeNode pNode32 = new TreeNode(3);
		TreeNode pNode41 = new TreeNode(4);
		TreeNode pNode42 = new TreeNode(4);
		TreeNode pNode21 = new TreeNode(2);
		TreeNode pNode22 = new TreeNode(2);
		TreeNode pNode11 = new TreeNode(1);
		TreeNode pNode12 = new TreeNode(1);

		TreeNode.connectTreeNodes(pNode5, pNode31, pNode32);
		TreeNode.connectTreeNodes(pNode31, pNode41, null);
		TreeNode.connectTreeNodes(pNode32, null, pNode42);
		TreeNode.connectTreeNodes(pNode41, pNode21, null);
		TreeNode.connectTreeNodes(pNode42, null, pNode22);
		TreeNode.connectTreeNodes(pNode21, pNode11, null);
		TreeNode.connectTreeNodes(pNode22, null, pNode12);

		test("test4", pNode5, true);
	}

	/*
	 *          5
	 *         / \
	 *        3   3
	 *       /     \
	 *      4       4
	 *     /         \
	 *    6           2
	 *   /             \
	 *  1               1
	 */
	private void test5() {

		TreeNode pNode5 = new TreeNode(5);
		TreeNode pNode31 = new TreeNode(3);
		TreeNode pNode32 = new TreeNode(3);
		TreeNode pNode41 = new TreeNode(4);
		TreeNode pNode42 = new TreeNode(4);
		TreeNode pNode6 = new TreeNode(6);
		TreeNode pNode22 = new TreeNode(2);
		TreeNode pNode11 = new TreeNode(1);
		TreeNode pNode12 = new TreeNode(1);

		TreeNode.connectTreeNodes(pNode5, pNode31, pNode32);
		TreeNode.connectTreeNodes(pNode31, pNode41, null);
		TreeNode.connectTreeNodes(pNode32, null, pNode42);
		TreeNode.connectTreeNodes(pNode41, pNode6, null);
		TreeNode.connectTreeNodes(pNode42, null, pNode22);
		TreeNode.connectTreeNodes(pNode6, pNode11, null);
		TreeNode.connectTreeNodes(pNode22, null, pNode12);

		test("test5", pNode5, false);
	}

	/*
	 *          5
	 *         / \
	 *        3   3
	 *       /     \
	 *      4       4
	 *     /         \
	 *    2           2
	 *                 \
	 *                  1
	 */
	private void test6() {

		TreeNode pNode5 = new TreeNode(5);
		TreeNode pNode31 = new TreeNode(3);
		TreeNode pNode32 = new TreeNode(3);
		TreeNode pNode41 = new TreeNode(4);
		TreeNode pNode42 = new TreeNode(4);
		TreeNode pNode21 = new TreeNode(2);
		TreeNode pNode22 = new TreeNode(2);
		TreeNode pNode11 = new TreeNode(1);

		TreeNode.connectTreeNodes(pNode5, pNode31, pNode32);
		TreeNode.connectTreeNodes(pNode31, pNode41, null);
		TreeNode.connectTreeNodes(pNode32, null, pNode42);
		TreeNode.connectTreeNodes(pNode41, pNode21, null);
		TreeNode.connectTreeNodes(pNode42, null, pNode22);
		TreeNode.connectTreeNodes(pNode21, pNode11, null);

		test("test6", pNode5, false);
	}

	// 只有一个结点
	private void test7() {
		TreeNode pNode = new TreeNode(1);
		test("test7", pNode, true);
	}

	// 只有一个结点
	private void test8() {
		test("test8", null, true);
	}

	/*
	 *          5
	 *         / \
	 *        5   5
	 *       /     \
	 *      5       5
	 *     /         \
	 *    5           5
	 */
	private void test9() {

		TreeNode pNode1 = new TreeNode(5);
		TreeNode pNode21 = new TreeNode(5);
		TreeNode pNode22 = new TreeNode(5);
		TreeNode pNode31 = new TreeNode(5);
		TreeNode pNode32 = new TreeNode(5);
		TreeNode pNode41 = new TreeNode(5);
		TreeNode pNode42 = new TreeNode(5);

		TreeNode.connectTreeNodes(pNode1, pNode21, pNode22);
		TreeNode.connectTreeNodes(pNode21, pNode31, null);
		TreeNode.connectTreeNodes(pNode22, null, pNode32);
		TreeNode.connectTreeNodes(pNode31, pNode41, null);
		TreeNode.connectTreeNodes(pNode32, null, pNode42);
		TreeNode.connectTreeNodes(pNode41, null, null);
		TreeNode.connectTreeNodes(pNode42, null, null);

		test("test9", pNode1, true);
	}

	public static void main(String[] args) {

		E28_SymmetricalBinaryTree exam = new E28_SymmetricalBinaryTree();

		exam.test1();
		exam.test2();
		exam.test3();
		exam.test4();
		exam.test5();
		exam.test6();
		exam.test7();
		exam.test8();
		exam.test9();
	}
}
