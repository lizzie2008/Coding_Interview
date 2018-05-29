/**
 * 
 * 【剑指Offer】面试题26 ：树的子结构
 * 【  题目描述 】输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）
 * 
 * @author lancel0t
 * @date 2018年5月22日
 */
package cn.lancel0t.examples;

import cn.lancel0t.utilities.TreeNode;

public class E26_SubstructureInTree {

	/*
	 * 树的子结构
	 */
	public boolean HasSubtree(TreeNode root1, TreeNode root2) {

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
	public boolean match(TreeNode root1, TreeNode root2) {

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
		TreeNode pNodeA1 = new TreeNode(8);
		TreeNode pNodeA2 = new TreeNode(8);
		TreeNode pNodeA3 = new TreeNode(7);
		TreeNode pNodeA4 = new TreeNode(9);
		TreeNode pNodeA5 = new TreeNode(2);
		TreeNode pNodeA6 = new TreeNode(4);
		TreeNode pNodeA7 = new TreeNode(7);

		TreeNode.connectTreeNodes(pNodeA1, pNodeA2, pNodeA3);
		TreeNode.connectTreeNodes(pNodeA2, pNodeA4, pNodeA5);
		TreeNode.connectTreeNodes(pNodeA5, pNodeA6, pNodeA7);

		TreeNode pNodeB1 = new TreeNode(8);
		TreeNode pNodeB2 = new TreeNode(9);
		TreeNode pNodeB3 = new TreeNode(2);

		TreeNode.connectTreeNodes(pNodeB1, pNodeB2, pNodeB3);

		System.out.printf("=====Test1===== \nResult:%b \nExpect:%b\n", HasSubtree(pNodeA1, pNodeB1), true);
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
		TreeNode pNodeA1 = new TreeNode(8);
		TreeNode pNodeA2 = new TreeNode(8);
		TreeNode pNodeA3 = new TreeNode(7);
		TreeNode pNodeA4 = new TreeNode(9);
		TreeNode pNodeA5 = new TreeNode(3);
		TreeNode pNodeA6 = new TreeNode(4);
		TreeNode pNodeA7 = new TreeNode(7);

		TreeNode.connectTreeNodes(pNodeA1, pNodeA2, pNodeA3);
		TreeNode.connectTreeNodes(pNodeA2, pNodeA4, pNodeA5);
		TreeNode.connectTreeNodes(pNodeA5, pNodeA6, pNodeA7);

		TreeNode pNodeB1 = new TreeNode(8);
		TreeNode pNodeB2 = new TreeNode(9);
		TreeNode pNodeB3 = new TreeNode(2);

		TreeNode.connectTreeNodes(pNodeB1, pNodeB2, pNodeB3);

		System.out.printf("=====Test2===== \nResult:%b \nExpect:%b\n", HasSubtree(pNodeA1, pNodeB1), false);
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
		TreeNode pNodeA1 = new TreeNode(8);
		TreeNode pNodeA2 = new TreeNode(8);
		TreeNode pNodeA3 = new TreeNode(9);
		TreeNode pNodeA4 = new TreeNode(2);
		TreeNode pNodeA5 = new TreeNode(5);

		TreeNode.connectTreeNodes(pNodeA1, pNodeA2, null);
		TreeNode.connectTreeNodes(pNodeA2, pNodeA3, null);
		TreeNode.connectTreeNodes(pNodeA3, pNodeA4, null);
		TreeNode.connectTreeNodes(pNodeA4, pNodeA5, null);

		TreeNode pNodeB1 = new TreeNode(8);
		TreeNode pNodeB2 = new TreeNode(9);
		TreeNode pNodeB3 = new TreeNode(2);

		TreeNode.connectTreeNodes(pNodeB1, pNodeB2, null);
		TreeNode.connectTreeNodes(pNodeB2, pNodeB3, null);

		System.out.printf("=====Test3===== \nResult:%b \nExpect:%b\n", HasSubtree(pNodeA1, pNodeB1), true);
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
		TreeNode pNodeA1 = new TreeNode(8);
		TreeNode pNodeA2 = new TreeNode(8);
		TreeNode pNodeA3 = new TreeNode(9);
		TreeNode pNodeA4 = new TreeNode(2);
		TreeNode pNodeA5 = new TreeNode(5);

		TreeNode.connectTreeNodes(pNodeA1, pNodeA2, null);
		TreeNode.connectTreeNodes(pNodeA2, pNodeA3, null);
		TreeNode.connectTreeNodes(pNodeA3, pNodeA4, null);
		TreeNode.connectTreeNodes(pNodeA4, pNodeA5, null);

		TreeNode pNodeB1 = new TreeNode(8);
		TreeNode pNodeB2 = new TreeNode(9);
		TreeNode pNodeB3 = new TreeNode(3);

		TreeNode.connectTreeNodes(pNodeB1, pNodeB2, null);
		TreeNode.connectTreeNodes(pNodeB2, pNodeB3, null);

		System.out.printf("=====Test4===== \nResult:%b \nExpect:%b\n", HasSubtree(pNodeA1, pNodeB1), false);
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
		TreeNode pNodeA1 = new TreeNode(8);
		TreeNode pNodeA2 = new TreeNode(8);
		TreeNode pNodeA3 = new TreeNode(9);
		TreeNode pNodeA4 = new TreeNode(2);
		TreeNode pNodeA5 = new TreeNode(5);

		TreeNode.connectTreeNodes(pNodeA1, null, pNodeA2);
		TreeNode.connectTreeNodes(pNodeA2, null, pNodeA3);
		TreeNode.connectTreeNodes(pNodeA3, null, pNodeA4);
		TreeNode.connectTreeNodes(pNodeA4, null, pNodeA5);

		TreeNode pNodeB1 = new TreeNode(8);
		TreeNode pNodeB2 = new TreeNode(9);
		TreeNode pNodeB3 = new TreeNode(2);

		TreeNode.connectTreeNodes(pNodeB1, null, pNodeB2);
		TreeNode.connectTreeNodes(pNodeB2, null, pNodeB3);

		System.out.printf("=====Test5===== \nResult:%b \nExpect:%b\n", HasSubtree(pNodeA1, pNodeB1), true);
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
		TreeNode pNodeA1 = new TreeNode(8);
		TreeNode pNodeA2 = new TreeNode(8);
		TreeNode pNodeA3 = new TreeNode(9);
		TreeNode pNodeA4 = new TreeNode(2);
		TreeNode pNodeA5 = new TreeNode(5);

		TreeNode.connectTreeNodes(pNodeA1, null, pNodeA2);
		TreeNode.connectTreeNodes(pNodeA2, null, pNodeA3);
		TreeNode.connectTreeNodes(pNodeA3, null, pNodeA4);
		TreeNode.connectTreeNodes(pNodeA4, null, pNodeA5);

		TreeNode pNodeB1 = new TreeNode(8);
		TreeNode pNodeB2 = new TreeNode(9);
		TreeNode pNodeB3 = new TreeNode(3);
		TreeNode pNodeB4 = new TreeNode(2);

		TreeNode.connectTreeNodes(pNodeB1, null, pNodeB2);
		TreeNode.connectTreeNodes(pNodeB2, pNodeB3, pNodeB4);

		System.out.printf("=====Test6===== \nResult:%b \nExpect:%b\n", HasSubtree(pNodeA1, pNodeB1), false);
	}

	/*
	 * 树A为空树
	 */
	private void test7() {

		TreeNode pNodeB1 = new TreeNode(8);
		TreeNode pNodeB2 = new TreeNode(9);
		TreeNode pNodeB3 = new TreeNode(3);
		TreeNode pNodeB4 = new TreeNode(2);

		TreeNode.connectTreeNodes(pNodeB1, null, pNodeB2);
		TreeNode.connectTreeNodes(pNodeB2, pNodeB3, pNodeB4);

		System.out.printf("=====Test7===== \nResult:%b \nExpect:%b\n", HasSubtree(null, pNodeB1), false);
	}

	/*
	 * 树B为空树
	 */
	private void test8() {

		TreeNode pNodeA1 = new TreeNode(8);
		TreeNode pNodeA2 = new TreeNode(9);
		TreeNode pNodeA3 = new TreeNode(3);
		TreeNode pNodeA4 = new TreeNode(2);

		TreeNode.connectTreeNodes(pNodeA1, null, pNodeA2);
		TreeNode.connectTreeNodes(pNodeA2, pNodeA3, pNodeA4);

		System.out.printf("=====Test8===== \nResult:%b \nExpect:%b\n", HasSubtree(pNodeA1, null), false);
	}

	/*
	 * 树A,B都为空树
	 */
	private void test9() {

		System.out.printf("=====Test9===== \nResult:%b \nExpect:%b\n", HasSubtree(null, null), false);
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
