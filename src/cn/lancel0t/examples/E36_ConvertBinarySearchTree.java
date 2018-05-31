/**
 * 
 * 【剑指Offer】	面试题36 ：二叉搜索树与双向链表
 * 【题目描述】	输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。
 * 要求不能创建任何新的结点，只能调整树中结点指针的指向。
 * 
 * @author lancel0t
 * @date 2018年5月22日
 */
package cn.lancel0t.examples;

import cn.lancel0t.utilities.TreeNode;

public class E36_ConvertBinarySearchTree {

	/*
	 * 二叉搜索树与双向链表
	 * 思路：要将如下数转换为升序的双链表，首先考虑根节点10，在双链表中的上一个元素为8，即左子树的最大一个元素，
	 * 同理，下一个元素为12，即右子树的最小元素。
	 * 	      10
	 *     /      \
	 *    6        14         =>      4<->6<->8<->10<->12<->14<->16
	 *   /\        /\
	 *  4  8     12  16
	 */
	public TreeNode Convert(TreeNode pRootOfTree) {

		TreeNode lastNode = Convert(pRootOfTree, null);

		// 往前找到链表头位置
		TreeNode headNode = lastNode;
		while (headNode != null && headNode.left != null) {
			headNode = headNode.left;
		}
		return headNode;
	}

	// 递归处理树节点的双向链接，并返回当前最后的节点
	public TreeNode Convert(TreeNode node, TreeNode lastNode) {

		if (node == null)
			return lastNode;

		TreeNode currNode = node;

		// 递归处理左子树
		if (currNode.left != null)
			lastNode = Convert(currNode.left, lastNode);

		// 将当前节点与左子树最大值双向链接
		currNode.left = lastNode;
		if (lastNode != null)
			lastNode.right = currNode;

		// 最大值变为当前节点
		lastNode = currNode;

		// 递归处理右子树
		if (currNode.right != null)
			lastNode = Convert(currNode.right, lastNode);

		return lastNode;
	}

	// ====================测试代码====================
	private void test(String testName, TreeNode pRootOfTree) {
		try {
			System.out.printf("=====%s=====\n", testName);
			TreeNode node = Convert(pRootOfTree);

			System.out.printf("链表从左到右打印：");
			while (node != null) {
				System.out.print(node.val + " ");
				if (node.right == null)
					break;

				node = node.right;
			}
			System.out.println();
			System.out.printf("链表从右到左打印：");
			while (node != null) {
				System.out.print(node.val + " ");
				if (node.left == null)
					break;

				node = node.left;
			}
			System.out.println();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println();
	}

	/*
	 *       10
	 *    /      \
	 *   6        14
	 *  /\        /\
	 * 4  8     12  16
	 */
	private void test1() {
		TreeNode pNode10 = new TreeNode(10);
		TreeNode pNode6 = new TreeNode(6);
		TreeNode pNode14 = new TreeNode(14);
		TreeNode pNode4 = new TreeNode(4);
		TreeNode pNode8 = new TreeNode(8);
		TreeNode pNode12 = new TreeNode(12);
		TreeNode pNode16 = new TreeNode(16);

		TreeNode.connectTreeNodes(pNode10, pNode6, pNode14);
		TreeNode.connectTreeNodes(pNode6, pNode4, pNode8);
		TreeNode.connectTreeNodes(pNode14, pNode12, pNode16);

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
		TreeNode pNode5 = new TreeNode(5);
		TreeNode pNode4 = new TreeNode(4);
		TreeNode pNode3 = new TreeNode(3);
		TreeNode pNode2 = new TreeNode(2);
		TreeNode pNode1 = new TreeNode(1);

		TreeNode.connectTreeNodes(pNode5, pNode4, null);
		TreeNode.connectTreeNodes(pNode4, pNode3, null);
		TreeNode.connectTreeNodes(pNode3, pNode2, null);
		TreeNode.connectTreeNodes(pNode2, pNode1, null);

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
		TreeNode pNode1 = new TreeNode(1);
		TreeNode pNode2 = new TreeNode(2);
		TreeNode pNode3 = new TreeNode(3);
		TreeNode pNode4 = new TreeNode(4);
		TreeNode pNode5 = new TreeNode(5);

		TreeNode.connectTreeNodes(pNode1, pNode2, null);
		TreeNode.connectTreeNodes(pNode2, pNode3, null);
		TreeNode.connectTreeNodes(pNode3, pNode4, null);
		TreeNode.connectTreeNodes(pNode4, pNode5, null);

		test("test3", pNode1);
	}

	// 树中只有1个结点
	private void test4() {
		TreeNode pNode1 = new TreeNode(1);
		test("test4", pNode1);
	}

	// 树中没有结点
	private void test5() {
		test("test5", null);
	}

	public static void main(String[] args) {

		E36_ConvertBinarySearchTree exam = new E36_ConvertBinarySearchTree();

		exam.test1();
		exam.test2();
		exam.test3();
		exam.test4();
		exam.test5();
	}
}
