/**
 * 
 * 【剑指Offer】	面试题26 ：树的子结构
 * 【题目描述】	输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）
 * 
 * @author lancel0t
 * @date 2018年5月22日
 */
package cn.lancel0t.examples;

import cn.lancel0t.utilities.BinaryTreeNode;

public class E26_SubstructureInTree {

	/*
	 * 树的子结构
	 */
	public boolean HasSubtree(BinaryTreeNode root1, BinaryTreeNode root2) {

		// A或B树为空情况
		if (root1 == null || root2 == null) {
			return false;
		}

		// A,B树都不为空情况
		if (root1.val == root2.val && match(root1, root2))
			// 如果A,B节点值相等，且节点匹配
			return true;

		// 否则，在左右子树去匹配
		return HasSubtree(root1.left, root2) || HasSubtree(root1.right, root2);
	}

	/*
	 * 检查节点即子节点是否匹配
	 */
	public boolean match(BinaryTreeNode root1, BinaryTreeNode root2) {

		// 树B为空，返回true
		if (root2 == null) {
			return true;
		}
		// 树B不为空，树A为空，返回false
		if (root1 == null) {
			return false;
		}

		// A,B树都不为空情况
		if (root1.val == root2.val) {
			return match(root1.left, root2.left) && match(root1.right, root2.right);
		}

		// 结点值不相等返回false
		return false;
	}

	// ====================测试代码====================
	private void test(String testName, BinaryTreeNode node1, BinaryTreeNode node2, boolean expect) {
		try {
			System.out.printf("=====%s=====\n", testName);
			System.out.printf("是否是树的子结构：Result:%b \t Expect:%b\n", HasSubtree(node1, node2), expect);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println();
	}

	/*
	 * 树中结点含有分叉，树B是树A的子结构
	 *       8             8
	 *     /   \          / \
	 *    8     7        9   2
	 *  /   \
	 * 9     2
	 *      / \
	 *     4   7
	 */
	private void test1() {
		BinaryTreeNode pNodeA1 = new BinaryTreeNode(8);
		BinaryTreeNode pNodeA2 = new BinaryTreeNode(8);
		BinaryTreeNode pNodeA3 = new BinaryTreeNode(7);
		BinaryTreeNode pNodeA4 = new BinaryTreeNode(9);
		BinaryTreeNode pNodeA5 = new BinaryTreeNode(2);
		BinaryTreeNode pNodeA6 = new BinaryTreeNode(4);
		BinaryTreeNode pNodeA7 = new BinaryTreeNode(7);

		BinaryTreeNode.connectTreeNodes(pNodeA1, pNodeA2, pNodeA3);
		BinaryTreeNode.connectTreeNodes(pNodeA2, pNodeA4, pNodeA5);
		BinaryTreeNode.connectTreeNodes(pNodeA5, pNodeA6, pNodeA7);

		BinaryTreeNode pNodeB1 = new BinaryTreeNode(8);
		BinaryTreeNode pNodeB2 = new BinaryTreeNode(9);
		BinaryTreeNode pNodeB3 = new BinaryTreeNode(2);

		BinaryTreeNode.connectTreeNodes(pNodeB1, pNodeB2, pNodeB3);

		test("test1", pNodeA1, pNodeB1, true);
	}

	/*
	 * 树中结点含有分叉，树B不是树A的子结构
	 *       8             8
	 *     /   \          / \
	 *    8     7        9   2
	 *  /   \
	 * 9     3
	 *      / \
	 *     4   7
	 */
	private void test2() {
		BinaryTreeNode pNodeA1 = new BinaryTreeNode(8);
		BinaryTreeNode pNodeA2 = new BinaryTreeNode(8);
		BinaryTreeNode pNodeA3 = new BinaryTreeNode(7);
		BinaryTreeNode pNodeA4 = new BinaryTreeNode(9);
		BinaryTreeNode pNodeA5 = new BinaryTreeNode(3);
		BinaryTreeNode pNodeA6 = new BinaryTreeNode(4);
		BinaryTreeNode pNodeA7 = new BinaryTreeNode(7);

		BinaryTreeNode.connectTreeNodes(pNodeA1, pNodeA2, pNodeA3);
		BinaryTreeNode.connectTreeNodes(pNodeA2, pNodeA4, pNodeA5);
		BinaryTreeNode.connectTreeNodes(pNodeA5, pNodeA6, pNodeA7);

		BinaryTreeNode pNodeB1 = new BinaryTreeNode(8);
		BinaryTreeNode pNodeB2 = new BinaryTreeNode(9);
		BinaryTreeNode pNodeB3 = new BinaryTreeNode(2);

		BinaryTreeNode.connectTreeNodes(pNodeB1, pNodeB2, pNodeB3);

		test("test2", pNodeA1, pNodeB1, false);
	}

	/*
	 * 树中结点只有左子结点，树B是树A的子结构
	 *            8          8
	 *          /           / 
	 *         8           9   
	 *       /            /
	 *      9            2
	 *     /      
	 *    2        
	 *   /
	 *  5
	 */
	private void test3() {
		BinaryTreeNode pNodeA1 = new BinaryTreeNode(8);
		BinaryTreeNode pNodeA2 = new BinaryTreeNode(8);
		BinaryTreeNode pNodeA3 = new BinaryTreeNode(9);
		BinaryTreeNode pNodeA4 = new BinaryTreeNode(2);
		BinaryTreeNode pNodeA5 = new BinaryTreeNode(5);

		BinaryTreeNode.connectTreeNodes(pNodeA1, pNodeA2, null);
		BinaryTreeNode.connectTreeNodes(pNodeA2, pNodeA3, null);
		BinaryTreeNode.connectTreeNodes(pNodeA3, pNodeA4, null);
		BinaryTreeNode.connectTreeNodes(pNodeA4, pNodeA5, null);

		BinaryTreeNode pNodeB1 = new BinaryTreeNode(8);
		BinaryTreeNode pNodeB2 = new BinaryTreeNode(9);
		BinaryTreeNode pNodeB3 = new BinaryTreeNode(2);

		BinaryTreeNode.connectTreeNodes(pNodeB1, pNodeB2, null);
		BinaryTreeNode.connectTreeNodes(pNodeB2, pNodeB3, null);

		test("test3", pNodeA1, pNodeB1, true);
	}

	/*
	 * 树中结点只有左子结点，树B不是是树A的子结构
	 *            8          8
	 *          /           / 
	 *         8           9   
	 *       /            /
	 *      9            3
	 *     /      
	 *    2        
	 *   /
	 *  5
	 */
	private void test4() {
		BinaryTreeNode pNodeA1 = new BinaryTreeNode(8);
		BinaryTreeNode pNodeA2 = new BinaryTreeNode(8);
		BinaryTreeNode pNodeA3 = new BinaryTreeNode(9);
		BinaryTreeNode pNodeA4 = new BinaryTreeNode(2);
		BinaryTreeNode pNodeA5 = new BinaryTreeNode(5);

		BinaryTreeNode.connectTreeNodes(pNodeA1, pNodeA2, null);
		BinaryTreeNode.connectTreeNodes(pNodeA2, pNodeA3, null);
		BinaryTreeNode.connectTreeNodes(pNodeA3, pNodeA4, null);
		BinaryTreeNode.connectTreeNodes(pNodeA4, pNodeA5, null);

		BinaryTreeNode pNodeB1 = new BinaryTreeNode(8);
		BinaryTreeNode pNodeB2 = new BinaryTreeNode(9);
		BinaryTreeNode pNodeB3 = new BinaryTreeNode(3);

		BinaryTreeNode.connectTreeNodes(pNodeB1, pNodeB2, null);
		BinaryTreeNode.connectTreeNodes(pNodeB2, pNodeB3, null);

		test("test4", pNodeA1, pNodeB1, false);
	}

	/*
	 * 树中结点只有右子结点，树B是树A的子结构
	 *  8             8
	 *   \             \ 
	 *    8             9   
	 *     \             \
	 *      9             2
	 *       \     
	 *        2    
	 *         \
	 *          5
	 */
	private void test5() {
		BinaryTreeNode pNodeA1 = new BinaryTreeNode(8);
		BinaryTreeNode pNodeA2 = new BinaryTreeNode(8);
		BinaryTreeNode pNodeA3 = new BinaryTreeNode(9);
		BinaryTreeNode pNodeA4 = new BinaryTreeNode(2);
		BinaryTreeNode pNodeA5 = new BinaryTreeNode(5);

		BinaryTreeNode.connectTreeNodes(pNodeA1, null, pNodeA2);
		BinaryTreeNode.connectTreeNodes(pNodeA2, null, pNodeA3);
		BinaryTreeNode.connectTreeNodes(pNodeA3, null, pNodeA4);
		BinaryTreeNode.connectTreeNodes(pNodeA4, null, pNodeA5);

		BinaryTreeNode pNodeB1 = new BinaryTreeNode(8);
		BinaryTreeNode pNodeB2 = new BinaryTreeNode(9);
		BinaryTreeNode pNodeB3 = new BinaryTreeNode(2);

		BinaryTreeNode.connectTreeNodes(pNodeB1, null, pNodeB2);
		BinaryTreeNode.connectTreeNodes(pNodeB2, null, pNodeB3);

		test("test5", pNodeA1, pNodeB1, true);
	}

	/*
	 * 树中结点只有右子结点，树B不是树A的子结构
	 *  8             8
	 *   \             \ 
	 *    8             9   
	 *     \    	  /   \
	 *      9        3     2
	 *       \     
	 *        2    
	 *         \
	 *          5
	 */
	private void test6() {
		BinaryTreeNode pNodeA1 = new BinaryTreeNode(8);
		BinaryTreeNode pNodeA2 = new BinaryTreeNode(8);
		BinaryTreeNode pNodeA3 = new BinaryTreeNode(9);
		BinaryTreeNode pNodeA4 = new BinaryTreeNode(2);
		BinaryTreeNode pNodeA5 = new BinaryTreeNode(5);

		BinaryTreeNode.connectTreeNodes(pNodeA1, null, pNodeA2);
		BinaryTreeNode.connectTreeNodes(pNodeA2, null, pNodeA3);
		BinaryTreeNode.connectTreeNodes(pNodeA3, null, pNodeA4);
		BinaryTreeNode.connectTreeNodes(pNodeA4, null, pNodeA5);

		BinaryTreeNode pNodeB1 = new BinaryTreeNode(8);
		BinaryTreeNode pNodeB2 = new BinaryTreeNode(9);
		BinaryTreeNode pNodeB3 = new BinaryTreeNode(3);
		BinaryTreeNode pNodeB4 = new BinaryTreeNode(2);

		BinaryTreeNode.connectTreeNodes(pNodeB1, null, pNodeB2);
		BinaryTreeNode.connectTreeNodes(pNodeB2, pNodeB3, pNodeB4);

		test("test6", pNodeA1, pNodeB1, false);
	}

	/*
	 * 树A为空树
	 */
	private void test7() {

		BinaryTreeNode pNodeB1 = new BinaryTreeNode(8);
		BinaryTreeNode pNodeB2 = new BinaryTreeNode(9);
		BinaryTreeNode pNodeB3 = new BinaryTreeNode(3);
		BinaryTreeNode pNodeB4 = new BinaryTreeNode(2);

		BinaryTreeNode.connectTreeNodes(pNodeB1, null, pNodeB2);
		BinaryTreeNode.connectTreeNodes(pNodeB2, pNodeB3, pNodeB4);

		test("test7", null, pNodeB1, false);
	}

	/*
	 * 树B为空树
	 */
	private void test8() {

		BinaryTreeNode pNodeA1 = new BinaryTreeNode(8);
		BinaryTreeNode pNodeA2 = new BinaryTreeNode(9);
		BinaryTreeNode pNodeA3 = new BinaryTreeNode(3);
		BinaryTreeNode pNodeA4 = new BinaryTreeNode(2);

		BinaryTreeNode.connectTreeNodes(pNodeA1, null, pNodeA2);
		BinaryTreeNode.connectTreeNodes(pNodeA2, pNodeA3, pNodeA4);

		test("test8", pNodeA1, null, false);
	}

	/*
	 * 树A,B都为空树
	 */
	private void test9() {
		test("test9", null, null, false);
	}

	public static void main(String[] args) {

		E26_SubstructureInTree exam = new E26_SubstructureInTree();

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
