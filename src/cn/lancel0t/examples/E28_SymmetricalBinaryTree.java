/**
 * 
 * 【剑指Offer】	面试题28 ：对称的二叉树
 * 【题目描述】	请实现一个函数，用来判断一颗二叉树是不是对称的。注意，如果一个二叉树同此二叉树的镜像是同样的，定义其为对称的。
 * 
 * @author lancel0t
 * @date 2018年5月22日
 */
package cn.lancel0t.examples;

import cn.lancel0t.utilities.BinaryTreeNode;

public class E28_SymmetricalBinaryTree {

	/*
	 * 判断一颗二叉树是不是对称
	 */
	public boolean isSymmetrical(BinaryTreeNode pRoot) {
		if (pRoot == null)
			return true;

		return isSymmetrical(pRoot.left, pRoot.right);
	}

	private boolean isSymmetrical(BinaryTreeNode root1, BinaryTreeNode root2) {

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
	private void test(String testName, BinaryTreeNode root, boolean expect) {
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

		BinaryTreeNode pNode8 = new BinaryTreeNode(8);
		BinaryTreeNode pNode61 = new BinaryTreeNode(6);
		BinaryTreeNode pNode62 = new BinaryTreeNode(6);
		BinaryTreeNode pNode51 = new BinaryTreeNode(5);
		BinaryTreeNode pNode71 = new BinaryTreeNode(7);
		BinaryTreeNode pNode72 = new BinaryTreeNode(7);
		BinaryTreeNode pNode52 = new BinaryTreeNode(5);

		BinaryTreeNode.connectTreeNodes(pNode8, pNode61, pNode62);
		BinaryTreeNode.connectTreeNodes(pNode61, pNode51, pNode71);
		BinaryTreeNode.connectTreeNodes(pNode62, pNode72, pNode52);

		test("test1", pNode8, true);
	}

	/*
	 *         8
	 *     6      9
	 *    5 7    7 5
	 */
	private void test2() {

		BinaryTreeNode pNode8 = new BinaryTreeNode(8);
		BinaryTreeNode pNode61 = new BinaryTreeNode(6);
		BinaryTreeNode pNode9 = new BinaryTreeNode(9);
		BinaryTreeNode pNode51 = new BinaryTreeNode(5);
		BinaryTreeNode pNode71 = new BinaryTreeNode(7);
		BinaryTreeNode pNode72 = new BinaryTreeNode(7);
		BinaryTreeNode pNode52 = new BinaryTreeNode(5);

		BinaryTreeNode.connectTreeNodes(pNode8, pNode61, pNode9);
		BinaryTreeNode.connectTreeNodes(pNode61, pNode51, pNode71);
		BinaryTreeNode.connectTreeNodes(pNode9, pNode72, pNode52);

		test("test2", pNode8, false);
	}

	/*
	 *         8
	 *     6      6
	 *    5 7    7 
	 */
	private void test3() {

		BinaryTreeNode pNode8 = new BinaryTreeNode(8);
		BinaryTreeNode pNode61 = new BinaryTreeNode(6);
		BinaryTreeNode pNode62 = new BinaryTreeNode(6);
		BinaryTreeNode pNode51 = new BinaryTreeNode(5);
		BinaryTreeNode pNode71 = new BinaryTreeNode(7);
		BinaryTreeNode pNode72 = new BinaryTreeNode(7);

		BinaryTreeNode.connectTreeNodes(pNode8, pNode61, pNode62);
		BinaryTreeNode.connectTreeNodes(pNode61, pNode51, pNode71);
		BinaryTreeNode.connectTreeNodes(pNode62, pNode72, null);

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

		BinaryTreeNode pNode5 = new BinaryTreeNode(5);
		BinaryTreeNode pNode31 = new BinaryTreeNode(3);
		BinaryTreeNode pNode32 = new BinaryTreeNode(3);
		BinaryTreeNode pNode41 = new BinaryTreeNode(4);
		BinaryTreeNode pNode42 = new BinaryTreeNode(4);
		BinaryTreeNode pNode21 = new BinaryTreeNode(2);
		BinaryTreeNode pNode22 = new BinaryTreeNode(2);
		BinaryTreeNode pNode11 = new BinaryTreeNode(1);
		BinaryTreeNode pNode12 = new BinaryTreeNode(1);

		BinaryTreeNode.connectTreeNodes(pNode5, pNode31, pNode32);
		BinaryTreeNode.connectTreeNodes(pNode31, pNode41, null);
		BinaryTreeNode.connectTreeNodes(pNode32, null, pNode42);
		BinaryTreeNode.connectTreeNodes(pNode41, pNode21, null);
		BinaryTreeNode.connectTreeNodes(pNode42, null, pNode22);
		BinaryTreeNode.connectTreeNodes(pNode21, pNode11, null);
		BinaryTreeNode.connectTreeNodes(pNode22, null, pNode12);

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

		BinaryTreeNode pNode5 = new BinaryTreeNode(5);
		BinaryTreeNode pNode31 = new BinaryTreeNode(3);
		BinaryTreeNode pNode32 = new BinaryTreeNode(3);
		BinaryTreeNode pNode41 = new BinaryTreeNode(4);
		BinaryTreeNode pNode42 = new BinaryTreeNode(4);
		BinaryTreeNode pNode6 = new BinaryTreeNode(6);
		BinaryTreeNode pNode22 = new BinaryTreeNode(2);
		BinaryTreeNode pNode11 = new BinaryTreeNode(1);
		BinaryTreeNode pNode12 = new BinaryTreeNode(1);

		BinaryTreeNode.connectTreeNodes(pNode5, pNode31, pNode32);
		BinaryTreeNode.connectTreeNodes(pNode31, pNode41, null);
		BinaryTreeNode.connectTreeNodes(pNode32, null, pNode42);
		BinaryTreeNode.connectTreeNodes(pNode41, pNode6, null);
		BinaryTreeNode.connectTreeNodes(pNode42, null, pNode22);
		BinaryTreeNode.connectTreeNodes(pNode6, pNode11, null);
		BinaryTreeNode.connectTreeNodes(pNode22, null, pNode12);

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

		BinaryTreeNode pNode5 = new BinaryTreeNode(5);
		BinaryTreeNode pNode31 = new BinaryTreeNode(3);
		BinaryTreeNode pNode32 = new BinaryTreeNode(3);
		BinaryTreeNode pNode41 = new BinaryTreeNode(4);
		BinaryTreeNode pNode42 = new BinaryTreeNode(4);
		BinaryTreeNode pNode21 = new BinaryTreeNode(2);
		BinaryTreeNode pNode22 = new BinaryTreeNode(2);
		BinaryTreeNode pNode11 = new BinaryTreeNode(1);

		BinaryTreeNode.connectTreeNodes(pNode5, pNode31, pNode32);
		BinaryTreeNode.connectTreeNodes(pNode31, pNode41, null);
		BinaryTreeNode.connectTreeNodes(pNode32, null, pNode42);
		BinaryTreeNode.connectTreeNodes(pNode41, pNode21, null);
		BinaryTreeNode.connectTreeNodes(pNode42, null, pNode22);
		BinaryTreeNode.connectTreeNodes(pNode21, pNode11, null);

		test("test6", pNode5, false);
	}

	// 只有一个结点
	private void test7() {
		BinaryTreeNode pNode = new BinaryTreeNode(1);
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

		BinaryTreeNode pNode1 = new BinaryTreeNode(5);
		BinaryTreeNode pNode21 = new BinaryTreeNode(5);
		BinaryTreeNode pNode22 = new BinaryTreeNode(5);
		BinaryTreeNode pNode31 = new BinaryTreeNode(5);
		BinaryTreeNode pNode32 = new BinaryTreeNode(5);
		BinaryTreeNode pNode41 = new BinaryTreeNode(5);
		BinaryTreeNode pNode42 = new BinaryTreeNode(5);

		BinaryTreeNode.connectTreeNodes(pNode1, pNode21, pNode22);
		BinaryTreeNode.connectTreeNodes(pNode21, pNode31, null);
		BinaryTreeNode.connectTreeNodes(pNode22, null, pNode32);
		BinaryTreeNode.connectTreeNodes(pNode31, pNode41, null);
		BinaryTreeNode.connectTreeNodes(pNode32, null, pNode42);
		BinaryTreeNode.connectTreeNodes(pNode41, null, null);
		BinaryTreeNode.connectTreeNodes(pNode42, null, null);

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
