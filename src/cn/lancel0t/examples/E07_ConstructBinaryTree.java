/**
 * 
 * 【剑指Offer】	面试题7 ：重建二叉树
 * 【题目描述】	输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。
 * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * 例如：前序遍历序列｛ 1, 2, 4, 7, 3, 5, 6, 8｝和中序遍历序列｛4, 7, 2, 1, 5, 3, 8，6}，
 * 重建出下图所示的二叉树并输出它的头结点。
 * 
 * @author lancel0t
 * @date 2018年5月22日
 */
package cn.lancel0t.examples;

import cn.lancel0t.utilities.TreeNode;

public class E07_ConstructBinaryTree {

	public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
		// 输入的合法性判断，两个数组都不能为空，长度相等且有数据
		if (pre == null || in == null || pre.length != in.length || pre.length < 1) {
			return null;
		}

		return construct(pre, in, 0, pre.length - 1, 0, in.length - 1);
	}

	// 递归法，根据前序和中序，分别生成左右子树
	private TreeNode construct(int[] preOrder, int[] inOrder, int preStart, int preEnd, int inStart, int inEnd) {
		if (preStart > preEnd) {
			return null;
		}

		// 前序遍历第1个节点为根节点
		int rootVal = preOrder[preStart];

		// 获取根节点在中序遍历的索引位置
		int index = inStart;
		while (index <= inEnd && inOrder[index] != rootVal)
			index++;

		if (index > inEnd) {
			throw new RuntimeException("无法重建二叉树，请检查输入的参数是否合法！");
		}

		TreeNode root = new TreeNode(rootVal);

		// 递归构建当前根结点的左子树，左子树的元素个数：index-inStart+1个
		// 左子树对应的前序遍历的位置在[preStart + 1, preStart + index - inStart]
		// 左子树对应的中序遍历的位置在[inStart, index-1]
		root.left = construct(preOrder, inOrder, preStart + 1, preStart + index - inStart, inStart, index - 1);
		// 递归构建当前根结点的右子树，右子树的元素个数：inEnd-index个
		// 右子树对应的前序遍历的位置在[preStart + index - inStart + 1, preEnd]
		// 右子树对应的中序遍历的位置在[index+1, inEnd]
		root.right = construct(preOrder, inOrder, preStart + index - inStart + 1, preEnd, index + 1, inEnd);

		return root;
	}

	// ====================测试代码====================
	private void test(String testName, int[] pre, int[] in) {
		try {
			System.out.printf("=====%s=====\n", testName);
			System.out.println("重建二叉树：");
			TreeNode root = reConstructBinaryTree(pre, in);
			TreeNode.printTree(root);
			System.out.println("");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/*
	 * 普通二叉树
	 *        1
	 *     /     \
	 *    2       3  
	 *   /       / \
	 *  4       5   6
	 *   \         /
	 *    7       8
	 */
	private void test1() {
		int[] preorder = { 1, 2, 4, 7, 3, 5, 6, 8 };
		int[] inorder = { 4, 7, 2, 1, 5, 3, 8, 6 };

		test("Test1", preorder, inorder);
	}

	/*
	 * 所有结点都没有右子结点
	 *          1
	 *         / 
	 *        2   
	 *       / 
	 *      3 
	 *     /
	 *    4
	 *   /
	 *  5
	 */
	private void test2() {
		int[] preorder = { 1, 2, 3, 4, 5 };
		int[] inorder = { 5, 4, 3, 2, 1 };

		test("Test2", preorder, inorder);
	}

	/*
	 * 所有结点都没有左子结点
	 *   1
	 *    \ 
	 *     2   
	 *      \ 
	 *       3 
	 *        \
	 *         4
	 *          \
	 *           5
	 */
	private void test3() {
		int[] preorder = { 1, 2, 3, 4, 5 };
		int[] inorder = { 1, 2, 3, 4, 5 };

		test("Test3", preorder, inorder);
	}

	/*
	 * 树中只有一个结点
	 */
	private void test4() {
		int[] preorder = { 1 };
		int[] inorder = { 1 };

		test("Test4", preorder, inorder);
	}

	/*
	 * 完全二叉树
	 *        1
	 *     /     \
	 *    2       3  
	 *   / \     / \
	 *  4   5   6   7
	 */
	private void test5() {
		int[] preorder = { 1, 2, 4, 5, 3, 6, 7 };
		int[] inorder = { 4, 2, 5, 1, 6, 3, 7 };

		test("Test5", preorder, inorder);
	}

	/*
	 * 输入空指针
	 */
	private void test6() {
		test("Test6", null, null);
	}

	/*
	 * 输入的两个序列不匹配
	 */
	private void test7() {
		int[] preorder = { 1, 2, 4, 5, 3, 6, 7 };
		int[] inorder = { 4, 2, 8, 1, 6, 3, 7 };

		test("Test7", preorder, inorder);
	}

	public static void main(String[] args) {

		E07_ConstructBinaryTree exam = new E07_ConstructBinaryTree();

		exam.test1();
		exam.test2();
		exam.test3();
		exam.test4();
		exam.test5();
		exam.test6();
		exam.test7();
	}
}
